// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.alg.tour.PalmerHamiltonianCycle;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;

public class Aula05ImportWeightedGraphMatrix {

	public static void main(String[] args) {

	    SimpleWeightedGraph <DefaultVertex,DefaultWeightedEdge> graph = 
				new SimpleWeightedGraph<DefaultVertex, DefaultWeightedEdge>(
						VertexEdgeUtil.createDefaultVertexSupplier(),
						SupplierUtil.createDefaultWeightedEdgeSupplier());
        ImportUtil.importWeightedGraphCSV(graph, "./src/main/java/graphs/hamiltonian.csv",CSVFormat.MATRIX,false,true,true);
 	    PrintUtil.printWeightedGraph(graph,"Graph: ");
 	    
 	    PalmerHamiltonianCycle <DefaultVertex,DefaultWeightedEdge> h =
 	    		new PalmerHamiltonianCycle <> ();
 	    System.out.println("Hamiltonian cycle: " + h.getTour(graph));
 	    
 	    

 

	}

}
