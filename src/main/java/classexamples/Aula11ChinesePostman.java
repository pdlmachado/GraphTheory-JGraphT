// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.alg.cycle.ChinesePostman;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;

public class Aula11ChinesePostman {
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;

	public static void main(String[] args) {
		
	    SimpleWeightedGraph <DefaultVertex,DefaultWeightedEdge> g1 = 
				new SimpleWeightedGraph<DefaultVertex, DefaultWeightedEdge>(
						VertexEdgeUtil.createDefaultVertexSupplier(),
						SupplierUtil.createDefaultWeightedEdgeSupplier());
        ImportUtil.importWeightedGraphCSV(g1, graphpathname + "chinese.csv",CSVFormat.MATRIX,false,true,true);
 	    PrintUtil.printGraph(g1,"Grafo " + "chinese.csv");
		menorRota(g1);
		
	    SimpleWeightedGraph <DefaultVertex,DefaultWeightedEdge> g2 = 
				new SimpleWeightedGraph<DefaultVertex, DefaultWeightedEdge>(
						VertexEdgeUtil.createDefaultVertexSupplier(),
						SupplierUtil.createDefaultWeightedEdgeSupplier());
        ImportUtil.importWeightedGraphCSV(g2, graphpathname + "weightededgelist.csv",true);
 	    PrintUtil.printGraph(g2,"Grafo " + "weightededgelist.csv");
		menorRota(g2);
	}

	
	static void menorRota (SimpleWeightedGraph <DefaultVertex,DefaultWeightedEdge> g) {
 	    ChinesePostman <DefaultVertex,DefaultWeightedEdge> gen = new ChinesePostman <> ();
 	    System.out.println("Passeio Fechado de Menor Peso: " + gen.getCPPSolution(g));
 	    System.out.println("Peso do Passeio: " + gen.getCPPSolution(g).getWeight() + NL);
 	    	
 	    }
}
