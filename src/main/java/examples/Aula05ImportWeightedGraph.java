// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.io.CSVFormat;

public class Aula05ImportWeightedGraph {
	// Importa Grafo Direcionado Ponderado no formato CSV

	public static void main(String[] args) {
		// Import CSV

		Graph<String, DefaultWeightedEdge> graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		MyJGraphTUtil.importWeightedGraphCSV(
				graph, 
				"./src/main/java/graphs/csv-weighted-example.txt", 
				CSVFormat.MATRIX, 
				false, 
				true, // EDGE_WEIGHTS
				true); // MATRIX_FORMAT_NODEID

		System.out.println("Grafo importado do arquivo CSV: ");
		System.out.println("Arestas: " + graph.edgeSet());
		System.out.println("Vertices: " + graph.vertexSet());
		System.out.println("Peso do arco (a,b): " + graph.getEdgeWeight(graph.getEdge("a", "b")));
		System.out.println("Peso do arco (a,d): " + graph.getEdgeWeight(graph.getEdge("a", "d")));
		System.out.println("Peso do arco (b,a): " + graph.getEdgeWeight(graph.getEdge("b", "a")));
		System.out.println("Peso do arco (c,d): " + graph.getEdgeWeight(graph.getEdge("c", "d")));
		System.out.println("Peso do arco (d,b): " + graph.getEdgeWeight(graph.getEdge("d", "b")));
		System.out.println("Peso do arco (e,a): " + graph.getEdgeWeight(graph.getEdge("e", "a")));

	}

}
