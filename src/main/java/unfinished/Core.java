// Teoria dos Grafos - UFCG

package unfinished;

import org.jgrapht.Graph;
import org.jgrapht.alg.scoring.Coreness;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;


public class Core {
	public static void main(String[] args) {

	    Graph<DefaultVertex, DefaultEdge> graphgml = new SimpleGraph <> (ImportUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
  	    ImportUtil.importDefaultGraphGML(graphgml, "./src/main/java/graphs/natural-cluster.gml");
   	    Coreness <DefaultVertex,DefaultEdge> core = new Coreness <> (graphgml);
  	    System.out.println("Degeneracy: " + core.getDegeneracy());
  	    System.out.println("Vertex Score: " + core.getScores());
  	    
	}
}
