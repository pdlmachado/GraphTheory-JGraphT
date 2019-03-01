// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.HierholzerEulerianCycle;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;


public class TourSearch {
	
	public static void main(String[] args) {
		
	    //Gml
	    VertexProvider <DefaultVertex> vp1 = 
	    		(label,attributes) -> new DefaultVertex (label,attributes);
	    EdgeProvider <DefaultVertex,RelationshipEdge> ep1 = 
	    		(from,to,label,attributes) -> new RelationshipEdge(from,to,attributes);
		GmlImporter <DefaultVertex,RelationshipEdge> gmlImporter = new GmlImporter <> (vp1,ep1);
	    Graph<DefaultVertex, RelationshipEdge> g = new SimpleGraph<>(RelationshipEdge.class);
  	    try {
	        gmlImporter.importGraph(g, ImportGraph.readFile("./src/graphs/rede.gml"));
	      } catch (ImportException e) {
	        throw new RuntimeException(e);
	      }	    
		
		HierholzerEulerianCycle <DefaultVertex,RelationshipEdge> h = 
				new HierholzerEulerianCycle <> ();
		if (h.isEulerian(g)) {
			System.out.println(h.getEulerianCycle(g));
		}

		
	}
}
