// Teoria dos Grafos - UFCG

package util;

import org.jgrapht.nio.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
	
	// EDGE LIST
	// Updated 1.4.0
	public static Graph<DefaultVertex,DefaultEdge> importGraphCSV 
		(Graph<DefaultVertex,DefaultEdge> graph, String filename, CSVFormat f) {
		try {
			StringReader reader = (StringReader) readFile(filename);
			return getGraphCSV(graph,reader,f);
		} catch (Exception e) {
			return graph;
		}
	}
	
	public static Graph<DefaultVertex,DefaultEdge> getGraphCSV 
	(Graph<DefaultVertex,DefaultEdge> graph, StringReader reader, CSVFormat f) {
		if (reader != null && (f == CSVFormat.EDGE_LIST || f == CSVFormat.ADJACENCY_LIST)) {
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
				csvImporter.importGraph(graph, reader);         
				for (DefaultVertex v : attrs.keySet()) {
					v.setAttrs(attrs.get(v));
				}
			} catch (ImportException e) { 
				throw new RuntimeException(e); 
			} 
		}
		return graph;		
	}
	
	// WEIGHTED EDGE LIST
	public static Graph<DefaultVertex,DefaultWeightedEdge> importWeightedGraphCSV 
		(Graph<DefaultVertex,DefaultWeightedEdge> graph, 
		 String filename,
		 boolean headsInFirstLine) {
		try {
			StringReader reader = (StringReader) readFile(filename);
			return getWeightedGraphCSV(graph,reader,headsInFirstLine);
		} catch (Exception e) {
			return graph;
		}
	}
	
	public static Graph<DefaultVertex,DefaultWeightedEdge> getWeightedGraphCSV 
	(Graph<DefaultVertex,DefaultWeightedEdge> graph, 
			 StringReader reader,
			 boolean headsInFirstLine) {
		if (reader != null) {
			try (BufferedReader br = new BufferedReader(reader)) {
				String sCurrentLine = "";
				if (headsInFirstLine) {
					sCurrentLine = br.readLine(); //Discard first line
				}
				while ((sCurrentLine = br.readLine()) != null) {
					String [] attributes = sCurrentLine.split(",");
					DefaultVertex v0 = VertexEdgeUtil.getVertexfromLabel(graph.vertexSet(), attributes[0]);
					if (v0 == null) {
						v0 = new DefaultVertex(attributes[0]);
					}
					DefaultVertex v1 = VertexEdgeUtil.getVertexfromLabel(graph.vertexSet(), attributes[1]);
					if (v1 == null) {
						v1 = new DefaultVertex(attributes[1]);
					}
					graph.addVertex(v0);
					graph.addVertex(v1);
					DefaultWeightedEdge e = new DefaultWeightedEdge();
					graph.addEdge(v0,v1,e);
					// Deprecated Code
					//graph.setEdgeWeight(e, new Double(attributes[2]).doubleValue());
					graph.setEdgeWeight(e, Double.valueOf(attributes[2]));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return graph;
	}
	
	// MATRIX
	// Updated 1.4.0
	// Remover parâmetro CSVFormat e EDGE_WEIGHT em versões posteriores. Não são utilizados.
	// 
	public static Graph<DefaultVertex,DefaultEdge> importGraphCSV (
			Graph<DefaultVertex,DefaultEdge> graph, 
			String filename, 
			CSVFormat f,
			boolean MATRIX_FORMAT_ZERO_WHEN_NO_EDGE,
			boolean EDGE_WEIGHT,
			boolean MATRIX_FORMAT_NODEID) {
		try {
	        StringReader reader = (StringReader) readFile(filename);
	        return getGraphCSV(graph,reader,f,MATRIX_FORMAT_ZERO_WHEN_NO_EDGE,EDGE_WEIGHT,MATRIX_FORMAT_NODEID);
		} catch (Exception e) {
			return graph;
		}
	}
        
	public static Graph<DefaultVertex,DefaultEdge> getGraphCSV (
			Graph<DefaultVertex,DefaultEdge> graph, 
			StringReader reader, 
			CSVFormat f,
			boolean MATRIX_FORMAT_ZERO_WHEN_NO_EDGE,
			boolean EDGE_WEIGHT,
			boolean MATRIX_FORMAT_NODEID) {		
		if (reader != null && f == CSVFormat.MATRIX) {
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
				csvImporter.importGraph(graph, reader); 
				for (DefaultVertex v : attrs.keySet()) {
					v.setAttrs(attrs.get(v));
				}
			} catch (ImportException e) { 
				throw new RuntimeException(e); 
			}
		}
		return graph;
	}
	
	// WEIGHTED MATRIX
	// Remover parâmetro CSVFormat em versões posteriores.
	public static Graph<DefaultVertex,DefaultWeightedEdge> importWeightedGraphCSV (
			Graph<DefaultVertex,DefaultWeightedEdge> graph, 
			String filename, 
			CSVFormat f,
			boolean MATRIX_FORMAT_ZERO_WHEN_NO_EDGE,
			boolean EDGE_WEIGHT,
			boolean MATRIX_FORMAT_NODEID) {
		try {
			StringReader reader = (StringReader) readFile(filename);
			return getWeightedGraphCSV(graph,reader,f,MATRIX_FORMAT_ZERO_WHEN_NO_EDGE,EDGE_WEIGHT,MATRIX_FORMAT_NODEID);
		} catch (Exception e) {
			return graph;
		}
	}
	
	public static Graph<DefaultVertex,DefaultWeightedEdge> getWeightedGraphCSV (
			Graph<DefaultVertex,DefaultWeightedEdge> graph, 
			StringReader reader, 
			CSVFormat f,
			boolean MATRIX_FORMAT_ZERO_WHEN_NO_EDGE,
			boolean EDGE_WEIGHT,
			boolean MATRIX_FORMAT_NODEID) {
		if (reader != null && f == CSVFormat.MATRIX) {
			CSVImporter<DefaultVertex, DefaultWeightedEdge> csvImporter = new CSVImporter<>(f);
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
				csvImporter.importGraph(graph, reader); 
				for (DefaultVertex v : attrs.keySet()) {
					v.setAttrs(attrs.get(v));
				}
			} catch (ImportException e) { 
				throw new RuntimeException(e); 
			}
		}
		return graph;
	}
	
////////////////////////////////////////
//GML IMPORTERS
////////////////////////////////////////
	
	// Updated 1.4.0
	// DefaultEdge
	public static Graph<DefaultVertex,DefaultEdge> importDefaultGraphGML 
		(Graph<DefaultVertex,DefaultEdge> graph, String filename) {
	    try {
	    	StringReader reader = (StringReader) readFile(filename);
		    return getDefaultGraphGML(graph,reader);
	    } catch (Exception e) {
		    return graph;
	    }

	}
	
	public static Graph<DefaultVertex,DefaultEdge> getDefaultGraphGML 
	(Graph<DefaultVertex,DefaultEdge> graph, StringReader reader) {		
		if (reader != null) {
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
				gmlImporter.importGraph(graph, reader);
				for (DefaultVertex v : attrs.keySet()) {
					v.setAttrs(attrs.get(v));
				}
			} catch (ImportException e) {
				throw new RuntimeException(e);
			}
		}
		return graph;
	}
	
	// Updated 1.4.0
	// RelationshipEdge
	public static Graph<DefaultVertex,RelationshipEdge> importGraphGML 
		(Graph<DefaultVertex,RelationshipEdge> graph, String filename) {
		try {
			StringReader reader = (StringReader) readFile(filename);
			return getGraphGML (graph, reader);
		} catch (Exception e) {
			return graph;
		}
	}
	
	static Graph<DefaultVertex,RelationshipEdge> getGraphGML 
	(Graph<DefaultVertex,RelationshipEdge> graph, StringReader reader) {	
		if (reader != null) {
			GmlImporter<DefaultVertex, RelationshipEdge> gmlImporter = new GmlImporter<>();
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
				gmlImporter.importGraph(graph, reader);
				for (DefaultVertex v : attrs.keySet()) {
					v.setAttrs(attrs.get(v));
				}
				for (RelationshipEdge e : edgeAttrs.keySet()) {
					e.setAttrs(edgeAttrs.get(e));
				}
			} catch (ImportException e) {
				throw new RuntimeException(e);
			}
		}
		return graph;
	}
	
	// To be updated
	// RelationshipDirectedEdge
	public static Graph<DefaultVertex,RelationshipDirectedEdge> importDirectedGraphGML 
		(Graph<DefaultVertex,RelationshipDirectedEdge> graph, String filename) {
		try {
			StringReader reader = (StringReader) readFile(filename);
			return getDirectedGraphGML(graph,reader);
		} catch (Exception e) {
			return graph;
		}
	}
	
	public static Graph<DefaultVertex,RelationshipDirectedEdge> getDirectedGraphGML 
	(Graph<DefaultVertex,RelationshipDirectedEdge> graph, StringReader reader) {
		if (reader != null) {
			GmlImporter<DefaultVertex, RelationshipDirectedEdge> gmlImporter = new GmlImporter<>();
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
				gmlImporter.importGraph(graph, reader);
				for (DefaultVertex v : attrs.keySet()) {
					v.setAttrs(attrs.get(v));
				}
				for (RelationshipDirectedEdge e : edgeAttrs.keySet()) {
					e.setAttrs(edgeAttrs.get(e));
				}
			} catch (ImportException e) {
				throw new RuntimeException(e);
			}
		}
		return graph;
	}
	
