package util;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphMapping;
import org.jgrapht.GraphTests;
import org.jgrapht.Graphs;
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class TreeUtil {
	

	public static <V,E> boolean  testSpanningTree (Graph <V,E> g, Graph <V,E> t) {
		
		boolean includeEdges = true;
		Iterator <E> itEdge = t.edgeSet().iterator();
		while (itEdge.hasNext()) {
			E e = itEdge.next();
			String sourceE = t.getEdgeSource(e).toString();
			String targetE = t.getEdgeTarget(e).toString();
			if(g.edgeSet().stream().anyMatch(ge -> g.getEdgeSource(ge).toString().equals(sourceE)&&g.getEdgeTarget(ge).toString().equals(targetE))==false &&
			   g.edgeSet().stream().anyMatch(ge -> g.getEdgeSource(ge).toString().equals(targetE)&&g.getEdgeTarget(ge).toString().equals(sourceE))==false		) {
				System.out.println(e);
				includeEdges = false;
			}
		}
		boolean includeVertices = true;
		Iterator <V> itVertex = t.vertexSet().iterator();
		while (itVertex.hasNext()) {
			String v = itVertex.next().toString();
			if(g.vertexSet().stream().anyMatch(gv -> gv.toString().equals(v))==false) {
				includeEdges = false;
			}
		}
		return GraphTests.isTree(t) && includeEdges && includeVertices;
	}
	
	public static <V,E> void getcoTree (Graph <V,E> coTree, Graph <V,E> basegraph, Graph <V,E> spanningtree) {
		Graphs.addAllVertices(coTree, basegraph.vertexSet());
		Set <E> edges = new HashSet<E>();
		Iterator <E> itEdge = basegraph.edgeSet().iterator();
		while (itEdge.hasNext()) {
			E e = itEdge.next();
			String sourceE = basegraph.getEdgeSource(e).toString();
			String targetE = basegraph.getEdgeTarget(e).toString();
			if(spanningtree.edgeSet().stream().anyMatch(ge -> spanningtree.getEdgeSource(ge).toString().equals(sourceE)&&spanningtree.getEdgeTarget(ge).toString().equals(targetE))==false &&
			   spanningtree.edgeSet().stream().anyMatch(ge -> spanningtree.getEdgeSource(ge).toString().equals(targetE)&&spanningtree.getEdgeTarget(ge).toString().equals(sourceE))==false		) {
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
	
	public static void getRootedTree (SimpleGraph <DefaultVertex,DefaultEdge> basegraph, DefaultVertex root, DefaultDirectedGraph <DefaultVertex,DefaultEdge> rt) {
		Graphs.addAllVertices(rt, basegraph.vertexSet());
		Iterator <DefaultEdge> it = basegraph.edgeSet().iterator();
		while (it.hasNext()) {
			DefaultEdge e = it.next();
			DefaultVertex source = basegraph.getEdgeSource(e);
			DefaultVertex target = basegraph.getEdgeTarget(e);
			if (level(basegraph,root,source) > level(basegraph,root,target)) {
				rt.addEdge(target, source, new DefaultEdge());
			} else rt.addEdge(source, target,new DefaultEdge());
		}	
	}
} 
