// Teoria dos Grafos - UFCG

package epgs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.GraphWalk;
import org.jgrapht.graph.WeightedMultigraph;

import util.DefaultVertex;
import util.ImportUtil;
import util.RelationshipWeightedEdge;
import util.VertexEdgeUtil;

public class EPG02LondonUnderground {
	
	//private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	private static final String datapathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "datasets" + sep;
	
	Graph<DefaultVertex, RelationshipWeightedEdge> graph;
	Set <DefaultVertex> V;
	GraphPath <DefaultVertex, RelationshipWeightedEdge> emptyPath;	
	HashMap <String, DefaultVertex> attractions; 
	Set <RelationshipWeightedEdge> E;

	///////////////////////////////////////
	// Constructor
	public EPG02LondonUnderground () {
		graph = new WeightedMultigraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
	            VertexEdgeUtil.createRelationshipWeightedEdgeSupplier());
		// Data from http://markdunne.github.io/2016/04/10/The-London-Tube-as-a-Graph/
		ImportUtil.importGraphMultipleCSV(graph, 
				graphpathname + "london.stations.csv","id","name",
				graphpathname + "london.connections.csv","station1", "station2", "time", false, true);
		V = graph.vertexSet();
		E = graph.edgeSet();
		emptyPath = new GraphWalk <> (graph,new ArrayList <DefaultVertex> (), 0.0);	
		readAttractions();
	}
	
	public void readAttractions () {
        String csvFile = datapathname + "london-attractions.csv";
        String line = "";
        String cvsSplitBy = ",";  
        attractions = new HashMap <> ();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] aline = line.split(cvsSplitBy);
                attractions.put(aline[0], VertexEdgeUtil.getVertexfromLabel(V, aline[1]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
		Iterator <String> it = attractions.keySet().iterator();
        while (it.hasNext()) {
        	String n = it.next();
        	if (attractions.get(n) == null) {
        		System.out.println(n + "," + attractions.get(n));
        	}
        }
        
	}
	
	///////////////////////////////////////
	// get methods
	public Graph<DefaultVertex, RelationshipWeightedEdge> getGraph () {
		return graph;
	}
	
	public DefaultVertex getStation (String attraction) {
		return attractions.get(attraction);
	}
	
	public Set<String> getLines (GraphPath <DefaultVertex, RelationshipWeightedEdge> path) {
		Set<String> lines = new HashSet <> ();
		Iterator <RelationshipWeightedEdge> it = path.getEdgeList().iterator();
		while (it.hasNext()) {
			RelationshipWeightedEdge e = it.next();
			lines.add(e.getAtt("line").toString());
		}
		return lines;
		
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	// Métodos do EPG01 utilizados nos testes desta classe. Acrescente seu código
	
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
	
	/////////////////////////////////////////////////
	// Métodos a serem implementados no EPG02
	// Tempo Total Estimado de um Trajeto
	public double estimatedTime (GraphPath <DefaultVertex, RelationshipWeightedEdge> p, double t) {
		// Adicione aqui sua implementação
		return 0.0;
	}
	
	// Menor Trajeto considerando Tempo Total Estimado
	public GraphPath <DefaultVertex, RelationshipWeightedEdge> shortestEstimatedPath 
				(String source, String sink, double t, int maxAttempts) {
		// Adicione aqui sua implementação
		return emptyPath;
	}
	
	// Menor Rota de uma Estação para Atrações Turísticas
	public GraphPath <DefaultVertex, RelationshipWeightedEdge> bestRoute (String originStation, List<String> atts) {
		// Adicione aqui sua implementação
		return emptyPath;
	}
	
	// Trechos em Destaque
	public List <RelationshipWeightedEdge> findSections (List <String> stations) {
		// Adicione aqui sua implementação
        return new ArrayList <> ();
	}
	
	public boolean serviceDisruption (List <String> stations) {
		// Adicione aqui sua implementação
		return false;
	}
}
