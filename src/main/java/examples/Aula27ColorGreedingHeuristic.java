// Teoria dos Grafos - UFCG

package examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.color.LargestDegreeFirstColoring;
import org.jgrapht.alg.color.RandomGreedyColoring;
import org.jgrapht.alg.color.SaturationDegreeColoring;
import org.jgrapht.alg.color.SmallestDegreeLastColoring;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;


public class Aula27ColorGreedingHeuristic {
	
	
	// Greedy Colouring Heuristic (from Class 27)
	
	public static void main(String[] args) {
	    Graph<String, DefaultEdge> graphgml = new SimpleGraph<>(RelationshipEdge.class);
        MyJGraphTUtil.importDefaultGraphGML(graphgml,"./src/main/java/graphs/cordal.gml");
        
        MyJGraphTUtil.printGraph(graphgml);
        HashMap <String, Integer> coloring = new HashMap <> ();
   
        // Manual Ordering
        graphgml.vertexSet().forEach(v -> coloring.put(v, 0));
        List <String> manuallistV = new ArrayList <> ();
        manuallistV.add("7");
        manuallistV.add("0");
        manuallistV.add("1");
        manuallistV.add("4");
        manuallistV.add("3");
        manuallistV.add("2");
        manuallistV.add("6");
        manuallistV.add("8");
        manuallistV.add("9");
        manuallistV.add("10");
        manuallistV.add("5");        

        manuallistV.forEach(v -> coloring.put(v, getColor(graphgml,coloring,v)));
        System.out.println("Manual: ");
        System.out.println(manuallistV);
        manuallistV.forEach(v -> System.out.print(v + "->" + coloring.get(v) + " "));
        
        // Alphabetical Ordering 
        graphgml.vertexSet().forEach(v -> coloring.put(v, 0));
        List <String> alphalistV = new ArrayList <> (graphgml.vertexSet());
        Collections.sort(alphalistV);
        alphalistV.forEach(v -> coloring.put(v, getColor(graphgml,coloring,v)));
        System.out.println("\nAlphabetical: ");
        System.out.println(alphalistV);
        alphalistV.forEach(v -> System.out.print(v + "->" + coloring.get(v)+ " "));
  	}
	
    public static Integer getColor (Graph<String, DefaultEdge> g, Map<String,Integer> m, String v) { 
    	List<Integer> nColors = new ArrayList<Integer>();
    	Integer color = 1;
    	for (String s : Graphs.neighborSetOf(g, v)) {
    		nColors.add(m.get(s));
    	}
    	boolean match = false;
    	int i = 1;
    	while (match==false) {
    		if (nColors.contains(i)==false) {
    			color = i;
    			match = true;
    		}
    		i++;
    	}
    	return color;
    }
	
}

