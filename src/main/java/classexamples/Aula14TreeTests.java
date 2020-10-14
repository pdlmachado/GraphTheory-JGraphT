package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.graph.SimpleGraph;

import util.DefaultVertex;
import util.ImportUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;
import util.PrintUtil;

public class Aula14TreeTests {
	public static void main(String[] args) {

		Graph<DefaultVertex, RelationshipEdge> g = 
				new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);

		g = ImportUtil.importGraphGML(g, "./src/main/java/graphs/acyclic.gml");

		PrintUtil.printGraph(g);
		System.out.println("Is it a forest? " + GraphTests.isForest(g));
		System.out.println("Is it a tree? " + GraphTests.isTree(g));

	}
}
