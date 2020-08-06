package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class MeasureUtil {

    static public <V,E> double assortativityCoefficient (Graph <V, E> graph) {
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
    
    static public <V,E> Set <V> getTreeCentroidPoints (Graph <V,E> graph) {
		// graph must be a Tree
		if (GraphTests.isTree(graph)==false) {
			return new HashSet <> ();
		}
        Set<Entry<V, Double>> set = getCutPointWeights(graph).entrySet();
        List<Entry<V, Double>> list = new ArrayList<Entry<V, Double>>(set);
		list = list.stream().sorted((e1,e2) -> (e1.getValue()).compareTo(e2.getValue())).collect(Collectors.toList());
		double size = list.get(0).getValue();
		return (list.stream().filter(e -> e.getValue().doubleValue() == size).collect(Collectors.toSet())).stream().map(e -> e.getKey()).collect(Collectors.toSet());
    }
    
	static public <V,E> Map <V,Double> getCutPointWeights (Graph <V,E> graph) {
		// graph must be a Tree
		Map <V,Double> weights = new HashMap <> (); 
		if (GraphTests.isTree(graph)==false) {
			return weights;
		}
		BiconnectivityInspector <V, E> insp =
				new BiconnectivityInspector <> (graph);
		DijkstraShortestPath <V, E> paths = 
				new DijkstraShortestPath <> (graph);

		Iterator <V> it1 = insp.getCutpoints().iterator();
		while (it1.hasNext()) {
			V v1 = it1.next();
			Iterator <V> it2 = Graphs.neighborListOf(graph, v1).iterator();
            int size = 0;
			while (it2.hasNext()) {
                V v2 = it2.next();
    			List <E> s = new ArrayList <>();
                Iterator <V> it3 = graph.vertexSet().iterator();
                while (it3.hasNext()) {
                	V v3 = it3.next();
                    if (paths.getPath(v2, v3).getVertexList().contains(v1)==false && graph.degreeOf(v3)==1) {
        	            s.addAll( paths.getPath(v2, v3).getEdgeList().stream().filter(e -> s.contains(e)==false ).collect(Collectors.toList())); 
                    }
                }
                if(s.size() >= size) { 
                	size = s.size()+1; 
                }
			}
            // Deprecated Code
			//weights.put(v1,new Double(size));	
			weights.put(v1,Double.valueOf(size));	
		}
		return weights;
	}


	
}
