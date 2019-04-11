// Teoria dos Grafos - UFCG

package examples;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.jgrapht.graph.SimpleGraph;

public class Aula08EdgeCut {
	public static void main(String[] args) {

		SimpleGraph <DefaultVertex,RelationshipEdge> g = new SimpleGraph <> (RelationshipEdge.class);
		MyJGraphTUtil.importGraphGML(g, "./src/main/java/graphs/asym.gml");

		Set <DefaultVertex> V = g.vertexSet();
		// Constructing the X set
        Collection <String> listOfXVertexesLabels = Arrays.asList("b","f");
		Set <DefaultVertex> X = new HashSet <> ();
        listOfXVertexesLabels.stream().forEach(v -> X.add(MyJGraphTUtil.getVertexfromLabel(V, v)));
		// Constructing the Y set; Y = V / X
        Set <DefaultVertex> Y = new HashSet <> (V);
        Y.removeAll(X);
        
        System.out.println("X = " + X);
        System.out.println("Y = " + Y);
        
        Set <RelationshipEdge> edgeCut = 
             g.edgeSet().stream().filter(e -> (X.contains(e.getV1()) && Y.contains(e.getV2())) || 
            		                          (X.contains(e.getV2()) && Y.contains(e.getV1())))
             .collect(Collectors.toSet());
        System.out.println("EdgeCut(X) = " + edgeCut);
 	}
	
}