////////////////////////////////////
// Multiple CSV Importers
////////////////////////////////////
	
	public static Graph<DefaultVertex,RelationshipWeightedEdge> importGraphMultipleCSV (
			Graph<DefaultVertex,RelationshipWeightedEdge> graph, 
			String Vfilename, String idAtt, String labelAtt,
			String Efilename, String sourceAtt, String targetAtt, String weightAtt,
			boolean simplegraph, boolean weighted) {
		try {
			StringReader vertexReader = (StringReader) readFile(Vfilename);
			graph = readVertexSet(graph,vertexReader,idAtt,labelAtt);
			StringReader edgeReader = (StringReader) readFile(Efilename);
			return readEdgeSet(graph,edgeReader,sourceAtt,targetAtt,weightAtt,simplegraph,weighted);
		} catch (Exception e) {
			return graph;
		}
	}
	
	public static Graph<DefaultVertex,RelationshipWeightedEdge> readVertexSet (
			Graph<DefaultVertex,RelationshipWeightedEdge> graph, 
			StringReader reader, String idAtt, String labelAtt) {		
		// Reading Vertex Set
		if (reader != null) {
			try (BufferedReader vSet = new BufferedReader(reader)) {
				String sCurrentLine = vSet.readLine();
				String [] headsV = sCurrentLine.split(",");
				int indexId = 0;
				int indexLabel = 0;
				for (int i = 0; i < headsV.length; i++) {
					if (headsV[i].equals(idAtt)) {
						indexId = i;
					}
					if (headsV[i].equals(labelAtt)) {
						indexLabel = i;
					}
				}
				while ((sCurrentLine = vSet.readLine()) != null) {
					String [] attributes = sCurrentLine.split(",");
					DefaultVertex v = new DefaultVertex (attributes[indexId]);
					for (int i = 0; i<attributes.length; i++) {
						if (i != indexId && i != indexLabel) {
							v.setAtt(headsV[i], new DefaultAttribute <> (attributes[i],AttributeType.STRING));
						}
					}
					v.setAtt("label", new DefaultAttribute <> (attributes[indexLabel],AttributeType.STRING));
					graph.addVertex(v);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return graph;
	}
	
	public static Graph<DefaultVertex,RelationshipWeightedEdge> readEdgeSet(
			Graph<DefaultVertex,RelationshipWeightedEdge> graph,
			StringReader reader, String sourceAtt, String targetAtt, String weightAtt,
			boolean simplegraph, boolean weighted) {
		// Reading Edge Set
		if (reader != null) {
			Set <DefaultVertex> V = graph.vertexSet();
			try (BufferedReader eSet = new BufferedReader(reader)) {
				String eCurrentLine = eSet.readLine();
				String [] headsE = eCurrentLine.split(",");
				int indexS = 0;
				int indexT = 0;
				int indexW = 0;
				for (int i = 0; i < headsE.length; i++) {
					if (headsE[i].equals(sourceAtt)) {
						indexS = i;
					}
					if (headsE[i].equals(targetAtt)) {
						indexT = i;
					}
					if (headsE[i].equals(weightAtt) && weighted) {
						indexW = i;
					}
				}
				while ((eCurrentLine = eSet.readLine()) != null) {
					String [] attributes = eCurrentLine.split(",");
					DefaultVertex s = VertexEdgeUtil.getVertexfromId(V, attributes[indexS]);
					DefaultVertex t = VertexEdgeUtil.getVertexfromId(V, attributes[indexT]);
					if ((simplegraph && graph.getEdge(s, t) == null && graph.getEdge(t, s)==null) || 
							(simplegraph == false)){
						RelationshipWeightedEdge e = new RelationshipWeightedEdge ();
						for (int i = 0; i<attributes.length; i++) {
							if (i != indexS && i != indexT) {
								e.setAtt(headsE[i], new DefaultAttribute <> (attributes[i],AttributeType.STRING));
							}
						}
						graph.addEdge(s, t, e);
						if (weighted) graph.setEdgeWeight(e, Double.valueOf(attributes[indexW]));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return graph;
	}
	
	

////////////////////////////////////////
// File Reader
////////////////////////////////////////	
	
    static Reader readFile(String filename) throws FileNotFoundException {
		StringBuilder contentBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				contentBuilder.append(sCurrentLine).append("\n");
			}
		} catch (IOException e) {
			throw new FileNotFoundException();
		}
		StringReader reader = new StringReader(contentBuilder.toString());
		return reader;
	}
    
}
    
	
