// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.*;

import util.DefaultVertex;
import util.ImportUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;
import util.PrintUtil;

import java.util.*;

public class Aula09Connectivity {

	public static void main(String[] args) {

	    SimpleGraph<DefaultVertex,RelationshipEdge> graphgml = 
				new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);
        ImportUtil.importGraphGML(graphgml, "./src/main/java/graphs/unconnected.gml");
 	    PrintUtil.printGraph(graphgml,"Graph: ");
   		
	    Set <DefaultVertex> V = new HashSet <DefaultVertex>(graphgml.vertexSet());

	    ConnectivityInspector <DefaultVertex,RelationshipEdge> k = 
	    		new ConnectivityInspector <> (graphgml);
	    System.out.println("Is connected? " + k.isConnected());
	    System.out.println("Connected Sets: " + k.connectedSets());
	    
        DefaultVertex source = VertexEdgeUtil.getVertexfromLabel(V,"a");
        DefaultVertex target = VertexEdgeUtil.getVertexfromLabel(V,"g");
	    System.out.println("Is there a path from " + source + " to " + target + "? " + k.pathExists(source,target));   
	}
}

