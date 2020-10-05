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

public class Aula11Connectivity {

	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {
		checkConnectivity("unconnected.gml");
		checkConnectivity("connected.gml");
	}
	
	public static void checkConnectivity (String filename) {
		SimpleGraph<DefaultVertex,RelationshipEdge> graphgml = 
				new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);
        ImportUtil.importGraphGML(graphgml, graphpathname + filename);
 	    PrintUtil.printGraph(graphgml,"Graph: " + filename);
   		
	    Set <DefaultVertex> V = new HashSet <DefaultVertex>(graphgml.vertexSet());

	    ConnectivityInspector <DefaultVertex,RelationshipEdge> k = 
	    		new ConnectivityInspector <> (graphgml);
	    System.out.println("Is connected? " + k.isConnected());
	    System.out.println("Connected Sets: " + k.connectedSets());
	    
        DefaultVertex source = VertexEdgeUtil.getVertexfromLabel(V,"a");
        DefaultVertex target = VertexEdgeUtil.getVertexfromLabel(V,"g");
	    System.out.println("Is there a path from " + source + " to " + target + "? " + k.pathExists(source,target) + NL);   
	}
}

