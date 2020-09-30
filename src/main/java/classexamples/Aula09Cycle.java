// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.PatonCycleBase;
import org.jgrapht.graph.SimpleGraph;

import util.DefaultVertex;
import util.ImportUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;
import util.PrintUtil;

public class Aula09Cycle {
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;

	public static void main(String[] args) {
		checkifCyclicGraph("bp1.gml");
		checkifCyclicGraph("acyclic.gml");
	}
	
	static void checkifCyclicGraph (String filename) {
	    Graph<DefaultVertex, RelationshipEdge> g = 
	    		new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), VertexEdgeUtil.createRelationshipEdgeSupplier(),false);
        ImportUtil.importGraphGML(g, graphpathname + filename);
 	    PrintUtil.printGraph(g, filename + ": ");
        PatonCycleBase <DefaultVertex,RelationshipEdge> p = 
        		new PatonCycleBase <> (g);
 	    if (p.getCycleBasis().getCyclesAsGraphPaths().isEmpty()) {
 	    	System.out.println(filename + " is acyclic");
 	    } else {
 	    	System.out.println(filename + " is cyclic." + NL + 
 	    			           "It has the following Simple Cycles: " + NL + 
 	    			           p.getCycleBasis().getCyclesAsGraphPaths() + NL);
 	    }
	}
}
