// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.alg.tour.TwoApproxMetricTSP;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import util.PrintUtil;

public class Aula21TwoApproxTSP {
    // 2-algoritmo de aproximação para problema TSP
	
	public static void main(String[] args) {

	    SimpleWeightedGraph<String,DefaultWeightedEdge> g = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	    g.addVertex("a"); 
	    g.addVertex("b");
	    g.addVertex("c");
	    g.addVertex("d");
	    g.setEdgeWeight(g.addEdge("a","b"),2);
	    g.setEdgeWeight(g.addEdge("a","d"),4);
	    g.setEdgeWeight(g.addEdge("a","c"),2);
	    g.setEdgeWeight(g.addEdge("b","c"),3);
	    g.setEdgeWeight(g.addEdge("b","d"),5);
	    g.setEdgeWeight(g.addEdge("d","c"),6);
	    PrintUtil.printWeightedGraph(g, "Graph: ");
        TwoApproxMetricTSP <String,DefaultWeightedEdge> t = new TwoApproxMetricTSP <> ();
	    System.out.println("Minimum weight Hamiltonian Cycle:");
	      System.out.println("C = " + t.getTour(g));
	      System.out.println("w(C) =  " + (t.getTour(g)).getWeight());	    
	}   
}
