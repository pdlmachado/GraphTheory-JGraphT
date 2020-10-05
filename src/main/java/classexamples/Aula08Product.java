// Teoria dos Grafos - UFCG

package classexamples;

import java.util.Iterator;
import org.jgrapht.Graph;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;

public class Aula08Product {
	
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {
	
		// Calcula o produto cartesiano entre G1 e G2
		//Import G1	

	    computeProduct("Vforprod","Uforprod");
	    computeProduct("prod-a","prod-x");
	}
	
	static void computeProduct(String filename1, String filename2) {
		
		Graph<DefaultVertex, DefaultEdge> g1 = 
				new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), 
									SupplierUtil.createDefaultEdgeSupplier(), false);
		ImportUtil.importGraphCSV(g1, graphpathname + filename1,CSVFormat.ADJACENCY_LIST);
		PrintUtil.printGraph(g1,"Grafo " + filename1 + ": ");
    
		//Import G2
		Graph<DefaultVertex, DefaultEdge> g2 = new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		ImportUtil.importGraphCSV(g2,graphpathname + filename2,CSVFormat.ADJACENCY_LIST);
		PrintUtil.printGraph(g2,"Grafo " + filename2 + ": ");
	    
	    Graph <Pair<DefaultVertex,DefaultVertex>,DefaultEdge> prod = getProduct(g1, g2);
		PrintUtil.printGraph(prod,"Produto: ");
	    
	}
	
	static Graph <Pair<DefaultVertex,DefaultVertex>,DefaultEdge> getProduct (Graph <DefaultVertex, DefaultEdge> g1, Graph <DefaultVertex, DefaultEdge> g2) {
	    
	    Graph <Pair<DefaultVertex,DefaultVertex>,DefaultEdge> prod = new SimpleGraph <> (DefaultEdge.class);
	    // Constroi conjunto de vértices
	    Iterator<DefaultVertex> it1 = g1.vertexSet().iterator();
	    while (it1.hasNext()) {
	    	DefaultVertex v1 = it1.next();
		    Iterator<DefaultVertex> it2 = g2.vertexSet().iterator();
		    while (it2.hasNext()) {
		        DefaultVertex v2 = it2.next();
		    	prod.addVertex(new Pair<DefaultVertex,DefaultVertex>(v1,v2));
		    }
	    }
	    // Constroi conjunto de arestas
	    Iterator<Pair<DefaultVertex,DefaultVertex>> itp1 = prod.vertexSet().iterator();
	    while (itp1.hasNext()) {
	    	Pair<DefaultVertex,DefaultVertex> pv1 = itp1.next();
		    Iterator<Pair<DefaultVertex,DefaultVertex>> itp2 = prod.vertexSet().iterator();
		    while (itp2.hasNext()) {
		    	Pair<DefaultVertex,DefaultVertex> pv2 = itp2.next();
		    	if (pv1.getFirst().equals(pv2.getFirst())) {
		    		if (g2.containsEdge(pv1.getSecond(),pv2.getSecond())) {
		    			prod.addEdge(pv1, pv2);
		    		}
		    	} else if (pv1.getSecond().equals(pv2.getSecond())) {
		    		if (g1.containsEdge(pv1.getFirst(),pv2.getFirst())) {
		    			prod.addEdge(pv1, pv2);
		    			
		    		}
		    	}
		    }
	    }
	    return prod;
	}

}
