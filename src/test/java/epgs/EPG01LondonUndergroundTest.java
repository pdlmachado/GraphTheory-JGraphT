// Teoria dos Grafos - UFCG

package epgs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.util.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import util.DefaultVertex;
import util.RelationshipWeightedEdge;
import util.VertexEdgeUtil;

class EPG01LondonUndergroundTest {

	static EPG01LondonUnderground ex05 = new EPG01LondonUnderground();
	static Graph<DefaultVertex, RelationshipWeightedEdge> graph = ex05.getGraph();
	static Set <DefaultVertex> V = graph.vertexSet();
	static Set <RelationshipWeightedEdge> E = graph.edgeSet();
		

	///////////////////////////////
	// Auxiliary Methods
	static List <DefaultVertex> makeVertexList (String [] arrayLabels) {
		List <DefaultVertex> listv = new ArrayList <> ();
		for (int i = 0; i < arrayLabels.length; i++) {
			listv.add(VertexEdgeUtil.getVertexfromLabel(V, arrayLabels[i]));
		}
		return listv;
	}
	
	/////////////////////////////////
	// centralKStation Tests
	
	static Stream <Arguments> centralKStationsDataProvider () {
		String [] expected1 = {"King's Cross St. Pancras", "Baker Street", "Embankment"};
		String [] expected2 = {"King's Cross St. Pancras", "Baker Street", "Embankment", "Liverpool Street", "Moorgate"};
		String [] expected3 = {};
	    return Stream.of(
	        Arguments.of(3,makeVertexList(expected1)),
	        Arguments.of(5,makeVertexList(expected2)),
	        Arguments.of(0,makeVertexList(expected3)),
	        Arguments.of(-1,makeVertexList(expected3))
	    );
	}
	
	@ParameterizedTest 
	@MethodSource("centralKStationsDataProvider")
	void centralKStationsTest(int numberofStations, List<DefaultVertex> expectedResult) {
		assertEquals(expectedResult, ex05.centralKStations(numberofStations));
	}
	
	/////////////////////////////////
	// shortestPath Tests
	
	static Stream <Arguments> shortestPathDataProvider () {
		String [] expected1 = {"Bank", "Shadwell", "Limehouse", "Westferry", "Poplar", "Blackwall", "East India"};
		String [] expected2 = {"Aldgate", "Liverpool Street", "Bethnal Green", "Mile End", "Bow Road", "Bromley-By-Bow", "West Ham", "Canning Town", "Royal Victoria", "Custom House", "Prince Regent", "Royal Albert"};
		String [] expected3 = {"Bank", "St. Paul's", "Chancery Lane", "Holborn", "Tottenham Court Road", "Oxford Circus", "Bond Street", "Baker Street", "Finchley Road", "Wembley Park", "Kingsbury", "Queensbury", "Canons Park", "Stanmore"};
		String [] expected4 = {};
		return Stream.of(
				Arguments.of("Bank", "East India", makeVertexList(expected1),12.0),
				Arguments.of("Aldgate", "Royal Albert", makeVertexList(expected2),23.0),
				Arguments.of("Bank", "Stanmore", makeVertexList(expected3),35.0),
				Arguments.of("Invalid","Bank",makeVertexList(expected4),0.0)
		);
	}
	
	@ParameterizedTest
	@MethodSource("shortestPathDataProvider")
	void shortestPathTest (
			String source, String target, List <DefaultVertex> expectedP, double expectedW) {
		GraphPath <DefaultVertex, RelationshipWeightedEdge> result = ex05.shortestPath(source,target);
		assertEquals(expectedP, result.getVertexList(), "Vertex List");
		assertEquals(expectedW, result.getWeight(), "Weight");
	}
	
	///////////////////////////////
	// changeofLines Tests
	
