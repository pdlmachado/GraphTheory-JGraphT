// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.BlockCutpointGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

public class Aula17BlockCutPoint {

	public static void main(String[] args) {
		
		Graph<DefaultVertex, DefaultEdge> graph = 
				new SimpleGraph <> (MyJGraphTUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		graph = MyJGraphTUtil.importDefaultGraphGML(graph, "./src/main/java/graphs/dissortativity.gml");
		MyJGraphTUtil.createAndShowGui(graph, "Graph", false, true, true, true, MyJGraphTUtil.layout_type.ORGANIC);
		
		BlockCutpointGraph <DefaultVertex,DefaultEdge> b = new BlockCutpointGraph <> (graph);
		System.out.println("Cut Point (Cut Vertexes): " + b.getCutpoints());
		System.out.println("Blocks: " + b.getBlocks());
		
	}

}
