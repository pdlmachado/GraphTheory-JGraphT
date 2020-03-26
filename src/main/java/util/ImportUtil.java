// Teoria dos Grafos - UFCG

package util;

import org.jgrapht.nio.*;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.nio.ImportException;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.nio.csv.CSVImporter;
import org.jgrapht.nio.gml.GmlImporter;

public class ImportUtil <V,E> {

////////////////////////////////////////
// CSV IMPORTERS
////////////////////////////////////////
	
	public static Graph<DefaultVertex,DefaultEdge> importGraphCSV 
		(Graph<DefaultVertex,DefaultEdge> graph, String filename, CSVFormat f) {
		// EDGE LIST
		// Updated 1.4.0
		CSVImporter<DefaultVertex, DefaultEdge> csvImporter = new CSVImporter<>(f);
		Map<DefaultVertex, Map<String, Attribute>> attrs = new HashMap<>();
        csvImporter.addVertexAttributeConsumer((p, a) -> {
            Map<String, Attribute> map = attrs.get(p.getFirst());
            if (map == null) {
                map = new HashMap<>();
                attrs.put(p.getFirst(), map);
            }
            map.put(p.getSecond(), a);
        });
		try {
			csvImporter.importGraph(graph, readFile(filename));         
			for (DefaultVertex v : attrs.keySet()) {
	        	v.setAttrs(attrs.get(v));
	        }
		} catch (ImportException e) { 
			throw new RuntimeException(e); 
		}
		return graph;
	}
	
