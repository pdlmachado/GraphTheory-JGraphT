// Teoria dos Grafos - UFCG
// Mostra como iterar sobre o conjunto de vértices de um grafo (sobre o conjunto de arestas é similar)

package classexamples;

import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.alg.util.NeighborCache;
import org.jgrapht.graph.Pseudograph;

import util.DefaultVertex;
import util.ImportUtil;
import util.VertexEdgeUtil;
import util.RelationshipEdge;

public class Aula04GraphIteration {
	
	public static void main(String[] args) {
		Graph<DefaultVertex, RelationshipEdge> graph = 
				new Pseudograph<DefaultVertex, RelationshipEdge>(
						VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);
		ImportUtil.importGraphGML(graph,"./src/main/java/graphs/pseudograph-Aula02.gml");
		NeighborCache <DefaultVertex,RelationshipEdge> nc = new NeighborCache <DefaultVertex,RelationshipEdge> (graph);

		// Usando Iterators
		System.out.println("\nGrau (d_G) e Vizinhos (N_G) de cada vertice (com Iterators):");
		Iterator<DefaultVertex> it = graph.vertexSet().iterator();
		while (it.hasNext()) {
	    	DefaultVertex v = it.next();
	    	System.out.println("d_G(" + v.toString() + ") = " + graph.degreeOf(v));
	    	System.out.println("N_G(" + v.toString() + ") = " + nc.neighborsOf(v));
	    }
	
	    // Usando expressões lambda
		System.out.println("\nGrau (d_G) e Vizinhos (N_G) de cada vertice (com Lambda):");
		graph.vertexSet().stream().forEach(v -> {
	    	System.out.println("d_G(" + v.toString() + ") = " + graph.degreeOf(v));
	    	System.out.println("N_G(" + v.toString() + ") = " + nc.neighborsOf(v));
	    });
	}
}

