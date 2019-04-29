package examples;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Aula13CoTree {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Graph<String, DefaultEdge> basegraph = new SimpleGraph<>(DefaultEdge.class);
		basegraph = MyJGraphTUtil.importDefaultGraphGML(basegraph, "./src/main/java/graphs/cubo.gml");
		
		Graph<String, DefaultEdge> tree = new SimpleGraph<>(RelationshipEdge.class);
		tree = MyJGraphTUtil.importDefaultGraphGML(tree, "./src/main/java/graphs/cubo-spanningtree.gml");
		

		
		Graph<String, DefaultEdge> coTree = new SimpleGraph<>(DefaultEdge.class);
		
		if (testSpanningTree(basegraph, tree)) {
			Graphs.addAllVertices(coTree, basegraph.vertexSet());
			Graphs.addAllEdges(coTree, basegraph, getCoTreeEdges(basegraph,tree));
			MyJGraphTUtil.printGraph(basegraph, "Base Graph");
			MyJGraphTUtil.printGraph(tree, "Spanning Tree");
			MyJGraphTUtil.printGraph(coTree, "coTree");
			
		}

	}
	

	public static <V,E> Set <E> getCoTreeEdges (Graph <V,E> g, Graph <V,E> t) {
		Set <E> edges = new HashSet<E>();
		Iterator <E> itEdge = g.edgeSet().iterator();
		while (itEdge.hasNext()) {
			E e = itEdge.next();
			if(t.containsEdge(g.getEdgeSource(e),g.getEdgeTarget(e))==false) {
				edges.add(e);
			}
		}
		return edges;
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
