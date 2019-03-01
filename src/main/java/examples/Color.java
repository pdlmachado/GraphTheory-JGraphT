// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.Graph;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.color.LargestDegreeFirstColoring;
import org.jgrapht.alg.color.RandomGreedyColoring;
import org.jgrapht.alg.color.SaturationDegreeColoring;
import org.jgrapht.alg.color.SmallestDegreeLastColoring;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;

public class Color {
	
	public static void main(String[] args) {
	    //Import Gml
	    VertexProvider <Object> vp1 = 
	    		(label,attributes) -> new DefaultVertex (label,attributes);
	    EdgeProvider <Object,RelationshipEdge> ep1 = 
	    		(from,to,label,attributes) -> new RelationshipEdge(from,to,attributes);
		GmlImporter <Object,RelationshipEdge> gmlImporter = new GmlImporter <> (vp1,ep1);
	    Graph<Object, RelationshipEdge> graphgml = new SimpleGraph<>(RelationshipEdge.class);
  	    try {
	        gmlImporter.importGraph(graphgml, 
	        		ImportGraph.readFile(System.getProperty("user.dir") + "\\src\\graphs\\sudoko4x4.gml"));
	      } catch (ImportException e) {
	        throw new RuntimeException(e);
	      }	    
	       
       GreedyColoring <Object, RelationshipEdge> g = new GreedyColoring <> (graphgml);
       System.out.println("GreedyColoring");
       System.out.println("Classes de Cores: " +(g.getColoring()).getColorClasses());
       System.out.println("Cores: " + (g.getColoring()).getColors());
       System.out.println("N�mero Crom�tico: " + (g.getColoring()).getNumberColors());
       
       LargestDegreeFirstColoring <Object, RelationshipEdge> l = new LargestDegreeFirstColoring <> (graphgml);
       System.out.println("\nLargestDegreeFirstColoring");      
       System.out.println("Classes de Cores: " +(l.getColoring()).getColorClasses());
       System.out.println("Cores: " + (l.getColoring()).getColors());
       System.out.println("N�mero Crom�tico: " + (l.getColoring()).getNumberColors());
       
       RandomGreedyColoring <Object, RelationshipEdge> r = new RandomGreedyColoring <> (graphgml);
       System.out.println("\nRandomGreedyColoring");      
       System.out.println("Classes de Cores: " +(r.getColoring()).getColorClasses());
       System.out.println("Cores: " + (r.getColoring()).getColors());
       System.out.println("N�mero Crom�tico: " + (r.getColoring()).getNumberColors());  
       
       SaturationDegreeColoring <Object, RelationshipEdge> s = new SaturationDegreeColoring <> (graphgml);
       System.out.println("\nSaturationDegreeColoring");      
       System.out.println("Classes de Cores: " +(s.getColoring()).getColorClasses());
       System.out.println("Cores: " + (s.getColoring()).getColors());
       System.out.println("N�mero Crom�tico: " + (s.getColoring()).getNumberColors()); 
       
       SmallestDegreeLastColoring <Object, RelationshipEdge> sd = new SmallestDegreeLastColoring <> (graphgml);
       System.out.println("\nSaturationDegreeColoring");      
       System.out.println("Classes de Cores: " +(sd.getColoring()).getColorClasses());
       System.out.println("Cores: " + (sd.getColoring()).getColors());
       System.out.println("N�mero Crom�tico: " + (sd.getColoring()).getNumberColors()); 
       
	}
	
	
}

