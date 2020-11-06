// Teoria dos Grafos - UFCG

package classexamples;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.Graphs;
import org.jgrapht.alg.scoring.AlphaCentrality;
import org.jgrapht.alg.scoring.BetweennessCentrality;
import org.jgrapht.alg.scoring.ClosenessCentrality;
import org.jgrapht.alg.scoring.ClusteringCoefficient;
import org.jgrapht.alg.scoring.HarmonicCentrality;
import org.jgrapht.alg.shortestpath.GraphMeasurer;
import org.jgrapht.graph.AsUndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.MeasureUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;


public class Aula20GraphScores {

	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {
        // Grafos não-direcionados
		printScores("cliques.gml", false, 10, true, 0.001);
        printScores("rich-club.gml",false, 10, true, 0.001);
        
        // Grafos direcionados
        printScores("aula12digrafo.gml",true, 10, true, 0.001);
        printScores("aula12digrafo2.gml",true, 10, true, 0.001);
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
			PrintUtil.printGraph(graph,"**Graph: " + filename);
			System.out.println("Grafo Direcionado");
			System.out.println(ugraph.vertexSet().size() + " vértices");
			System.out.println(ugraph.edgeSet().size() + " arcos" + NL);
		} else {
			PrintUtil.printGraph(ugraph,"**Graph: " + filename);
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
  	   	
  	    if (isdirected) {
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
  	    } else {
  	    	System.out.println(NL + "-DEGREE-");
  	    	Map <DefaultVertex, Double> degree = new HashMap <> ();
  	    	Iterator <DefaultVertex> it = graph.vertexSet().iterator();
  	    	while (it.hasNext()) {
  	    		DefaultVertex v = it.next();
  	    		degree.put(v,Double.valueOf(graph.degreeOf(v)));
  	    	} 
  	  	    PrintUtil.printOrderedVertexMeasures (degree,numberOfValues,descending);
  	    }
	   
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
  	   	
	    System.out.println(NL + "-Eccentricity-");
  	    GraphMeasurer <DefaultVertex, DefaultEdge> g;
  	   	if (isdirected) {
  	   		g = new GraphMeasurer <> (graph);
  	   	} else {
  	   		g = new GraphMeasurer <> (ugraph);
  	   	}
	    PrintUtil.printOrderedVertexMeasures(g.getVertexEccentricityMap(),numberOfValues,descending);
		System.out.println(NL + "DIAMETER: " + g.getDiameter());	
  	   	System.out.println("RADIUS: " + g.getRadius());
	    System.out.println("CENTER: " + g.getGraphCenter());
	    System.out.println("PERIPHERY: " + g.getGraphPeriphery());

     	// Métricas aplicadas em grafo não-direcionado ou no grafo base de um direcionado
	    ClusteringCoefficient <DefaultVertex, DefaultEdge> cluster = 
	       	new ClusteringCoefficient <> (ugraph);
	    System.out.println(NL + "Cluster Coefficient: " + cluster.getGlobalClusteringCoefficient());
	    
	    System.out.println(NL + "ASSORTATIVITY: " + MeasureUtil.assortativityCoefficient(ugraph) + NL);
	}
}	

	
	

