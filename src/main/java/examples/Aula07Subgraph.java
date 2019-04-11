// Teoria dos Grafos - UFCG

package examples;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.graph.AsSubgraph;
import org.jgrapht.graph.SimpleGraph;

public class Aula07Subgraph {

	public static void main(String[] args) {

		SimpleGraph <DefaultVertex,RelationshipEdge> graph = new SimpleGraph <> (RelationshipEdge.class);
		MyJGraphTUtil.importGraphGML(graph, "./src/main/java/graphs/asym.gml");
		
		Set <DefaultVertex> V = graph.vertexSet();
		Set <RelationshipEdge> E = graph.edgeSet();
		
		// INDUCED SUBGRAPH
        Collection <String> listOfVertexestoRemove = Arrays.asList("b","f");
		Set <DefaultVertex> S = V.stream().filter(v -> listOfVertexestoRemove.contains(v.getLabel())==false).collect(Collectors.toSet());
		AsSubgraph <DefaultVertex,RelationshipEdge> inducedSubgraph = new AsSubgraph <> (graph,S);
		
		//SPANNING (Gerador) SUBGRAPH
        Set <RelationshipEdge> listofEdgestoRemove = new HashSet <> ();
        listofEdgestoRemove.add(MyJGraphTUtil.getEdgefromVertexLabels(E,V,"b","e"));
		Set <RelationshipEdge> R = E.stream().filter(e -> listofEdgestoRemove.contains(e)==false).collect(Collectors.toSet());
		
		AsSubgraph <DefaultVertex,RelationshipEdge> spanningSubgraph = new AsSubgraph <> (graph,V,R);
		MyJGraphTUtil.printGraph(graph,"Original Graph: ");
		MyJGraphTUtil.printGraph(inducedSubgraph, "Induced Subgraph without vertexes " + listOfVertexestoRemove + ": ");	
		MyJGraphTUtil.printGraph(spanningSubgraph, "Spanning Subgraph without edges " + listofEdgestoRemove + ": ");
	}

}
