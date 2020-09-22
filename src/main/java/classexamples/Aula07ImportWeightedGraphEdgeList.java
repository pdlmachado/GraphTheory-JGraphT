// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.alg.shortestpath.BFSShortestPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;

public class Aula07ImportWeightedGraphEdgeList {
	
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;

	public static void main(String[] args) {

	    SimpleWeightedGraph <DefaultVertex,DefaultWeightedEdge> graph = 
				new SimpleWeightedGraph<DefaultVertex, DefaultWeightedEdge>(
						VertexEdgeUtil.createDefaultVertexSupplier(),
						SupplierUtil.createDefaultWeightedEdgeSupplier());
        ImportUtil.importWeightedGraphCSV(graph, graphpathname + "weightededgelist.csv", false);
 	    PrintUtil.printWeightedGraph(graph,"Graph: ");
 
 	    DefaultVertex e = VertexEdgeUtil.getVertexfromLabel(graph.vertexSet(), "e");
 	    DefaultVertex f = VertexEdgeUtil.getVertexfromLabel(graph.vertexSet(), "f");
 	    
   	    DijkstraShortestPath <DefaultVertex,DefaultWeightedEdge> dsp = 
 	    		new DijkstraShortestPath <> (graph);
 	    System.out.println("Shortest Path between e and f: " + dsp.getPath(e, f).getVertexList());
 
   	    BFSShortestPath <DefaultVertex,DefaultWeightedEdge> bfs = 
 	    		new BFSShortestPath <> (graph);
 	    System.out.println("Path with less length between e and f: " + bfs.getPath(e, f).getVertexList());
 	    
	}
}
