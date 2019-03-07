// Teoria dos Grafos - UFCG
// Importa um grafo simples, sem labels (rótulos) nas arestas, a partir de um arquivo no formato CSV

package examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.CSVFormat;

public class Aula04ImportSimpleGraphEdgeCSV {

	public static void main(String[] args) {

		Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

		graph = MyJGraphTUtil.importGraphCSV(
				graph, 
				"./src/main/java/graphs/csv-example.txt",
				CSVFormat.EDGE_LIST);
		
		System.out.println("Vertices: " + graph.vertexSet());
		System.out.println("Arestas: " + graph.edgeSet());
	}
}
