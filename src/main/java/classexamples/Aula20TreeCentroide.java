package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.graph.SimpleGraph;

import util.DefaultVertex;
import util.DrawUtil;
import util.ImportUtil;
import util.MeasureUtil;
import util.PrintUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;

public class Aula20TreeCentroide {
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {
		printCentroids("root-tree.gml");
		printCentroids("treec.gml");
		printCentroids("simplepath5.gml");
	}
	
	public static void printCentroids(String filename) {
		Graph<DefaultVertex, RelationshipEdge> graph = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);
	    ImportUtil.importGraphGML(graph, graphpathname + filename);
	    if (GraphTests.isTree(graph)) {
	    	DrawUtil.createAndShowGui(graph, "Tree", false, false, true, true,DrawUtil.layout_type.HIERARCHICAL);	    
	    	System.out.println("**Tree " + filename);
	    	System.out.println("- Weight of the Tree at Cut Vertexes (points) -");
	    	PrintUtil.printOrderedVertexMeasures(MeasureUtil.getCutPointWeights(graph),0,false);
	    	System.out.println(NL + "- Tree Centroids: " + MeasureUtil.getTreeCentroidPoints(graph) + NL);
	    } else {
	    	System.out.println("Graph " + filename + " is not a tree." + NL);
	    }
		
		
	}
	

}