package examples;

import java.util.HashSet;
import java.util.Set;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;


public class Aula05ImportDefaultDirectedGraph {
	public static void main(String[] args) {

	    DefaultDirectedGraph<DefaultVertex,RelationshipDirectedEdge> g = 
	    		new DefaultDirectedGraph<>(RelationshipDirectedEdge.class);
        MyJGraphTUtil.importDirectedGraphGML(g, "./src/main/java/graphs/grid.gml");    		
 	    
	    Set <DefaultVertex> V = new HashSet <DefaultVertex>(g.vertexSet());
	    Set <DefaultEdge> E = new HashSet <DefaultEdge>(g.edgeSet());
    	System.out.println("V(G): " + V.toString());
	    System.out.println("E(G): " + E.toString());
	    
        DefaultVertex v = MyJGraphTUtil.getVertexfromLabel(V,"1");
        System.out.println("Grau de Entrada de v: " + g.inDegreeOf(v));
        System.out.println("Grau de Saída de v: " + g.outDegreeOf(v));
        System.out.println("Arcos onde v é cabeça: " + g.incomingEdgesOf(v));
        System.out.println("Arcos onde v é cauda: " + g.outgoingEdgesOf(v));
        System.out.println("Vizinhos de entrada de v:" + Graphs.predecessorListOf(g,v));
        System.out.println("Vizinhos de saída de v:" + Graphs.successorListOf(g,v));
	}
}

