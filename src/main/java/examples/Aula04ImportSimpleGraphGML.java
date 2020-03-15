// Teoria dos Grafos - UFCG
// Importa um grafo simples com ou sem labels (rótulos) nas arestas a partir de um arquivo no formato GML

package examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

public class Aula04ImportSimpleGraphGML {

	public static void main(String[] args) {

		Graph<DefaultVertex, DefaultEdge> graphgml = 
				new SimpleGraph <> (MyJGraphTUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		
		graphgml = MyJGraphTUtil.importDefaultGraphGML(graphgml, "./src/main/java/graphs/cubo.gml");

		MyJGraphTUtil.printGraph(graphgml);

	}
}
