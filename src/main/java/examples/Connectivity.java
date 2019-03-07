// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.*;



import java.util.*;

public class Connectivity {
	// Conectividade para grafos n�o-direcionados
	// Testar com connected e unconnected.gml

	public static void main(String[] args) {

	    DefaultDirectedGraph<DefaultVertex,RelationshipDirectedEdge> graphgml = new DefaultDirectedGraph<>(RelationshipDirectedEdge.class);
        MyJGraphTUtil.importDirectedGraphGML(graphgml, "./src/main/java/graphs/unconnected.gml");
   		
 	    
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
	    
        DefaultVertex source = MyJGraphTUtil.getVertexfromLabel(V,"a");
        DefaultVertex target = MyJGraphTUtil.getVertexfromLabel(V,"g");
	    System.out.println("Is there a path from " + source + " to " + target + "? " + k.pathExists(source,target));   
	}
}

