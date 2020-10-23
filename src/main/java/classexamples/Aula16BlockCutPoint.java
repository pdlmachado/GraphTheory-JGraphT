// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.BlockCutpointGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.DrawUtil;
import util.ImportUtil;
import util.VertexEdgeUtil;

public class Aula16BlockCutPoint {
	
	// A classe BlockCutpoint identifica apenas vértices de corte e considera-os para calcular os blocos
	// Se houverem vértices de separação com aresta loop, estes serão ignorados como no segundo exemplo abaixo.
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;

	public static void main(String[] args) {
		
		Graph<DefaultVertex, DefaultEdge> graph1 = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
		graph1 = ImportUtil.importDefaultGraphGML(graph1, graphpathname + "dissortativity.gml");
		DrawUtil.createAndShowGui(graph1, "Graph 1", false, true, true, true, DrawUtil.layout_type.ORGANIC);
		BlockCutpointGraph <DefaultVertex,DefaultEdge> b1 = new BlockCutpointGraph <> (graph1);
		System.out.println("Cut Point (Cut Vertexes): " + b1.getCutpoints());
		System.out.println("Blocks: " + b1.getBlocks() + NL);
		
		Graph<DefaultVertex, DefaultEdge> graph2 = 
				new Pseudograph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
		graph2 = ImportUtil.importDefaultGraphGML(graph2, graphpathname + "aula03-pseudo-export.gml");
		DrawUtil.createAndShowGui(graph2, "Graph 2", false, true, true, true, DrawUtil.layout_type.ORGANIC);
		BlockCutpointGraph <DefaultVertex,DefaultEdge> b2 = new BlockCutpointGraph <> (graph2);
		System.out.println("Cut Point (Cut Vertexes): " + b2.getCutpoints());
		System.out.println("Blocks: " + b2.getBlocks() + NL);
		
	}

}
