// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.graph.*;
import org.jgrapht.io.*;

import java.util.*;

public class MyPseudograph {
	// Grafo comb arestas paralelas e loops. Arestas possuem label.

	public static void main(String[] args) {
		// Import Pseudograph
		VertexProvider<DefaultVertex> vp1 = (label, attributes) -> new DefaultVertex(label, attributes);
		EdgeProvider<DefaultVertex, RelationshipEdge> ep1 = (from, to, label, attributes) -> new RelationshipEdge(from,
				to, attributes);
		GmlImporter<DefaultVertex, RelationshipEdge> gmlImporter = new GmlImporter<>(vp1, ep1);
		Pseudograph<DefaultVertex, RelationshipEdge> graphgml = new Pseudograph<>(RelationshipEdge.class);
		try {
			gmlImporter.importGraph(graphgml, ImportGraph.readFile("./src/graphs/pseudograph-Aula02.gml"));
		} catch (ImportException e) {
			throw new RuntimeException(e);
		}

		Set<DefaultVertex> V = new HashSet<DefaultVertex>(graphgml.vertexSet());
		Set<RelationshipEdge> E = new HashSet<RelationshipEdge>(graphgml.edgeSet());

		System.out.println("Ordem do Grafo G: " + V.size());
		System.out.println("Tamanho do Grafo G: " + E.size());
		System.out.println("V(G): " + V);
		System.out.println("E(G): " + E);

		System.out.println("\nGrau (d_G) e Vizinhos (N_G) de cada vertice:");
		Iterator<DefaultVertex> it = V.iterator();
		while (it.hasNext()) {
			DefaultVertex v = it.next();
			System.out.println("d_G(" + v.toString() + ") = " + graphgml.degreeOf(v));
			String N = new String("N_G(" + v.toString() + ") = ");
			Iterator<RelationshipEdge> it2 = (graphgml.edgesOf(v)).iterator();
			while (it2.hasNext()) {
				RelationshipEdge e = it2.next();
				N = N + " " + ((e).getNeighbour(v)).toString();
				System.out.println((e.getV1()).toString() + (e.getV2()).toString());
			}
			System.out.println(N + "\n");
		}

	}

}
