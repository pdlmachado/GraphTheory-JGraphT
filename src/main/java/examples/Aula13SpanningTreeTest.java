package examples;

import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Aula13SpanningTreeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Graph<String, DefaultEdge> basegraph = new SimpleGraph<>(DefaultEdge.class);
		basegraph = MyJGraphTUtil.importDefaultGraphGML(basegraph, "./src/main/java/graphs/cubo.gml");
		
		Graph<String, DefaultEdge> tree = new SimpleGraph<>(RelationshipEdge.class);
		tree = MyJGraphTUtil.importDefaultGraphGML(tree, "./src/main/java/graphs/cubo-spanningtree.gml");
		
		MyJGraphTUtil.printGraph(basegraph, "Base Graph");
		MyJGraphTUtil.printGraph(tree, "Spanning Tree Candidate");
		
		System.out.println("Is spanning tree? " + testSpanningTree(basegraph, tree));	

	}
	
	public static <V,E> boolean testSpanningTree (Graph <V,E> g, Graph <V,E> t) {
		
		boolean includeEdges = true;
		Iterator <E> itEdge = t.edgeSet().iterator();
		while (itEdge.hasNext()) {
			E e = itEdge.next();
			if(g.containsEdge(t.getEdgeSource(e),t.getEdgeTarget(e))==false) {
				includeEdges = false;
			}
		}
		return GraphTests.isTree(t) && includeEdges && (g.vertexSet().equals(t.vertexSet()));
	}

}
