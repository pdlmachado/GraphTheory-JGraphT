// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.alg.cycle.HierholzerEulerianCycle;
import org.jgrapht.graph.SimpleGraph;

public class Aula09Euler {
	public static void main(String[] args) {

	    SimpleGraph<DefaultVertex,RelationshipEdge> graphgml = new SimpleGraph<>(RelationshipEdge.class);
        MyJGraphTUtil.importGraphGML(graphgml, "./src/main/java/graphs/bp1.gml");
 	    MyJGraphTUtil.printGraph(graphgml,"Graph: ");
   		
    
	    HierholzerEulerianCycle <DefaultVertex,RelationshipEdge> eCGen = new HierholzerEulerianCycle <> ();
	    if (eCGen.isEulerian(graphgml)) {
	    	System.out.println("Euler Cycle: " + eCGen.getEulerianCycle(graphgml));
	    } else System.out.println("Graph is not Eulerian");
	}

}
