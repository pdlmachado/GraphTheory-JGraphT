package examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

public class Aula13SpanningTreeTest {

	public static void main(String[] args) {
		
		Graph<DefaultVertex, DefaultEdge> basegraph = 
				new SimpleGraph <> (MyJGraphTUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		basegraph = MyJGraphTUtil.importDefaultGraphGML(basegraph, "./src/main/java/graphs/cubo.gml");
		
		Graph<DefaultVertex, DefaultEdge> tree = 
				new SimpleGraph <> (MyJGraphTUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		tree = MyJGraphTUtil.importDefaultGraphGML(tree, "./src/main/java/graphs/cubo-spanningtree.gml");
		
		MyJGraphTUtil.printGraph(basegraph, "Base Graph");
		MyJGraphTUtil.printGraph(tree, "Spanning Tree Candidate");
		
		System.out.println("Is spanning tree? " + MyTreeUtil.testSpanningTree(basegraph, tree));	

	}

}
