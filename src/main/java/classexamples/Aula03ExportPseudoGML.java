// Teoria dos Grafos - UFCG
// Criando um Grafo Simples sem labels (rótulos) nas arestas

package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;

import util.DefaultVertex;
import util.ExportUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;

public class Aula03ExportPseudoGML {
	
	public static void main(String[] args) {
	
		Graph<DefaultVertex,RelationshipEdge> graph = 
				new Pseudograph <DefaultVertex,RelationshipEdge> 
		              (VertexEdgeUtil.createDefaultVertexSupplier(),VertexEdgeUtil.createRelationshipEdgeSupplier(),false);
		
		DefaultVertex a = new DefaultVertex("a");
		DefaultVertex b = new DefaultVertex("b");
		DefaultVertex c = new DefaultVertex("c");
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addEdge(a,b,new RelationshipEdge("1"));
		graph.addEdge(a,c,new RelationshipEdge("2"));
		graph.addEdge(b,b,new RelationshipEdge("3"));
		graph.addEdge(b,c,new RelationshipEdge("4"));
		graph.addEdge(b,c,new RelationshipEdge("5"));
		
		ExportUtil.exportGML(graph,"./src/main/java/graphs/aula03-pseudo-export.gml");
		
	}
}
