package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.TreeUtil;
import util.VertexEdgeUtil;

public class Aula15SpanningTreeTest {

	public static void main(String[] args) {
		
		Graph<DefaultVertex, DefaultEdge> basegraph = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
		basegraph = ImportUtil.importDefaultGraphGML(basegraph, "./src/main/java/graphs/cubo.gml");
		
		Graph<DefaultVertex, DefaultEdge> tree = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
		tree = ImportUtil.importDefaultGraphGML(tree, "./src/main/java/graphs/cubo-spanningtree.gml");
		
		PrintUtil.printGraph(basegraph, "Base Graph");
		PrintUtil.printGraph(tree, "Spanning Tree Candidate");
		
		System.out.println("Is spanning tree? " + TreeUtil.testSpanningTree(basegraph, tree));	

	}

}
