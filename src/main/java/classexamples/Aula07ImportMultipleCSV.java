package classexamples;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.RelationshipWeightedEdge;
import util.VertexEdgeUtil;

public class Aula07ImportMultipleCSV {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph<DefaultVertex, RelationshipWeightedEdge> graph = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
			            VertexEdgeUtil.createRelationshipWeightedEdgeSupplier(),false);
		Set <DefaultVertex> V = graph.vertexSet();
		ImportUtil.importGraphMultipleCSV(graph, 
					"./src/main/java/graphs/estados.csv","Sigla","Nome",
					"./src/main/java/graphs/fronteiras.csv", "Estado1", "Estado2", null, true, false);
		
		PrintUtil.printGraph(graph);
		System.out.println("PB é a sigla de: " + VertexEdgeUtil.getVertexfromId(V, "PB"));
	}
}
