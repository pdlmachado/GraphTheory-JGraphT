// Teoria dos Grafos - UFCG

package classexamples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;


public class Aula27ColorGreedingHeuristic {
	
	
	// Greedy Colouring Heuristic (from Class 27)
	
	public static void main(String[] args) {
	    Graph<DefaultVertex, DefaultEdge> graphgml = 
				new SimpleGraph <> (ImportUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
        ImportUtil.importDefaultGraphGML(graphgml,"./src/main/java/graphs/cordal.gml");
        
        ImportUtil.printGraph(graphgml);
        HashMap <DefaultVertex, Integer> coloring = new HashMap <> ();
   
        // Manual Ordering
        graphgml.vertexSet().forEach(v -> coloring.put(v, 0));
        List <DefaultVertex> manuallistV = new ArrayList <> ();
        manuallistV.add(ImportUtil.getVertexfromLabel(graphgml.vertexSet(),"7"));
        manuallistV.add(ImportUtil.getVertexfromLabel(graphgml.vertexSet(),"0"));
        manuallistV.add(ImportUtil.getVertexfromLabel(graphgml.vertexSet(),"1"));
        manuallistV.add(ImportUtil.getVertexfromLabel(graphgml.vertexSet(),"4"));
        manuallistV.add(ImportUtil.getVertexfromLabel(graphgml.vertexSet(),"3"));
        manuallistV.add(ImportUtil.getVertexfromLabel(graphgml.vertexSet(),"2"));
        manuallistV.add(ImportUtil.getVertexfromLabel(graphgml.vertexSet(),"6"));
        manuallistV.add(ImportUtil.getVertexfromLabel(graphgml.vertexSet(),"8"));
        manuallistV.add(ImportUtil.getVertexfromLabel(graphgml.vertexSet(),"9"));
        manuallistV.add(ImportUtil.getVertexfromLabel(graphgml.vertexSet(),"10"));
        manuallistV.add(ImportUtil.getVertexfromLabel(graphgml.vertexSet(),"5"));        

        manuallistV.forEach(v -> coloring.put(v, getColor(graphgml,coloring,v)));
        System.out.println("Manual: ");
        System.out.println(manuallistV);
        manuallistV.forEach(v -> System.out.print(v + "->" + coloring.get(v) + " "));
        
        // Alphabetical Ordering 
        graphgml.vertexSet().forEach(v -> coloring.put(v, 0));
        List <DefaultVertex> alphalistV = new ArrayList <> (graphgml.vertexSet());
        // To be fixed Update 1.4.0
        	//Collections.sort(alphalistV);
        alphalistV.forEach(v -> coloring.put(v, getColor(graphgml,coloring,v)));
        System.out.println("\nAlphabetical: ");
        System.out.println(alphalistV);
        alphalistV.forEach(v -> System.out.print(v + "->" + coloring.get(v)+ " "));
  	}
	
    public static Integer getColor (Graph<DefaultVertex, DefaultEdge> g, Map<DefaultVertex,Integer> m, DefaultVertex v) { 
    	List<Integer> nColors = new ArrayList<Integer>();
    	Integer color = 1;
    	for (DefaultVertex s : Graphs.neighborSetOf(g, v)) {
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

