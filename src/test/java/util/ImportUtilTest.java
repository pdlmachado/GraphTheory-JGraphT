package util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.jgrapht.Graph;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.alg.util.Triple;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.nio.ImportException;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.util.SupplierUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.mockito.Mockito.*;

class ImportUtilTest {
	
    private static final String NL = System.getProperty("line.separator");
	static String edgeCSVf = 	
			"a,b" + NL +// "./src/main/java/graphs/assortativity.gml"
			"b,c" + NL +
			"c,f" + NL +
			"f,d" + NL +
			"f,e" + NL +
			"f,g" + NL +
			"d,e" + NL +
			"d,g" + NL +
			"e,g" + NL +
			"g,i" + NL +
			"i,h" + NL;
	static String [] vedgeCSV = {"a","b","c","d","e","f","g","h","i"};
	static String [] eedgeCSV = {"a,b", "b,c", "c,f", "f,d", "f,e", "f,g", "d,e", "d,g", "e,g", "g,i", "i,h"};
	static String wedgeCSVf = 
			"a,c,4" + NL +
			"a,f,7" + NL +
			"c,f,2" + NL +
			"c,d,3" + NL +
			"c,g,9" + NL +
			"f,g,8" + NL +
			"d,g,7" + NL +
			"d,e,3" + NL +
			"g,e,2" + NL +
			"g,h,3" + NL +
			"e,h,7" + NL +
			"e,b,9" + NL +
			"h,b,3" + NL;
	static String [] wvedgeCSV = {"a", "c", "f", "d", "g", "e", "h", "b"};
	static String [] weedgeCSV = {"a,c,4","a,f,7","c,f,2","c,d,3","c,g,9","f,g,8","d,g,7","d,e,3","g,e,2","g,h,3","e,h,7","e,b,9","h,b,3"};
	static String matrixCSVf =
			",a,b,c,d,e" + NL +
			"a,,1,1,1," + NL +
			"b,1,,1,,1" + NL +
			"c,,,,1,1" + NL +
			"d,,,,,1" + NL +
			"e,,,,," + NL ;
	static String matrixCSV0f =
			",a,b,c,d,e" + NL +
			"a,0,1,1,1,0" + NL +
			"b,1,0,1,0,1" + NL +
			"c,0,0,0,1,1" + NL +
			"d,0,0,0,0,1" + NL +
			"e,0,0,0,0,0" + NL ;
	static String matrixCSVwnodef =
			"0,1,1,1,0" + NL +
			"1,0,1,0,1" + NL +
			"0,0,0,1,1" + NL +
			"0,0,0,0,1" + NL +
			"0,0,0,0,0" + NL ;
	static String [] vmatrixCSV = {"a", "b", "c", "d", "e"};
    static String [] ematrixCSV = {"a,b","a,c","a,d","b,c","b,e","c,d","c,e","d,e"};
	static String [] vmatrixCSVwnode = {"1", "2", "3", "4", "5"};
    static String [] ematrixCSVwnode = {"1,2","1,3","1,4","2,3","2,5","3,4","3,5","4,5"};
    static String wmatrixCSVf =
    		",a,b,c,d,e" + NL +
    		"a,,2,2,1,4" + NL +
    		"b,,,3,2,3" + NL +
    		"c,,,,2,2" + NL +
    		"d,,,,,4" + NL +
    		"e,,,,," + NL;
    static String wmatrixCSV0f =
    		",a,b,c,d,e" + NL +
    		"a,0,2,2,1,4" + NL +
    		"b,0,0,3,2,3" + NL +
    		"c,0,0,0,2,2" + NL +
    		"d,0,0,0,0,4" + NL +
    		"e,0,0,0,0,0" + NL;
    static String wmatrixCSVwnodef =
    		",2,2,1,4" + NL +
    		",,3,2,3" + NL +
    		",,,2,2" + NL +
    		",,,,4" + NL +
    		",,,," + NL;
    static String [] wvmatrixCSV = {"a", "b", "c", "d", "e"};
    static String [] wematrixCSV = {"a,b,2.0","a,c,2.0","a,d,1.0","a,e,4.0","b,c,3.0","b,d,2.0","b,e,3.0","c,d,2.0","c,e,2.0","d,e,4.0"};
    static String [] wvmatrixCSVwnode = {"1", "2", "3", "4", "5"};
    static String [] wematrixCSVwnode = {"1,2,2.0","1,3,2.0","1,4,1.0","1,5,4.0","2,3,3.0","2,4,2.0","2,5,3.0","3,4,2.0","3,5,2.0","4,5,4.0"};    
    
    
	////////////////////////
	// Auxiliary Methods
	static List <String> makeStringList (String [] arrayLabels) {
		List <String> listv = new ArrayList <> ();
		for (int i = 0; i < arrayLabels.length; i++) {
			listv.add(arrayLabels[i]);
		}
		return listv;
	}
	
