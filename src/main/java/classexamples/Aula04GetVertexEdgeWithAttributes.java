// Teoria dos Grafos - UFCG
// Acesso a arestas e vértices em um grafo com labels (rótulos) em vértices e arestas

package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;

public class Aula04GetVertexEdgeWithAttributes {
	
	public static void main(String[] args) {
		Graph<DefaultVertex, RelationshipEdge> graph = 
				new Pseudograph<DefaultVertex, RelationshipEdge>(
						VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);
		ImportUtil.importGraphGML(graph,"./src/main/java/graphs/pseudograph-Aula02.gml");
        PrintUtil.printGraph(graph);
		
        // getVertexfromLabel retorna referência para o objeto vértice criado
        DefaultVertex a = VertexEdgeUtil.getVertexfromLabel(graph.vertexSet(),"a");
		DefaultVertex b = VertexEdgeUtil.getVertexfromLabel(graph.vertexSet(), "b");
        System.out.println("d_G(a) = " + graph.degreeOf(a));
		System.out.println("Aresta: " + graph.getEdge(a, b));
		System.out.println("Aresta: " + graph.getEdge(b, a) + "\n");
		
		// getEdgefromLabel retorna referência para o objeto aresta criado
		RelationshipEdge e1 = VertexEdgeUtil.getEdgefromLabel(graph.edgeSet(), "x");
		System.out.println("Aresta: " + e1);
		System.out.println("Label: " + e1.getLabel());
		System.out.println("Terminais da aresta: " + e1.getV1() + "," + e1.getV2());
		DefaultVertex v1 = graph.getEdgeSource(e1);
		System.out.println("Vizinho de " + v1 + ": " + e1.getNeighbor(v1));
	}
}

