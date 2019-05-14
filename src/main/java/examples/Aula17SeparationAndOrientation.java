package examples;

import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Aula17SeparationAndOrientation {

	public static void main(String[] args) {

		Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
		graph = MyJGraphTUtil.importDefaultGraphGML(graph, "./src/main/java/graphs/cubo.gml");
		MyJGraphTUtil.createAndShowGui(graph, "Graph", false, true, true, true, MyJGraphTUtil.layout_type.HIERARCHICAL);
		
		System.out.println("Is biconnected (non-separable)?  " +
		                     GraphTests.isBiconnected(graph));
		
		BiconnectivityInspector <String,DefaultEdge> insp =
				new BiconnectivityInspector <> (graph);
		System.out.println("Has strong orientation? " + insp.getBridges().isEmpty());

	}

}
