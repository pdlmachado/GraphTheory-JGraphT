package epgs;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.util.Pair;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherFactory;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.launcher.listeners.TestExecutionSummary.Failure;

import util.DefaultVertex;
import util.RelationshipWeightedEdge;
import util.VertexEdgeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


public class EPG01LondonUndergroundTestRunner {
	
	private static final String NL = System.getProperty("line.separator");
	

	static EPG01LondonUnderground epg01 = new EPG01LondonUnderground();
	static Graph<DefaultVertex, RelationshipWeightedEdge> graph = epg01.getGraph();
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
		assertEquals(expectedResult, epg01.centralKStations(numberofStations));
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
		GraphPath <DefaultVertex, RelationshipWeightedEdge> result = epg01.shortestPath(source,target);
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
				Arguments.of(epg01.shortestPath("Bank", "East India"), expected1),
				Arguments.of(epg01.shortestPath("Aldgate", "Royal Albert"), expected2),
				Arguments.of(epg01.shortestPath("Bank", "Stanmore"), expected3),
				Arguments.of(epg01.shortestPath("Bank","Bank"), expected4) //Caminho Vazio
		);
	}
	
	@ParameterizedTest
	@MethodSource("changeofLinesDataProvider")
	void changeOfLinesTest (
			GraphPath <DefaultVertex,RelationshipWeightedEdge> path, List <Pair<String,RelationshipWeightedEdge>> expectedResult) {
		assertEquals(expectedResult, epg01.changeofLines(path));
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
		GraphPath <DefaultVertex, RelationshipWeightedEdge> result = epg01.shortestPathDropLine(line,source,target,maxAttempts);
		assertEquals(expectedP, result.getVertexList(), "Vertex List");
		assertEquals(expectedW, result.getWeight(),"Weight");
	}
	
    public static void main(String args[]) {
    final LauncherDiscoveryRequest request = 
        LauncherDiscoveryRequestBuilder.request()
                                   .selectors(selectClass(EPG01LondonUndergroundTestRunner.class))
                                   .build();

        final Launcher launcher = LauncherFactory.create();
        final SummaryGeneratingListener listener = new SummaryGeneratingListener();

        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        TestExecutionSummary summary = listener.getSummary();
        long testFoundCount = summary.getTestsFoundCount();
        long passCount = summary.getTestsSucceededCount();
        long failCount = summary.getTestsFailedCount();
        List<Failure> failures = summary.getFailures();
        System.out.println("Total de casos de teste: " + testFoundCount);
        System.out.println("Execução com sucesso: " + passCount);
        System.out.println("Execução com falha: " + failCount);
        failures.forEach(failure -> System.out.println("Falha: "  + failure.getTestIdentifier().getLegacyReportingName() + NL + failure.getTestIdentifier().getDisplayName() + NL + failure.getException()));
    }

}
