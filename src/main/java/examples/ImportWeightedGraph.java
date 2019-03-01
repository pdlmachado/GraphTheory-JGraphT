// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.io.CSVFormat;
import org.jgrapht.io.CSVImporter;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;

public class ImportWeightedGraph {
	// Importa Grafo Direcionado Ponderado no formato CSV

	public static void main(String[] args) {
		// Import CSV
		VertexProvider<String> vp = (label, attributes) -> label;
		EdgeProvider<String, DefaultWeightedEdge> ep = (from, to, label, attributes) -> new DefaultWeightedEdge();

		CSVImporter<String, DefaultWeightedEdge> csvImporter = new CSVImporter<>(vp, ep);
		csvImporter.setFormat(CSVFormat.MATRIX);
		csvImporter.setParameter(CSVFormat.Parameter.MATRIX_FORMAT_ZERO_WHEN_NO_EDGE, true);
		csvImporter.setParameter(CSVFormat.Parameter.MATRIX_FORMAT_EDGE_WEIGHTS, true);
		csvImporter.setParameter(CSVFormat.Parameter.MATRIX_FORMAT_NODEID, true);
		Graph<String, DefaultWeightedEdge> graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		try {
			csvImporter.importGraph(graph, ImportGraph.readFile("./src/graphs/csv-weighted-example.txt"));
		} catch (ImportException e) {
			throw new RuntimeException(e);
		}
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
