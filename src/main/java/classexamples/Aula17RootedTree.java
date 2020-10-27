package classexamples;


import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.RandomWalkIterator;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.DrawUtil;
import util.ImportUtil;
import util.TreeUtil;
import util.VertexEdgeUtil;

public class Aula17RootedTree {

	public static void main(String[] args) {
		// Dá uma orientação a uma árvore a partir da escolha de um vértice raiz.
		
		SimpleGraph<DefaultVertex, DefaultEdge> tree = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
		tree = (SimpleGraph<DefaultVertex, DefaultEdge>) ImportUtil.importDefaultGraphGML(tree, "./src/main/java/graphs/cubo-spanningtree.gml");
		DefaultVertex root = VertexEdgeUtil.getVertexfromLabel(tree.vertexSet(), "e");
		DefaultDirectedGraph<DefaultVertex, DefaultEdge> rootedTree = 
				new DefaultDirectedGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
		
		TreeUtil.getRootedTree(tree, root, rootedTree);
		
		DrawUtil.createAndShowGui(rootedTree, "Tree", false, false, true, true, DrawUtil.layout_type.ORGANIC);
		DrawUtil.createAndShowGui(rootedTree, "Rooted Tree", true, false, true, true, DrawUtil.layout_type.HIERARCHICAL);

		System.out.println("Root: " + root);
		RandomWalkIterator <DefaultVertex, DefaultEdge> random = new RandomWalkIterator <> (tree);
		DefaultVertex v = random.next();
     	System.out.print("Vertex: " + v);
		System.out.print("; Level: " + TreeUtil.level(rootedTree, root, v));
		System.out.print("; Predecessors : " + Graphs.predecessorListOf(rootedTree, v));
		System.out.println("; Successors : " + Graphs.successorListOf(rootedTree, v));
	}
}
