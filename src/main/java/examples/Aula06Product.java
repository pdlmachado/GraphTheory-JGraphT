// Teoria dos Grafos - UFCG

package examples;

import java.util.Iterator;
import org.jgrapht.Graph;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.util.SupplierUtil;

public class Aula06Product {
	
	public static void main(String[] args) {
	
		// Calcula o produto cartesiano entre G1 e G2
		//Import G1	
		Graph<DefaultVertex, DefaultEdge> g1 = new SimpleGraph<>(MyJGraphTUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		MyJGraphTUtil.importGraphCSV(g1,"./src/main/java/graphs/Uforprod",CSVFormat.EDGE_LIST);
		System.out.println("Grafo G1: ");
		System.out.println("Arestas: "+ g1.edgeSet());
		System.out.println("Vertices: " + g1.vertexSet());
    
		//Import G2
		Graph<DefaultVertex, DefaultEdge> g2 = new SimpleGraph<>(MyJGraphTUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		MyJGraphTUtil.importGraphCSV(g2,"./src/main/java/graphs/vforprod",CSVFormat.EDGE_LIST);
	    System.out.println("\nGrafo G2: ");
	    System.out.println("Arestas: "+ g2.edgeSet());
	    System.out.println("Vertices: " + g2.vertexSet());
	    
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
	    
	    System.out.println("\nProduto entre G1 e G2:");
	    System.out.println(prod.vertexSet());
	    System.out.println(prod.edgeSet());

	}

}
