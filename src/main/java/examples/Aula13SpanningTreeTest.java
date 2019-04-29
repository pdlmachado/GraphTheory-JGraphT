package examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Aula13SpanningTreeTest {

	public static void main(String[] args) {
		
		Graph<String, DefaultEdge> basegraph = new SimpleGraph<>(DefaultEdge.class);
		basegraph = MyJGraphTUtil.importDefaultGraphGML(basegraph, "./src/main/java/graphs/cubo.gml");
		
		Graph<String, DefaultEdge> tree = new SimpleGraph<>(RelationshipEdge.class);
		tree = MyJGraphTUtil.importDefaultGraphGML(tree, "./src/main/java/graphs/cubo-spanningtree.gml");
		
		MyJGraphTUtil.printGraph(basegraph, "Base Graph");
		MyJGraphTUtil.printGraph(tree, "Spanning Tree Candidate");
		
		System.out.println("Is spanning tree? " + MyTreeUtil.testSpanningTree(basegraph, tree));	

	}

}
