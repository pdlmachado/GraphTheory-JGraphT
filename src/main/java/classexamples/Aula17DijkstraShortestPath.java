package classexamples;
// Teoria dos Grafos - UFCG



import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.DrawUtil;
import util.ImportUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;

import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.*;

public class Aula17DijkstraShortestPath {
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;

	public static void main(String[] args) {
		printPaths("germany-roads.csv", "Fra");
		printPaths("aula17-6.csv", "e");
	}
	
	public static void printPaths (String filename, String sourceV) {
		
		Graph<DefaultVertex, DefaultWeightedEdge> g = 
				new SimpleWeightedGraph<DefaultVertex, DefaultWeightedEdge>(
						VertexEdgeUtil.createDefaultVertexSupplier(),
						SupplierUtil.createDefaultWeightedEdgeSupplier());
		g = ImportUtil.importWeightedGraphCSV(g, 				
				graphpathname + filename, 
				CSVFormat.MATRIX, 
				false, 
				true, // EDGE_WEIGHTS
				true); // MATRIX_FORMAT_NODEID
		
		PrintUtil.printGraph(g, "Graph: " + filename);
	
		DijkstraShortestPath<DefaultVertex, DefaultWeightedEdge> dsp = new DijkstraShortestPath<>(g);
		
		DefaultVertex source = VertexEdgeUtil.getVertexfromLabel(g.vertexSet(), sourceV);
		ShortestPathAlgorithm.SingleSourcePaths<DefaultVertex, DefaultWeightedEdge> paths = dsp.getPaths(source);
		
		Iterator <DefaultVertex> it = g.vertexSet().iterator();
		while (it.hasNext()) {
			DefaultVertex v = it.next();
			System.out.println(source+"-"+v+":"+ paths.getPath(v) + paths.getWeight(v));
		}
		System.out.println(NL);

	}
}
