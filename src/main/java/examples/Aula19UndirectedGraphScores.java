// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.alg.scoring.AlphaCentrality;
import org.jgrapht.alg.scoring.BetweennessCentrality;
import org.jgrapht.alg.scoring.ClosenessCentrality;
import org.jgrapht.alg.scoring.ClusteringCoefficient;
import org.jgrapht.alg.scoring.HarmonicCentrality;
import org.jgrapht.alg.shortestpath.GraphMeasurer;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.util.SupplierUtil;


public class Aula19UndirectedGraphScores {

	public static void main(String[] args) {
		
		Graph<DefaultVertex, DefaultEdge> ugraph = 
				new Multigraph <> (MyJGraphTUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		MyJGraphTUtil.importDefaultGraphGML(ugraph, "./src/main/java/graphs/dolphins.gml");
		
		MyJGraphTUtil.printGraph(ugraph);
  	    
  	   	// Compute Metrics
  	   	System.out.println("-BETWEENESS CENTRALITY- ");
  	   	BetweennessCentrality <DefaultVertex, DefaultEdge> bc = 
  	   		new BetweennessCentrality <> (ugraph,true);
  	   	MyJGraphTUtil.printOrderedVertexMeasures (bc.getScores(),0,true);

  	   	System.out.println("\n-ALPHA CENTRALITY- ");
  	   	AlphaCentrality <DefaultVertex, DefaultEdge> ac = 
  	   		new AlphaCentrality <> (ugraph,0.001);
  	   	MyJGraphTUtil.printOrderedVertexMeasures (ac.getScores(),0,true);
	    
  	   	System.out.println("\n-CLOSENESS CENTRALITY- ");
  	   	ClosenessCentrality <DefaultVertex, DefaultEdge> cc = 
	   		new ClosenessCentrality <> (ugraph);
  	   	MyJGraphTUtil.printOrderedVertexMeasures (cc.getScores(),0,true);
	    
  	   	System.out.println("\n-HARMONIC CENTRALITY- ");
  	   	HarmonicCentrality <DefaultVertex, DefaultEdge> hc = 
	   		new HarmonicCentrality <> (ugraph);
  	   	MyJGraphTUtil.printOrderedVertexMeasures (hc.getScores(),0,true);
	    
  	   	ClusteringCoefficient <DefaultVertex, DefaultEdge> cluster = 
	    	new ClusteringCoefficient <> (ugraph);
  	   	System.out.println("\n\nCluster Coefficient: " + cluster.getGlobalClusteringCoefficient());
	    
  	   	System.out.println("\nASSORTATIVITY: " + MyJGraphTUtil.assortativityCoefficient(ugraph));
	   
  	   	if (GraphTests.hasMultipleEdges(ugraph)==false) {
  	  	   	double E = (ugraph.edgeSet()).size();
  	  	   	double V = (ugraph.vertexSet()).size();
  	  	   	double density = (2*E) / (V*(V-1));
  	  	   	System.out.println("\nDENSITY: " + density);   		
  	   	}   
  	   	GraphMeasurer <DefaultVertex, DefaultEdge> g = new GraphMeasurer <> (ugraph);
	   
  	   	System.out.println("\nDIAMETER: " + g.getDiameter());	
  	   	System.out.println("RADIUS: " + g.getRadius());
	   
  	   	System.out.println("CENTER: " + g.getGraphCenter());
  	   	System.out.println("PERIPHERY: " + g.getGraphPeriphery());
  	   	System.out.println("\n-Eccentricity-");
  	   	MyJGraphTUtil.printOrderedVertexMeasures(g.getVertexEccentricityMap(),0,false);
	}	  
}	

	
	

