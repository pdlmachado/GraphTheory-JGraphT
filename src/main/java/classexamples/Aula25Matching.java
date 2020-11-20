package classexamples;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.DenseEdmondsMaximumCardinalityMatching;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
import org.jgrapht.alg.partition.BipartitePartitioning;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.VertexEdgeUtil;
import util.DrawUtil;

public class Aula25Matching {

	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {
		
		Graph<DefaultVertex, DefaultEdge> graph1 = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
									SupplierUtil.createDefaultEdgeSupplier(), false);
		graph1 = ImportUtil.importDefaultGraphGML(graph1, graphpathname + "dissortativity.gml");
		DrawUtil.createAndShowGui(graph1, "Graph 1", false, true, true, true, DrawUtil.layout_type.ORGANIC);
		
		Graph<DefaultVertex, DefaultEdge> graph2 = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		graph2 = ImportUtil.importDefaultGraphGML(graph2, graphpathname + "cubo.gml");
		DrawUtil.createAndShowGui(graph2, "Graph 2", false, true, true, true, DrawUtil.layout_type.ORGANIC);
		
		Graph<DefaultVertex, DefaultEdge> graph3 = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);
		graph3 = ImportUtil.importDefaultGraphGML(graph3, graphpathname + "bipartido4-4.gml");
		DrawUtil.createAndShowGui(graph3, "Graph 3", false, true, true, true, DrawUtil.layout_type.ORGANIC);
		
		System.out.println("**DenseEdmondsMaximumCardinalityMatching Algorithm**" + NL);
		
		System.out.println("Maximum Matching:");
		
		DenseEdmondsMaximumCardinalityMatching <DefaultVertex,DefaultEdge> m1 = 
				new DenseEdmondsMaximumCardinalityMatching <>(graph1);
		System.out.println("Graph 1:" +  m1.getMatching().getEdges());
		
		DenseEdmondsMaximumCardinalityMatching <DefaultVertex,DefaultEdge> m2 = 
				new DenseEdmondsMaximumCardinalityMatching <>(graph2);
		System.out.println("Graph 2:" +  m2.getMatching().getEdges());
		
		System.out.println("Testing a matching:");
		
		HashSet <DefaultEdge> edgeSet = new HashSet <> ();
		edgeSet.add(graph2.getEdge(VertexEdgeUtil.getVertexfromLabel(graph2.vertexSet(),"e"), 
									VertexEdgeUtil.getVertexfromLabel(graph2.vertexSet(),"h")));
		edgeSet.add(graph2.getEdge(VertexEdgeUtil.getVertexfromLabel(graph2.vertexSet(),"g"), 
									VertexEdgeUtil.getVertexfromLabel(graph2.vertexSet(),"d")));
		edgeSet.add(graph2.getEdge(VertexEdgeUtil.getVertexfromLabel(graph2.vertexSet(),"b"), 
									VertexEdgeUtil.getVertexfromLabel(graph2.vertexSet(),"c")));
		System.out.println("For Graph 2, is match " + edgeSet + " maximum? " + 
		                   m2.isMaximumMatching(new MatchingAlgorithm.MatchingImpl<DefaultVertex, DefaultEdge> (graph2, edgeSet, 3.0))+ NL);
		
		System.out.println("**HopcroftKarpMaximumCardinalityBipartiteMatching Algorithm**" + NL);
		
		BipartitePartitioning <DefaultVertex, DefaultEdge> bp =
				new BipartitePartitioning <> (graph3);
		Set <DefaultVertex> X3 = bp.getPartitioning().getPartition(0);
		Set <DefaultVertex> Y3 = bp.getPartitioning().getPartition(1);
		HopcroftKarpMaximumCardinalityBipartiteMatching <DefaultVertex,DefaultEdge> m3 = 
				new HopcroftKarpMaximumCardinalityBipartiteMatching <>(graph3,X3,Y3);
		System.out.println("Maximum Matching:");
		System.out.println("Graph 2:" + m3.getMatching().getEdges());
		

	}
}
