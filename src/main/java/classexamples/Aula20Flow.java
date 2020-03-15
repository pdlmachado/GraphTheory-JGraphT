// Teoria dos Grafos - UFCG

package classexamples;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;

public class Aula20Flow {
	public static void main(String[] args) {

	    SimpleWeightedGraph <DefaultVertex,DefaultWeightedEdge> graph = 
				new SimpleWeightedGraph<DefaultVertex, DefaultWeightedEdge>(
						ImportUtil.createDefaultVertexSupplier(),
						SupplierUtil.createDefaultWeightedEdgeSupplier());
        ImportUtil.importWeightedGraphCSV(graph, "./src/main/java/graphs/netflow1.csv",CSVFormat.MATRIX,false,true,true);
 	    ImportUtil.printGraph(graph,"Graph: ");
 	          
        Set <DefaultVertex> X = new HashSet <> ();
        X.add(ImportUtil.getVertexfromLabel(graph.vertexSet(), "s"));
        X.add(ImportUtil.getVertexfromLabel(graph.vertexSet(), "p"));
        X.add(ImportUtil.getVertexfromLabel(graph.vertexSet(), "r"));
        
        System.out.println("X =" + X);
        
        Set <DefaultWeightedEdge> outputedgecut = 
        		graph.edgeSet().stream().filter(e -> X.contains(graph.getEdgeSource(e))&&
        				                             X.contains(graph.getEdgeTarget(e))==false).collect(Collectors.toSet());
        System.out.println("K: " + outputedgecut);
        Optional<Double> fplus = (outputedgecut.stream().map(e -> graph.getEdgeWeight(e))).reduce(Double::sum);
        System.out.println("cap(X) = " + fplus);
   	}
	
}
