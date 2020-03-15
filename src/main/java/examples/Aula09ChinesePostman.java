// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.alg.cycle.ChinesePostman;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.util.SupplierUtil;

public class Aula09ChinesePostman {

	public static void main(String[] args) {

	    SimpleWeightedGraph <DefaultVertex,DefaultWeightedEdge> graph = 
				new SimpleWeightedGraph<DefaultVertex, DefaultWeightedEdge>(
						MyJGraphTUtil.createDefaultVertexSupplier(),
						SupplierUtil.createDefaultWeightedEdgeSupplier());
        MyJGraphTUtil.importWeightedGraphCSV(graph, "./src/main/java/graphs/chinese.csv",CSVFormat.MATRIX,false,true,true);
 	    MyJGraphTUtil.printGraph(graph,"Graph: ");
 	    
 	    ChinesePostman <DefaultVertex,DefaultWeightedEdge> gen = new ChinesePostman <> ();
 	    System.out.println("Passeio Fechado de Menor Peso: " + gen.getCPPSolution(graph));
 	    System.out.println("Peso do Passeio: " + gen.getCPPSolution(graph).getWeight());

	}

}
