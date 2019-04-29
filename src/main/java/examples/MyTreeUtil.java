package examples;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class MyTreeUtil {
	
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
	
	public static <V,E> void getcoTree (Graph <V,E> coTree, Graph <V,E> basegraph, Graph <V,E> spanningtree) {
		Graphs.addAllVertices(coTree, basegraph.vertexSet());
		Set <E> edges = new HashSet<E>();
		Iterator <E> itEdge = basegraph.edgeSet().iterator();
		while (itEdge.hasNext()) {
			E e = itEdge.next();
			if(spanningtree.containsEdge(basegraph.getEdgeSource(e),basegraph.getEdgeTarget(e))==false) {
				edges.add(e);
			}
		}
		Graphs.addAllEdges(coTree, basegraph, edges);
	}
	
	public static <V,E> int level (Graph <V,E> g, V root, V v) {
		if (g.containsVertex(v)&&root.equals(v)) {
			return 0;
		} else {
			DijkstraShortestPath <V,E> pfinder = new DijkstraShortestPath <> (g);
			return pfinder.getPath(root,v).getLength();
		}
	}
	
	public static void getRootedTree (SimpleGraph <String,DefaultEdge> basegraph, String root, DefaultDirectedGraph <String,DefaultEdge> rt) {
		Graphs.addAllVertices(rt, basegraph.vertexSet());
		Iterator <DefaultEdge> it = basegraph.edgeSet().iterator();
		while (it.hasNext()) {
			DefaultEdge e = it.next();
			String source = basegraph.getEdgeSource(e);
			String target = basegraph.getEdgeTarget(e);
			if (level(basegraph,root,source) > level(basegraph,root,target)) {
				rt.addEdge(target, source, new DefaultEdge());
			} else rt.addEdge(source, target,new DefaultEdge());
		}	
	}
} 
