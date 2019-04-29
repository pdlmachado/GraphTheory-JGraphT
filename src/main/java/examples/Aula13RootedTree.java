package examples;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.RandomWalkIterator;

public class Aula13RootedTree {

	public static void main(String[] args) {
		// Dá uma orientação a uma árvore a partir da escolha de um vértice raiz.
		
		SimpleGraph<String, DefaultEdge> tree = new SimpleGraph<>(RelationshipEdge.class);
		tree = (SimpleGraph<String, DefaultEdge>) MyJGraphTUtil.importDefaultGraphGML(tree, "./src/main/java/graphs/cubo-spanningtree.gml");
		String root = "2";
		DefaultDirectedGraph<String, DefaultEdge> rootedTree = new DefaultDirectedGraph<>(RelationshipEdge.class);
		
		MyTreeUtil.getRootedTree(tree, root, rootedTree);
		
		MyJGraphTUtil.createAndShowGui(rootedTree, "Tree", false, false, true, true, MyJGraphTUtil.layout_type.ORGANIC);
		MyJGraphTUtil.createAndShowGui(rootedTree, "Rooted Tree", true, false, true, true, MyJGraphTUtil.layout_type.HIERARCHICAL);

		System.out.println("Root: " + root);
		RandomWalkIterator <String, DefaultEdge> random = new RandomWalkIterator <> (tree);
		String v = random.next();
     	System.out.print("Vertex: " + v);
		System.out.print("; Level: " + MyTreeUtil.level(rootedTree, root, v));
		System.out.print("; Predecessors : " + Graphs.predecessorListOf(rootedTree, v));
		System.out.println("; Successors : " + Graphs.successorListOf(rootedTree, v));
	}
}
