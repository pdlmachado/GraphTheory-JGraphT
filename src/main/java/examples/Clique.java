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



public class Clique {
	
	
	public static void main(String[] args) {
	    Graph<DefaultVertex, RelationshipEdge> graphgml = new SimpleGraph<>(RelationshipEdge.class);
        MyJGraphTUtil.importGraphGML(graphgml,"./src/main/java/graphs/lesmis.gml");

	    DegeneracyBronKerboschCliqueFinder <DefaultVertex,RelationshipEdge> cf2 = 
	    		new DegeneracyBronKerboschCliqueFinder <> (graphgml); 
	    Iterator  <Set <DefaultVertex>> it1 = cf2.iterator();
	    List <Set <DefaultVertex>> t = new ArrayList <>();
	    while (it1.hasNext()) {
	    	t.add(it1.next());
	    }
        Collections.sort( t, new Comparator<Set<DefaultVertex>>()
        {
            public int compare( Set <DefaultVertex> o1, Set <DefaultVertex> o2 )
            {
                return (new Integer(o2.size())).compareTo( (new Integer(o1.size())) );
            }
        } );
	    System.out.print("DegenearyBronKerboschCliqueFinder cliques: \n");
	    Iterator <Set<DefaultVertex>> it2 = t.iterator();
	    while (it2.hasNext()) {
	    	System.out.println(it2.next());
	    }
	    
	    PivotBronKerboschCliqueFinder <DefaultVertex,RelationshipEdge> cf3 = 
	    		new PivotBronKerboschCliqueFinder <> (graphgml); 
	    Iterator  <Set <DefaultVertex>> it3 = cf3.iterator();
	    List <Set <DefaultVertex>> t2 = new ArrayList <>();
	    while (it3.hasNext()) {
	    	t2.add(it3.next());
	    }
        Collections.sort( t2, new Comparator<Set<DefaultVertex>>()
        {
            public int compare( Set <DefaultVertex> o1, Set <DefaultVertex> o2 )
            {
                return (new Integer(o2.size())).compareTo( (new Integer(o1.size())) );
            }
        } );
	    System.out.print("\n\nPivotBronKerboschCliqueFinder cliques: \n");
	    Iterator <Set<DefaultVertex>> it4 = t2.iterator();
	    while (it4.hasNext()) {
	    	System.out.println(it4.next());
	    	
	    }

	}
	
	
	
	
}
