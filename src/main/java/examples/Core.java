// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.Graph;
import org.jgrapht.alg.scoring.Coreness;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;


public class Core {
	public static void main(String[] args) {

	    Graph<String, DefaultEdge> graphgml = new SimpleGraph<>(DefaultEdge.class);
  	    MyJGraphTUtil.importDefaultGraphGML(graphgml, "./src/main/java/graphs/natural-cluster.gml");
   	    Coreness <String,DefaultEdge> core = new Coreness <> (graphgml);
  	    System.out.println("Degeneracy: " + core.getDegeneracy());
  	    System.out.println("Vertex Score: " + core.getScores());
  	    
	}
}
