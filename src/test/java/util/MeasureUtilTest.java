package util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.generate.NamedGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MeasureUtilTest {
	
	/////////////////////////
	// Preparing Graph Data
	
	static Graph <String, DefaultEdge> nullgraph;
	// Graphs with cycles
	static Graph <Integer, DefaultEdge> bullgraph;
	static Graph <Integer, DefaultEdge> petersengraph;
	static Graph <Integer, DefaultEdge> butterflygraph;
	static Graph<String, DefaultEdge> asso;
	static String assof = 	"a,b\n" + // "./src/main/java/graphs/assortativity.gml"
							"b,c\n" +
							"c,f\n" +
							"f,d\n" +
							"f,e\n" +
							"f,g\n" +
							"d,e\n" +
							"d,g\n" +
							"e,g\n" +
							"g,i\n" +
							"i,h\n";
	static Graph<String, DefaultEdge> disso;
	static String dissof = 	"a,d\n" + // "./src/main/java/graphs/dissortativity.gml"
							"b,d\n" +
							"c,d\n" +
							"e,d\n" +
							"f,d\n" +
							"d,g\n" +
							"g,h\n" +
							"g,l\n" +
							"l,m\n" +
							"l,n\n" +
							"l,j\n" +
							"l,i\n" +
							"l,h\n";
	// Trees
	static Graph <Integer, DefaultEdge> clawgraph; 
    static Graph <String, DefaultEdge> tree;
    static String treef = 	"a,b\n" + // "./src/main/java/graphs/treec.gml"
    						"b,c\n" +
    						"c,d\n" +
    						"d,e\n" +
    						"e,f\n" +
    						"e,g\n" +
    						"e,h\n" +
    						"h,i\n" +
    						"i,j\n";
    static Graph <String, DefaultEdge> cent;
    static String centf = 	"1,2\n" +	//"./src/main/java/graphs/centroids.gml"
    						"1,5\n" +
    						"1,6\n" +
    						"2,3\n" +
    						"2,4\n" +
    						"6,7\n" +
    						"6,8\n" +
    						"8,9\n" +
    						"8,10\n";
    
    static Graph<String,DefaultEdge> readGraph 
    	(Graph <String, DefaultEdge> g, String input) {
    	StringReader r = new StringReader(input);
    	try (BufferedReader br = new BufferedReader(r)) {
    		String line = "";

    		while ((line = br.readLine()) != null) {
    			String [] attributes = line.split(",");
    			g.addVertex(attributes[0]);
    			g.addVertex(attributes[1]);
    			g.addEdge(attributes[0], attributes[1]);
    		}
    	} catch (IOException e) {
			// TODO Auto-generated catch block
    		System.out.println("Graph was not created.");
		}
    	return g;
    }
	
	@BeforeAll
	static void beforeAll () {
		nullgraph = new SimpleGraph <> (DefaultEdge.class);
		bullgraph = NamedGraphGenerator.bullGraph();
		petersengraph = NamedGraphGenerator.petersenGraph();
		clawgraph = NamedGraphGenerator.clawGraph(); 
		butterflygraph = NamedGraphGenerator.butterflyGraph(); 
		asso = new SimpleGraph <> (DefaultEdge.class);
		asso = readGraph(asso, assof);
		disso = new SimpleGraph <> (DefaultEdge.class);
		disso = readGraph(disso, dissof);
		tree = new SimpleGraph <> (DefaultEdge.class);
		tree = readGraph(tree,treef);		
		cent = new SimpleGraph <> (DefaultEdge.class);
		cent = readGraph(cent, centf);
	}

	/////////////////////////////
	// Test Cases
	
	// assortativityCoefficient
	static Stream <Arguments> assortativityCoefficientProvider () {
		return Stream.of(
				Arguments.of(nullgraph, 0.000),
				Arguments.of(bullgraph, -0.562),
				Arguments.of(butterflygraph, -0.499),
				Arguments.of(clawgraph, -1.000),
				Arguments.of(asso, 0.358),
				Arguments.of(disso, -0.909),
				Arguments.of(tree, -0.465)
				);
	}
	
	@ParameterizedTest
	@MethodSource("assortativityCoefficientProvider")
	<V,E> void assortativityCoefficientTest(Graph <V,E> g, double expected ) {
		int e = Double.valueOf(expected*1000).intValue();
		int r = Double.valueOf(MeasureUtil.assortativityCoefficient(g)*1000).intValue();
		assertEquals(e,r);
	}
	
	@Test
	<V,E> void assortativityCoefficientTestNull () {
		assertThrows(NullPointerException.class, () -> MeasureUtil.assortativityCoefficient(null));
	}
	
	//getTreeCentroidPoints
	static Stream <Arguments> getTreeCentroidPointsProvider () {
		Set <Integer> vclaw = new HashSet<Integer> ();
		vclaw.add(Integer.valueOf(0));
		Set <String> vtree = new HashSet<String> ();
		vtree.add("e");
		Set <String> vcent = new HashSet<String> ();
		vcent.add("1");
		vcent.add("6");
		return Stream.of(
				Arguments.of(nullgraph, new HashSet<Object>()),
				Arguments.of(petersengraph, new HashSet <Integer>()),
				Arguments.of(clawgraph, vclaw),
				Arguments.of(tree, vtree),
				Arguments.of(cent, vcent)
				);
	}
	
	@ParameterizedTest
	@MethodSource("getTreeCentroidPointsProvider")
	<V,E> void getTreeCentroidPointsTest (Graph <V,E> g, Set <V> expected) {
		Set <V> result = MeasureUtil.getTreeCentroidPoints(g);
		assertEquals(expected, result);
	}
	
	@Test
	<V,E> void getTreeCentroidPointsTestNull () {
		assertThrows(NullPointerException.class, () -> MeasureUtil.getTreeCentroidPoints(null));
	}
	
	//getCutPointWeights
	static Stream <Arguments> getCutPointWeightsProvider () {
		Map <String, Double> mtree = new HashMap <> ();
		mtree.put("b",8.0);
		mtree.put("d",6.0);
		mtree.put("e",4.0);
		mtree.put("h",7.0);
		mtree.put("i",8.0);
		mtree.put("c",7.0);
		Map <String, Double> mcent = new HashMap <> ();
		mcent.put("2",7.0);
		mcent.put("6",5.0);
		mcent.put("8",7.0);
		mcent.put("1",5.0);
		return Stream.of(
				Arguments.of(nullgraph,new HashMap <> ()),
				Arguments.of(tree, mtree),
				Arguments.of(cent, mcent),
				Arguments.of(petersengraph, new HashMap <>())				
				);
	}
	
	@ParameterizedTest
	@MethodSource("getCutPointWeightsProvider")
	<V,E> void getCutPointWeightsTest (Graph <V,E> graph, Map <V, Double> expected) {
		assertEquals(expected, MeasureUtil.getCutPointWeights(graph));
	}
	
	@Test
	<V,E> void getCutPointWeightsTestNull () {
		assertThrows(NullPointerException.class, () -> MeasureUtil.getCutPointWeights(null));
	}
}
