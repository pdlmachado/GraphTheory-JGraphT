// Teoria dos Grafos - UFCG

package classexamples;

import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.clique.PivotBronKerboschCliqueFinder;
import org.jgrapht.alg.independentset.ChordalGraphIndependentSetFinder;
import org.jgrapht.generate.ComplementGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;

public class Aula19IndependentSetChordal {
	public static void main(String[] args) {
        // For Chordal Graphs ONLY
	    Graph<DefaultVertex, DefaultEdge> graphgml = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
									SupplierUtil.createDefaultEdgeSupplier(), false);
	    ImportUtil.importDefaultGraphGML(graphgml,"./src/main/java/graphs/cordal.gml");
	    PrintUtil.printGraph(graphgml);

	    ChordalGraphIndependentSetFinder <DefaultVertex,DefaultEdge> is = 
	    		new ChordalGraphIndependentSetFinder <> (graphgml); 
        System.out.println("ChordalGraphIndependentSetFinder (conjunto estável maximo): " + is.getIndependentSet() );
        
        // Aplicando o algoritmo que calcula cliques no complemento do grafo
        Graph<DefaultVertex, DefaultEdge> complement = new SimpleGraph<>(DefaultEdge.class);
        ComplementGraphGenerator<DefaultVertex, DefaultEdge> cGenerator =
            new ComplementGraphGenerator<>(graphgml);
        cGenerator.generateGraph(complement);
        PivotBronKerboschCliqueFinder <DefaultVertex,DefaultEdge> cf3 = 
	    		new PivotBronKerboschCliqueFinder <> (complement); 
	    Iterator  <Set <DefaultVertex>> it3 = cf3.iterator();
	    System.out.println("\nConjuntos Estáveis:");
	    while (it3.hasNext()) {
	    	System.out.println(it3.next());
	    }	    
	    
	    
        
	}	    
	

}
