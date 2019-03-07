// Teoria dos Grafos - UFCG

package examples;

import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.clique.PivotBronKerboschCliqueFinder;
import org.jgrapht.alg.independentset.ChordalGraphIndependentSetFinder;
import org.jgrapht.generate.ComplementGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class IndependentSet {
	public static void main(String[] args) {

	    Graph<String, DefaultEdge> graphgml = new SimpleGraph<>(DefaultEdge.class);
	    MyJGraphTUtil.importDefaultGraphGML(graphgml,"./src/main/java/graphs/cordal.gml");

	    ChordalGraphIndependentSetFinder <String,DefaultEdge> is = 
	    		new ChordalGraphIndependentSetFinder <> (graphgml); 
        System.out.println("ChordalGraphIndependentSetFinder (conjunto maximo): " + is.getIndependentSet() );
        
        // Aplicando o algoritmo que calcula cliques no complemento do grafo
        Graph<String, DefaultEdge> complement = new SimpleGraph<>(DefaultEdge.class);
        ComplementGraphGenerator<String, DefaultEdge> cGenerator =
            new ComplementGraphGenerator<>(graphgml);
        cGenerator.generateGraph(complement);
        PivotBronKerboschCliqueFinder <String,DefaultEdge> cf3 = 
	    		new PivotBronKerboschCliqueFinder <> (complement); 
	    Iterator  <Set <String>> it3 = cf3.iterator();
	    System.out.println("Conjuntos Estaveis:");
	    while (it3.hasNext()) {
	    	System.out.println(it3.next());
	    }	    
	    
	    
        
	}	    
	

}
