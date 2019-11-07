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
import org.jgrapht.generate.ComplementGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;



public class Aula18RelCliqueIndependent {
	
	// Compute Independent Set and Stability Number using a Clique Algorithm
	public static void main(String[] args) {
	    Graph<String, DefaultEdge> original = new SimpleGraph<>(RelationshipEdge.class);
        MyJGraphTUtil.importDefaultGraphGML(original,"./src/main/java/graphs/graph-layout.gml");
        
        Graph<String, DefaultEdge> complement = new SimpleGraph<>(DefaultEdge.class);

        ComplementGraphGenerator<String, DefaultEdge> cGenerator =
            new ComplementGraphGenerator<>(original);

        cGenerator.generateGraph(complement);

	    ///////////////
        DegeneracyBronKerboschCliqueFinder <String,DefaultEdge> cf2 = 
	    		new DegeneracyBronKerboschCliqueFinder <> (complement); 
	    Iterator  <Set <String>> it1 = cf2.iterator();
	    List <Set <String>> t = new ArrayList <>();
	    while (it1.hasNext()) {
	    	t.add(it1.next());
	    }
	    // SORT TO PRINT
        Collections.sort( t, new Comparator<Set<String>>()
        {
            public int compare( Set <String> o1, Set <String> o2 )
            {
                return (new Integer(o2.size())).compareTo( (new Integer(o1.size())) );
            }
        } );
        int stabilityNumber = t.get(0).size();
        System.out.println("Número de Estabilidade: " + stabilityNumber);
	    System.out.print("Maximum Independent Sets: \n");
	    Iterator <Set<String>> it2 = t.iterator();
	    while (it2.hasNext()) {
	    	Set<String> s = it2.next();
	    	if (s.size() == stabilityNumber ) {
  			    	System.out.println(it2.next());
	    	}
	    }
	    
	    ///////////////
	}
}
