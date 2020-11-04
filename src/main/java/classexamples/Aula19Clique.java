// Teoria dos Grafos - UFCG

package classexamples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import org.jgrapht.Graph;
import org.jgrapht.alg.clique.BronKerboschCliqueFinder;
import org.jgrapht.alg.clique.DegeneracyBronKerboschCliqueFinder;
import org.jgrapht.alg.clique.PivotBronKerboschCliqueFinder;
import org.jgrapht.graph.SimpleGraph;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;

public class Aula19Clique {
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {
		findMaximumCliques("cliques.gml");
		findAllCliques("cliques.gml");

	}
	
	public static void findAllCliques (String filename) {
		
		System.out.println("***ALL MAXIMAL CLIQUES***" + NL);
		Graph<DefaultVertex, RelationshipEdge> g = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);				
		g = ImportUtil.importGraphGML(g, graphpathname + filename);
	    PrintUtil.printGraph(g);
		
		
	    /////////////// BronKerboschCliqueFinder
        BronKerboschCliqueFinder <DefaultVertex,RelationshipEdge> cf1 = 
	    		new BronKerboschCliqueFinder <> (g); 
	    Iterator  <Set <DefaultVertex>> it1 = cf1.iterator();
	    List <Set <DefaultVertex>> t1 = new ArrayList <>();
	    while (it1.hasNext()) {
	    	t1.add(it1.next());
	    }
	    printClique(t1, "BronkerboschCliqueFinder");
	    
	    
	    /////////////// DegeneracyBronKerboschCliqueFinder
        DegeneracyBronKerboschCliqueFinder <DefaultVertex,RelationshipEdge> cf2 = 
	    		new DegeneracyBronKerboschCliqueFinder <> (g); 
	    Iterator  <Set <DefaultVertex>> it2 = cf2.iterator();
	    List <Set <DefaultVertex>> t2 = new ArrayList <>();
	    while (it2.hasNext()) {
	    	t2.add(it2.next());
	    }
	    printClique(t2, "DegeneracyBronkerboschCliqueFinder");

	    /////////////// DegeneracyBronKerboschCliqueFinder
	    PivotBronKerboschCliqueFinder <DefaultVertex,RelationshipEdge> cf3 = 
	    		new PivotBronKerboschCliqueFinder <> (g); 
	    Iterator  <Set <DefaultVertex>> it3 = cf3.iterator();
	    List <Set <DefaultVertex>> t3 = new ArrayList <>();
	    while (it3.hasNext()) {
	    	t3.add(it3.next());
	    }
	    printClique(t3, "PivotBronKerboschCliqueFinder");
        
	}
	
	public static void findMaximumCliques (String filename) {
		
		System.out.println("***MAXIMUM CLIQUES ONLY***" + NL);
		
		Graph<DefaultVertex, RelationshipEdge> g = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);				
		g = ImportUtil.importGraphGML(g, graphpathname + filename);
	    PrintUtil.printGraph(g);
		
		
	    /////////////// BronKerboschCliqueFinder
        BronKerboschCliqueFinder <DefaultVertex,RelationshipEdge> cf1 = 
	    		new BronKerboschCliqueFinder <> (g); 
	    Iterator  <Set <DefaultVertex>> it1 = cf1.maximumIterator();
	    List <Set <DefaultVertex>> t1 = new ArrayList <>();
	    while (it1.hasNext()) {
	    	t1.add(it1.next());
	    }
	    printClique(t1, "BronkerboschCliqueFinder");
	    
	    
	    /////////////// DegeneracyBronKerboschCliqueFinder
        DegeneracyBronKerboschCliqueFinder <DefaultVertex,RelationshipEdge> cf2 = 
	    		new DegeneracyBronKerboschCliqueFinder <> (g); 
	    Iterator  <Set <DefaultVertex>> it2 = cf2.maximumIterator();
	    List <Set <DefaultVertex>> t2 = new ArrayList <>();
	    while (it2.hasNext()) {
	    	t2.add(it2.next());
	    }
	    printClique(t2, "DegeneracyBronkerboschCliqueFinder");

	    /////////////// DegeneracyBronKerboschCliqueFinder
	    PivotBronKerboschCliqueFinder <DefaultVertex,RelationshipEdge> cf3 = 
	    		new PivotBronKerboschCliqueFinder <> (g); 
	    Iterator  <Set <DefaultVertex>> it3 = cf3.maximumIterator();
	    List <Set <DefaultVertex>> t3 = new ArrayList <>();
	    while (it3.hasNext()) {
	    	t3.add(it3.next());
	    }
	    printClique(t3, "PivotBronKerboschCliqueFinder");
        
	}
	
	public static void printClique (List <Set <DefaultVertex>> t , String title) {
		

	    // SORT TO PRINT
        Collections.sort(t, new Comparator<Set<DefaultVertex>>()
        {
            public int compare( Set <DefaultVertex> o1, Set <DefaultVertex> o2 )
            {
                // Deprecated code
            	//return (new Integer(o2.size())).compareTo( (new Integer(o1.size())) );
            	return (Integer.valueOf(o2.size())).compareTo( (Integer.valueOf(o1.size())) );
            }
        } );
	    System.out.print(title + ":" + NL);
	    Iterator <Set<DefaultVertex>> itb = t.iterator();
	    while (itb.hasNext()) {
	    	System.out.println(itb.next());
	    }
		System.out.println();
	}
	
}