	public static Graph<String,DefaultWeightedEdge> importWeightedGraphCSV 
		(Graph<String,DefaultWeightedEdge> graph, String filename) {
		// WEIGHTED EDGE LIST
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String sCurrentLine = br.readLine();
			while ((sCurrentLine = br.readLine()) != null) {
				String [] attributes = sCurrentLine.split(",");
				graph.addVertex(attributes[0]);
				graph.addVertex(attributes[1]);
				DefaultWeightedEdge e = new DefaultWeightedEdge();
				graph.addEdge(attributes[0], attributes[1], e);
				graph.setEdgeWeight(e, new Double(attributes[2]).doubleValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return graph;
	}
	
	public static Graph<DefaultVertex,DefaultEdge> importGraphCSV (
			Graph<DefaultVertex,DefaultEdge> graph, 
			String filename, 
			CSVFormat f,
			boolean MATRIX_FORMAT_ZERO_WHEN_NO_EDGE,
			boolean EDGE_WEIGHT,
			boolean MATRIX_FORMAT_NODEID) {
		// MATRIX
		// Updated 1.4.0
		CSVImporter<DefaultVertex, DefaultEdge> csvImporter = new CSVImporter<>(f);
	    csvImporter.setParameter(CSVFormat.Parameter.MATRIX_FORMAT_ZERO_WHEN_NO_EDGE,MATRIX_FORMAT_ZERO_WHEN_NO_EDGE);
	    csvImporter.setParameter(CSVFormat.Parameter.EDGE_WEIGHTS, EDGE_WEIGHT);
	    csvImporter.setParameter(CSVFormat.Parameter.MATRIX_FORMAT_NODEID, MATRIX_FORMAT_NODEID);		
		Map<DefaultVertex, Map<String, Attribute>> attrs = new HashMap<>();
        csvImporter.addVertexAttributeConsumer((p, a) -> {
            Map<String, Attribute> map = attrs.get(p.getFirst());
            if (map == null) {
                map = new HashMap<>();
                attrs.put(p.getFirst(), map);
            }
            map.put(p.getSecond(), a);
        });
	    try {
			csvImporter.importGraph(graph, readFile(filename)); 
			for (DefaultVertex v : attrs.keySet()) {
	        	v.setAttrs(attrs.get(v));
			}
		} catch (ImportException e) { 
			throw new RuntimeException(e); 
		}
		return graph;
	}
	
	public static Graph<DefaultVertex,DefaultWeightedEdge> importWeightedGraphCSV (
			Graph<DefaultVertex,DefaultWeightedEdge> graph, 
			String filename, 
			CSVFormat f,
			boolean pMATRIX_FORMAT_ZERO_WHEN_NO_EDGE,
			boolean pEDGE_WEIGHT,
			boolean pMATRIX_FORMAT_NODEID) {
		// WEIGHTED MATRIX
		CSVImporter<DefaultVertex, DefaultWeightedEdge> csvImporter = new CSVImporter<>(f);
	    csvImporter.setParameter(CSVFormat.Parameter.MATRIX_FORMAT_ZERO_WHEN_NO_EDGE,pMATRIX_FORMAT_ZERO_WHEN_NO_EDGE);
	    csvImporter.setParameter(CSVFormat.Parameter.EDGE_WEIGHTS, pEDGE_WEIGHT);
	    csvImporter.setParameter(CSVFormat.Parameter.MATRIX_FORMAT_NODEID, pMATRIX_FORMAT_NODEID);
		Map<DefaultVertex, Map<String, Attribute>> attrs = new HashMap<>();
        csvImporter.addVertexAttributeConsumer((p, a) -> {
            Map<String, Attribute> map = attrs.get(p.getFirst());
            if (map == null) {
                map = new HashMap<>();
                attrs.put(p.getFirst(), map);
            }
            map.put(p.getSecond(), a);
        });
		try {
			csvImporter.importGraph(graph, readFile(filename)); 
			for (DefaultVertex v : attrs.keySet()) {
	        	v.setAttrs(attrs.get(v));
			}
		} catch (ImportException e) { 
			throw new RuntimeException(e); 
		}
		return graph;
	}
	
////////////////////////////////////////
//GML IMPORTERS
////////////////////////////////////////	
	
	public static Graph<DefaultVertex,DefaultEdge> importDefaultGraphGML 
		(Graph<DefaultVertex,DefaultEdge> graph, String filename) {
		// Updated 1.4.0
		GmlImporter<DefaultVertex, DefaultEdge> gmlImporter = new GmlImporter<>();	
		Map<DefaultVertex, Map<String, Attribute>> attrs = new HashMap<>();
        gmlImporter.addVertexAttributeConsumer((p, a) -> {
            Map<String, Attribute> map = attrs.get(p.getFirst());
            if (map == null) {
                map = new HashMap<>();
                attrs.put(p.getFirst(), map);
            }
            map.put(p.getSecond(), a);
        });
        try {
			gmlImporter.importGraph(graph, readFile(filename));
			for (DefaultVertex v : attrs.keySet()) {
	        	v.setAttrs(attrs.get(v));
			}
		} catch (ImportException e) {
			throw new RuntimeException(e);
		}
		return graph;
	}
	
	
	public static Graph<DefaultVertex,RelationshipEdge> importGraphGML (Graph<DefaultVertex,RelationshipEdge> graph, String filename) {
		GmlImporter<DefaultVertex, RelationshipEdge> gmlImporter = new GmlImporter<>();
		// Updated 1.4.0
		Map<DefaultVertex, Map<String, Attribute>> attrs = new HashMap<>();
        gmlImporter.addVertexAttributeConsumer((p, a) -> {
            Map<String, Attribute> map = attrs.get(p.getFirst());
            if (map == null) {
                map = new HashMap<>();
                attrs.put(p.getFirst(), map);
            }
            map.put(p.getSecond(), a);
        });
        Map<RelationshipEdge, Map<String, Attribute>> edgeAttrs = new HashMap<>();
        gmlImporter.addEdgeAttributeConsumer((p, a) -> {
            Map<String, Attribute> map = edgeAttrs.get(p.getFirst());
            if (map == null) {
                map = new HashMap<>();
                edgeAttrs.put(p.getFirst(), map);
            }
            map.put(p.getSecond(), a);
        }); 
		try {
			gmlImporter.importGraph(graph, readFile(filename));
			for (DefaultVertex v : attrs.keySet()) {
	        	v.setAttrs(attrs.get(v));
			}
			for (RelationshipEdge e : edgeAttrs.keySet()) {
	        	e.setAttrs(edgeAttrs.get(e));
			}
		} catch (ImportException e) {
			throw new RuntimeException(e);
		}
		return graph;
	}
	
	public static Graph<DefaultVertex,RelationshipDirectedEdge> importDirectedGraphGML (Graph<DefaultVertex,RelationshipDirectedEdge> graph, String filename) {
		GmlImporter<DefaultVertex, RelationshipDirectedEdge> gmlImporter = new GmlImporter<>();
		// To be updated
		Map<DefaultVertex, Map<String, Attribute>> attrs = new HashMap<>();
        gmlImporter.addVertexAttributeConsumer((p, a) -> {
            Map<String, Attribute> map = attrs.get(p.getFirst());
            if (map == null) {
                map = new HashMap<>();
                attrs.put(p.getFirst(), map);
            }
            map.put(p.getSecond(), a);
        });
        Map<RelationshipDirectedEdge, Map<String, Attribute>> edgeAttrs = new HashMap<>();
        gmlImporter.addEdgeAttributeConsumer((p, a) -> {
            Map<String, Attribute> map = edgeAttrs.get(p.getFirst());
            if (map == null) {
                map = new HashMap<>();
                edgeAttrs.put(p.getFirst(), map);
            }
            map.put(p.getSecond(), a);
        }); 
		try {
			gmlImporter.importGraph(graph, readFile(filename));
			for (DefaultVertex v : attrs.keySet()) {
	        	v.setAttrs(attrs.get(v));
			}
			for (RelationshipDirectedEdge e : edgeAttrs.keySet()) {
	        	e.setAttrs(edgeAttrs.get(e));
			}
		} catch (ImportException e) {
			throw new RuntimeException(e);
		}
		return graph;
	}
	
////////////////////////////////////////
// File Reader
////////////////////////////////////////	
	
    static Reader readFile(String filename) {
		StringBuilder contentBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				contentBuilder.append(sCurrentLine).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringReader readergml = new StringReader(contentBuilder.toString());
		return readergml;
	}
    
}
    
	
