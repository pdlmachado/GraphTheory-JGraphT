package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;

public class Aula15DFS {
	public static void main(String[] args) {
		Graph<DefaultVertex, RelationshipEdge> basegraph = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);
				new SimpleGraph<>(RelationshipEdge.class);
		basegraph = ImportUtil.importGraphGML(basegraph, "./src/main/java/graphs/search.gml");
	    PrintUtil.printGraph(basegraph);
		
		DefaultVertex startVertex = VertexEdgeUtil.getVertexfromLabel(basegraph.vertexSet(), "a");
		DepthFirstIterator <DefaultVertex, RelationshipEdge> dfs = new DepthFirstIterator <> (basegraph,startVertex);
		System.out.println("Order of visited vertexes in DFS: ");
        while (dfs.hasNext()) {
        	DefaultVertex v = dfs.next();
        	System.out.println(v);
        }
	}

}
