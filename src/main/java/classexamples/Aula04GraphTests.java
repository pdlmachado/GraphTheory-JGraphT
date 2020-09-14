// Teoria dos Grafos - UFCG
// Cria um Pseudografo, utiliza a classe GraphTests (teste de propriedades de um grafo) 
// e a classe MyJGraphUtil (recuperação da referência a um vértice / aresta)
package classexamples;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.graph.Pseudograph;

import util.VertexEdgeUtil;
import util.RelationshipEdge;

public class Aula04GraphTests {
	
	public static void main(String[] args) {
		Graph<String,RelationshipEdge> graph = 
				new Pseudograph <String,RelationshipEdge> (RelationshipEdge.class);
		Set <RelationshipEdge> eSet;

		graph.addVertex("a");
		graph.addVertex("b");
		graph.addVertex("c"); 
		graph.addEdge("a","b",new RelationshipEdge("ab1"));
		graph.addEdge("a","b",new RelationshipEdge("ab2"));
		graph.addEdge("a","c",new RelationshipEdge("ac"));		
		graph.addEdge("b","b",new RelationshipEdge("bb"));
		eSet = graph.edgeSet();
		System.out.println("Graph edges: " + eSet);
		
		System.out.println("has multiple edges? " + GraphTests.hasMultipleEdges(graph));
		System.out.println("has self loops? " + GraphTests.hasSelfLoops(graph));
		System.out.println("is connected? " + GraphTests.isConnected(graph));
		System.out.println("is bipartite? " + GraphTests.isBipartite(graph));
		System.out.println("is complete? " + GraphTests.isComplete(graph));
		
        graph.removeEdge(VertexEdgeUtil.getEdgefromLabel(eSet,"ab1"));
        graph.removeEdge(VertexEdgeUtil.getEdgefromLabel(eSet,"bb"));
        
        System.out.println("After removing edges ab1 and bb ... ");
		System.out.println("has multiple edges? " + GraphTests.hasMultipleEdges(graph));
		System.out.println("has self loops? " + GraphTests.hasSelfLoops(graph));
		System.out.println("is connected? " + GraphTests.isConnected(graph));
		System.out.println("is bipartite? " + GraphTests.isBipartite(graph));
		System.out.println("is complete? " + GraphTests.isComplete(graph));
		System.out.println("is cubic?" + GraphTests.isCubic(graph));
	}	
}
