package examples;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Aula13CoTree {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Graph<String, DefaultEdge> basegraph = new SimpleGraph<>(DefaultEdge.class);
		basegraph = MyJGraphTUtil.importDefaultGraphGML(basegraph, "./src/main/java/graphs/cubo.gml");
		
		Graph<String, DefaultEdge> tree = new SimpleGraph<>(RelationshipEdge.class);
		tree = MyJGraphTUtil.importDefaultGraphGML(tree, "./src/main/java/graphs/cubo-spanningtree.gml");
		
		Graph<String, DefaultEdge> coTree = new SimpleGraph<>(DefaultEdge.class);
		
		if (MyTreeUtil.testSpanningTree(basegraph, tree)) {
			Graphs.addAllVertices(coTree, basegraph.vertexSet());
			Graphs.addAllEdges(coTree, basegraph, MyTreeUtil.getcoTreeEdges(basegraph,tree));
			MyJGraphTUtil.printGraph(basegraph, "Base Graph");
			MyJGraphTUtil.printGraph(tree, "Spanning Tree");
			MyJGraphTUtil.printGraph(coTree, "coTree");
			
		}

	}
	




}
