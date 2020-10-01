// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.graph.SimpleGraph;

import util.DefaultVertex;
import util.ImportUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;

public class Aula10Bridges {

	public static void main(String[] args) {

		SimpleGraph <DefaultVertex,RelationshipEdge> g = 
				new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);
		ImportUtil.importGraphGML(g, "./src/main/java/graphs/asym.gml");
		
		BiconnectivityInspector <DefaultVertex,RelationshipEdge> b = 
				new BiconnectivityInspector <> (g);
		System.out.println("Graph Edges:" + g.edgeSet());
		System.out.println("Bridges: " + b.getBridges());

	}

}
