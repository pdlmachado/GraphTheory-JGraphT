// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.PrintUtil;
import util.DrawUtil;

public class Aula11RandomGenerator {

	public static void main(String[] args) {
		GnmRandomGraphGenerator <String,DefaultEdge> genGraph = 
				new GnmRandomGraphGenerator <> (10,20);
		SimpleGraph <String,DefaultEdge> graph = 
				new SimpleGraph <> (SupplierUtil.createStringSupplier(), 
		                            SupplierUtil.createDefaultEdgeSupplier(),false);
		genGraph.generateGraph(graph);
		PrintUtil.printGraph(graph);
		
		DrawUtil.createAndShowGui(graph,"Random Graph",
				                       false,false,true,true,
				                       DrawUtil.layout_type.CIRCLE);
	}
}
