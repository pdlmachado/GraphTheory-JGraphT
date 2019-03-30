package examples;

import java.util.Iterator;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;


public class Adjacencia {
	
	public static void main(String[] args) {

		Graph<DefaultVertex, RelationshipEdge> g = new Pseudograph<>(RelationshipEdge.class);
		g = MyJGraphTUtil.importGraphGML(g, "./src/main/java/graphs/pseudograph-Aula02.gml");
		MyJGraphTUtil.printGraph(g);

		Set <DefaultVertex> vertexSet = g.vertexSet();
		int vsize = vertexSet.size();
		
		String [] lines = new String [vsize + 1];
		lines[0] = "\t";
	    vertexSet.stream().forEach(v -> {lines[0] = lines[0] + v + "\t";}); 
	    lines[0] = lines[0] + "\n";
		
	    Iterator<DefaultVertex> it1 = vertexSet.iterator();
	    int i = 1;
	    while (it1.hasNext()) {
	    	DefaultVertex v1 = it1.next();
	    	lines[i] = new String(v1 + "\t");
		    Iterator<DefaultVertex> it2 = vertexSet.iterator();
	    	while (it2.hasNext()) {
	    		DefaultVertex v2 = it2.next();
	    		int edgescount = g.getAllEdges(v1,v2).size();
	    		if (v1.equals(v2)) {
	    			edgescount = 2 * edgescount;
	    		}
		        lines[i] = lines[i] + edgescount + "\t";
	    	}
		    lines[i] = lines[i] + "\n";
		    i++;
	    }	    
	    for (int j = 0; j <= vsize; j++) {
		    System.out.println(lines[j]);
	    }	
	}
}
