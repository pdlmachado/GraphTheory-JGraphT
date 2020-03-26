// Teoria dos Grafos - UFCG
// Criando um Grafo Simples sem labels (rótulos) nas arestas

package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;
import util.RelationshipEdge;

public class Aula03MyPseudoGraph {
	
	public static void main(String[] args) {
	
		Graph<String,RelationshipEdge> graph = 
				new Pseudograph <String,RelationshipEdge> (RelationshipEdge.class);
	
		graph.addVertex("a");
		graph.addVertex("b");
		graph.addVertex("c");
		graph.addEdge("a","b",new RelationshipEdge("ab1"));
		graph.addEdge("a","b",new RelationshipEdge("ab2"));
		graph.addEdge("a","c",new RelationshipEdge("ac"));
		graph.addEdge("b","b",new RelationshipEdge("bb"));
		
		System.out.println(graph.edgeSet());
		System.out.println(graph.vertexSet());
		

		
	}
}
