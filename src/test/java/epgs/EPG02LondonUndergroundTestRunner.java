package epgs;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
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
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class EPG02LondonUndergroundTestRunner {
	
	private static final String NL = System.getProperty("line.separator");
	

	static EPG02LondonUnderground epg02 = new EPG02LondonUnderground();
	static Graph<DefaultVertex, RelationshipWeightedEdge> graph = epg02.getGraph();
	static Set <DefaultVertex> V = graph.vertexSet();
	static Set <RelationshipWeightedEdge> E = graph.edgeSet();
		

    @Test 
    public void getGraphTest () {
    	assertEquals(graph.edgeSet().size(),406);
    	assertEquals(graph.vertexSet().size(),302);
    }
    
    @Test 
    public void readAttractionsTest () {
    	assertEquals("Baker Street", epg02.getStation("Sherlock Holmes Museum").getLabel());
    	assertEquals("High Street Kensington", epg02.getStation("Kensington Palace").getLabel());
    	assertEquals("Waterloo", epg02.getStation("Royal Festival Hall").getLabel());
    }
    
    @Test
    public void connectedGraphTest () {
    	ConnectivityInspector <DefaultVertex, RelationshipWeightedEdge> c = 
    			new ConnectivityInspector<DefaultVertex, RelationshipWeightedEdge> (graph);
    	assertTrue(c.isConnected(),"Connectivity");

    	
    }
    
	///////////////////////////////
	// Auxiliary Methods
	static List <DefaultVertex> makeVertexList (String [] arrayVLabels) {
		List <DefaultVertex> listv = new ArrayList <> ();
		for (int i = 0; i < arrayVLabels.length; i++) {
			listv.add(VertexEdgeUtil.getVertexfromLabel(V, arrayVLabels[i]));
		}
		return listv;
	}
	
	static List <RelationshipWeightedEdge> makeEdgeList (String [] arrayELabels) {
		List <RelationshipWeightedEdge> liste = new ArrayList <> ();
		for (int i = 0; i < arrayELabels.length; i++) {
			StringTokenizer st = new StringTokenizer(arrayELabels[i],",");
			String o = st.nextToken();
			String d = st.nextToken();
			liste.add(VertexEdgeUtil.getWEdgefromVertexLabels(E, V, o, d));
		}
		return liste;
	}
	
	static List <String> makeStringList (String [] arrayStrings) {
		List <String> l = new ArrayList <> ();
		for (int i = 0; i < arrayStrings.length; i++) {
			l.add(arrayStrings[i]);
		}
		return l;
	}
	
	///////////////////////////////
	// estimatedTime Tests

	static Stream <Arguments> estimatedTimeDataProvider () {
		return Stream.of(
				Arguments.of(epg02.shortestPath("Bank", "East India"), 10.0, 22.0),
				Arguments.of(epg02.shortestPath("Bank", "East India"), 0.0, 12.0),
				Arguments.of(epg02.shortestPath("Aldgate", "Royal Albert"), 20.0, 123.0),
				Arguments.of(epg02.shortestPath("Bank", "Stanmore"), 15.0, 95.0),
				Arguments.of(epg02.shortestPath("Invalid","Bank"), 20.0, 0.0)
		);
	}

	@ParameterizedTest
	@MethodSource("estimatedTimeDataProvider")
	void estimatedTimeTest (
			GraphPath <DefaultVertex,RelationshipWeightedEdge> path, double time, double expectedResult) {
		assertEquals(expectedResult,epg02.estimatedTime(path,time));
	}

	/////////////////////////////////
	// shortestEstimatedPath Tests

	static Stream <Arguments> shortestEstimatedPathDataProvider () {
		String [] expected1 = {"Bank", "Shadwell", "Limehouse", "Westferry", "Poplar", "Blackwall", "East India"};
		String [] expected2 = {"Aldgate", "Liverpool Street", "Bethnal Green", "Mile End", "Bow Road", "Bromley-By-Bow", "West Ham", "Canning Town", "Royal Victoria", "Custom House", "Prince Regent", "Royal Albert"};
		String [] expected3 = {"Aldgate", "Liverpool Street", "Bank", "Shadwell", "Limehouse", "Westferry", "Poplar", "Blackwall", "East India", "Canning Town", "Royal Victoria", "Custom House", "Prince Regent", "Royal Albert"};
		String [] expected4 = {"Bank", "St. Paul's", "Chancery Lane", "Holborn", "Tottenham Court Road", "Oxford Circus", "Bond Street", "Baker Street", "Finchley Road", "Wembley Park", "Kingsbury", "Queensbury", "Canons Park", "Stanmore"};
		String [] expected5 = {};
		return Stream.of(
				Arguments.of("Bank", "East India", 15.0,20,makeVertexList(expected1),27.0),
				Arguments.of("Aldgate", "Royal Albert", 15.0, 5, makeVertexList(expected2),98.0),
				Arguments.of("Aldgate", "Royal Albert", 15.0, 20, makeVertexList(expected3),71.0),
				Arguments.of("Bank", "Stanmore", 15.0, 20, makeVertexList(expected4),95.0),
				Arguments.of("Bank", "Stanmore", 15.0, 0, makeVertexList(expected5),0.0),
				Arguments.of("Invalid","Bank", 15.0, 20, makeVertexList(expected5),0.0)
		);
	}

	@ParameterizedTest
	@MethodSource("shortestEstimatedPathDataProvider")
	void shortestEstimatedPathTest (
			String source, String target, double time, int maxAttempts, 
			List <DefaultVertex> expectedP, double expectedW) {
		GraphPath <DefaultVertex, RelationshipWeightedEdge> result = epg02.shortestEstimatedPath(source,target,time,maxAttempts);
		assertEquals(expectedP, result.getVertexList(), "Vertex List");
		assertEquals(expectedW, epg02.estimatedTime(result, time), "Estimated Time");
	}
	
	//////////////////////////////////////////////
	// bestRoute Tests
	
	static Stream <Arguments> bestRouteDataProvider () {
		String [] expected1 = {"Bank"};
		String [] input2 = {"Buckingham Palace"};
		String [] expected2 = {"Bank", "Waterloo", "Westminster", "Green Park", "Westminster", "Waterloo", "Bank"};
		String [] input3 = {"Buckingham Palace", "Marble Arch"};
		String [] expected3 = {"Bank", "Waterloo", "Westminster", "Green Park", "Bond Street", "Marble Arch", "Bond Street", "Oxford Circus", "Tottenham Court Road", "Holborn", "Chancery Lane", "St. Paul's", "Bank"};
		String [] input4 = {"Buckingham Palace", "Invalid"};
		String [] expected4 = {};
		String [] input5 = {"Buckingham Palace", "Marble Arch", "Tower Bridge"};
		String [] expected5 = {"Barking", "East Ham", "Upton Park", "Plaistow", "West Ham", "Bromley-By-Bow", "Bow Road", "Mile End", "Bethnal Green", "Liverpool Street", "Bank" , "Waterloo", "Westminster", "Green Park", "Bond Street", "Marble Arch", "Bond Street", "Oxford Circus", "Picadilly Circus", "Charing Cross", "Embankment", "Temple", "Blackfriars", "Mansion House", "Cannon Street", "Monument", "Tower Hill", "Aldgate East", "Whitechapel", "Stepney Green", "Mile End", "Bow Road", "Bromley-By-Bow", "West Ham", "Plaistow", "Upton Park", "East Ham", "Barking"};
		return Stream.of(
				Arguments.of("Bank", new ArrayList <> (), makeVertexList(expected1), 0.0),
				Arguments.of("Bank", makeStringList(input2), makeVertexList(expected2),18.0),
				Arguments.of("Bank", makeStringList(input3), makeVertexList(expected3),23.0),
				Arguments.of("Bank", makeStringList(input4), makeVertexList(expected4),0.0),
				Arguments.of("Invalid", makeStringList(input4), makeVertexList(expected4),0.0),
				Arguments.of("Barking", makeStringList(input5), makeVertexList(expected5), 75.0)
				);
				
	}
	
	@ParameterizedTest
	@MethodSource("bestRouteDataProvider")
	void bestRouteTest (
			String origin, List <String> atts, List <DefaultVertex> expectedP, double expectedW) {
		GraphPath <DefaultVertex, RelationshipWeightedEdge> result = epg02.bestRoute(origin, atts);
		assertEquals(expectedP, result.getVertexList(), "Vertex List");
		assertEquals(expectedW, result.getWeight(), "Weight");
	}
	
	
	////////////////////////////////////////////////
	// findSections test
	
	static Stream <Arguments> findSectionsDataProvider () {
		String [] input1 = {"Invalid", "Bond Street"};
		String [] expected1 = {"Bond Street,Marble Arch", "Bond Street,Oxford Circus", "Baker Street,Bond Street", "Bond Street,Green Park"};
		String [] input2 = {"Bank", "Green Park"};
		String [] expected2 = {"Bank,Liverpool Street", "Bank,St. Paul's", "Bank,Shadwell", "Bond Street,Green Park", "Green Park,Westminster", "Bank,London Bridge", "Bank,Moorgate", "Green Park,Hyde Park Corner", "Green Park,Picadilly Circus", "Green Park,Oxford Circus", "Green Park,Victoria", "Bank,Waterloo"};
        String [] input3 = {"Pinner"};
        String [] expected3 = {"Northwood Hills,Pinner", "North Harrow,Pinner"};
        String [] input4 = {"Pinner", "Green Park"};
        String [] expected4 = {"Bond Street,Green Park", "Green Park,Westminster", "Northwood Hills,Pinner", "North Harrow,Pinner", "Green Park,Hyde Park Corner", "Green Park,Picadilly Circus", "Green Park,Oxford Circus", "Green Park,Victoria"};
		return Stream.of(
				Arguments.of(new ArrayList <> (), new ArrayList <> ()),
				Arguments.of(makeStringList(input1), makeEdgeList(expected1)),
				Arguments.of(makeStringList(input2), makeEdgeList(expected2)),
				Arguments.of(makeStringList(input3), makeEdgeList(expected3)),
				Arguments.of(makeStringList(input4), makeEdgeList(expected4))
				);
	}
	
	@ParameterizedTest
	@MethodSource("findSectionsDataProvider")
	void findSectionsTest (
			List <String> stations, List <RelationshipWeightedEdge> expected) {
		List <RelationshipWeightedEdge> result = epg02.findSections(stations);
		Predicate <RelationshipWeightedEdge> pe = p -> expected.contains(p);
		assertTrue((expected==result)||result.stream().allMatch(pe),"Same Elements");
		assertTrue((expected==result)||result.size() == expected.size(),"Same Size");
	}
	
	////////////////////////////////////////////////////////
	// serviceDisruption test
	
	static Stream <Arguments> serviceDisruptionDataProvider () {
		String [] input1 = {"Bank"};
		String [] input2 = {"Pinner"};
		String [] input3 = {"Invalid"};
		String [] input4 = {"Bank", "Bond Street"};
		String [] input5 = {"South Kensington", "Baker Street", "Oxford Circus"};
		return Stream.of(
				Arguments.of(new ArrayList <> (), false),
				Arguments.of(makeStringList(input1),false),
				Arguments.of(makeStringList(input2),true),
				Arguments.of(makeStringList(input3),false),
				Arguments.of(makeStringList(input4),false),
				Arguments.of(makeStringList(input5),true)
				);
	}
	
	@ParameterizedTest
	@MethodSource("serviceDisruptionDataProvider")
	void serviceDisruptionTest (
			List <String> stations, boolean expected) {
		boolean result = epg02.serviceDisruption(stations);
		assertEquals(expected,result);
	}
	
	/////////////////////////////////////
	// Main Runner
	
    public static void main(String args[]) {
    final LauncherDiscoveryRequest request = 
        LauncherDiscoveryRequestBuilder.request()
                                   .selectors(selectClass(EPG02LondonUndergroundTestRunner.class))
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