	static List <Pair<String,String>> makeStringPairList (String [] arrayLabels) {
		List <Pair<String,String>> liste = new ArrayList <> ();
		for (int i = 0; i < arrayLabels.length; i++) {
			String [] attributes = arrayLabels[i].split(",");
			liste.add(new Pair <> (attributes[0],attributes[1]));
		}
		return liste;
	}
	
	static List <Triple<String,String,Double>> makeStringTripleList (String [] arrayLabels) {
		List <Triple<String,String,Double>> liste = new ArrayList <> ();
		for (int i = 0; i < arrayLabels.length; i++) {
			String [] attributes = arrayLabels[i].split(",");
			liste.add(new Triple <> (attributes[0],attributes[1],new Double(attributes[2])));
		}
		return liste;
	}
	
    @BeforeAll
    static void beforeAll () {
    }
	
    ///////////////////////////////////////////////////
    //importGraphCSV and getGraphCSV EDGE
    @Test
    void importGraphCSVTest () {
		SimpleGraph <DefaultVertex, DefaultEdge> graph = new SimpleGraph <> 
			(VertexEdgeUtil.createDefaultVertexSupplier(), 
	            SupplierUtil.createDefaultEdgeSupplier(), false);
		// Graph is not read
    	assertEquals(graph, ImportUtil.importGraphCSV(graph,"invalid-name",CSVFormat.EDGE_LIST));   	
    }
    
	static Stream <Arguments> getGraphCSVProvider () {
		return Stream.of(
			Arguments.of(new StringReader(edgeCSVf), CSVFormat.EDGE_LIST, makeStringList(vedgeCSV), makeStringPairList(eedgeCSV)),
			Arguments.of(null,CSVFormat.EDGE_LIST, new ArrayList <>(), new ArrayList <> ()),
			Arguments.of(new StringReader(edgeCSVf), CSVFormat.ADJACENCY_LIST, new ArrayList <>(), new ArrayList <> ())
			);
	}
	
	@ParameterizedTest
	@MethodSource("getGraphCSVProvider")
    void getGraphCSVTest (StringReader reader, CSVFormat f, List <String> expectedV, List <Pair<String,String>> expectedE) {
		Graph <DefaultVertex,DefaultEdge> graph = new SimpleGraph <> 
			(VertexEdgeUtil.createDefaultVertexSupplier(), 
	            SupplierUtil.createDefaultEdgeSupplier(), false);
		final Graph <DefaultVertex,DefaultEdge> g = ImportUtil.getGraphCSV(graph, reader, f); 
		Set <DefaultVertex> V = graph.vertexSet();
		Set <DefaultEdge> E = graph.edgeSet();
		assertEquals(expectedV.size(),V.size(), "Must have the same Vertex Set Size");
		assertEquals(expectedE.size(),E.size(), "Must have the same Edge Set Size");
		Predicate <String> pv = v -> VertexEdgeUtil.getVertexfromLabel(V, v) != null;
		assertTrue(expectedV.stream().allMatch(pv), "Must contain all Vertexes");
		Predicate <Pair<String,String>> pe = p -> g.getEdge(VertexEdgeUtil.getVertexfromLabel(V, p.getFirst()), 
				                                                VertexEdgeUtil.getVertexfromLabel(V, p.getSecond())) != null;
		assertTrue(expectedE.stream().allMatch(pe), "Must contain all Edges");
	}
	
