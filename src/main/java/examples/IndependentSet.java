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
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;

public class IndependentSet {
	public static void main(String[] args) {
	    //Gml
	    VertexProvider <Object> vp1 = 
	    		(label,attributes) -> new DefaultVertex (label,attributes);
	    EdgeProvider <Object,DefaultEdge> ep1 = 
	    		(from,to,label,attributes) -> new DefaultEdge();
		GmlImporter <Object,DefaultEdge> gmlImporter = new GmlImporter <> (vp1,ep1);
	    Graph<Object, DefaultEdge> graphgml = new SimpleGraph<>(DefaultEdge.class);
  	    try {
	        gmlImporter.importGraph(graphgml, 
	        		ImportGraph.readFile("./src/graphs/cordal.gml"));
	      } catch (ImportException e) {
	        throw new RuntimeException(e);
	      }	    
	       
	    ChordalGraphIndependentSetFinder <Object,DefaultEdge> is = 
	    		new ChordalGraphIndependentSetFinder <> (graphgml); 
        System.out.println("ChordalGraphIndependentSetFinder (conjunto m�ximo): " + is.getIndependentSet() );
        
        // Aplicando o algoritmo que calcula cliques no complemento do grafo
        Graph<Object, DefaultEdge> complement = new SimpleGraph<>(DefaultEdge.class);
        ComplementGraphGenerator<Object, DefaultEdge> cGenerator =
            new ComplementGraphGenerator<>(graphgml);
        cGenerator.generateGraph(complement);
        PivotBronKerboschCliqueFinder <Object,DefaultEdge> cf3 = 
	    		new PivotBronKerboschCliqueFinder <> (complement); 
	    Iterator  <Set <Object>> it3 = cf3.iterator();
	    System.out.println("Conjuntos Est�veis:");
	    while (it3.hasNext()) {
	    	System.out.println(it3.next());
	    }	    
	    
	    
        
	}	    
	

}
