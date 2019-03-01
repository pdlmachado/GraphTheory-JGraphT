// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;


public class MaximumFlow {
	// Fluxo M�ximo e Corte M�nimo usando algoritmo de Edmonds-Karp

	public static void main(String[] args) {

	    DefaultDirectedWeightedGraph<String,DefaultWeightedEdge> n1 = 
	    		new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
	    n1.addVertex("a"); 	    n1.addVertex("b");
	    n1.addVertex("c");	    n1.addVertex("d");
	    n1.addVertex("s");      n1.addVertex("t");
	    n1.setEdgeWeight(n1.addEdge("s","a"),3);	n1.setEdgeWeight(n1.addEdge("s","b"),2);
	    n1.setEdgeWeight(n1.addEdge("s","c"),4); 	n1.setEdgeWeight(n1.addEdge("s","d"),4);
	    n1.setEdgeWeight(n1.addEdge("d","c"),8);  	n1.setEdgeWeight(n1.addEdge("b","a"),9);
	    n1.setEdgeWeight(n1.addEdge("b","c"),7);  	n1.setEdgeWeight(n1.addEdge("a","c"),10);
	    n1.setEdgeWeight(n1.addEdge("a","t"),4);  	n1.setEdgeWeight(n1.addEdge("c","t"),12);
	    
	    EdmondsKarpMFImpl <String,DefaultWeightedEdge> mf1 = new EdmondsKarpMFImpl <> (n1);
	    System.out.println(n1);
	    System.out.println("Fluxo M�ximo: " + mf1.getMaximumFlow("s","t"));
	    System.out.println("Valor do Fluxo M�ximo: " + mf1.calculateMaximumFlow("s","t"));
	    System.out.println("Corte M�nimo: "+ mf1.getCutEdges());
	    System.out.println("Capacidade do Corte M�nimo: " + mf1.calculateMinCut("s", "t"));
	    
	    DefaultDirectedWeightedGraph<String,DefaultWeightedEdge> n2 = 
	    		new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
	    n2.addVertex("a"); 	    n2.addVertex("b");
	    n2.addVertex("c");	    n2.addVertex("d");
	    n2.addVertex("e");      n2.addVertex("f");
	    n2.addVertex("g");
	    n2.setEdgeWeight(n2.addEdge("a","d"),3);	n2.setEdgeWeight(n2.addEdge("c","d"),1);
	    n2.setEdgeWeight(n2.addEdge("a","b"),3); 	n2.setEdgeWeight(n2.addEdge("c","e"),2);
	    n2.setEdgeWeight(n2.addEdge("c","a"),3);  	n2.setEdgeWeight(n2.addEdge("d","e"),2);
	    n2.setEdgeWeight(n2.addEdge("b","c"),4);  	n2.setEdgeWeight(n2.addEdge("d","f"),6);
	    n2.setEdgeWeight(n2.addEdge("e","b"),1);  	n2.setEdgeWeight(n2.addEdge("e","g"),1);
	    n2.setEdgeWeight(n2.addEdge("f","g"),9);
	  
	    EdmondsKarpMFImpl <String,DefaultWeightedEdge> mf2 = new EdmondsKarpMFImpl <> (n2);
	    System.out.println("\n" + n2);
	    System.out.println("Fluxo M�ximo: " + mf2.getMaximumFlow("a","g"));
	    System.out.println("Valor do Fluxo M�ximo: " + mf2.calculateMaximumFlow("a","g"));
	    System.out.println("Corte M�nimo: "+ mf2.getCutEdges());
	    System.out.println("Capacidade do Corte M�nimo: " + mf2.calculateMinCut("a", "g"));
	    
	}
}