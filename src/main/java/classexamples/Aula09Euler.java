// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.alg.cycle.HierholzerEulerianCycle;
import org.jgrapht.graph.SimpleGraph;

import util.DefaultVertex;
import util.ImportUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;
import util.PrintUtil;

public class Aula09Euler {
	public static void main(String[] args) {

	    SimpleGraph<DefaultVertex,RelationshipEdge> graphgml = 
				new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);
        ImportUtil.importGraphGML(graphgml, "./src/main/java/graphs/bp1.gml");
 	    PrintUtil.printGraph(graphgml,"Graph: ");
   		
    
	    HierholzerEulerianCycle <DefaultVertex,RelationshipEdge> eCGen = new HierholzerEulerianCycle <> ();
	    if (eCGen.isEulerian(graphgml)) {
	    	System.out.println("Euler Cycle: " + eCGen.getEulerianCycle(graphgml));
	    } else System.out.println("Graph is not Eulerian");
	}

}
