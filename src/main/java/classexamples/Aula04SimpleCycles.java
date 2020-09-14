package classexamples;

import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.cycle.PatonCycleBase;
import org.jgrapht.graph.SimpleGraph;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;

public class Aula04SimpleCycles {
	
	public static void main(String[] args) {
		Graph<DefaultVertex, RelationshipEdge> graph = 
			new SimpleGraph<DefaultVertex, RelationshipEdge>(
					VertexEdgeUtil.createDefaultVertexSupplier(), 
					VertexEdgeUtil.createRelationshipEdgeSupplier(), false);
		ImportUtil.importGraphGML(graph,"./src/main/java/graphs/bp1.gml");
		
		PrintUtil.printGraph(graph,"Graph:");
		
		System.out.println("Simple Cycles: ");
		PatonCycleBase <DefaultVertex, RelationshipEdge> patoncycles = new PatonCycleBase <> (graph);
		Iterator <GraphPath<DefaultVertex,RelationshipEdge>> it = 	
				(patoncycles.getCycleBasis().getCyclesAsGraphPaths()).iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
