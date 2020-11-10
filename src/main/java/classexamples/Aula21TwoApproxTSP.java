// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.alg.tour.TwoApproxMetricTSP;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

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
        TwoApproxMetricTSP <String,DefaultWeightedEdge> t = new TwoApproxMetricTSP <> ();
        System.out.println(t.getTour(g));
        System.out.println((t.getTour(g)).getWeight());	    
	}   
}
