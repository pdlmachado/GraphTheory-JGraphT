package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.TreeUtil;

public class Aula13SpanningTreeTest {

	public static void main(String[] args) {
		
		Graph<DefaultVertex, DefaultEdge> basegraph = 
				new SimpleGraph <> (ImportUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		basegraph = ImportUtil.importDefaultGraphGML(basegraph, "./src/main/java/graphs/cubo.gml");
		
		Graph<DefaultVertex, DefaultEdge> tree = 
				new SimpleGraph <> (ImportUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		tree = ImportUtil.importDefaultGraphGML(tree, "./src/main/java/graphs/cubo-spanningtree.gml");
		
		ImportUtil.printGraph(basegraph, "Base Graph");
		ImportUtil.printGraph(tree, "Spanning Tree Candidate");
		
		System.out.println("Is spanning tree? " + TreeUtil.testSpanningTree(basegraph, tree));	

	}

}
