// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.Graph;
import org.jgrapht.alg.vertexcover.BarYehudaEvenTwoApproxVCImpl;
import org.jgrapht.alg.vertexcover.ClarksonTwoApproxVCImpl;
import org.jgrapht.alg.vertexcover.EdgeBasedTwoApproxVCImpl;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.alg.vertexcover.RecursiveExactVCImpl;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

public class Aula18VertexCover {
	public static void main(DefaultVertex[] args) {

	    Graph<DefaultVertex, DefaultEdge> graphgml = 
				new SimpleGraph <> (MyJGraphTUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
	    MyJGraphTUtil.importDefaultGraphGML(graphgml, "./src/main/java/graphs/graph-layout.gml");
	       
	    BarYehudaEvenTwoApproxVCImpl <DefaultVertex, DefaultEdge> vc1 = 
	    		new BarYehudaEvenTwoApproxVCImpl <> (graphgml); 
        System.out.println("BarYehudaEvenTwoApproxVCImpl (cobertura): \n" + vc1.getVertexCover() );

        ClarksonTwoApproxVCImpl <DefaultVertex, DefaultEdge> vc2 = 
	    		new ClarksonTwoApproxVCImpl <> (graphgml); 
        System.out.println("ClarksonTwoApproxVCImpl (cobertura): \n" + vc2.getVertexCover() );
        
        EdgeBasedTwoApproxVCImpl <DefaultVertex, DefaultEdge> vc3 = 
	    		new EdgeBasedTwoApproxVCImpl <> (graphgml); 
        System.out.println("EdgeBasedTwoApproxVCImpl (cobertura): \n" + vc3.getVertexCover() );
        
        GreedyVCImpl <DefaultVertex, DefaultEdge> vc4 = 
	    		new GreedyVCImpl <> (graphgml); 
        System.out.println("GreedyVCImpl (cobertura): \n" + vc4.getVertexCover() );
        
        RecursiveExactVCImpl <DefaultVertex, DefaultEdge> vc5 = 
	    		new RecursiveExactVCImpl <> (graphgml); 
        System.out.println("RecursiveExactVCImpl (cobertura): \n" + vc5.getVertexCover() );
	}	    
}
