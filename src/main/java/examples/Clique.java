// Teoria dos Grafos - UFCG

package examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import org.jgrapht.Graph;
import org.jgrapht.alg.clique.DegeneracyBronKerboschCliqueFinder;
import org.jgrapht.alg.clique.PivotBronKerboschCliqueFinder;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;


public class Clique {
	
	
	public static void main(String[] args) {
	    //Import Gml
	    VertexProvider <Object> vp1 = 
	    		(label,attributes) -> new DefaultVertex (label,attributes);
	    EdgeProvider <Object,RelationshipEdge> ep1 = 
	    		(from,to,label,attributes) -> new RelationshipEdge(from,to,attributes);
		GmlImporter <Object,RelationshipEdge> gmlImporter = new GmlImporter <> (vp1,ep1);
	    Graph<Object, RelationshipEdge> graphgml = new SimpleGraph<>(RelationshipEdge.class);
	    System.out.println(System.getProperty("java.class.path"));
  	    try {
	        gmlImporter.importGraph(graphgml, 
	        		ImportGraph.readFile("./src/main/java/graphs/lesmis.gml"));
	      } catch (ImportException e) {
	        throw new RuntimeException(e);
	      }	    
	       
	    DegeneracyBronKerboschCliqueFinder <Object,RelationshipEdge> cf2 = 
	    		new DegeneracyBronKerboschCliqueFinder <> (graphgml); 
	    Iterator  <Set <Object>> it1 = cf2.iterator();
	    List <Set <Object>> t = new ArrayList <>();
	    while (it1.hasNext()) {
	    	t.add(it1.next());
	    }
        Collections.sort( t, new Comparator<Set<Object>>()
        {
            public int compare( Set <Object> o1, Set <Object> o2 )
            {
                return (new Integer(o2.size())).compareTo( (new Integer(o1.size())) );
            }
        } );
	    System.out.print("DegenearyBronKerboschCliqueFinder cliques: \n");
	    Iterator <Set<Object>> it2 = t.iterator();
	    while (it2.hasNext()) {
	    	System.out.println(it2.next());
	    }
	    
	    PivotBronKerboschCliqueFinder <Object,RelationshipEdge> cf3 = 
	    		new PivotBronKerboschCliqueFinder <> (graphgml); 
	    Iterator  <Set <Object>> it3 = cf3.iterator();
	    List <Set <Object>> t2 = new ArrayList <>();
	    while (it3.hasNext()) {
	    	t2.add(it3.next());
	    }
        Collections.sort( t2, new Comparator<Set<Object>>()
        {
            public int compare( Set <Object> o1, Set <Object> o2 )
            {
                return (new Integer(o2.size())).compareTo( (new Integer(o1.size())) );
            }
        } );
	    System.out.print("\n\nPivotBronKerboschCliqueFinder cliques: \n");
	    Iterator <Set<Object>> it4 = t2.iterator();
	    while (it4.hasNext()) {
	    	System.out.println(it4.next());
	    	
	    }

	}
	
	
	
	
}
