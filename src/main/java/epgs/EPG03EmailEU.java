// Teoria dos Grafos - UFCG

package epgs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.Graphs;
import org.jgrapht.alg.clique.BronKerboschCliqueFinder;
import org.jgrapht.alg.scoring.AlphaCentrality;
import org.jgrapht.alg.scoring.BetweennessCentrality;
import org.jgrapht.alg.scoring.ClosenessCentrality;
import org.jgrapht.alg.scoring.ClusteringCoefficient;
import org.jgrapht.alg.scoring.HarmonicCentrality;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.MeasureUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;


public class EPG03EmailEU {

	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {
       
        // Grafos direcionados
        printScores("email-Eu-core.gml",true, 0, true, 0.001);
	}

	public static void printScores (
			String filename, // nome do arquivo com o grafo
			boolean isdirected, // grafo é direcionado (true) ou não-direcionado (false)
			int numberOfValues, // qtde de valores a imprimir para cada métrica em nível de vértice - 0 indica todos
			boolean descending, // métricas listadas em ordem decrescente (true) ou crescente (false) de valores
            double alpha) // parâmetro para alphacentrality 
	{
		///////////////
		// Criando grafo original e grafo base (versão não-direcionada)
		Graph<DefaultVertex, DefaultEdge> graph = 
				new DirectedMultigraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
		Graph<DefaultVertex, DefaultEdge> ugraph = Graphs.undirectedGraph(graph);
		ImportUtil.importDefaultGraphGML(graph, graphpathname + filename);
		
		if (numberOfValues > graph.vertexSet().size()) {
			numberOfValues = graph.vertexSet().size();
		}
 
		if (isdirected) {
			//PrintUtil.printGraph(graph,"**Graph: " + filename);
			System.out.println("Grafo Direcionado");
			System.out.println(ugraph.vertexSet().size() + " vértices");
			System.out.println(ugraph.edgeSet().size() + " arcos" + NL);
		} else {
			//PrintUtil.printGraph(ugraph,"**Graph: " + filename);
			System.out.println("Grafo Não-direcionado");
			System.out.println(ugraph.vertexSet().size() + " vértices");
			System.out.println(ugraph.edgeSet().size() + " arestas" + NL);
		}

  	    
		////
		// Métricas
	    System.out.println("-BETWEENESS CENTRALITY- ");
	    BetweennessCentrality <DefaultVertex, DefaultEdge> bc;
		if (isdirected) {
			bc = new BetweennessCentrality <> (graph,true);
		} else {
			bc = new BetweennessCentrality <> (ugraph,true);
		}
  	   	PrintUtil.printOrderedVertexMeasures (bc.getScores(),numberOfValues,descending);

  	   	System.out.println(NL + "-ALPHA CENTRALITY- ");
  	   	AlphaCentrality <DefaultVertex, DefaultEdge> ac;
  	   	if (isdirected) {
  	   		ac = new AlphaCentrality <> (graph,alpha);
  	   	} else {
  	   		ac = new AlphaCentrality <> (ugraph,alpha);
  	   	}			
  	   	PrintUtil.printOrderedVertexMeasures (ac.getScores(),numberOfValues,descending);
	    
  	   	System.out.println(NL + "-CLOSENESS CENTRALITY- ");
  	   	ClosenessCentrality <DefaultVertex, DefaultEdge> cc;
  	   	if (isdirected) {
  	   		cc = new ClosenessCentrality <> (graph);
  	   	} else {
  	   		cc = new ClosenessCentrality <> (ugraph);
  	   	}
   		PrintUtil.printOrderedVertexMeasures (cc.getScores(),numberOfValues,descending);
	    
   		System.out.println(NL + "-HARMONIC CENTRALITY- ");
  	   	HarmonicCentrality <DefaultVertex, DefaultEdge> hc;
  	   	if (isdirected) {
  	   		hc = new HarmonicCentrality <> (graph);
  	   	} else {
  	   		hc = new HarmonicCentrality <> (ugraph);
  	   	}
  	   	PrintUtil.printOrderedVertexMeasures (hc.getScores(),numberOfValues,descending);
	   
  	   	if (GraphTests.hasMultipleEdges(graph)==false) {
   			double E = (graph.edgeSet()).size();
   			double V = (graph.vertexSet()).size();
   			double density;
   			if (isdirected) {
   				density = (E) / (V*(V-1));
   			} else {
   				density = 2*(E) / (V*(V-1));
   			}
   			System.out.println(NL + NL + "-DENSITY: " + density);   		
  	   	}   
  	   	
  	    System.out.println(NL + "-INDEGREE-");
  	   	Map <DefaultVertex, Double> indegree = new HashMap <> ();
  	   	Iterator <DefaultVertex> inIt = graph.vertexSet().iterator();
  	   	while (inIt.hasNext()) {
  	   		DefaultVertex v = inIt.next();
  	   		indegree.put(v,Double.valueOf(graph.inDegreeOf(v)));
  	   	}
  	    PrintUtil.printOrderedVertexMeasures (indegree,numberOfValues,descending);
  	    
  	    System.out.println(NL + "-OUTDEGREE-");
  	   	Map <DefaultVertex, Double> outdegree = new HashMap <> ();
  	   	Iterator <DefaultVertex> outIt = graph.vertexSet().iterator();
  	   	while (outIt.hasNext()) {
  	   		DefaultVertex v = outIt.next();
  	   		outdegree.put(v,Double.valueOf(graph.outDegreeOf(v)));
  	   	}
  	    PrintUtil.printOrderedVertexMeasures (outdegree,numberOfValues,descending);
  	   	
     	// Métricas aplicadas em grafo não-direcionado ou no grafo base de um direcionado
	    ClusteringCoefficient <DefaultVertex, DefaultEdge> cluster = 
	       	new ClusteringCoefficient <> (ugraph);
	    System.out.println(NL + NL + "Cluster Coefficient: " + cluster.getGlobalClusteringCoefficient());
	    
	    System.out.println(NL + "ASSORTATIVITY: " + MeasureUtil.assortativityCoefficient(ugraph) + NL);
	    
	    // Cliques são calculados sobre uma versão especial do grafo direcionado onde
	    // uma aresta xy existe se graph possui os arcos (x,y) e (y,x)
		Graph<DefaultVertex, DefaultEdge> sgraph = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
		List <DefaultVertex> listV = new ArrayList <> (graph.vertexSet());
		for (int x = 0; x < listV.size(); x++) {
			DefaultVertex vx = listV.get(x);
			if (sgraph.containsVertex(vx) == false) {
				sgraph.addVertex(vx);
			}
			sgraph.addVertex (vx);
			List <DefaultVertex> listN = Graphs.neighborListOf(graph,vx);
			for (int y = 0; y < listN.size(); y++) {
				DefaultVertex vy = listN.get(y);
				if (graph.containsEdge(vx,vy) && graph.containsEdge(vy,vx)) {
					if (sgraph.containsVertex(vy) == false) {
						sgraph.addVertex(vy);
					}
					sgraph.addEdge(vx,vy);
				}
			}
		}
        BronKerboschCliqueFinder <DefaultVertex,DefaultEdge> cf1 = 
	    		new BronKerboschCliqueFinder <> (sgraph); 
	    Iterator  <Set <DefaultVertex>> it1 = cf1.maximumIterator();
	    List <Set <DefaultVertex>> t1 = new ArrayList <>();
	    while (it1.hasNext()) {
	    	t1.add(it1.next());
	    }
	    printClique(t1, "-Cliques");
		
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

	
	