	@Test
	void getGraphCSVInvalidReaderTest () {
		Graph <DefaultVertex,DefaultEdge> graph = new SimpleGraph <> 
		(VertexEdgeUtil.createDefaultVertexSupplier(), 
            SupplierUtil.createDefaultEdgeSupplier(), false);
		assertThrows(RuntimeException.class, 
				     () -> ImportUtil.getGraphCSV(graph, new StringReader(""), CSVFormat.EDGE_LIST));
	}
	
	////////////////////////////////////////////////////////////
	//importWeightedGraphCSV and getWeightedGraphCSV EDGE
    @Test
    void importGraphWeightedCSVTest () {
		SimpleWeightedGraph <DefaultVertex, DefaultWeightedEdge> graph = new SimpleWeightedGraph <> 
			(VertexEdgeUtil.createDefaultVertexSupplier(), 
	            SupplierUtil.createDefaultWeightedEdgeSupplier());
		// Graph is not read
    	assertEquals(graph, ImportUtil.importWeightedGraphCSV(graph,"invalid-name",true));   	
    }
    
	static Stream <Arguments> getWeightedGraphCSVProvider () {
		List <Triple<String,String,Double>> removefirst = makeStringTripleList(weedgeCSV);
		removefirst.remove(0);
		return Stream.of(
			Arguments.of(new StringReader(wedgeCSVf), false, 
							makeStringList(wvedgeCSV), makeStringTripleList(weedgeCSV)),
			Arguments.of(null,false, new ArrayList <>(), new ArrayList <> ()),
			Arguments.of(new StringReader(""),false, new ArrayList <>(), new ArrayList <> ()),
			Arguments.of(new StringReader(wedgeCSVf), true, 
					          makeStringList(wvedgeCSV), removefirst)
			);
	}
	
	@ParameterizedTest
	@MethodSource("getWeightedGraphCSVProvider")
    void getWeightedGraphCSVTest (StringReader reader, boolean head, List <String> expectedV, List <Triple<String,String,Double>> expectedE) {
		Graph <DefaultVertex,DefaultWeightedEdge> graph = new SimpleWeightedGraph <> 
			(VertexEdgeUtil.createDefaultVertexSupplier(), 
	            SupplierUtil.createDefaultWeightedEdgeSupplier());
		final Graph <DefaultVertex,DefaultWeightedEdge> g = ImportUtil.getWeightedGraphCSV(graph, reader, head); 
		Set <DefaultVertex> V = graph.vertexSet();
		Set <DefaultWeightedEdge> E = graph.edgeSet();
		assertEquals(expectedV.size(),V.size(), "Must have the same Vertex Set Size");
		assertEquals(expectedE.size(),E.size(), "Must have the same Edge Set Size");
		Predicate <String> pv = v -> VertexEdgeUtil.getVertexfromLabel(V, v) != null;
		assertTrue(expectedV.stream().allMatch(pv), "Must contain all Vertexes");
		Predicate <Triple<String,String,Double>> pe = p -> g.getEdge(VertexEdgeUtil.getVertexfromLabel(V, p.getFirst()), 
				                                                VertexEdgeUtil.getVertexfromLabel(V, p.getSecond())) != null &&
				                                           g.getEdgeWeight(g.getEdge(VertexEdgeUtil.getVertexfromLabel(V, p.getFirst()), 
								                                     VertexEdgeUtil.getVertexfromLabel(V, p.getSecond()))) == p.getThird();    
		assertTrue(expectedE.stream().allMatch(pe), "Must contain all Edges and Weights");
	}
	
