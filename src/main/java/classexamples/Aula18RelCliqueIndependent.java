// Teoria dos Grafos - UFCG

package classexamples;

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
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.VertexEdgeUtil;

public class Aula18RelCliqueIndependent {
	
	// Compute Independent Set and Stability Number using a Clique Algorithm
	public static void main(String[] args) {
	    Graph<DefaultVertex, DefaultEdge> original = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
        ImportUtil.importDefaultGraphGML(original,"./src/main/java/graphs/graph-layout.gml");
        
        Graph<DefaultVertex, DefaultEdge> complement = new SimpleGraph<>(DefaultEdge.class);

        ComplementGraphGenerator<DefaultVertex, DefaultEdge> cGenerator =
            new ComplementGraphGenerator<>(original);

        cGenerator.generateGraph(complement);

	    ///////////////
        DegeneracyBronKerboschCliqueFinder <DefaultVertex,DefaultEdge> cf2 = 
	    		new DegeneracyBronKerboschCliqueFinder <> (complement); 
	    Iterator  <Set <DefaultVertex>> it1 = cf2.iterator();
	    List <Set <DefaultVertex>> t = new ArrayList <>();
	    while (it1.hasNext()) {
	    	t.add(it1.next());
	    }
	    // SORT TO PRINT
        Collections.sort( t, new Comparator<Set<DefaultVertex>>()
        {
            public int compare( Set <DefaultVertex> o1, Set <DefaultVertex> o2 )
            {
                return (new Integer(o2.size())).compareTo( (new Integer(o1.size())) );
            }
        } );
        int stabilityNumber = t.get(0).size();
        System.out.println("Número de Estabilidade: " + stabilityNumber);
	    System.out.print("Maximum Independent Sets: \n");
	    Iterator <Set<DefaultVertex>> it2 = t.iterator();
	    while (it2.hasNext()) {
	    	Set<DefaultVertex> s = it2.next();
	    	if (s.size() == stabilityNumber ) {
  			    	System.out.println(it2.next());
	    	}
	    }
	    
	    ///////////////
	}
}
