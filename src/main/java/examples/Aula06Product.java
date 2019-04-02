// Teoria dos Grafos - UFCG

package examples;

import java.util.Iterator;
import org.jgrapht.Graph;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.CSVFormat;

public class Aula06Product {
	
	public static void main(String[] args) {
	
		// Calcula o produto cartesiano entre G1 e G2
		//Import G1	
		Graph<String, DefaultEdge> g1 = new SimpleGraph<>(DefaultEdge.class);
		MyJGraphTUtil.importGraphCSV(g1,"./src/main/java/graphs/Uforprod",CSVFormat.EDGE_LIST);
		System.out.println("Grafo G1: ");
		System.out.println("Arestas: "+ g1.edgeSet());
		System.out.println("Vertices: " + g1.vertexSet());
    
		//Import G2
		Graph<String, DefaultEdge> g2 = new SimpleGraph<>(RelationshipEdge.class);
		MyJGraphTUtil.importGraphCSV(g2,"./src/main/java/graphs/vforprod",CSVFormat.EDGE_LIST);
	    System.out.println("\nGrafo G2: ");
	    System.out.println("Arestas: "+ g2.edgeSet());
	    System.out.println("Vertices: " + g2.vertexSet());
	    
	    Graph <Pair<String,String>,DefaultEdge> prod = new SimpleGraph <> (DefaultEdge.class);
	    // Constroi conjunto de vértices
	    Iterator<String> it1 = g1.vertexSet().iterator();
	    while (it1.hasNext()) {
	    	String v1 = it1.next();
		    Iterator<String> it2 = g2.vertexSet().iterator();
		    while (it2.hasNext()) {
		        String v2 = it2.next();
		    	prod.addVertex(new Pair<String,String>(v1,v2));
		    }
	    }
	    // Constroi conjunto de arestas
	    Iterator<Pair<String,String>> itp1 = prod.vertexSet().iterator();
	    while (itp1.hasNext()) {
	    	Pair<String,String> pv1 = itp1.next();
		    Iterator<Pair<String,String>> itp2 = prod.vertexSet().iterator();
		    while (itp2.hasNext()) {
		    	Pair<String,String> pv2 = itp2.next();
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
