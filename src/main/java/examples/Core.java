// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.Graph;
import org.jgrapht.alg.scoring.Coreness;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;


public class Core {
	public static void main(String[] args) {

	    Graph<DefaultVertex, DefaultEdge> graphgml = new SimpleGraph <> (MyJGraphTUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
  	    MyJGraphTUtil.importDefaultGraphGML(graphgml, "./src/main/java/graphs/natural-cluster.gml");
   	    Coreness <DefaultVertex,DefaultEdge> core = new Coreness <> (graphgml);
  	    System.out.println("Degeneracy: " + core.getDegeneracy());
  	    System.out.println("Vertex Score: " + core.getScores());
  	    
	}
}
