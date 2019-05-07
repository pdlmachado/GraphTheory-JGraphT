package examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

public class Aula15BFS {
	
	public static void main(String[] args) {
		Graph<DefaultVertex, RelationshipEdge> basegraph = new SimpleGraph<>(RelationshipEdge.class);
		basegraph = MyJGraphTUtil.importGraphGML(basegraph, "./src/main/java/graphs/search.gml");
	    MyJGraphTUtil.printGraph(basegraph);
		
		DefaultVertex startVertex = MyJGraphTUtil.getVertexfromLabel(basegraph.vertexSet(), "a");
		BreadthFirstIterator <DefaultVertex, RelationshipEdge> bfs = new BreadthFirstIterator <> (basegraph,startVertex);
        while (bfs.hasNext()) {
        	DefaultVertex v = bfs.next();
        	System.out.print(v + " level:" + bfs.getDepth(v));
        	DefaultVertex p = bfs.getParent(v);
        	if (p!=null) {
               System.out.println(" predecessor:" + bfs.getParent(v));
        	} else System.out.println(" predecessor:_");   	
        }
	}
}
