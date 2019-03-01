// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.*;
import org.jgrapht.io.*;



import java.util.*;

public class Connectivity {
	// Conectividade para grafos n�o-direcionados
	// Testar com connected e unconnected.gml

	public static void main(String[] args) {
	    VertexProvider <DefaultVertex> vp1 = 
	    		(label,attributes) -> new DefaultVertex (label,attributes);
	    EdgeProvider <DefaultVertex,RelationshipDirectedEdge> ep1 = 
	    		(from,to,label,attributes) -> new RelationshipDirectedEdge(from,to,attributes);
		GmlImporter <DefaultVertex,RelationshipDirectedEdge> gmlImporter = new GmlImporter <> (vp1,ep1);
	    SimpleGraph<DefaultVertex,RelationshipDirectedEdge> graphgml = new SimpleGraph<>(RelationshipDirectedEdge.class);
 	    try {
	        gmlImporter.importGraph(graphgml, ImportGraph.readFile(System.getProperty("user.dir") + "\\src\\graphs\\unconnected.gml"));
	    } catch (ImportException e) {
	        throw new RuntimeException(e);
	    }	    		
 	    
	    Set <DefaultVertex> V = new HashSet <DefaultVertex>(graphgml.vertexSet());
	    Set <DefaultEdge> E = new HashSet <DefaultEdge>(graphgml.edgeSet());
	    
	    
 	    System.out.println("Ordem do Grafo G: " + V.size());
 	    System.out.println("Tamanho do Grafo G: " + E.size());
	    System.out.println("V(G): " + V);
	    System.out.println("E(G): " + E.toString());

	    ConnectivityInspector <DefaultVertex,RelationshipDirectedEdge> k = 
	    		new ConnectivityInspector <> (graphgml);
	    System.out.println("Is connected? " + k.isConnected());
	    System.out.println("Connected Sets: " + k.connectedSets());
	    
        DefaultVertex source = DefaultVertexSet.getVertexfromLabel(V,"a");
        DefaultVertex target = DefaultVertexSet.getVertexfromLabel(V,"g");
	    System.out.println("Is there a path from " + source + " to " + target + "? " + k.pathExists(source,target));   
	}
}

