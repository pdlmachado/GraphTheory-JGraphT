// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;

public class Aula07ImportWeightedDirectedGraph {
	// Importa Grafo Direcionado Ponderado no formato CSV
	
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;

	public static void main(String[] args) {
		// Import CSV

		Graph<DefaultVertex, DefaultWeightedEdge> graph = 
				new DefaultDirectedWeightedGraph<DefaultVertex, DefaultWeightedEdge>(
						VertexEdgeUtil.createDefaultVertexSupplier(),
						SupplierUtil.createDefaultWeightedEdgeSupplier());
		ImportUtil.importWeightedGraphCSV(
				graph, 
				graphpathname + "csv-weighted-example.txt", 
				CSVFormat.MATRIX, 
				false, 
				true, // EDGE_WEIGHTS
				true); // MATRIX_FORMAT_NODEID
		PrintUtil.printWeightedGraph(graph, "Grafo importado do arquivo CSV: ");
		
		AllDirectedPaths <DefaultVertex,DefaultWeightedEdge> paths =
				new AllDirectedPaths <> (graph);
		graph.vertexSet().stream().forEach(v1 -> {
			graph.vertexSet().stream().forEach(v2 -> {
				if (v1.equals(v2) == false) {
					System.out.println("Paths from " + v1 + " to " + v2);
					paths.getAllPaths(v1,v2,true,null).stream().forEach(p -> {
						System.out.println(p.getVertexList() + " " + p.getWeight());
					});
				}
			});
		});			
	}
}
