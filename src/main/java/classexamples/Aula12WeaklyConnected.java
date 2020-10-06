// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.*;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.RelationshipDirectedEdge;
import util.VertexEdgeUtil;

import java.util.*;

public class Aula12WeaklyConnected {
	// Conectividade de Grafos Directionados
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;

	public static void main(String[] args) {
		directedPaths("grid.gml","1","4");
		directedPaths("strongly4.gml","b","e");
	}
	
	
	public static void directedPaths (String filename, String s, String t) {

	    DefaultDirectedGraph<DefaultVertex,RelationshipDirectedEdge> g = 
				new DefaultDirectedGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipDirectedEdgeSupplier(), false);
        ImportUtil.importDirectedGraphGML(g, graphpathname + filename);    		
 	    
	    Set <DefaultVertex> V = new HashSet <DefaultVertex>(g.vertexSet());
	    
        PrintUtil.printGraph(g,"Graph: " + filename);
        
	    ConnectivityInspector <DefaultVertex,RelationshipDirectedEdge> k = 
	    		new ConnectivityInspector <> (g);
	    System.out.println("Is weakly connected? " + k.isConnected());
	    System.out.println("Weakly connected sets: " + k.connectedSets());

        DefaultVertex source = VertexEdgeUtil.getVertexfromLabel(V,s);
        DefaultVertex target = VertexEdgeUtil.getVertexfromLabel(V,t);
        	    
	    AllDirectedPaths <DefaultVertex,RelationshipDirectedEdge> p = new AllDirectedPaths <> (g);
	    System.out.println("Directed Paths from " + source + " to " + target + "? " + 
	                           p.getAllPaths(source,target,true,null));
	    System.out.println("Directed Paths from " + target + " to " + source + "? " + 
                p.getAllPaths(target,source,true,null) + NL);
	    
	}

}

