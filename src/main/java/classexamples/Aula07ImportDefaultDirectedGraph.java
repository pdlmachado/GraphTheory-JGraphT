// Teoria dos Grafos - UFCG

package classexamples;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.VertexEdgeUtil;
import util.RelationshipDirectedEdge;

public class Aula07ImportDefaultDirectedGraph {

	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {

	    DefaultDirectedGraph<DefaultVertex,RelationshipDirectedEdge> g = 
	    		new DefaultDirectedGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(),
	    									VertexEdgeUtil.createRelationshipDirectedEdgeSupplier(),false);
        ImportUtil.importDirectedGraphGML(g, graphpathname + "grid.gml");    		
        PrintUtil.printGraph(g);
	    	    
        g.vertexSet().stream().forEach(v -> { 
        	System.out.println("Vértice " + v);
        	System.out.print("Grau de Entrada: " + g.inDegreeOf(v) + "|");
        	System.out.print("Grau de Saída: " + g.outDegreeOf(v) + "|");
        	System.out.print("Arcos onde é a cabeça: " + g.incomingEdgesOf(v) + "|");
        	System.out.print("Arcos onde é a cauda: " + g.outgoingEdgesOf(v) + "|");
        	System.out.print("Vizinhos de entrada: " + Graphs.predecessorListOf(g,v) + "|");
        	System.out.println("Vizinhos de saída: " + Graphs.successorListOf(g,v));
        } );
	}
}

