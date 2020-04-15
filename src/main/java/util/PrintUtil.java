package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jgrapht.Graph;

public class PrintUtil {

	public static <V,E> void printGraph (Graph <V,E> g ) {
        System.out.println(g.vertexSet());
		System.out.println(g.edgeSet()+"\n");
	}
	
	public static <V,E> void printGraph (Graph <V,E> g, String title ) {
		System.out.println(title);
        System.out.println(g.vertexSet());
		System.out.println(g.edgeSet()+"\n");
	}
	
	public static <V,E> void printWeightedGraph (Graph <V,E> g ) {
        System.out.println(g.vertexSet());
        Iterator <E> it = g.edgeSet().iterator();
        while (it.hasNext()) {
        	E e = it.next();
        	System.out.print(e + ":" + g.getEdgeWeight(e) + " ");
        }
        System.out.print("\n");
	}
	
	public static <V,E> void printWeightedGraph (Graph <V,E> g, String title ) {
		System.out.println(title);
        System.out.println(g.vertexSet());
        Iterator <E> it = g.edgeSet().iterator();
        while (it.hasNext()) {
        	E e = it.next();
        	System.out.print(e + ":" + g.getEdgeWeight(e) + " ");
        }
        System.out.print("\n");
	}
	
	public static <V,E> void printGraphSize (Graph <V,E> g) {
		System.out.println("#vertex = " + g.vertexSet().size());
		System.out.println("#edge = " + g.edgeSet().size() + "\n");
	}
	
	public static <V> void printOrderedVertexMeasures (Map <V,Double> M, int count, boolean descending) {
		// count representa a quantidade de elementos que devem ser exibidos 
		// em ordem decrescente do score. Se count = 0, ent�o todos ser�o exibidos
        Set<Entry<V, Double>> set = M.entrySet();
        List<Entry<V, Double>> list = new ArrayList<Entry<V, Double>>(set);
        if (descending) {
        	Collections.sort( list, new Comparator<Map.Entry<V, Double>>()
        		{
        			public int compare( Map.Entry<V, Double> o1, Map.Entry<V, Double> o2 ) {
        				return (o2.getValue()).compareTo( o1.getValue() );
        			}
        		} );
        } else {
        	Collections.sort( list, new Comparator<Map.Entry<V, Double>>()
    		{
    			public int compare( Map.Entry<V, Double> o1, Map.Entry<V, Double> o2 ) {
    				return (o1.getValue()).compareTo( o2.getValue() );
    			}
    		} );
        }
        if (count == 0) {
        	count = list.size();
        }
        for (int i = 0; i<count; i++) {
        	Entry<V,Double> e = list.get(i);
        	System.out.print(e.getKey()+": "+ String.format("%.2f",(e.getValue()))+ "; ");
        }

	}
}
