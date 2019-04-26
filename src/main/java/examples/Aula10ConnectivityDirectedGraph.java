// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.*;
import java.util.*;

public class Aula10ConnectivityDirectedGraph {
	// Conectividade de Grafos Directionados

	public static void main(String[] args) {

	    DefaultDirectedGraph<DefaultVertex,RelationshipDirectedEdge> graphgml = new DefaultDirectedGraph<>(RelationshipDirectedEdge.class);
        MyJGraphTUtil.importDirectedGraphGML(graphgml, "./src/main/java/graphs/grid.gml");    		
 	    
	    Set <DefaultVertex> V = new HashSet <DefaultVertex>(graphgml.vertexSet());
	    Set <DefaultEdge> E = new HashSet <DefaultEdge>(graphgml.edgeSet());
	    
 	    System.out.println("Ordem do Grafo G: " + V.size());
 	    System.out.println("Tamanho do Grafo G: " + E.size());
	    System.out.println("V(G): " + V.toString());
	    System.out.println("E(G): " + E.toString());

	    ConnectivityInspector <DefaultVertex,RelationshipDirectedEdge> k = 
	    		new ConnectivityInspector <> (graphgml);
	    System.out.println("Is connected? " + k.isConnected());
	    
	    System.out.println("Connected Sets: " + k.connectedSets());
	    
        DefaultVertex source = MyJGraphTUtil.getVertexfromLabel(V,"1");
        DefaultVertex target = MyJGraphTUtil.getVertexfromLabel(V,"2");
	    
	    AllDirectedPaths <DefaultVertex,RelationshipDirectedEdge> p = new AllDirectedPaths <> (graphgml);
	    System.out.println("Paths from " + source + " to " + target + "? " + 
	                           p.getAllPaths(source,target,true,null));
	    
	}

}

