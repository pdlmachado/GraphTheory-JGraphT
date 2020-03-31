// Teoria dos Grafos - UFCG
// Importa um pseudografo com ou sem labels (rótulos) nas arestas a partir de um arquivo no formato GML

package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;

import util.DefaultVertex;
import util.ImportUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;

import java.util.Set;

public class Aula03ImportPseudographGML {

	public static void main(String[] args) {
		
		Graph<DefaultVertex, RelationshipEdge> graphgml = 
			new Pseudograph<DefaultVertex, RelationshipEdge>(
					VertexEdgeUtil.createDefaultVertexSupplier(), 
					VertexEdgeUtil.createRelationshipEdgeSupplier(), false);

		ImportUtil.importGraphGML(graphgml,"./src/main/java/graphs/pseudograph-Aula02.gml");

		Set<DefaultVertex> V = graphgml.vertexSet();
		Set<RelationshipEdge> E = graphgml.edgeSet();

		System.out.println("Ordem do Grafo G: " + V.size());
		System.out.println("Tamanho do Grafo G: " + E.size());
		System.out.println("V(G): " + V);
		System.out.println("E(G): " + E);
	}
}
