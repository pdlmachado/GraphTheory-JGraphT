package classexamples;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.VertexEdgeUtil;
import util.PrintUtil;

public class Aula10Fleury {
	public static void main(String[] args) {
		Graph<DefaultVertex, DefaultEdge> g = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
		g = ImportUtil.importDefaultGraphGML(g, "./src/main/java/graphs/euler.gml");
	    DefaultVertex v = VertexEdgeUtil.getVertexfromLabel(g.vertexSet(),"1");
		PrintUtil.printGraph(g);		
		System.out.println("Circuito: " + getCircuit(g,v));
	}
	
	public static <V,E> List<E> getCircuit (Graph <V,E> f, V v) {
		V x = v;
		List <E> w = new ArrayList <> ();
		if (GraphTests.isEulerian(f)) {
			while (f.edgesOf(x).isEmpty()==false) {
		        E e = getNextEdge(f,x);
		        w.add(e);
		        x = Graphs.getOppositeVertex(f,e,x);
		        f.removeEdge(e);
			}
		} else {
			System.out.println("Not Eulerian");
		}
		return w;
	}

	public static <V,E> E getNextEdge (Graph <V,E> g, V v) {
		// Returns an edge e that is not a bridge, unless there is no other option. 
        Iterator <E> s = g.edgesOf(v).iterator();
		BiconnectivityInspector <V,E> b = new BiconnectivityInspector <> (g);
		E e = s.next();
        while (b.getBridges().contains(e) && s.hasNext()) {
        	e = s.next();
        }
        return e;
	}
}
