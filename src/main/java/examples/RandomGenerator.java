// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.GraphTests;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

public class RandomGenerator {

	public static void main(String[] args) {
		GnmRandomGraphGenerator <String,DefaultEdge> genGraph = 
				new GnmRandomGraphGenerator <> (10,20);
		SimpleGraph <String,DefaultEdge> graph = 
				new SimpleGraph <> (SupplierUtil.createStringSupplier(), 
		                            SupplierUtil.createDefaultEdgeSupplier(),false);
		genGraph.generateGraph(graph);
		MyJGraphTUtil.printGraph(graph);
	
		System.out.println(GraphTests.isBiconnected(graph));
		
		MyJGraphTUtil.createAndShowGui(graph,"Random Graph",false,false,true,true);
	}
}
