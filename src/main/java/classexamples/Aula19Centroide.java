package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.Multigraph;

import util.DefaultVertex;
import util.ImportUtil;
import util.RelationshipEdge;

public class Aula19Centroide {
	
	public static void main(String[] args) {
		
		Graph<DefaultVertex, RelationshipEdge> graph = new Multigraph<>(RelationshipEdge.class);
	    ImportUtil.importGraphGML(graph, "./src/main/java/graphs/root-tree.gml");
	    ImportUtil.createAndShowGui(graph, "Tree", false, false, true, true,JGraphTUtil.ImportUtil.HIERARCHICAL);
	    
	    System.out.println("- Weight of the Tree at Cut Vertexes (points) -");
	    ImportUtil.printOrderedVertexMeasures(ImportUtil.getCutPointWeights(graph),0,false);

		System.out.println("\n\n- Tree Centroids: " + ImportUtil.getTreeCentroidPoints(graph));
	
		
		
	}
	

}