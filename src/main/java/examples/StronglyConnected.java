// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.alg.connectivity.KosarajuStrongConnectivityInspector;
import org.jgrapht.graph.*;
import org.jgrapht.io.*;


import java.util.*;

public class StronglyConnected {
    //Conectividade em grafos direcionados
	
	public static void main(String[] args) {
	    VertexProvider <DefaultVertex> vp1 = 
	    		(label,attributes) -> new DefaultVertex (label,attributes);
	    EdgeProvider <DefaultVertex,RelationshipDirectedEdge> ep1 = 
	    		(from,to,label,attributes) -> new RelationshipDirectedEdge(from,to,attributes);
		GmlImporter <DefaultVertex,RelationshipDirectedEdge> gmlImporter = new GmlImporter <> (vp1,ep1);
	    DefaultDirectedGraph<DefaultVertex,RelationshipDirectedEdge> graphgml = new DefaultDirectedGraph<>(RelationshipDirectedEdge.class);
   	    try {
	        // Testar com grafos strongly1-strongly4.gml
   	    	gmlImporter.importGraph(graphgml, ImportGraph.readFile("./src/graphs/strongly4.gml"));
	      } catch (ImportException e) {
	        throw new RuntimeException(e);
	      }	    		
 	    
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
