// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.BlockCutpointGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Aula17BlockCutPoint {

	public static void main(String[] args) {
		
		Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
		graph = MyJGraphTUtil.importDefaultGraphGML(graph, "./src/main/java/graphs/dissortativity.gml");
		MyJGraphTUtil.createAndShowGui(graph, "Graph", false, true, true, true, MyJGraphTUtil.layout_type.ORGANIC);
		
		BlockCutpointGraph <String,DefaultEdge> b = new BlockCutpointGraph <> (graph);
		System.out.println("Cut Point (Cut Vertexes): " + b.getCutpoints());
		System.out.println("Blocks: " + b.getBlocks());
		
	}

}
