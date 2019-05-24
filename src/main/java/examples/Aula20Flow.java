package examples;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.alg.cycle.ChinesePostman;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.io.CSVFormat;

public class Aula20Flow {
	public static void main(String[] args) {

	    SimpleWeightedGraph <String,DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        MyJGraphTUtil.importWeightedGraphCSV(graph, "./src/main/java/graphs/netflow1.csv",CSVFormat.MATRIX,false,true,true);
 	    MyJGraphTUtil.printGraph(graph,"Graph: ");
 	          
        Set <String> X = new HashSet <> ();
        X.add("s");
        X.add("p");
        X.add("r");
        
        System.out.println("X =" + X);
        
        Set <DefaultWeightedEdge> outputedgecut = 
        		graph.edgeSet().stream().filter(e -> X.contains(graph.getEdgeSource(e))&&
        				                             X.contains(graph.getEdgeTarget(e))==false).collect(Collectors.toSet());
        System.out.println("K: " + outputedgecut);
        Optional<Double> fplus = (outputedgecut.stream().map(e -> graph.getEdgeWeight(e))).reduce(Double::sum);
        System.out.println("cap(X) = " + fplus);
   	}
	
}
