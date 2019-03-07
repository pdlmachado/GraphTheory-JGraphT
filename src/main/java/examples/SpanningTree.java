// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.Graph;
import org.jgrapht.alg.spanning.BoruvkaMinimumSpanningTree;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.spanning.PrimMinimumSpanningTree;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.io.CSVFormat;


public class SpanningTree {
	public static void main(String[] args) {
	    Graph<String, DefaultWeightedEdge> graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		MyJGraphTUtil.importWeightedGraphCSV(graph, "./src/main/java/graphs/spanning-input.csv", CSVFormat.MATRIX,true, true, true);

		
  	    BoruvkaMinimumSpanningTree <String,DefaultWeightedEdge> sp1 = 
  	    		new BoruvkaMinimumSpanningTree <> (graph);
        System.out.println("BoruvkaMinimumSpanningTree (O((E+V)logV)): \n" + sp1.getSpanningTree());
        
  	    KruskalMinimumSpanningTree <String,DefaultWeightedEdge> sp2 = 
  	    		new KruskalMinimumSpanningTree <> (graph);
        System.out.println("\nKruskalMinimumSpanningTree (O(ElogE)): \n" + sp2.getSpanningTree());
  	 
  	    PrimMinimumSpanningTree <String,DefaultWeightedEdge> sp3 = 
  	    		new PrimMinimumSpanningTree <> (graph);
        System.out.println("\nPrimMinimumSpanningTree (O(|E|+|V|log(|V|))): \n" + sp3.getSpanningTree());
        
	    SimpleWeightedGraph<String,DefaultWeightedEdge> g = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	    g.addVertex("Fra"); 
	    g.addVertex("Man");
	    g.addVertex("Wur");
	    g.addVertex("Stu");
	    g.addVertex("Kas");
	    g.addVertex("Num");
	    g.addVertex("Mun");
	    g.addVertex("Aug");
	    g.addVertex("Erf");
	    g.addVertex("Kar");
	    g.setEdgeWeight(g.addEdge("Fra","Man"),85);
	    g.setEdgeWeight(g.addEdge("Fra","Wur"),217);
	    g.setEdgeWeight(g.addEdge("Fra","Kas"),173);
	    g.setEdgeWeight(g.addEdge("Man","Kar"),80);
	    g.setEdgeWeight(g.addEdge("Wur","Erf"),186);
	    g.setEdgeWeight(g.addEdge("Wur","Num"),103);
	    g.setEdgeWeight(g.addEdge("Num","Stu"),183);
	    g.setEdgeWeight(g.addEdge("Kas","Mun"),502);
	    g.setEdgeWeight(g.addEdge("Num","Mun"),167);
	    g.setEdgeWeight(g.addEdge("Kar","Aug"),250);
	    g.setEdgeWeight(g.addEdge("Aug","Mun"),84);
  
    
  	    PrimMinimumSpanningTree <String,DefaultWeightedEdge> sp4 = 
  	    		new PrimMinimumSpanningTree <> (g);
        System.out.println("\nPrimMinimumSpanningTree (O(|E|+|V|log(|V|))): \n" + sp4.getSpanningTree());
	}
}
