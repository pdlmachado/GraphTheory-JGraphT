// Teoria dos Grafos - UFCG
// Mostra como iterar sobre o conjunto de vértices de um grafo (sobre o conjunto de arestas é similar)

package examples;

import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.alg.util.NeighborCache;
import org.jgrapht.graph.Pseudograph;

public class Aula04MyGraphIteration {
	
	public static void main(String[] args) {
		Graph<DefaultVertex, RelationshipEdge> graphgml = new Pseudograph<DefaultVertex, RelationshipEdge>(RelationshipEdge.class);
		graphgml = MyJGraphTUtil.importGraphGML(graphgml,"./src/main/java/graphs/pseudograph-Aula02.gml");
		NeighborCache <DefaultVertex,RelationshipEdge> nc = new NeighborCache <DefaultVertex,RelationshipEdge> (graphgml);

		// Usando Iterators
        System.out.println("\nGrau (d_G) e Vizinhos (N_G) de cada vertice (com Iterators):");
	    Iterator<DefaultVertex> it = graphgml.vertexSet().iterator();
	    while (it.hasNext()) {
	    	DefaultVertex v = it.next();
	    	System.out.println("d_G(" + v.toString() + ") = " + graphgml.degreeOf(v));
	    	System.out.println("N_G(" + v.toString() + ") = " + nc.neighborsOf(v));
	    }
	
	    // Usando expressões lambda
	    System.out.println("\nGrau (d_G) e Vizinhos (N_G) de cada vertice (com Lambda):");
	    final Graph<DefaultVertex, RelationshipEdge> g = graphgml;
	    g.vertexSet().stream().forEach(v -> {
	    	System.out.println("d_G(" + v.toString() + ") = " + g.degreeOf(v));
	    	System.out.println("N_G(" + v.toString() + ") = " + nc.neighborsOf(v));
	    });
	}
}
