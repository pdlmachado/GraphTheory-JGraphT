// Teoria dos Grafos - UFCG
// Importa um grafo simples, sem labels (rótulos) nas arestas, a partir de um arquivo no formato CSV

package classexamples;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.VertexEdgeUtil;

public class Aula03ImportSimpleGraphEdgeCSV {

	public static void main(String[] args) {
		
		SimpleGraph<DefaultVertex, DefaultEdge> graph = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						            SupplierUtil.createDefaultEdgeSupplier(), false);

		ImportUtil.importGraphCSV(
				graph, 
				"./src/main/java/graphs/csv-example.txt",
				CSVFormat.EDGE_LIST);
		
		System.out.println("Vertices: " + graph.vertexSet());
		System.out.println("Arestas: " + graph.edgeSet());
	}
}
