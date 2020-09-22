// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.tour.PalmerHamiltonianCycle;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;

public class Aula07ImportWeightedGraphMatrix {
	
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;

	public static void main(String[] args) {

	    SimpleWeightedGraph <DefaultVertex,DefaultWeightedEdge> graph = 
				new SimpleWeightedGraph<DefaultVertex, DefaultWeightedEdge>(
						VertexEdgeUtil.createDefaultVertexSupplier(),
						SupplierUtil.createDefaultWeightedEdgeSupplier());
        ImportUtil.importWeightedGraphCSV(graph, graphpathname + "hamiltonian.csv",CSVFormat.MATRIX,false,true,true);
 	    PrintUtil.printWeightedGraph(graph,"Graph: ");
 	    
 	    PalmerHamiltonianCycle <DefaultVertex,DefaultWeightedEdge> h =
 	    		new PalmerHamiltonianCycle <> ();
 	    GraphPath<DefaultVertex, DefaultWeightedEdge> hcycle = h.getTour(graph);
 	    System.out.println("Hamiltonian cycle: " + hcycle);
 	    // Para a lista de arestas do caminho, a expressão lambda abaixo calcula a soma dos pesos destas arestas
 	    // Este calculo pode ser feita por uma iteração simples usando o comando while.
        double weight = hcycle.getEdgeList().stream().map(e -> graph.getEdgeWeight(e)).reduce(0.0, (a, b) -> a + b);
        System.out.println("Cycle weight: " + weight);
	}
}
