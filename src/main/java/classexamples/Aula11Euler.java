// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.alg.cycle.HierholzerEulerianCycle;
import org.jgrapht.graph.Multigraph;

import util.DefaultVertex;
import util.ImportUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;
import util.PrintUtil;

public class Aula11Euler {

	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {
		circuitoEuler("bp1.gml");
		circuitoEuler("sevenbridges.gml");
	}
	
	public static void circuitoEuler (String filename) {
	    Multigraph<DefaultVertex,RelationshipEdge> graphgml = 
				new Multigraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);
        ImportUtil.importGraphGML(graphgml, graphpathname + filename);
 	    PrintUtil.printGraph(graphgml,"Graph " + filename);
 	    
	    HierholzerEulerianCycle <DefaultVertex,RelationshipEdge> eCGen = new HierholzerEulerianCycle <> ();
	    if (eCGen.isEulerian(graphgml)) {
	    	System.out.println("Euler Cycle (vertices): " + eCGen.getEulerianCycle(graphgml).getVertexList());
	    	System.out.println("Euler Cycle (edges): " + eCGen.getEulerianCycle(graphgml).getEdgeList() + NL);
	    } else System.out.println("Graph is not Eulerian" + NL);
	}

}
