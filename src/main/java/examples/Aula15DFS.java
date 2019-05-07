package examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;

public class Aula15DFS {
	public static void main(String[] args) {
		Graph<DefaultVertex, RelationshipEdge> basegraph = new SimpleGraph<>(RelationshipEdge.class);
		basegraph = MyJGraphTUtil.importGraphGML(basegraph, "./src/main/java/graphs/search.gml");
	    MyJGraphTUtil.printGraph(basegraph);
		
		DefaultVertex startVertex = MyJGraphTUtil.getVertexfromLabel(basegraph.vertexSet(), "a");
		DepthFirstIterator <DefaultVertex, RelationshipEdge> dfs = new DepthFirstIterator <> (basegraph,startVertex);
		System.out.println("Order of visited vertexes in DFS: ");
        while (dfs.hasNext()) {
        	DefaultVertex v = dfs.next();
        	System.out.println(v);
        }
	}

}
