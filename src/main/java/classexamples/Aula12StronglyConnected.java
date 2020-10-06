// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.alg.connectivity.KosarajuStrongConnectivityInspector;
import org.jgrapht.graph.*;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.RelationshipDirectedEdge;
import util.VertexEdgeUtil;

public class Aula12StronglyConnected {
    //Conectividade em grafos direcionados

	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {
		stronglyConnected("strongly1.gml");
		stronglyConnected("strongly2.gml");
		stronglyConnected("strongly3.gml");
		stronglyConnected("strongly4.gml");	
		stronglyConnected("grid.gml");	
	}
	
	public static void stronglyConnected (String filename) {
		
	    DefaultDirectedGraph<DefaultVertex,RelationshipDirectedEdge> g = 
				new DefaultDirectedGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipDirectedEdgeSupplier(), false);
   	    ImportUtil.importDirectedGraphGML(g, graphpathname + filename);
        PrintUtil.printGraph(g, "Graph :" + filename);

	    KosarajuStrongConnectivityInspector <DefaultVertex,RelationshipDirectedEdge> k = 
	    		new KosarajuStrongConnectivityInspector <> (g);
	    System.out.println("Strongly connected? " + k.isStronglyConnected());
	    
	    System.out.println("Strongly Conected Components: " + k.getStronglyConnectedComponents() + NL);   
	}
}
