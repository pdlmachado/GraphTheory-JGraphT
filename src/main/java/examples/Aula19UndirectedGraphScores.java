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
import org.jgrapht.graph.Multigraph;


public class Aula19UndirectedGraphScores {

	public static void main(String[] args) {
		
		Graph<DefaultVertex, RelationshipEdge> ugraph = new Multigraph<>(RelationshipEdge.class);
		MyJGraphTUtil.importGraphGML(ugraph, "./src/main/java/graphs/email-Eu-core.gml");
  	    
  	   	// Compute Metrics
  	   	System.out.println("-BETWEENESS CENTRALITY- ");
  	   	BetweennessCentrality <DefaultVertex, RelationshipEdge> bc = 
  	   		new BetweennessCentrality <> (ugraph,true);
  	   	MyJGraphTUtil.printOrderedVertexMeasures (bc.getScores(),0,true);

  	   	System.out.println("\n-ALPHA CENTRALITY- ");
  	   	AlphaCentrality <DefaultVertex, RelationshipEdge> ac = 
  	   		new AlphaCentrality <> (ugraph,0.001);
  	   	MyJGraphTUtil.printOrderedVertexMeasures (ac.getScores(),0,true);
	    
  	   	System.out.println("\n-CLOSENESS CENTRALITY- ");
  	   	ClosenessCentrality <DefaultVertex, RelationshipEdge> cc = 
	   		new ClosenessCentrality <> (ugraph);
  	   	MyJGraphTUtil.printOrderedVertexMeasures (cc.getScores(),0,true);
	    
  	   	System.out.println("\n-HARMONIC CENTRALITY- ");
  	   	HarmonicCentrality <DefaultVertex, RelationshipEdge> hc = 
	   		new HarmonicCentrality <> (ugraph);
  	   	MyJGraphTUtil.printOrderedVertexMeasures (hc.getScores(),0,true);
	    
  	   	ClusteringCoefficient <DefaultVertex,RelationshipEdge> cluster = 
	    	new ClusteringCoefficient <> (ugraph);
  	   	System.out.println("\n\nCluster Coefficient: " + cluster.getGlobalClusteringCoefficient());
	    
  	   	System.out.println("\nASSORTATIVITY: " + MyJGraphTUtil.assortativityCoefficient(ugraph));
	   
  	   	if (GraphTests.hasMultipleEdges(ugraph)==false) {
  	  	   	double E = (ugraph.edgeSet()).size();
  	  	   	double V = (ugraph.vertexSet()).size();
  	  	   	double density = (2*E) / (V*(V-1));
  	  	   	System.out.println("\nDENSITY: " + density);   		
  	   	}   
  	   	GraphMeasurer <DefaultVertex, RelationshipEdge> g = new GraphMeasurer <> (ugraph);
	   
  	   	System.out.println("\nDIAMETER: " + g.getDiameter());	
  	   	System.out.println("RADIUS: " + g.getRadius());
	   
  	   	System.out.println("CENTER: " + g.getGraphCenter());
  	   	System.out.println("PERIPHERY: " + g.getGraphPeriphery());
  	   	System.out.println("\n-Eccentricity-");
  	   	MyJGraphTUtil.printOrderedVertexMeasures(g.getVertexEccentricityMap(),0,false);
	}	  
}	

	
	

