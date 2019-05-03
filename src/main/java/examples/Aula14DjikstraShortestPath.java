// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.io.CSVFormat;

import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.*;
import org.jgrapht.alg.spanning.PrimMinimumSpanningTree;

public class Aula14DjikstraShortestPath {


	public static void main(String[] args) {

		Graph<String, DefaultWeightedEdge> g = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		g = MyJGraphTUtil.importWeightedGraphCSV(g, 				
				"./src/main/java/graphs/germany-roads.csv", 
				CSVFormat.MATRIX, 
				false, 
				true, // EDGE_WEIGHTS
				true); // MATRIX_FORMAT_NODEID
		MyJGraphTUtil.createAndShowGui(g, "Germany Roads", false, false, true, true, MyJGraphTUtil.layout_type.HIERARCHICAL);
	
		DijkstraShortestPath<String, DefaultWeightedEdge> dsp = new DijkstraShortestPath<>(g);
		
		String source = "Fra";
		ShortestPathAlgorithm.SingleSourcePaths<String, DefaultWeightedEdge> paths = dsp.getPaths(source);
		
		Iterator <String> it = g.vertexSet().iterator();
		while (it.hasNext()) {
			String v = it.next();
			System.out.println(source+"-"+v+":"+ paths.getPath(v) + paths.getWeight(v));
		}

	}
}