	static Stream <Arguments> changeofLinesDataProvider () {
	    List <Pair <String, RelationshipWeightedEdge>> expected1 = new ArrayList <> ();
	    expected1.add(new Pair <> ("13",VertexEdgeUtil.getWEdgefromVertexLabels(E, V, "Bank", "Shadwell")));
	    List <Pair <String, RelationshipWeightedEdge>> expected2 = new ArrayList <> ();
	    expected2.add(new Pair <> ("3", VertexEdgeUtil.getWEdgefromVertexLabels(E, V, "Aldgate", "Liverpool Street")));
	    expected2.add(new Pair <> ("2", VertexEdgeUtil.getWEdgefromVertexLabels(E, V, "Bethnal Green", "Liverpool Street")));	    
	    expected2.add(new Pair <> ("4", VertexEdgeUtil.getWEdgefromVertexLabels(E, V, "Bow Road", "Mile End")));	  
	    expected2.add(new Pair <> ("7", VertexEdgeUtil.getWEdgefromVertexLabels(E, V, "Canning Town", "West Ham")));
	    expected2.add(new Pair <> ("13", VertexEdgeUtil.getWEdgefromVertexLabels(E, V, "Canning Town", "Royal Victoria")));
	    List <Pair <String, RelationshipWeightedEdge>> expected3 = new ArrayList <> ();
	    expected3.add(new Pair <> ("2",VertexEdgeUtil.getWEdgefromVertexLabels(E, V, "Bank", "St. Paul's")));	    
	    expected3.add(new Pair <> ("7",VertexEdgeUtil.getWEdgefromVertexLabels(E, V, "Baker Street", "Bond Street")));	    
	    expected3.add(new Pair <> ("8",VertexEdgeUtil.getWEdgefromVertexLabels(E, V, "Baker Street", "Finchley Road")));	    
	    expected3.add(new Pair <> ("7",VertexEdgeUtil.getWEdgefromVertexLabels(E, V, "Kingsbury", "Wembley Park")));	    	    
	    List <Pair <String, RelationshipWeightedEdge>> expected4 = new ArrayList <> ();
		return Stream.of(
				Arguments.of(ex05.shortestPath("Bank", "East India"), expected1),
				Arguments.of(ex05.shortestPath("Aldgate", "Royal Albert"), expected2),
				Arguments.of(ex05.shortestPath("Bank", "Stanmore"), expected3),
				Arguments.of(ex05.shortestPath("Invalid","Bank"), expected4)
		);
	}
	
	@ParameterizedTest
	@MethodSource("changeofLinesDataProvider")
	void changeOfLinesTest (
			GraphPath <DefaultVertex,RelationshipWeightedEdge> path, List <Pair<String,RelationshipWeightedEdge>> expectedResult) {
		assertEquals(expectedResult, ex05.changeofLines(path));
	}
	
	/////////////////////////////////
	// shortestPathDropLine Tests
	
	static Stream <Arguments> shortestPathDropLineDataProvider () {
		String [] expected1 = {};
		String [] expected2 = {"Bank", "Shadwell", "Limehouse", "Westferry", "Poplar", "Blackwall", "East India"};
		String [] expected3 = {"Aldgate", "Liverpool Street", "Bank", "Shadwell", "Limehouse", "Westferry", "Poplar", "Blackwall", "East India", "Canning Town", "Royal Victoria", "Custom House", "Prince Regent", "Royal Albert"};
		String [] expected4 = {};
		String [] expected5 = {"Bank", "Waterloo", "Westminster", "Green Park", "Bond Street", "Baker Street", "Finchley Road", "Wembley Park", "Kingsbury", "Queensbury", "Canons Park", "Stanmore"};
		String [] expected6 = {};
		return Stream.of(
				Arguments.of("13", "Bank", "East India", 100, makeVertexList(expected1),0.0),
				Arguments.of("2", "Bank", "East India", 100, makeVertexList(expected2),12.0),				
				Arguments.of("7", "Aldgate", "Royal Albert", 100, makeVertexList(expected3),26.0),
				Arguments.of("7", "Aldgate", "Royal Albert", 5, makeVertexList(expected4),0.0),
				Arguments.of("2", "Bank", "Stanmore", 100, makeVertexList(expected5),36.0),
				Arguments.of("2", "Bank", "Stanmore", 0, makeVertexList(expected6),0.0)
		);
	}
	
	@ParameterizedTest
	@MethodSource("shortestPathDropLineDataProvider")
	void shortestPathDropLineTest (
			String line, String source, String target, int maxAttempts, 
			List <DefaultVertex> expectedP, double expectedW) {
		GraphPath <DefaultVertex, RelationshipWeightedEdge> result = ex05.shortestPathDropLine(line,source,target,maxAttempts);
		assertEquals(expectedP, result.getVertexList(), "Vertex List");
		assertEquals(expectedW, result.getWeight(),"Weight");
	}
	
}
