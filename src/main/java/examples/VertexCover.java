package examples;

import org.jgrapht.Graph;
import org.jgrapht.alg.vertexcover.BarYehudaEvenTwoApproxVCImpl;
import org.jgrapht.alg.vertexcover.ClarksonTwoApproxVCImpl;
import org.jgrapht.alg.vertexcover.EdgeBasedTwoApproxVCImpl;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.alg.vertexcover.RecursiveExactVCImpl;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class VertexCover {
	public static void main(String[] args) {

	    Graph<String, DefaultEdge> graphgml = new SimpleGraph<>(DefaultEdge.class);
	    MyJGraphTUtil.importDefaultGraphGML(graphgml, "./src/main/java/graphs/cordal.gml");
	       
	    BarYehudaEvenTwoApproxVCImpl <String, DefaultEdge> vc1 = 
	    		new BarYehudaEvenTwoApproxVCImpl <> (graphgml); 
        System.out.println("BarYehudaEvenTwoApproxVCImpl (cobertura): \n" + vc1.getVertexCover() );

        ClarksonTwoApproxVCImpl <String, DefaultEdge> vc2 = 
	    		new ClarksonTwoApproxVCImpl <> (graphgml); 
        System.out.println("ClarksonTwoApproxVCImpl (cobertura): \n" + vc2.getVertexCover() );
        
        EdgeBasedTwoApproxVCImpl <String, DefaultEdge> vc3 = 
	    		new EdgeBasedTwoApproxVCImpl <> (graphgml); 
        System.out.println("EdgeBasedTwoApproxVCImpl (cobertura): \n" + vc3.getVertexCover() );
        
        GreedyVCImpl <String, DefaultEdge> vc4 = 
	    		new GreedyVCImpl <> (graphgml); 
        System.out.println("GreedyVCImpl (cobertura): \n" + vc4.getVertexCover() );
        
        RecursiveExactVCImpl <String, DefaultEdge> vc5 = 
	    		new RecursiveExactVCImpl <> (graphgml); 
        System.out.println("RecursiveExactVCImpl (cobertura): \n" + vc5.getVertexCover() );
	}	    
}
