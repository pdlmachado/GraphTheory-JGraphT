// Teoria dos Grafos - UFCG

package examples;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.util.SupplierUtil;

public class Aula20Flow {
	public static void main(String[] args) {

	    SimpleWeightedGraph <DefaultVertex,DefaultWeightedEdge> graph = 
				new SimpleWeightedGraph<DefaultVertex, DefaultWeightedEdge>(
						MyJGraphTUtil.createDefaultVertexSupplier(),
						SupplierUtil.createDefaultWeightedEdgeSupplier());
        MyJGraphTUtil.importWeightedGraphCSV(graph, "./src/main/java/graphs/netflow1.csv",CSVFormat.MATRIX,false,true,true);
 	    MyJGraphTUtil.printGraph(graph,"Graph: ");
 	          
        Set <DefaultVertex> X = new HashSet <> ();
        X.add(MyJGraphTUtil.getVertexfromLabel(graph.vertexSet(), "s"));
        X.add(MyJGraphTUtil.getVertexfromLabel(graph.vertexSet(), "p"));
        X.add(MyJGraphTUtil.getVertexfromLabel(graph.vertexSet(), "r"));
        
        System.out.println("X =" + X);
        
        Set <DefaultWeightedEdge> outputedgecut = 
        		graph.edgeSet().stream().filter(e -> X.contains(graph.getEdgeSource(e))&&
        				                             X.contains(graph.getEdgeTarget(e))==false).collect(Collectors.toSet());
        System.out.println("K: " + outputedgecut);
        Optional<Double> fplus = (outputedgecut.stream().map(e -> graph.getEdgeWeight(e))).reduce(Double::sum);
        System.out.println("cap(X) = " + fplus);
   	}
	
}
