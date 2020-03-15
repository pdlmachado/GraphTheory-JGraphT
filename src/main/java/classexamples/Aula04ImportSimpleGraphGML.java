// Teoria dos Grafos - UFCG
// Importa um grafo simples com ou sem labels (rótulos) nas arestas a partir de um arquivo no formato GML

package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;

public class Aula04ImportSimpleGraphGML {

	public static void main(String[] args) {

		Graph<DefaultVertex, DefaultEdge> graphgml = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						            SupplierUtil.createDefaultEdgeSupplier(), false);
		
		graphgml = ImportUtil.importDefaultGraphGML(graphgml, "./src/main/java/graphs/cubo.gml");

		PrintUtil.printGraph(graphgml);

	}
}
