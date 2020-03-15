// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.BlockCutpointGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;

public class Aula17BlockCutPoint {

	public static void main(String[] args) {
		
		Graph<DefaultVertex, DefaultEdge> graph = 
				new SimpleGraph <> (ImportUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		graph = ImportUtil.importDefaultGraphGML(graph, "./src/main/java/graphs/dissortativity.gml");
		ImportUtil.createAndShowGui(graph, "Graph", false, true, true, true, JGraphTUtil.ImportUtil.ORGANIC);
		
		BlockCutpointGraph <DefaultVertex,DefaultEdge> b = new BlockCutpointGraph <> (graph);
		System.out.println("Cut Point (Cut Vertexes): " + b.getCutpoints());
		System.out.println("Blocks: " + b.getBlocks());
		
	}

}
