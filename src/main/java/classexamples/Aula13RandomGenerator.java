// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.PrintUtil;
import util.VertexEdgeUtil;
import util.DefaultVertex;
import util.DrawUtil;
import util.ExportUtil;

public class Aula13RandomGenerator {

	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {
		GnmRandomGraphGenerator <DefaultVertex,DefaultEdge> genGraph = 
				new GnmRandomGraphGenerator <> (10,20);
		SimpleGraph <DefaultVertex,DefaultEdge> graph = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
		                            SupplierUtil.createDefaultEdgeSupplier(),false);
		genGraph.generateGraph(graph);
		PrintUtil.printGraph(graph);
		
		DrawUtil.createAndShowGui(graph,"ORGANIC",
				                       false,false,true,true,
				                       DrawUtil.layout_type.ORGANIC);
		
		DrawUtil.createAndShowGui(graph,"HIERARQUICAL",
                						false,false,true,true,
                						DrawUtil.layout_type.HIERARCHICAL);
		
		DrawUtil.createAndShowGui(graph,"CIRCLE",
                						false,false,true,true,
                						DrawUtil.layout_type.CIRCLE);
		
		ExportUtil.exportDefaultGML(graph, graphpathname + "aula13.gml");
	}
}
