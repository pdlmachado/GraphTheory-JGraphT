package examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.Multigraph;

public class Aula19Centroide {
	
	public static void main(String[] args) {
		
		Graph<DefaultVertex, RelationshipEdge> graph = new Multigraph<>(RelationshipEdge.class);
	    MyJGraphTUtil.importGraphGML(graph, "./src/main/java/graphs/root-tree.gml");
	    MyJGraphTUtil.createAndShowGui(graph, "Tree", false, false, true, true,MyJGraphTUtil.layout_type.HIERARCHICAL);
	    
	    System.out.println("- Weight of the Tree at Cut Vertexes (points) -");
	    MyJGraphTUtil.printOrderedVertexMeasures(MyJGraphTUtil.getCutPointWeights(graph),0,false);

		System.out.println("\n\n- Tree Centroids: " + MyJGraphTUtil.getTreeCentroidPoints(graph));
	
		
		
	}
	

}