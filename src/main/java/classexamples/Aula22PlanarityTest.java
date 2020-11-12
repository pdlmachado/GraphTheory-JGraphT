// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.alg.planar.BoyerMyrvoldPlanarityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;

public class Aula22PlanarityTest {
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {
		printPlanarityTest("graph-layout.gml");
		printPlanarityTest("asym.gml");
		printPlanarityTest("K5.gml");
		printPlanarityTest("planar40.gml");
		printPlanarityTest("nonplanar2.gml");
	}

	public static void printPlanarityTest(String filename) {
	    Graph<DefaultVertex, DefaultEdge> g = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
	    ImportUtil.importDefaultGraphGML(g, graphpathname + filename);
	    System.out.println("--------------------------------------");
	    PrintUtil.printGraph(g, "Graph: " + filename);
	     
	    BoyerMyrvoldPlanarityInspector <DefaultVertex, DefaultEdge> bp = new BoyerMyrvoldPlanarityInspector<DefaultVertex, DefaultEdge> (g);
	    if (bp.isPlanar()) {
	    	System.out.println("Graph is planar" + NL);
	    } else {
	    	System.out.println("Graph is not planar");
	    	Graph <DefaultVertex, DefaultEdge> k = bp.getKuratowskiSubdivision();
	    	System.out.println("Kuratowski subgraph: " + k + NL);
	    }
	}	    
}
