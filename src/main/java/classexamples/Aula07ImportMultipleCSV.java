package classexamples;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.RelationshipWeightedEdge;
import util.VertexEdgeUtil;

public class Aula07ImportMultipleCSV {
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;

	public static void main(String[] args) {

		// Criando um grafo sem pesos
		Graph<DefaultVertex, RelationshipWeightedEdge> unweightedgraph = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
			            VertexEdgeUtil.createRelationshipWeightedEdgeSupplier(),false);
		Set <DefaultVertex> VU = unweightedgraph.vertexSet();
		ImportUtil.importGraphMultipleCSV(unweightedgraph, 
					graphpathname + "estados.csv","Sigla","Nome",
				    graphpathname + "fronteiras.csv", "Estado1", "Estado2", null, true, false);
		
		PrintUtil.printGraph(unweightedgraph, "Grafo sem Pesos");
		System.out.println("PB é a sigla de: " + VertexEdgeUtil.getVertexfromId(VU, "PB"));
		System.out.println("PI possui litoral? " + VertexEdgeUtil.getVertexfromId(VU, "PI").getAtt("Litoral"));
		
		//Criando um grafo com pesos
		Graph<DefaultVertex, RelationshipWeightedEdge> weightedgraph = 
				new SimpleWeightedGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
			            VertexEdgeUtil.createRelationshipWeightedEdgeSupplier());
		ImportUtil.importGraphMultipleCSV(weightedgraph, 
					graphpathname + "estados.csv","Sigla","Nome",
					graphpathname + "fronteiras.csv", "Estado1", "Estado2", "Distancia", true, true);
		PrintUtil.printWeightedGraph(weightedgraph, NL + "Grafo com Pesos");

	}
}
