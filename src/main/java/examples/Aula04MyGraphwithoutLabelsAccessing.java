// Teoria dos Grafos - UFCG
// Acesso a arestas e vértices em um grafo sem labels (rótulos) em vértices e arestas

package examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

public class Aula04MyGraphwithoutLabelsAccessing {

	public static void main(String[] args) {
		Graph<DefaultVertex, DefaultEdge> graphgml = 
				new SimpleGraph <> (MyJGraphTUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		graphgml = MyJGraphTUtil.importDefaultGraphGML(graphgml, "./src/main/java/graphs/cubo.gml");
        System.out.println(graphgml.vertexSet());
		System.out.println(graphgml.edgeSet()+"\n");
		
		DefaultVertex a = MyJGraphTUtil.getVertexfromLabel(graphgml.vertexSet(),"a");
		DefaultVertex b = MyJGraphTUtil.getVertexfromLabel(graphgml.vertexSet(), "b");
        System.out.println("d_G(a) = " + graphgml.degreeOf(a));
		System.out.println("Aresta: " + graphgml.getEdge(a, b));
		System.out.println("Aresta: " + graphgml.getEdge(b, a));
		
		DefaultEdge e = graphgml.getEdge(b, a);
		System.out.println("Source: " + graphgml.getEdgeSource(e));
        System.out.println("Target: " + graphgml.getEdgeTarget(e));

	}
}
