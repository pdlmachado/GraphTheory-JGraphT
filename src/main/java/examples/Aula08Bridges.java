// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.graph.SimpleGraph;

public class Aula08Bridges {

	public static void main(String[] args) {

		SimpleGraph <DefaultVertex,RelationshipEdge> g = new SimpleGraph <> (RelationshipEdge.class);
		MyJGraphTUtil.importGraphGML(g, "./src/main/java/graphs/asym.gml");
		
		BiconnectivityInspector <DefaultVertex,RelationshipEdge> b = 
				new BiconnectivityInspector <> (g);
		System.out.println("Graph Edges:" + g.edgeSet());
		System.out.println("Bridges: " + b.getBridges());

	}

}