	/////////////////////////////////////////////
	// importGraphCSV and getGraphCSV MATRIX
    @Test
    void importGraphMCSVTest () {
		SimpleGraph <DefaultVertex, DefaultEdge> graph = new SimpleGraph <> 
			(VertexEdgeUtil.createDefaultVertexSupplier(), 
	            SupplierUtil.createDefaultEdgeSupplier(), false);
		// Graph is not read
    	assertEquals(graph, 
    			     ImportUtil.importGraphCSV(graph,"invalid-name",CSVFormat.MATRIX,false,false,true));   	
    }
    
	static Stream <Arguments> getGraphMCSVProvider () {
		return Stream.of(
			Arguments.of(new StringReader(matrixCSVf), CSVFormat.MATRIX, false, false, true, 
					makeStringList(vmatrixCSV), makeStringPairList(ematrixCSV)),
			Arguments.of(new StringReader(matrixCSV0f), CSVFormat.MATRIX, true, false, true, 
					makeStringList(vmatrixCSV), makeStringPairList(ematrixCSV)),
			Arguments.of(new StringReader(matrixCSVwnodef), CSVFormat.MATRIX, true, false, false, 
					makeStringList(vmatrixCSVwnode), makeStringPairList(ematrixCSVwnode)),
			Arguments.of(null,CSVFormat.MATRIX,true,true,true, new ArrayList <>(), new ArrayList <> ()),
			Arguments.of(new StringReader(edgeCSVf), CSVFormat.ADJACENCY_LIST, true, true, true, new ArrayList <>(), new ArrayList <> ())
			);
	}
	
	@ParameterizedTest
	@MethodSource("getGraphMCSVProvider")
    void getGraphMCSVTest (StringReader reader, CSVFormat f, 
    		boolean ZERO,
			boolean WEIGHT,
			boolean NODEID,
    		List <String> expectedV, List <Pair<String,String>> expectedE) {
		Graph <DefaultVertex,DefaultEdge> graph = new SimpleGraph <> 
			(VertexEdgeUtil.createDefaultVertexSupplier(), 
	            SupplierUtil.createDefaultEdgeSupplier(), false);
		final Graph <DefaultVertex,DefaultEdge> g = 
				ImportUtil.getGraphCSV(graph, reader, f, ZERO, WEIGHT, NODEID); 
		Set <DefaultVertex> V = graph.vertexSet();
		Set <DefaultEdge> E = graph.edgeSet();
		assertEquals(expectedV.size(),V.size(), "Must have the same Vertex Set Size");
		assertEquals(expectedE.size(),E.size(), "Must have the same Edge Set Size");
		Predicate <String> pv = v -> VertexEdgeUtil.getVertexfromLabel(V, v) != null;
		assertTrue(expectedV.stream().allMatch(pv), "Must contain all Vertexes");
		Predicate <Pair<String,String>> pe = p -> g.getEdge(VertexEdgeUtil.getVertexfromLabel(V, p.getFirst()), 
				                                                VertexEdgeUtil.getVertexfromLabel(V, p.getSecond())) != null;
		assertTrue(expectedE.stream().allMatch(pe), "Must contain all Edges");
	}
	
	@Test
	void getGraphMCSVInvalidReaderTest () {
		Graph <DefaultVertex,DefaultEdge> graph = new SimpleGraph <> 
		(VertexEdgeUtil.createDefaultVertexSupplier(), 
            SupplierUtil.createDefaultEdgeSupplier(), false);
		assertThrows(RuntimeException.class, 
				     () -> ImportUtil.getGraphCSV(graph, new StringReader(""), CSVFormat.MATRIX,true,true,true));
	}
	
