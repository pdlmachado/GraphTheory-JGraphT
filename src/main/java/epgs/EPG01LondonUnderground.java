package epgs;

//Teoria dos Grafos - UFCG

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.YenKShortestPath;
import org.jgrapht.alg.shortestpath.YenShortestPathIterator;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.GraphWalk;
import org.jgrapht.graph.WeightedMultigraph;

import util.DefaultVertex;
import util.ImportUtil;
import util.RelationshipWeightedEdge;
import util.VertexEdgeUtil;

public class EPG01LondonUnderground {
	
	Graph<DefaultVertex, RelationshipWeightedEdge> graph;
	GraphPath <DefaultVertex, RelationshipWeightedEdge> emptyPath;
	
	// Constructor
	public EPG01LondonUnderground () {
		graph = new WeightedMultigraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
	            VertexEdgeUtil.createRelationshipWeightedEdgeSupplier());
		ImportUtil.importGraphMultipleCSV(graph, 
				"graphs/london.stations.csv","id","name",
				"graphs/london.connections.csv","station1", "station2", "time", false, true);
		emptyPath = new GraphWalk <> (graph,new ArrayList <DefaultVertex> (), 0.0);
		// Complemente este construtor se necessário
	}
	
	// get methods
	public Graph<DefaultVertex, RelationshipWeightedEdge> getGraph () {
		return graph;
	}

	// Estações Centrais
	public List <DefaultVertex> centralKStations (int k) {
		// Adicione aqui sua implementação
		return new ArrayList <> ();
	}
	
	// Menor Trajeto de Trem entre duas Estações
	public GraphPath <DefaultVertex, RelationshipWeightedEdge> shortestPath (String source, String sink) {
		// Adicione aqui sua implementação
		return emptyPath;
	}
	
	// Troca de Linhas em um Trajeto
	public List <Pair<String,RelationshipWeightedEdge>> changeofLines 
						(GraphPath <DefaultVertex, RelationshipWeightedEdge> path) {
		// Adicione aqui sua implementação
		return new ArrayList <> ();
	}
	
	// Menor Trajeto entre duas Estações sem usar Trens de uma Linha
	public GraphPath <DefaultVertex, RelationshipWeightedEdge> shortestPathDropLine 
			(String line, String source, String sink, int maxsteps) {
		// Adicione aqui sua implementação    
		return emptyPath;
	}
															
}

