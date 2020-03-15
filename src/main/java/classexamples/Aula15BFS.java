package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;

public class Aula15BFS {
	
	public static void main(String[] args) {
		Graph<DefaultVertex, RelationshipEdge> basegraph = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);				
		basegraph = ImportUtil.importGraphGML(basegraph, "./src/main/java/graphs/search.gml");
	    PrintUtil.printGraph(basegraph);
		
		DefaultVertex startVertex = VertexEdgeUtil.getVertexfromLabel(basegraph.vertexSet(), "a");
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
