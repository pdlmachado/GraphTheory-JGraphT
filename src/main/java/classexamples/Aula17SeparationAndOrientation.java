package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;

public class Aula17SeparationAndOrientation {

	public static void main(String[] args) {

		Graph<DefaultVertex, DefaultEdge> graph = 
				new SimpleGraph <> (ImportUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		graph = ImportUtil.importDefaultGraphGML(graph, "./src/main/java/graphs/cubo.gml");
		ImportUtil.createAndShowGui(graph, "Graph", false, true, true, true, JGraphTUtil.ImportUtil.HIERARCHICAL);
		
		System.out.println("Is biconnected (non-separable)?  " +
		                     GraphTests.isBiconnected(graph));
		
		BiconnectivityInspector <DefaultVertex,DefaultEdge> insp =
				new BiconnectivityInspector <> (graph);
		System.out.println("Has strong orientation? " + insp.getBridges().isEmpty());

	}

}
