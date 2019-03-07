// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.Graph;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.color.LargestDegreeFirstColoring;
import org.jgrapht.alg.color.RandomGreedyColoring;
import org.jgrapht.alg.color.SaturationDegreeColoring;
import org.jgrapht.alg.color.SmallestDegreeLastColoring;
import org.jgrapht.graph.SimpleGraph;


public class Color {
	
	public static void main(String[] args) {
	    Graph<DefaultVertex, RelationshipEdge> graphgml = new SimpleGraph<>(RelationshipEdge.class);
        MyJGraphTUtil.importGraphGML(graphgml,"./src/main/java/graphs/sudoko4x4.gml");	    
	       
       GreedyColoring <DefaultVertex, RelationshipEdge> g = new GreedyColoring <> (graphgml);
       System.out.println("GreedyColoring");
       System.out.println("Classes de Cores: " +(g.getColoring()).getColorClasses());
       System.out.println("Cores: " + (g.getColoring()).getColors());
       System.out.println("Número Cromático: " + (g.getColoring()).getNumberColors());
       
       LargestDegreeFirstColoring <DefaultVertex, RelationshipEdge> l = new LargestDegreeFirstColoring <> (graphgml);
       System.out.println("\nLargestDegreeFirstColoring");      
       System.out.println("Classes de Cores: " +(l.getColoring()).getColorClasses());
       System.out.println("Cores: " + (l.getColoring()).getColors());
       System.out.println("Número Cromático: " + (l.getColoring()).getNumberColors());
       
       RandomGreedyColoring <DefaultVertex, RelationshipEdge> r = new RandomGreedyColoring <> (graphgml);
       System.out.println("\nRandomGreedyColoring");      
       System.out.println("Classes de Cores: " +(r.getColoring()).getColorClasses());
       System.out.println("Cores: " + (r.getColoring()).getColors());
       System.out.println("Número Cromático: " + (r.getColoring()).getNumberColors());  
       
       SaturationDegreeColoring <DefaultVertex, RelationshipEdge> s = new SaturationDegreeColoring <> (graphgml);
       System.out.println("\nSaturationDegreeColoring");      
       System.out.println("Classes de Cores: " +(s.getColoring()).getColorClasses());
       System.out.println("Cores: " + (s.getColoring()).getColors());
       System.out.println("Número Cromático: " + (s.getColoring()).getNumberColors()); 
       
       SmallestDegreeLastColoring <DefaultVertex, RelationshipEdge> sd = new SmallestDegreeLastColoring <> (graphgml);
       System.out.println("\nSaturationDegreeColoring");      
       System.out.println("Classes de Cores: " +(sd.getColoring()).getColorClasses());
       System.out.println("Cores: " + (sd.getColoring()).getColors());
       System.out.println("Número Cromático: " + (sd.getColoring()).getNumberColors()); 
       
	}
	
	
}

