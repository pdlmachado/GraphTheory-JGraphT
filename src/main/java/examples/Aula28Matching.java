package examples;

import java.util.HashSet;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.EdmondsMaximumCardinalityMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Aula28Matching {

	public static void main(String[] args) {
		
		Graph<String, DefaultEdge> graph1 = new SimpleGraph<>(DefaultEdge.class);
		graph1 = MyJGraphTUtil.importDefaultGraphGML(graph1, "./src/main/java/graphs/dissortativity.gml");
		MyJGraphTUtil.createAndShowGui(graph1, "Graph 1", false, true, true, true, MyJGraphTUtil.layout_type.ORGANIC);
		
		Graph<String, DefaultEdge> graph2 = new SimpleGraph<>(DefaultEdge.class);
		graph2 = MyJGraphTUtil.importDefaultGraphGML(graph2, "./src/main/java/graphs/cubo.gml");
		MyJGraphTUtil.createAndShowGui(graph2, "Graph 2", false, true, true, true, MyJGraphTUtil.layout_type.ORGANIC);
		
		EdmondsMaximumCardinalityMatching <String,DefaultEdge> m1 = 
				new EdmondsMaximumCardinalityMatching <>(graph1);
		System.out.println("Graph 1:" +  m1.getMatching());
		
		EdmondsMaximumCardinalityMatching <String,DefaultEdge> m2 = 
				new EdmondsMaximumCardinalityMatching <>(graph2);
		System.out.println("Graph 2:" + m2.getMatching());
		
		HashSet <DefaultEdge> edgeSet = new HashSet <> ();
		edgeSet.add(graph2.getEdge("0", "6"));
		edgeSet.add(graph2.getEdge("7", "1"));	
		edgeSet.add(graph2.getEdge("5", "4"));
		System.out.println("For Graph 2, is match " + edgeSet + " maximum? " + 
		                   m2.isMaximumMatching(new MatchingAlgorithm.MatchingImpl<String, DefaultEdge> (graph2, edgeSet, 3.0)));
		
	}
}
