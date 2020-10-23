package classexamples;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.YenKShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;

public class Aula16KConnectivity {
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;

	public static void main(String[] args) {
		connectivity("asym.gml");
		connectivity("K5.gml");
		connectivity("cycle5.gml");
		connectivity("aula16-pseudografo.gml");
	}
		
	// Aplica-se apenas a grafos simples
	public static void connectivity (String filename) {
		Graph<DefaultVertex, DefaultEdge> graph = 
				new Multigraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						SupplierUtil.createDefaultEdgeSupplier(), false);
		graph = ImportUtil.importDefaultGraphGML(graph, graphpathname + filename);
		System.out.println("-----------------");
		PrintUtil.printGraph(graph, "Graph: " + filename);
		List <DefaultVertex> V = new ArrayList <> (graph.vertexSet());
		int vsize = V.size();
		int esize = graph.edgeSet().size();
		int [][] matrix = new int [vsize][vsize];
        for (int i = 0; i < vsize; i++) {
        	DefaultVertex vi = V.get(i);
        	for (int j = 0; j < vsize; j++) {
        		DefaultVertex vj = V.get(j);
        		YenKShortestPath <DefaultVertex, DefaultEdge> paths = new YenKShortestPath <> (graph);
        		Iterator <GraphPath<DefaultVertex, DefaultEdge>> it = paths.getPaths(vi,vj,vsize+esize).iterator();
        		int count = 0;
        		Set <DefaultVertex> coveredVertex = new HashSet <> ();
        		if (i!=j) {
        			while (it.hasNext()) {
        				List <DefaultVertex> l = it.next().getVertexList();
        				l.remove(vi);
        				l.remove(vj);
        				if (l.stream().anyMatch(v -> coveredVertex.contains(v))==false) {
        					count++;
        					coveredVertex.addAll(l);
        				}
        			}
        		}
        		matrix[i][j]=count;
        	}
        }
        System.out.println("Matrix de Conectividade Local de " + filename + ": ");
        for (int i = 0; i < vsize; i++) {
        	DefaultVertex vi = V.get(i);
        	System.out.println("Linha " + vi + ">");
        	for (int j = 0; j < vsize; j++) {
        		DefaultVertex vj = V.get(j);
        		System.out.print(vj + ":");
        		if (i!=j) {
        			System.out.print(matrix[i][j] + " ");
        		} else {
        			System.out.print("* ");
        		}
        	}
    		System.out.println();
        }
        System.out.print("Conectividade K(G) de " + filename + " = ");
        int min = vsize;
        for (int i = 0; i < vsize; i++) {
        	for (int j = 0; j < vsize; j++) {
        		if (matrix[i][j] < min && i!=j) {
        			min = (matrix[i][j]);
        		}
        	}
        }
        System.out.println(min + NL);        
	}
}
