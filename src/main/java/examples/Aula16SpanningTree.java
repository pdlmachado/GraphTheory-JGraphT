// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.Graph;
import org.jgrapht.alg.spanning.BoruvkaMinimumSpanningTree;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.spanning.PrimMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.io.CSVFormat;


public class Aula16SpanningTree {
	public static void main(String[] args) {
	    Graph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		MyJGraphTUtil.importWeightedGraphCSV(graph, "./src/main/java/graphs/chinese.csv", CSVFormat.MATRIX,true, true, true);
		MyJGraphTUtil.createAndShowGui(graph, "Graph", false, false, true, true, MyJGraphTUtil.layout_type.HIERARCHICAL);		
  	    BoruvkaMinimumSpanningTree <String,DefaultWeightedEdge> sp1 = 
  	    		new BoruvkaMinimumSpanningTree <> (graph);
        System.out.println("BoruvkaMinimumSpanningTree (O((E+V)logV)): \n" + sp1.getSpanningTree());
        
  	    KruskalMinimumSpanningTree <String,DefaultWeightedEdge> sp2 = 
  	    		new KruskalMinimumSpanningTree <> (graph);
        System.out.println("\nKruskalMinimumSpanningTree (O(ElogE)): \n" + sp2.getSpanningTree());
  	 
  	    PrimMinimumSpanningTree <String,DefaultWeightedEdge> sp3 = 
  	    		new PrimMinimumSpanningTree <> (graph);
        System.out.println("\nPrimMinimumSpanningTree (O(|E|+|V|log(|V|))): \n" + sp3.getSpanningTree());
   	}
}