	/////////////////////////////////////////////
	// importWeightedGraphCSV and getWeightedGraphCSV MATRIX
    @Test
    void importGraphWMCSVTest () {
		SimpleWeightedGraph <DefaultVertex, DefaultWeightedEdge> graph = new SimpleWeightedGraph <> 
			(VertexEdgeUtil.createDefaultVertexSupplier(), 
	            SupplierUtil.createDefaultWeightedEdgeSupplier());
		// Graph is not read
    	assertEquals(graph, 
    			     ImportUtil.importWeightedGraphCSV(graph,"invalid-name",CSVFormat.MATRIX,false,false,true));   	
    }
    
	static Stream <Arguments> getGraphWMCSVProvider () {
		return Stream.of(
			Arguments.of(new StringReader(wmatrixCSVf), CSVFormat.MATRIX, false, true, true, 
					makeStringList(wvmatrixCSV), makeStringTripleList(wematrixCSV)),
			Arguments.of(new StringReader(wmatrixCSVwnodef), CSVFormat.MATRIX, false, true, false, 
					makeStringList(wvmatrixCSVwnode), makeStringTripleList(wematrixCSVwnode)),
			Arguments.of(new StringReader(wmatrixCSV0f), CSVFormat.MATRIX, true, true, true, 
					makeStringList(wvmatrixCSV), makeStringTripleList(wematrixCSV)),
			Arguments.of(null,CSVFormat.MATRIX,true,true,true, new ArrayList <>(), new ArrayList <> ()),
			Arguments.of(new StringReader(edgeCSVf), CSVFormat.ADJACENCY_LIST, true, true, true, new ArrayList <>(), new ArrayList <> ())
			);
	}
	
	@ParameterizedTest
	@MethodSource("getGraphWMCSVProvider")
    void getGraphWMCSVTest (StringReader reader, CSVFormat f, 
    		boolean ZERO,
			boolean WEIGHT,
			boolean NODEID,
    		List <String> expectedV, List <Triple<String,String,Double>> expectedE) {
		Graph <DefaultVertex,DefaultWeightedEdge> graph = new SimpleWeightedGraph <> 
			(VertexEdgeUtil.createDefaultVertexSupplier(), 
	            SupplierUtil.createDefaultWeightedEdgeSupplier());
		final Graph <DefaultVertex,DefaultWeightedEdge> g = 
				ImportUtil.getWeightedGraphCSV(graph, reader, f, ZERO, WEIGHT, NODEID); 
		Set <DefaultVertex> V = graph.vertexSet();
		Set <DefaultWeightedEdge> E = graph.edgeSet();
		assertEquals(expectedV.size(),V.size(), "Must have the same Vertex Set Size");
		assertEquals(expectedE.size(),E.size(), "Must have the same Edge Set Size");
		Predicate <String> pv = v -> VertexEdgeUtil.getVertexfromLabel(V, v) != null;
		assertTrue(expectedV.stream().allMatch(pv), "Must contain all Vertexes");
		Predicate <Triple<String,String,Double>> pe = p -> g.getEdge(VertexEdgeUtil.getVertexfromLabel(V, p.getFirst()), 
                VertexEdgeUtil.getVertexfromLabel(V, p.getSecond())) != null &&
           g.getEdgeWeight(g.getEdge(VertexEdgeUtil.getVertexfromLabel(V, p.getFirst()), 
                     VertexEdgeUtil.getVertexfromLabel(V, p.getSecond()))) == p.getThird();    
		assertTrue(expectedE.stream().allMatch(pe), "Must contain all Edges");
	}
	
	@Test
	void getGraphWMCSVInvalidReaderTest () {
		Graph <DefaultVertex,DefaultWeightedEdge> graph = new SimpleGraph <> 
		(VertexEdgeUtil.createDefaultVertexSupplier(), 
            SupplierUtil.createDefaultWeightedEdgeSupplier(), false);
		assertThrows(RuntimeException.class, 
				     () -> ImportUtil.getWeightedGraphCSV(graph, new StringReader(""), CSVFormat.MATRIX,true,true,true));
	}
}
