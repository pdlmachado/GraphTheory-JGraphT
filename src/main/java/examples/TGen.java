package examples;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;


//Teoria dos Grafos - UFCG

import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.*;


public class TGen {
	// Conectividade de Grafos Directionados
	
	enum redTech {G,GE,Sim};

	public static void main(String[] args) {
		
		// Parameters
        String actor1V = "emailUser";
        String actor1D = "Email User";
		String initialNode = "1";
		String finalNode = "7";
		int maxTCSize = 5;
		String filename = "./src/main/java/graphs/Sending Email.gml";
		redTech r = redTech.G;
		

		
		Map <String,String> actorMap = new HashMap <> ();
		actorMap.put(actor1V, actor1D);
		
	    DirectedMultigraph<DefaultVertex,RelationshipDirectedEdge> graphgml = new DirectedMultigraph<>(RelationshipDirectedEdge.class);
        MyJGraphTUtil.importDirectedGraphGML(graphgml, filename);    		
	    
	    Set <DefaultVertex> V = new HashSet <DefaultVertex>(graphgml.vertexSet());
        DefaultVertex source = MyJGraphTUtil.getVertexfromLabel(V,initialNode);
	    DefaultVertex target = MyJGraphTUtil.getVertexfromLabel(V,finalNode);
	    
	    AllDirectedPaths <DefaultVertex, RelationshipDirectedEdge> adp = new AllDirectedPaths <> (graphgml);
	    List<GraphPath<DefaultVertex,RelationshipDirectedEdge>> paths = adp.getAllPaths(source, target, false, maxTCSize*2+2);
	    Iterator <GraphPath<DefaultVertex,RelationshipDirectedEdge>> it = paths.iterator();
	    
	    Set <RelationshipDirectedEdge> reqTransitions = new HashSet <RelationshipDirectedEdge>(graphgml.edgeSet());
	    System.out.println(getBiggest(paths));
	    
	    int countTC = 0;
	    while (it.hasNext()) {
	    	printTC(it.next(),countTC++,actorMap);
	    }
	    
	}
	
	public static GraphPath<DefaultVertex,RelationshipDirectedEdge> getBiggest 
	           (List<GraphPath<DefaultVertex,RelationshipDirectedEdge>> tcs) {
		List< GraphPath<DefaultVertex,RelationshipDirectedEdge> > otcs = 
				tcs.stream().sorted((e1,e2) -> 
				(new Integer (e1.getLength())).compareTo(new Integer (e2.getLength()))).collect(Collectors.toList());
		System.out.println(otcs.get(0));
		return tcs.get(0);
	}
	
	public static String replaceActorVariables (Map <String,String> actorMap, String label) {
		for (Map.Entry<String,String> entry : actorMap.entrySet()) {
			label = label.replaceAll(entry.getKey(),entry.getValue());
		}
		return label;
	}
	
	public static void printTC (GraphPath<DefaultVertex,RelationshipDirectedEdge> p, int count, Map <String,String> actorMap) {
	    Iterator <RelationshipDirectedEdge> it = p.getEdgeList().iterator();
		boolean flag = true;
		System.out.println("Test Case:" + count);
		while (it.hasNext()) {
			RelationshipDirectedEdge e = it.next();
			System.out.println(e);
			String kindS= e.getLabel().substring(1,2);
			String labelS = e.getLabel().substring(4);
			if(kindS.contentEquals("c")) {
				if (flag) { 
					System.out.println("Precondition: " + labelS);
					flag = false;
				} else {
					System.out.println("Postcondition: " + labelS);
				}

			} else {
				if (kindS.contentEquals("s")) {
					System.out.print(replaceActorVariables(actorMap,labelS) + " -> ");
					if (it.hasNext()) {
						RelationshipDirectedEdge e2 = it.next();
						String kindER= e2.getLabel().substring(1,2);
						String labelER = e2.getLabel().substring(4);
						if (kindER.equals("e")) {
							System.out.println(labelER.replaceAll("system", "SYSTEM"));
						}
					}
				}
			}
		}
		System.out.println("\n");
	}
	
}
