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
import util.PrintUtil;
import util.VertexEdgeUtil;

public class Aula19RelCliqueIndependent {
	
	// Compute Independent Set and Stability Number using a Clique Algorithm
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {
		printIndependentSet("cordal.gml");
		printIndependentSet("asym.gml");
	}
	
	public static void printIndependentSet(String filename) {
	    Graph<DefaultVertex, DefaultEdge> original = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
        ImportUtil.importDefaultGraphGML(original, graphpathname + filename);
        PrintUtil.printGraph(original,"Graph: " + filename);
        // Compute Complement        
        Graph<DefaultVertex, DefaultEdge> complement = new SimpleGraph<>(DefaultEdge.class);
        ComplementGraphGenerator<DefaultVertex, DefaultEdge> cGenerator =
            new ComplementGraphGenerator<>(original);
        cGenerator.generateGraph(complement);

	    // Compute Maximum Cliques of Complement
        DegeneracyBronKerboschCliqueFinder <DefaultVertex,DefaultEdge> cf2 = 
	    		new DegeneracyBronKerboschCliqueFinder <> (complement); 
	    Iterator  <Set <DefaultVertex>> it1 = cf2.maximumIterator();
	    List <Set <DefaultVertex>> t = new ArrayList <>();
	    while (it1.hasNext()) {
	    	t.add(it1.next());
	    }
	    // SORT TO PRINT
        Collections.sort( t, new Comparator<Set<DefaultVertex>>()
        {
            public int compare( Set <DefaultVertex> o1, Set <DefaultVertex> o2 )
            {
            	return (Integer.valueOf(o2.size())).compareTo( (Integer.valueOf(o1.size())) );
            }
        } );
        int stabilityNumber = t.get(0).size();
        System.out.println("Número de Estabilidade: " + stabilityNumber);
	    System.out.print("Maximum Independent Sets: " + NL);
	    Iterator <Set<DefaultVertex>> it2 = t.iterator();
	    while (it2.hasNext()) {
	    	Set<DefaultVertex> s = it2.next();
	    	if (s.size() == stabilityNumber ) {
  			    	System.out.println(s);
	    	}
	    }
	    System.out.println(NL);
	}
}
