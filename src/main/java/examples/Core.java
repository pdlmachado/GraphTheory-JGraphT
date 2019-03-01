// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.Graph;
import org.jgrapht.alg.scoring.Coreness;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;

public class Core {
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
	        		ImportGraph.readFile(System.getProperty("user.dir") + "\\src\\graphs\\natural-cluster.gml"));
	      } catch (ImportException e) {
	        throw new RuntimeException(e);
	      }	  
  	    Coreness <Object,RelationshipEdge> core = new Coreness <> (graphgml);
  	    System.out.println("Degeneracy: " + core.getDegeneracy());
  	    System.out.println("Vertex Score: " + core.getScores());
  	    
	}
}
