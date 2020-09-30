package classexamples;

import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.GraphMapping;
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;
import org.jgrapht.graph.SimpleGraph;

import util.DefaultVertex;
import util.ImportUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;
import util.PrintUtil;

public class Aula09SubgraphEmbedding {
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;


	public static void main(String[] args) {

		//Import Base Graph
	    Graph<DefaultVertex, RelationshipEdge> base = 
				new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);
	    String basegraphname = "K3-3.gml";
	    ImportUtil.importGraphGML(base, graphpathname + basegraphname);
        PrintUtil.printGraph(base,"Base Graph (" + basegraphname + "):");
        checkEmbedding(base, "bp1.gml");
        checkEmbedding(base, "K2-2.gml");
	}
	
	static void checkEmbedding (Graph <DefaultVertex,RelationshipEdge> base, String filename) {
	    Graph<DefaultVertex, RelationshipEdge> g = 
				new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);
	    ImportUtil.importGraphGML(g, graphpathname + filename);
        PrintUtil.printGraph(g, filename + ": ");
		VF2SubgraphIsomorphismInspector <DefaultVertex, RelationshipEdge> embeddingChecker = 
	      		new VF2SubgraphIsomorphismInspector <> (base,g);
		Iterator <GraphMapping <DefaultVertex,RelationshipEdge>> it = embeddingChecker.getMappings();
		if (it.hasNext()) {
		  	System.out.println(filename + " is embedded in Base Graph. " + NL + "An example of a mapping: " + NL);
		  	System.out.println(it.next());
		} else {
		  	System.out.println(filename + " is not embedded in Base Graph" + NL);
		}
	}

}
