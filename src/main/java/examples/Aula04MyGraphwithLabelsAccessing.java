// Teoria dos Grafos - UFCG
// Acesso a arestas e vértices em um grafo com labels (rótulos) em vértices e arestas

package examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;

public class Aula04MyGraphwithLabelsAccessing {
	
	public static void main(String[] args) {
		Graph<DefaultVertex, RelationshipEdge> graphgml = new Pseudograph<DefaultVertex, RelationshipEdge>(RelationshipEdge.class);
		graphgml = MyJGraphTUtil.importGraphGML(graphgml,"./src/main/java/graphs/pseudograph-Aula02.gml");
        System.out.println(graphgml.vertexSet());
		System.out.println(graphgml.edgeSet()+"\n");
		
		RelationshipEdge e1 = MyJGraphTUtil.getEdgefromLabel(graphgml.edgeSet(), "x");
		System.out.println("Arestas: " + e1);
		System.out.println("Label: " + e1.getLabel());
		DefaultVertex v1 = (DefaultVertex) e1.getV1();
		System.out.println("Vizinho de " + v1 + ": " + e1.getNeighbor(v1));
		
        DefaultVertex d1 = MyJGraphTUtil.getVertexfromLabel(graphgml.vertexSet(), "a");
        System.out.println("d_G(a) = " + graphgml.degreeOf(d1));
	}
}
