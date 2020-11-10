// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.alg.spanning.BoruvkaMinimumSpanningTree;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.spanning.PrimMinimumSpanningTree;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.util.SupplierUtil;

import util.PrintUtil;

import java.lang.Math;
import java.util.Random;

public class Aula21SpanningTree {
	
	private static final String NL = System.getProperty("line.separator");
	
	public static void main(String[] args) {
		
		int e = 8; //Number of Edged
		int v = 6; //Number of Vertexes

		GnmRandomGraphGenerator <String,DefaultWeightedEdge> genGraph = 
				new GnmRandomGraphGenerator <> (v,e);
		SimpleWeightedGraph <String,DefaultWeightedEdge> graph = 
				new SimpleWeightedGraph <> 
		            (SupplierUtil.createStringSupplier(),SupplierUtil.createDefaultWeightedEdgeSupplier());
		genGraph.generateGraph(graph);
		Random rd = new Random();
		graph.edgeSet().stream().forEach(i -> graph.setEdgeWeight(i,rd.nextInt(20)*1.0));
		
		PrintUtil.printWeightedGraph(graph, "Graph: ");
		
		BoruvkaMinimumSpanningTree <String,DefaultWeightedEdge> sp1 = 
  	    		new BoruvkaMinimumSpanningTree <> (graph);
        System.out.println(NL + "BoruvkaMinimumSpanningTree (O((E+V)logV)): " + NL + 
        					sp1.getSpanningTree() + NL +
        					"Computational Complexity Factor: " + (e+v)*Math.log(v));
        
  	    KruskalMinimumSpanningTree <String,DefaultWeightedEdge> sp2 = 
  	    		new KruskalMinimumSpanningTree <> (graph);
        System.out.println(NL + "KruskalMinimumSpanningTree (O(ElogE)): " + NL + 
		           			sp2.getSpanningTree() + NL +
		           			"Computational Complexity Factor: " + e*Math.log(e)); 
  	 
  	    PrimMinimumSpanningTree <String,DefaultWeightedEdge> sp3 = 
  	    		new PrimMinimumSpanningTree <> (graph);
        System.out.println(NL + "PrimMinimumSpanningTree (O(|E|+|V|log(|V|))):" +  NL +
        					sp3.getSpanningTree() + NL +
        					"Computational Complexity Factor: " + (e+(v*Math.log(v))) + NL);
   	}
}
