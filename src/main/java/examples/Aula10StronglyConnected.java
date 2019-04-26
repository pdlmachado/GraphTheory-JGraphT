// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.alg.connectivity.KosarajuStrongConnectivityInspector;
import org.jgrapht.graph.*;



import java.util.*;

public class Aula10StronglyConnected {
    //Conectividade em grafos direcionados
	
	public static void main(String[] args) {
	    DefaultDirectedGraph<DefaultVertex,RelationshipDirectedEdge> graphgml = new DefaultDirectedGraph<>(RelationshipDirectedEdge.class);
   	    MyJGraphTUtil.importDirectedGraphGML(graphgml,("./src/main/java/graphs/strongly4.gml"));
		
 
	    Set <Object> V = new HashSet <Object>(graphgml.vertexSet());
	    Set <DefaultEdge> E = new HashSet <DefaultEdge>(graphgml.edgeSet());
	    
 	    System.out.println("Ordem do Grafo G: " + V.size());
 	    System.out.println("Tamanho do Grafo G: " + E.size());
	    System.out.println("V(G): " + V.toString());
	    System.out.println("E(G): " + E.toString());

	    KosarajuStrongConnectivityInspector <DefaultVertex,RelationshipDirectedEdge> k = 
	    		new KosarajuStrongConnectivityInspector <> (graphgml);
	    System.out.println("Strongly connected? " + k.isStronglyConnected());
	    
	    System.out.println("Strongly Conected Components: " + k.getStronglyConnectedComponents());   
	}
}
