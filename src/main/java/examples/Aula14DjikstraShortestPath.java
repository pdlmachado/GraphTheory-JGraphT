// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.util.SupplierUtil;

import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.*;

public class Aula14DjikstraShortestPath {


	public static void main(String[] args) {

		Graph<DefaultVertex, DefaultWeightedEdge> g = 
				new SimpleWeightedGraph<DefaultVertex, DefaultWeightedEdge>(
						MyJGraphTUtil.createDefaultVertexSupplier(),
						SupplierUtil.createDefaultWeightedEdgeSupplier());
		g = MyJGraphTUtil.importWeightedGraphCSV(g, 				
				"./src/main/java/graphs/germany-roads.csv", 
				CSVFormat.MATRIX, 
				false, 
				true, // EDGE_WEIGHTS
				true); // MATRIX_FORMAT_NODEID
		MyJGraphTUtil.createAndShowGui(g, "Germany Roads", false, false, true, true, MyJGraphTUtil.layout_type.HIERARCHICAL);
	
		DijkstraShortestPath<DefaultVertex, DefaultWeightedEdge> dsp = new DijkstraShortestPath<>(g);
		
		DefaultVertex source = MyJGraphTUtil.getVertexfromLabel(g.vertexSet(), "Fra");
		ShortestPathAlgorithm.SingleSourcePaths<DefaultVertex, DefaultWeightedEdge> paths = dsp.getPaths(source);
		
		Iterator <DefaultVertex> it = g.vertexSet().iterator();
		while (it.hasNext()) {
			DefaultVertex v = it.next();
			System.out.println(source+"-"+v+":"+ paths.getPath(v) + paths.getWeight(v));
		}

	}
}
