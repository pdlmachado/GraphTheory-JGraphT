package examples;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Aula13RootedTree {

	public static void main(String[] args) {
		// Dá uma orientação a uma árvore a partir da escolha de um vértice raiz.
		
		SimpleGraph<String, DefaultEdge> tree = new SimpleGraph<>(RelationshipEdge.class);
		DefaultDirectedGraph<String, DefaultEdge> rootedTree = new DefaultDirectedGraph<>(RelationshipEdge.class);
		tree = (SimpleGraph<String, DefaultEdge>) MyJGraphTUtil.importDefaultGraphGML(tree, "./src/main/java/graphs/cubo-spanningtree.gml");
		
		MyTreeUtil.getRootedTree(tree, "2", rootedTree);
		
		MyJGraphTUtil.createAndShowGui(rootedTree, "Tree", false, false, true, true, MyJGraphTUtil.layout_type.ORGANIC);
		MyJGraphTUtil.createAndShowGui(rootedTree, "Rooted Tree", true, false, true, true, MyJGraphTUtil.layout_type.HIERARCHICAL);

	}

}
