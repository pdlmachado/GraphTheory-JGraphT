// Teoria dos Grafos - UFCG
// Acesso a arestas e vértices em um grafo sem labels (rótulos) em vértices e arestas

package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Aula04GetVertexEdgeWithOutAttributes {

	public static void main(String[] args) {
		Graph<Integer,DefaultEdge> graph = 
				new SimpleGraph <Integer,DefaultEdge> (DefaultEdge.class);
	
		graph.addVertex(0);
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addEdge(0,1);
		graph.addEdge(1,2);
		
		System.out.println("d_G(0) = " + graph.degreeOf(0));
		
		// getEdge retorna referência para o objeto aresta criado
		DefaultEdge e = graph.getEdge(0, 1);
		System.out.println("Edge: " + e);
		System.out.println("Source: " + graph.getEdgeSource(e));
        System.out.println("Target: " + graph.getEdgeTarget(e)); 
	}
}
