// Teoria dos Grafos - UFCG
// Importa um pseudografo com ou sem labels (rótulos) nas arestas a partir de um arquivo no formato GML

package examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;
import java.util.Set;

public class Aula04ImportPseudographGML {

	public static void main(String[] args) {
		
		Graph<DefaultVertex, RelationshipEdge> graphgml = new Pseudograph<DefaultVertex, RelationshipEdge>(RelationshipEdge.class);
		graphgml = MyJGraphTUtil.importGraphGML(graphgml,"./src/main/java/graphs/pseudograph-Aula02.gml");

		Set<DefaultVertex> V = graphgml.vertexSet();
		Set<RelationshipEdge> E = graphgml.edgeSet();

		System.out.println("Ordem do Grafo G: " + V.size());
		System.out.println("Tamanho do Grafo G: " + E.size());
		System.out.println("V(G): " + V);
		System.out.println("E(G): " + E);
	}
}
