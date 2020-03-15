package examples;

import java.util.HashSet;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.DenseEdmondsMaximumCardinalityMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

public class Aula28Matching {

	public static void main(String[] args) {
		
		Graph<DefaultVertex, DefaultEdge> graph1 = 
				new SimpleGraph <> (MyJGraphTUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		graph1 = MyJGraphTUtil.importDefaultGraphGML(graph1, "./src/main/java/graphs/dissortativity.gml");
		MyJGraphTUtil.createAndShowGui(graph1, "Graph 1", false, true, true, true, MyJGraphTUtil.layout_type.ORGANIC);
		
		Graph<DefaultVertex, DefaultEdge> graph2 = 
				new SimpleGraph <> (MyJGraphTUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		graph2 = MyJGraphTUtil.importDefaultGraphGML(graph2, "./src/main/java/graphs/cubo.gml");
		MyJGraphTUtil.createAndShowGui(graph2, "Graph 2", false, true, true, true, MyJGraphTUtil.layout_type.ORGANIC);
		
		DenseEdmondsMaximumCardinalityMatching <DefaultVertex,DefaultEdge> m1 = 
				new DenseEdmondsMaximumCardinalityMatching <>(graph1);
		System.out.println("Graph 1:" +  m1.getMatching());
		
		DenseEdmondsMaximumCardinalityMatching <DefaultVertex,DefaultEdge> m2 = 
				new DenseEdmondsMaximumCardinalityMatching <>(graph2);
		System.out.println("Graph 2:" + m2.getMatching());
		
		HashSet <DefaultEdge> edgeSet = new HashSet <> ();
		edgeSet.add(graph2.getEdge(MyJGraphTUtil.getVertexfromLabel(graph2.vertexSet(),"e"), 
									MyJGraphTUtil.getVertexfromLabel(graph2.vertexSet(),"h")));
		edgeSet.add(graph2.getEdge(MyJGraphTUtil.getVertexfromLabel(graph2.vertexSet(),"g"), 
									MyJGraphTUtil.getVertexfromLabel(graph2.vertexSet(),"d")));
		edgeSet.add(graph2.getEdge(MyJGraphTUtil.getVertexfromLabel(graph2.vertexSet(),"b"), 
									MyJGraphTUtil.getVertexfromLabel(graph2.vertexSet(),"c")));
		System.out.println("For Graph 2, is match " + edgeSet + " maximum? " + 
		                   m2.isMaximumMatching(new MatchingAlgorithm.MatchingImpl<DefaultVertex, DefaultEdge> (graph2, edgeSet, 3.0)));
		
	}
}
