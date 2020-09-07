// Teoria dos Grafos - UFCG
// Criando um Grafo Simples sem labels (rótulos) nas arestas

package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ExportUtil;
import util.VertexEdgeUtil;

public class Aula03ExportGML {
	
	public static void main(String[] args) {
	
		Graph<DefaultVertex,DefaultEdge> graph = 
				new SimpleGraph <DefaultVertex,DefaultEdge> 
		              (VertexEdgeUtil.createDefaultVertexSupplier(),SupplierUtil.DEFAULT_EDGE_SUPPLIER,false);
		
		DefaultVertex a = new DefaultVertex("a");
		DefaultVertex b = new DefaultVertex("b");
		DefaultVertex c = new DefaultVertex("c");
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addEdge(a,b);
		graph.addEdge(a,c);
		graph.addEdge(b,c);
		
		ExportUtil.exportDefaultGML(graph,"./src/main/java/graphs/aula03-simple-export.gml");
		
	}
}
