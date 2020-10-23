package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.DrawUtil;
import util.ImportUtil;
import util.VertexEdgeUtil;

// A classe GraphTests identifica apenas vértices de corte e considera-os para determinar se o grafo é separável
// Se houverem vértices de separação com aresta loop, estes serão ignorados como no grafo K3withloop.gml.

public class Aula16Separation {
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;

	public static void main(String[] args) {
		isNonseparable("K3.gml");
		isNonseparable("K3withloop.gml");
		isNonseparable("asym.gml");
	}

	public static void isNonseparable (String filename) {
		Graph<DefaultVertex, DefaultEdge> graph = 
				new Pseudograph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
		graph = ImportUtil.importDefaultGraphGML(graph, graphpathname + filename);
		DrawUtil.createAndShowGui(graph, "Graph", false, true, true, true, DrawUtil.layout_type.HIERARCHICAL);
		System.out.println("Is biconnected (non-separable)?  " + GraphTests.isBiconnected(graph) + NL);
		
	}
	
}
