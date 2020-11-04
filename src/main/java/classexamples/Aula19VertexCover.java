// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.alg.vertexcover.BarYehudaEvenTwoApproxVCImpl;
import org.jgrapht.alg.vertexcover.ClarksonTwoApproxVCImpl;
import org.jgrapht.alg.vertexcover.EdgeBasedTwoApproxVCImpl;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.alg.vertexcover.RecursiveExactVCImpl;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;

public class Aula19VertexCover {
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {
		printVertexCover("graph-layout.gml");
		printVertexCover("asym.gml");
	}

	public static void printVertexCover(String filename) {
	    Graph<DefaultVertex, DefaultEdge> graphgml = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
	    ImportUtil.importDefaultGraphGML(graphgml, graphpathname + filename);
	    PrintUtil.printGraph(graphgml, "Graph: " + filename);
	       
	    BarYehudaEvenTwoApproxVCImpl <DefaultVertex, DefaultEdge> vc1 = 
	    		new BarYehudaEvenTwoApproxVCImpl <> (graphgml); 
        System.out.println("BarYehudaEvenTwoApproxVCImpl (cobertura): " + NL + vc1.getVertexCover() );

        ClarksonTwoApproxVCImpl <DefaultVertex, DefaultEdge> vc2 = 
	    		new ClarksonTwoApproxVCImpl <> (graphgml); 
        System.out.println("ClarksonTwoApproxVCImpl (cobertura): " + NL + vc2.getVertexCover() );
        
        EdgeBasedTwoApproxVCImpl <DefaultVertex, DefaultEdge> vc3 = 
	    		new EdgeBasedTwoApproxVCImpl <> (graphgml); 
        System.out.println("EdgeBasedTwoApproxVCImpl (cobertura): " + NL + vc3.getVertexCover() );
        
        GreedyVCImpl <DefaultVertex, DefaultEdge> vc4 = 
	    		new GreedyVCImpl <> (graphgml); 
        System.out.println("GreedyVCImpl (cobertura): " + NL + vc4.getVertexCover() );
        
        RecursiveExactVCImpl <DefaultVertex, DefaultEdge> vc5 = 
	    		new RecursiveExactVCImpl <> (graphgml); 
        System.out.println("RecursiveExactVCImpl (cobertura): " + NL +  vc5.getVertexCover() + NL );
	}	    
}
