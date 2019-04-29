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
	
	public static <V,E> Set <E> getcoTreeEdges (Graph <V,E> g, Graph <V,E> t) {
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
	
	public static <V,E> int level (Graph <V,E> g, V root, V v) {
		if (g.containsVertex(v)&&root.equals(v)) {
			return 0;
		} else {
			DijkstraShortestPath <V,E> pfinder = new DijkstraShortestPath <> (g);
			return pfinder.getPath(root,v).getLength();
		}
	}
	
	public static void getRootedTree (SimpleGraph <String,DefaultEdge> g, String root, DefaultDirectedGraph <String,DefaultEdge> t) {
		Graphs.addAllVertices(t, g.vertexSet());
		Iterator <DefaultEdge> it = g.edgeSet().iterator();
		while (it.hasNext()) {
			DefaultEdge e = it.next();
			String source = g.getEdgeSource(e);
			String target = g.getEdgeTarget(e);
			if (level(g,root,source) > level(g,root,target)) {
				t.addEdge(target, source, new DefaultEdge());
			} else t.addEdge(source, target,new DefaultEdge());
		}	
	}
} 
