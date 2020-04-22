package util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.generate.NamedGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MeasureUtilTest {
	
	static Graph <String, DefaultEdge> nullgraph;
	// Graphs with cycles
	static Graph <Integer, DefaultEdge> bullgraph;
	static Graph <Integer, DefaultEdge> petersengraph;
	static Graph <Integer, DefaultEdge> butterflygraph;
	static Graph<DefaultVertex, DefaultEdge> asso;
	static Graph<DefaultVertex, DefaultEdge> disso;
	// Trees
	static Graph <Integer, DefaultEdge> clawgraph; 
    static Graph <DefaultVertex, DefaultEdge> tree;
    static Graph <DefaultVertex, DefaultEdge> cent;

	
	@BeforeAll
	static void beforeAll () {
		nullgraph = new SimpleGraph <> (DefaultEdge.class);
		bullgraph = NamedGraphGenerator.bullGraph();
		petersengraph = NamedGraphGenerator.petersenGraph();
		clawgraph = NamedGraphGenerator.clawGraph(); 
		butterflygraph = NamedGraphGenerator.butterflyGraph(); 
		asso = new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
	            SupplierUtil.createDefaultEdgeSupplier(), false);
		ImportUtil.importDefaultGraphGML(asso, "./src/main/java/graphs/assortativity.gml");
		disso = new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
	            SupplierUtil.createDefaultEdgeSupplier(), false);
		ImportUtil.importDefaultGraphGML(disso, "./src/main/java/graphs/dissortativity.gml");
		tree = new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
	            SupplierUtil.createDefaultEdgeSupplier(), false);
		ImportUtil.importDefaultGraphGML(tree, "./src/main/java/graphs/treec.gml");
		cent = new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
	            SupplierUtil.createDefaultEdgeSupplier(), false);
		ImportUtil.importDefaultGraphGML(cent, "./src/main/java/graphs/centroids.gml");
	}

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
		int e = new Double(expected*1000).intValue();
		int r = new Double(MeasureUtil.assortativityCoefficient(g)*1000).intValue();
		assertEquals(e,r);
	}
	
	@Test
	<V,E> void assortativityCoefficientTestNull () {
		assertThrows(NullPointerException.class, () -> MeasureUtil.assortativityCoefficient(null));
	}
	
	//getTreeCentroidPoints
	static Stream <Arguments> getTreeCentroidPointsProvider () {
		Set <Integer> vclaw = new HashSet<Integer> ();
		vclaw.add(new Integer(0));
		Set <DefaultVertex> vtree = new HashSet<DefaultVertex> ();
		vtree.add(VertexEdgeUtil.getVertexfromLabel(tree.vertexSet(), "e"));
		Set <DefaultVertex> vcent = new HashSet<DefaultVertex> ();
		vcent.add(VertexEdgeUtil.getVertexfromLabel(cent.vertexSet(), "1"));
		vcent.add(VertexEdgeUtil.getVertexfromLabel(cent.vertexSet(), "6"));
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
		Set<DefaultVertex> Vtree = tree.vertexSet();
		DefaultVertex b = VertexEdgeUtil.getVertexfromLabel(Vtree, "b");
		DefaultVertex d = VertexEdgeUtil.getVertexfromLabel(Vtree, "d");
		DefaultVertex e = VertexEdgeUtil.getVertexfromLabel(Vtree, "e");
		DefaultVertex h = VertexEdgeUtil.getVertexfromLabel(Vtree, "h");
		DefaultVertex i = VertexEdgeUtil.getVertexfromLabel(Vtree, "i");
		DefaultVertex c = VertexEdgeUtil.getVertexfromLabel(Vtree, "c");
		Map <DefaultVertex, Double> mtree = new HashMap <> ();
		mtree.put(b,8.0);
		mtree.put(d,6.0);
		mtree.put(e,4.0);
		mtree.put(h,7.0);
		mtree.put(i,8.0);
		mtree.put(c,7.0);
		Set<DefaultVertex> Vcent = cent.vertexSet();
		DefaultVertex v2 = VertexEdgeUtil.getVertexfromLabel(Vcent, "2");
		DefaultVertex v6 = VertexEdgeUtil.getVertexfromLabel(Vcent, "6");
		DefaultVertex v8 = VertexEdgeUtil.getVertexfromLabel(Vcent, "8");
		DefaultVertex v1 = VertexEdgeUtil.getVertexfromLabel(Vcent, "1");
		Map <DefaultVertex, Double> mcent = new HashMap <> ();
		mcent.put(v2,7.0);
		mcent.put(v6,5.0);
		mcent.put(v8,7.0);
		mcent.put(v1,5.0);
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
