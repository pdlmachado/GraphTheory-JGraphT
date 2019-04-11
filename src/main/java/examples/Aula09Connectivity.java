// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.*;



import java.util.*;

public class Aula09Connectivity {

	public static void main(String[] args) {

	    SimpleGraph<DefaultVertex,RelationshipEdge> graphgml = new SimpleGraph<>(RelationshipEdge.class);
        MyJGraphTUtil.importGraphGML(graphgml, "./src/main/java/graphs/unconnected.gml");
 	    MyJGraphTUtil.printGraph(graphgml,"Graph: ");
   		
	    Set <DefaultVertex> V = new HashSet <DefaultVertex>(graphgml.vertexSet());

	    ConnectivityInspector <DefaultVertex,RelationshipEdge> k = 
	    		new ConnectivityInspector <> (graphgml);
	    System.out.println("Is connected? " + k.isConnected());
	    System.out.println("Connected Sets: " + k.connectedSets());
	    
        DefaultVertex source = MyJGraphTUtil.getVertexfromLabel(V,"a");
        DefaultVertex target = MyJGraphTUtil.getVertexfromLabel(V,"g");
	    System.out.println("Is there a path from " + source + " to " + target + "? " + k.pathExists(source,target));   
	}
}

