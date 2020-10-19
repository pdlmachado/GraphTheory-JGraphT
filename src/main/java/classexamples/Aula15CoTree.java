package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.TreeUtil;
import util.VertexEdgeUtil;
import util.PrintUtil;

public class Aula15CoTree {
	
	public static void main(String[] args) {
		
		Graph<DefaultVertex, DefaultEdge> basegraph = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
									SupplierUtil.createDefaultEdgeSupplier(), false);
		basegraph = ImportUtil.importDefaultGraphGML(basegraph, "./src/main/java/graphs/cubo.gml");
		
		Graph<DefaultVertex, DefaultEdge> tree = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
		tree = ImportUtil.importDefaultGraphGML(tree, "./src/main/java/graphs/cubo-spanningtree.gml");
		
		Graph<DefaultVertex, DefaultEdge> coTree = new SimpleGraph<>(DefaultEdge.class);
		
		if (TreeUtil.testSpanningTree(basegraph, tree)) {
			TreeUtil.getcoTree(coTree,basegraph,tree);
			PrintUtil.printGraph(basegraph, "Base Graph");
			PrintUtil.printGraph(tree, "Spanning Tree");
			PrintUtil.printGraph(coTree, "coTree");
			
		}

	}
	




}
