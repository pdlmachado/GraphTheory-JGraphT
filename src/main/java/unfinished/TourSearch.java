// Teoria dos Grafos - UFCG

package unfinished;

import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.HierholzerEulerianCycle;
import org.jgrapht.graph.SimpleGraph;

import util.DefaultVertex;
import util.ImportUtil;
import util.RelationshipEdge;

public class TourSearch {
	
	public static void main(String[] args) {

	    Graph<DefaultVertex, RelationshipEdge> g = new SimpleGraph<>(RelationshipEdge.class);
	    ImportUtil.importGraphGML(g,"./src/main/java/graphs/rede.gml");

		
		HierholzerEulerianCycle <DefaultVertex,RelationshipEdge> h = 
				new HierholzerEulerianCycle <> ();
		if (h.isEulerian(g)) {
			System.out.println(h.getEulerianCycle(g));
		}

		
	}
}
