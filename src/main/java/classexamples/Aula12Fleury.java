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

public class Aula12Fleury {
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {
		printCircuit("euler.gml","1");
		printCircuit("bp6-falso.gml", "a");
	}
	
	public static void printCircuit (String filename, String u) {
		Graph<DefaultVertex, DefaultEdge> g = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
		g = ImportUtil.importDefaultGraphGML(g, graphpathname + filename);
	    DefaultVertex v = VertexEdgeUtil.getVertexfromLabel(g.vertexSet(),u);
		PrintUtil.printGraph(g);		
		System.out.println("Circuito: " + getCircuit(g,v) + NL);
	}
	
	public static <V,E> List<String> getCircuit (Graph <V,E> f, V v) {
		V x = v;
		List <String> w = new ArrayList <> ();
		if (GraphTests.isEulerian(f)) {
			while (f.edgesOf(x).isEmpty()==false) {
		        E e = getNextEdge(f,x);
		        V old = x;
		        x = Graphs.getOppositeVertex(f,e,x);
		        w.add(old.toString()+x.toString());
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
