package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.Multigraph;

import util.DefaultVertex;
import util.DrawUtil;
import util.ImportUtil;
import util.MeasureUtil;
import util.PrintUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;

public class Aula19Centroide {
	
	public static void main(String[] args) {
		
		Graph<DefaultVertex, RelationshipEdge> graph = 
				new Multigraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);
	    ImportUtil.importGraphGML(graph, "./src/main/java/graphs/root-tree.gml");
	    DrawUtil.createAndShowGui(graph, "Tree", false, false, true, true,DrawUtil.layout_type.HIERARCHICAL);
	    
	    System.out.println("- Weight of the Tree at Cut Vertexes (points) -");
	    PrintUtil.printOrderedVertexMeasures(MeasureUtil.getCutPointWeights(graph),0,false);

		System.out.println("\n\n- Tree Centroids: " + MeasureUtil.getTreeCentroidPoints(graph));
	
		
		
	}
	

}