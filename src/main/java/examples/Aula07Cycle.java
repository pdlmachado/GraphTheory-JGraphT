// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.alg.cycle.PatonCycleBase;
import org.jgrapht.graph.SimpleGraph;

public class Aula07Cycle {
	public static void main(String[] args) {

	    SimpleGraph<DefaultVertex,RelationshipEdge> g1 = new SimpleGraph <>(RelationshipEdge.class);
        MyJGraphTUtil.importGraphGML(g1, "./src/main/java/graphs/bp1.gml");
 	    MyJGraphTUtil.printGraph(g1,"G1: ");
        PatonCycleBase <DefaultVertex,RelationshipEdge> p = 
        		new PatonCycleBase <> (g1);
 	    if (p.getCycleBasis().getCyclesAsGraphPaths().isEmpty()) {
 	    	System.out.println("G1 is acyclic");
 	    } else {
 	    	System.out.println("G1 has the following Simple Cycles: " + p.getCycleBasis().getCyclesAsGraphPaths() + "\n");
 	    }
        
	    SimpleGraph<DefaultVertex,RelationshipEdge> g2 = new SimpleGraph <>(RelationshipEdge.class);
        MyJGraphTUtil.importGraphGML(g2, "./src/main/java/graphs/acyclic.gml");
 	    MyJGraphTUtil.printGraph(g2,"G2: "); 
 	    p = new PatonCycleBase <> (g2);
 	    if (p.getCycleBasis().getCyclesAsGraphPaths().isEmpty()) {
 	    	System.out.println("G2 is acyclic");
 	    } else {
 	    	System.out.println("G2 has the following Simple Cycles: " + p.getCycleBasis().getCyclesAsGraphPaths());
 	    }
        
        
        
	}

}
