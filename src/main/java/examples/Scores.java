// Teoria dos Grafos - UFCG

package examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.PatonCycleBase;
import org.jgrapht.alg.scoring.AlphaCentrality;
import org.jgrapht.alg.scoring.BetweennessCentrality;
import org.jgrapht.alg.scoring.ClosenessCentrality;
import org.jgrapht.alg.scoring.Coreness;
import org.jgrapht.alg.scoring.HarmonicCentrality;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

public class Scores {
	public static void main(String[] args) {

	    Graph<DefaultVertex, RelationshipEdge> graphgml = new SimpleGraph<>(RelationshipEdge.class);
        MyJGraphTUtil.importGraphGML(graphgml, "./src/main/java/graphs/lesmis.gml");
  	    
  	    // Compute Metrics
  	    System.out.println("-BETWEENESS CENTRALITY- ");
	    BetweennessCentrality <DefaultVertex, RelationshipEdge> bc = 
	    		new BetweennessCentrality <> (graphgml,true);
	    printOrderedDouble (bc.getScores(),0);

	    System.out.println("\n-ALPHA CENTRALITY- ");
	    AlphaCentrality <DefaultVertex, RelationshipEdge> ac = 
	    		new AlphaCentrality <> (graphgml,0.1);
	    printOrderedDouble (ac.getScores(),0);
	    
	    System.out.println("\n-CLOSENESS CENTRALITY- ");
	    ClosenessCentrality <DefaultVertex, RelationshipEdge> cc = 
	    		new ClosenessCentrality <> (graphgml);
	    printOrderedDouble (cc.getScores(),0);
	    
	    System.out.println("\n-HARMONIC CENTRALITY- ");
	    HarmonicCentrality <DefaultVertex, RelationshipEdge> hc = 
	    		new HarmonicCentrality <> (graphgml);
	    printOrderedDouble (hc.getScores(),0);
	    
		double triplets = get_NTriplets(graphgml);
	    System.out.println("\nN. Triplets: " + triplets);
        double triangles = get_NTriangles(graphgml);
	    System.out.println("N. Triangles: " + triangles);
	    double coefCluster = 3*triangles/triplets;
	    System.out.println("CLUSTERING COEFFICIENT: " + new Double(coefCluster));
	    
	   System.out.println("\nASSORTATIVITY: " + calculateAssortativityCoefficient(graphgml));
	   
	   double E = (graphgml.edgeSet()).size();
	   double V = (graphgml.vertexSet()).size();
	   double density = (2*E) / (V*(V-1));
	   System.out.println("\nDENSITY: " + density);
	   
	   int diameter = 0;
	   ArrayList <Integer> a = get_allpathLenghts(graphgml);
	   	   int sum = 0;
	   for(int i=0; i < a.size() ; i++) {
	         sum = sum + a.get(i);
	   		if (diameter<a.get(i)) {
	   			diameter = a.get(i);
	   		}
	   }
       double average = sum / a.size();
       System.out.println("DISTANCE: " + average);
	   System.out.println("DIAMETER: " + diameter);	
	   
	   Coreness <DefaultVertex,RelationshipEdge> c = new Coreness <> (graphgml);
	   System.out.println("Degeneracy: " + c.getDegeneracy());
	   System.out.println(c.getScores());
	}	  

	// M�todos Auxiliares
    static long factorial (long n) {
    	if (n == 1 || n == 0) {
    		return 1;
    	} else {
    		return n * factorial(n-1);
    	}
    }
	
	static <V> void printOrderedDouble (Map <V,Double> M, int count) {
		// count representa a quantidade de elementos que devem ser exibidos 
		// em ordem decrescente do score. Se count = 0, ent�o todos ser�o exibidos
        Set<Entry<V, Double>> set = M.entrySet();
        List<Entry<V, Double>> list = new ArrayList<Entry<V, Double>>(set);
        Collections.sort( list, new Comparator<Map.Entry<V, Double>>()
        {
            public int compare( Map.Entry<V, Double> o1, Map.Entry<V, Double> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        if (count == 0) {
        	count = list.size();
        }
        for (int i = 0; i<count; i++) {
        	Entry<V,Double> e = list.get(i);
        	System.out.print(e.getKey()+": "+ String.format("%.2f",(e.getValue()))+ "; ");
        }
	}
	
	static <V,E> double get_NTriplets (Graph <V, E> g) {
 	
		double triplets = 0;
		BreadthFirstIterator <V,E> cfi = 
				new BreadthFirstIterator <> (g);
		while (cfi.hasNext()) {
			V v = cfi.next();
			int n = (g.edgesOf(v)).size();
			if (n >=2) {
				triplets = triplets + factorial(n) / (2*factorial(n-2));
			}			
		}
		return triplets;
	}
	
	static <V,E> double get_NTriangles (Graph <V,E> g) {
		double triangles = 0;
		PatonCycleBase <V,E> pc = new PatonCycleBase <> (g);
		Iterator <List<E>> it2 = ((pc.getCycleBasis()).getCycles()).iterator();
	    while (it2.hasNext()) {
	    	List <E> s = it2.next();
	    	if ((s).size()==3) {
		    	//System.out.println(s);
	    		triangles++;
	    	}
	    }
	    return triangles;
	}
	
    static <V,E> double calculateAssortativityCoefficient (Graph <V, E> graph) {
        // from: https://github.com/Infeligo/jgrapht-metrics/blob/master/src/main/java/org/jgrapht/metrics/AssortativityCoefficientMetric.java
    	double edgeCount = graph.edgeSet().size();
        double n1 = 0, n2 = 0, dn = 0;

        for (E e : graph.edgeSet()) {
            int d1 = graph.degreeOf(graph.getEdgeSource(e));
            int d2 = graph.degreeOf(graph.getEdgeTarget(e));

            n1 += d1 * d2;
            n2 += d1 + d2;
            dn += d1 * d1 + d2 * d2;
        }
        n1 /= edgeCount;
        n2 = (n2 / (2 * edgeCount)) * (n2 / (2 * edgeCount));
        dn /= (2 * edgeCount);
        
        return (n1 - n2) / (dn - n2);
    }
    
    static <V,E> ArrayList <Integer> get_allpathLenghts (Graph <V,E> g) {
    	DijkstraShortestPath <V,E>  p = 
    			new DijkstraShortestPath <> (g);
    	ArrayList <Integer> a = new ArrayList <Integer> ();
    	BreadthFirstIterator <V,E> pf = 
    			new BreadthFirstIterator <> (g);
    	while (pf.hasNext()) {
    		V v1 = pf.next();
    		Iterator <V> vs = g.vertexSet().iterator();
    		while (vs.hasNext()) {
    			V v2 = vs.next();
    			int dist = (p.getPath(v1, v2)).getLength();
    			if (v1.equals(v2) == false) {
    				a.add(dist);
    			}
    		}			
    	}
        return a;
    }
}
