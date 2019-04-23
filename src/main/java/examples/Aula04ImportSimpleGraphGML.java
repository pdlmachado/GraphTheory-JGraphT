// Teoria dos Grafos - UFCG
// Importa um grafo simples com ou sem labels (rótulos) nas arestas a partir de um arquivo no formato GML

package examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;

public class Aula04ImportSimpleGraphGML {

	public static void main(String[] args) {

		Graph<DefaultVertex, RelationshipEdge> graphgml = new SimpleGraph<>(RelationshipEdge.class);

		graphgml = MyJGraphTUtil.importGraphGML(graphgml, "./src/main/java/graphs/cubo.gml");

		MyJGraphTUtil.printGraph(graphgml);

	}
}
