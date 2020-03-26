// Teoria dos Grafos - UFCG
// Criando um Grafo Simples sem labels (rótulos) nas arestas

package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Aula03MyFirstGraph {
	
	public static void main(String[] args) {
	
		Graph<String,DefaultEdge> graph = 
				new SimpleGraph <String,DefaultEdge> (DefaultEdge.class);
	
		graph.addVertex("a");
		graph.addVertex("b");
		graph.addEdge("a","b");
		
		System.out.println(graph.edgeSet());
		System.out.println(graph.vertexSet());
		
		System.out.println(graph.degreeOf("a"));
		System.out.println(graph.edgesOf("a"));
		
	}
}
