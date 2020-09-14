package classexamples;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.YenKShortestPath;
import org.jgrapht.alg.shortestpath.YenShortestPathIterator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;

public class Aula04ShortestPaths {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph<DefaultVertex, DefaultEdge> graph = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						            SupplierUtil.createDefaultEdgeSupplier(), false);
		
		ImportUtil.importDefaultGraphGML(graph, "./src/main/java/graphs/cordal.gml");
		
		Set <DefaultVertex> V = graph.vertexSet();
		
		PrintUtil.printGraph(graph, "Graph:");

		YenKShortestPath <DefaultVertex, DefaultEdge> yenk = 
				new YenKShortestPath <> (graph);
		DefaultVertex v1 = VertexEdgeUtil.getVertexfromId(V, "0");
		DefaultVertex v2 = VertexEdgeUtil.getVertexfromId(V, "6");
		System.out.println("Shortest Path: " + yenk.getPaths(v1, v2, 1));
		
		
		YenShortestPathIterator <DefaultVertex, DefaultEdge> yenI = 
				new YenShortestPathIterator <> (graph,v1,v2);
		int count = 0;
		int limit = 10;
		System.out.println(limit + " shortest paths:");
		while ((yenI.hasNext()) && (count < limit)) {
			GraphPath <DefaultVertex, DefaultEdge> yenIpath = yenI.next();
			System.out.println(yenIpath.getVertexList());
			count++;
		}



	}

}
