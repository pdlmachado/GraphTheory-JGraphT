package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;
import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;

public class Aula12StrongOrientation {
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;

	public static void main(String[] args) {

		strongOrientation("cubo.gml");
		strongOrientation("K5.gml");
		strongOrientation("asym.gml");

	}
	
	public static void strongOrientation (String filename) {
		Graph<DefaultVertex, DefaultEdge> graph = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
		graph = ImportUtil.importDefaultGraphGML(graph, graphpathname + filename);
		PrintUtil.printGraph(graph,"Graph: " + filename);	
		BiconnectivityInspector <DefaultVertex,DefaultEdge> insp =
				new BiconnectivityInspector <> (graph);
		System.out.println("Has strong orientation? " + insp.getBridges().isEmpty() + NL);
	}

}
