// Teoria dos Grafos - UFCG
// Acesso a arestas e vértices em um grafo sem labels (rótulos) em vértices e arestas

package examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Aula04MyGraphwithoutLabelsAccessing {

	public static void main(String[] args) {
		Graph<String, DefaultEdge> graphgml = new SimpleGraph<>(DefaultEdge.class);
		graphgml = MyJGraphTUtil.importDefaultGraphGML(graphgml, "./src/main/java/graphs/cubo.gml");
        System.out.println(graphgml.vertexSet());
		System.out.println(graphgml.edgeSet()+"\n");
		
		DefaultEdge e1 = graphgml.getEdge("3", "1");
		System.out.println("Aresta: " + graphgml.getEdge("3", "1"));
		System.out.println("Aresta: " + graphgml.getEdge("1", "3"));
		System.out.println("Source: " + graphgml.getEdgeSource(e1));
		System.out.println("Source: " + graphgml.getEdgeTarget(e1));
		
        System.out.println("d_G(1) = " + graphgml.degreeOf("1"));
	}
}
