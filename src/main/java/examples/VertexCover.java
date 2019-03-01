package examples;

import org.jgrapht.Graph;
import org.jgrapht.alg.vertexcover.BarYehudaEvenTwoApproxVCImpl;
import org.jgrapht.alg.vertexcover.ClarksonTwoApproxVCImpl;
import org.jgrapht.alg.vertexcover.EdgeBasedTwoApproxVCImpl;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.alg.vertexcover.RecursiveExactVCImpl;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;

public class VertexCover {
	public static void main(String[] args) {
	    //Gml
	    VertexProvider <Object> vp1 = 
	    		(label,attributes) -> new DefaultVertex (label,attributes);
	    EdgeProvider <Object,RelationshipEdge> ep1 = 
	    		(from,to,label,attributes) -> new RelationshipEdge(from,to,attributes);
		GmlImporter <Object,RelationshipEdge> gmlImporter = new GmlImporter <> (vp1,ep1);
	    Graph<Object, RelationshipEdge> graphgml = new SimpleGraph<>(RelationshipEdge.class);
  	    try {
	        gmlImporter.importGraph(graphgml, 
	        		ImportGraph.readFile("./src/graphs/cordal.gml"));
	      } catch (ImportException e) {
	        throw new RuntimeException(e);
	      }	    
	       
	    BarYehudaEvenTwoApproxVCImpl <Object,RelationshipEdge> vc1 = 
	    		new BarYehudaEvenTwoApproxVCImpl <> (graphgml); 
        System.out.println("BarYehudaEvenTwoApproxVCImpl (cobertura): \n" + vc1.getVertexCover() );

        ClarksonTwoApproxVCImpl <Object,RelationshipEdge> vc2 = 
	    		new ClarksonTwoApproxVCImpl <> (graphgml); 
        System.out.println("ClarksonTwoApproxVCImpl (cobertura): \n" + vc2.getVertexCover() );
        
        EdgeBasedTwoApproxVCImpl <Object,RelationshipEdge> vc3 = 
	    		new EdgeBasedTwoApproxVCImpl <> (graphgml); 
        System.out.println("EdgeBasedTwoApproxVCImpl (cobertura): \n" + vc3.getVertexCover() );
        
        GreedyVCImpl <Object,RelationshipEdge> vc4 = 
	    		new GreedyVCImpl <> (graphgml); 
        System.out.println("GreedyVCImpl (cobertura): \n" + vc4.getVertexCover() );
        
        RecursiveExactVCImpl <Object,RelationshipEdge> vc5 = 
	    		new RecursiveExactVCImpl <> (graphgml); 
        System.out.println("RecursiveExactVCImpl (cobertura): \n" + vc5.getVertexCover() );
	}	    
}
