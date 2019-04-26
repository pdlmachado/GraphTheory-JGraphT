package examples;

import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.graph.SimpleGraph;

public class Aula12TreeTests {
	public static void main(String[] args) {

		Graph<DefaultVertex, RelationshipEdge> g = new SimpleGraph<>(RelationshipEdge.class);

		g = MyJGraphTUtil.importGraphGML(g, "./src/main/java/graphs/acyclic.gml");

		MyJGraphTUtil.printGraph(g);
		System.out.println("Is it a forest? " + GraphTests.isForest(g));
		System.out.println("Is it a tree? " + GraphTests.isTree(g));

	}
}
