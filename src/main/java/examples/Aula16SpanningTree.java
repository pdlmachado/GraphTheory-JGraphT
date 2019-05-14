// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.alg.spanning.BoruvkaMinimumSpanningTree;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.spanning.PrimMinimumSpanningTree;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.util.SupplierUtil;
import java.lang.Math;


public class Aula16SpanningTree {
	public static void main(String[] args) {
		
		int v = 20000; //Number of Vertexes
		int e = 100000; //Number of Edged

		GnmRandomGraphGenerator <String,DefaultWeightedEdge> genGraph = 
				new GnmRandomGraphGenerator <> (v,e);
		SimpleWeightedGraph <String,DefaultWeightedEdge> graph = 
				new SimpleWeightedGraph <> (SupplierUtil.createStringSupplier(),SupplierUtil.createDefaultWeightedEdgeSupplier());
		genGraph.generateGraph(graph);
				
		BoruvkaMinimumSpanningTree <String,DefaultWeightedEdge> sp1 = 
  	    		new BoruvkaMinimumSpanningTree <> (graph);
        System.out.println("\nBoruvkaMinimumSpanningTree (O((E+V)logV)): \n" + sp1.getSpanningTree().getWeight());
        System.out.println("Computational Complexity: " + (e+v)*Math.log(v));
        
  	    KruskalMinimumSpanningTree <String,DefaultWeightedEdge> sp2 = 
  	    		new KruskalMinimumSpanningTree <> (graph);
        System.out.println("\nKruskalMinimumSpanningTree (O(ElogE)): \n" + sp2.getSpanningTree().getWeight());
        System.out.println("Computational Complexity: " + e*Math.log(e));
  	 
  	    PrimMinimumSpanningTree <String,DefaultWeightedEdge> sp3 = 
  	    		new PrimMinimumSpanningTree <> (graph);
        System.out.println("\nPrimMinimumSpanningTree (O(|E|+|V|log(|V|))): \n" + sp3.getSpanningTree().getWeight());
        System.out.println("Computational Complexity: " + (e+(v*Math.log(v))));
   	}
}
