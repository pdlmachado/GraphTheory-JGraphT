// Teoria dos Grafos - UFCG

package examples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.Graphs;
import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.io.CSVFormat;
import org.jgrapht.io.CSVImporter;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.orthogonal.mxOrthogonalLayout;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxStyleUtils;

public class MyJGraphTUtil <V,E> {
	
///////////////////////////////////
// GRAPH VIEW METHODS
///////////////////////////////////
	
	public static <V,E> void printGraph (Graph <V,E> g ) {
        System.out.println(g.vertexSet());
		System.out.println(g.edgeSet()+"\n");
	}
	
	public static <V,E> void printGraph (Graph <V,E> g, String title ) {
		System.out.println(title);
        System.out.println(g.vertexSet());
		System.out.println(g.edgeSet()+"\n");
	}
	
	public static <V,E> void printWeightedGraph (Graph <V,E> g, String title ) {
		System.out.println(title);
        System.out.println(g.vertexSet());
        Iterator <E> it = g.edgeSet().iterator();
        while (it.hasNext()) {
        	E e = it.next();
        	System.out.print(e + ":" + g.getEdgeWeight(e) + " ");
        }
        System.out.print("\n");
	}

	public enum layout_type {CIRCLE,ORGANIC,HIERARCHICAL,ORTHOGONAL;}
	
	// Graphic view for directed graphs
	public static <V,E> void createAndShowGui(Graph <V,E> graph, String frameLabel, 
			                                  boolean directed, 
			                                  boolean danglingEdges, 
			                                  boolean labelsVisible, 
			                                  boolean labelsClipped, 
			                                  layout_type layoutType
			) {

		JFrame frame = new JFrame(frameLabel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ListenableGraph<V,E> g = new DefaultListenableGraph <> (graph);
		JGraphXAdapter<V,E> graphAdapter = new JGraphXAdapter<V,E>(g);
		graphAdapter.setAllowDanglingEdges(danglingEdges);
		graphAdapter.setLabelsVisible(labelsVisible);
		graphAdapter.setLabelsClipped(labelsClipped);
		graphAdapter.setCellsMovable(true);
		graphAdapter.setCellsSelectable(true);
		graphAdapter.setEdgeLabelsMovable(true);

		mxIGraphLayout layout;
		switch (layoutType) {
			case CIRCLE : layout = new mxCircleLayout(graphAdapter); break;
			case ORGANIC: layout = new mxFastOrganicLayout(graphAdapter);break;
			case HIERARCHICAL : layout = new mxHierarchicalLayout(graphAdapter);break;
			case ORTHOGONAL: layout = new mxOrthogonalLayout(graphAdapter);break;
			default: layout = new mxCircleLayout(graphAdapter);break;
		}
		layout.execute(graphAdapter.getDefaultParent());

		mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
		mxGraphModel graphModel  = (mxGraphModel)graphComponent.getGraph().getModel(); 
		Collection<Object> cells =  graphModel.getCells().values(); 
		if (directed == false) {
			mxStyleUtils.setCellStyles(graphComponent.getGraph().getModel(), 
				    cells.toArray(), mxConstants.STYLE_ENDARROW, mxConstants.NONE);
		}
        frame.add(graphComponent);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}
	
	static <V> void printOrderedVertexMeasures (Map <V,Double> M, int count, boolean descending) {
		// count representa a quantidade de elementos que devem ser exibidos 
		// em ordem decrescente do score. Se count = 0, ent�o todos ser�o exibidos
        Set<Entry<V, Double>> set = M.entrySet();
        List<Entry<V, Double>> list = new ArrayList<Entry<V, Double>>(set);
        if (descending) {
        	Collections.sort( list, new Comparator<Map.Entry<V, Double>>()
        		{
        			public int compare( Map.Entry<V, Double> o1, Map.Entry<V, Double> o2 ) {
        				return (o2.getValue()).compareTo( o1.getValue() );
        			}
        		} );
        } else {
        	Collections.sort( list, new Comparator<Map.Entry<V, Double>>()
    		{
    			public int compare( Map.Entry<V, Double> o1, Map.Entry<V, Double> o2 ) {
    				return (o1.getValue()).compareTo( o2.getValue() );
    			}
    		} );
        }
        if (count == 0) {
        	count = list.size();
        }
        for (int i = 0; i<count; i++) {
        	Entry<V,Double> e = list.get(i);
        	System.out.print(e.getKey()+": "+ String.format("%.2f",(e.getValue()))+ "; ");
        }
	}
	
/////////////////////////////////////////
// VERTEX AND EGDES UTILITIES
/////////////////////////////////////////
	/**
	 * Os métodos a seguir retornam um vértice ou aresta cujo label eh igual ao passado como parametro.
	 * 
	 */
	public static DefaultVertex getVertexfromLabel(Set<DefaultVertex> V, String label) {	
		return V.stream().filter(v-> v.getLabel().equals(label)).findAny().get();
	}

	public static RelationshipEdge getEdgefromLabel(Set<RelationshipEdge> E, String label) {	
		return E.stream().filter(e-> e.getLabel().equals(label)).findAny().get();
	}
	
	public static RelationshipEdge getEdgefromVertexLabels(Set<RelationshipEdge> E, Set<DefaultVertex> V, String l1, String l2) {	
		DefaultVertex v1 = getVertexfromLabel(V,l1);
		DefaultVertex v2 = getVertexfromLabel(V,l2);
		return E.stream().filter(e-> (e.getV1().equals(v1) && e.getV2().equals(v2)) ||
			                        	(e.getV1().equals(v2) && e.getV2().equals(v1))).findAny().get();
	}
	
////////////////////////////////////////
// CSV and GML IMPORTERS
////////////////////////////////////////
	
	/**
	 * Os métodos a seguir realizam a importação de grafos no formato CSV e GML.
	 * 
	 */	
	
	public static Graph<String,DefaultEdge> importGraphCSV (Graph<String,DefaultEdge> graph, String filename, CSVFormat f) {
		// EDGE LIST
		VertexProvider<String> vp = (label, attributes) -> label;
		EdgeProvider<String, DefaultEdge> ep = (from, to, label, attributes) -> new DefaultEdge();

		CSVImporter<String, DefaultEdge> csvImporter = new CSVImporter<>(vp, ep);
		csvImporter.setFormat(f); 
		
		try {
			csvImporter.importGraph(graph, readFile(filename)); 
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
	
	public static Graph<String,DefaultEdge> importGraphCSV (
			Graph<String,DefaultEdge> graph, 
			String filename, 
			CSVFormat f,
			boolean pMATRIX_FORMAT_ZERO_WHEN_NO_EDGE,
			boolean pEDGE_WEIGHT,
			boolean pMATRIX_FORMAT_NODEID) {
		// MATRIX
		VertexProvider<String> vp = (label, attributes) -> label;
		EdgeProvider<String, DefaultEdge> ep = (from, to, label, attributes) -> new DefaultEdge();

		CSVImporter<String, DefaultEdge> csvImporter = new CSVImporter<>(vp, ep);
		csvImporter.setFormat(f);
	    csvImporter.setParameter(CSVFormat.Parameter.MATRIX_FORMAT_ZERO_WHEN_NO_EDGE,pMATRIX_FORMAT_ZERO_WHEN_NO_EDGE);
	    csvImporter.setParameter(CSVFormat.Parameter.EDGE_WEIGHTS, pEDGE_WEIGHT);
	    csvImporter.setParameter(CSVFormat.Parameter.MATRIX_FORMAT_NODEID, pMATRIX_FORMAT_NODEID);
		
		try {
			csvImporter.importGraph(graph, readFile(filename)); 
		} catch (ImportException e) { 
			throw new RuntimeException(e); 
		}
		return graph;
	}
	
	public static Graph<String,DefaultWeightedEdge> importWeightedGraphCSV (
			Graph<String,DefaultWeightedEdge> graph, 
			String filename, 
			CSVFormat f,
			boolean pMATRIX_FORMAT_ZERO_WHEN_NO_EDGE,
			boolean pEDGE_WEIGHT,
			boolean pMATRIX_FORMAT_NODEID) {
		// WEIGHTED MATRIX
		VertexProvider<String> vp = (label, attributes) -> label;
		EdgeProvider<String, DefaultWeightedEdge> ep = (from, to, label, attributes) -> new DefaultWeightedEdge();

		CSVImporter<String, DefaultWeightedEdge> csvImporter = new CSVImporter<>(vp, ep);
		csvImporter.setFormat(f);
	    csvImporter.setParameter(CSVFormat.Parameter.MATRIX_FORMAT_ZERO_WHEN_NO_EDGE,pMATRIX_FORMAT_ZERO_WHEN_NO_EDGE);
	    csvImporter.setParameter(CSVFormat.Parameter.EDGE_WEIGHTS, pEDGE_WEIGHT);
	    csvImporter.setParameter(CSVFormat.Parameter.MATRIX_FORMAT_NODEID, pMATRIX_FORMAT_NODEID);
		
		try {
			csvImporter.importGraph(graph, readFile(filename)); 
		} catch (ImportException e) { 
			throw new RuntimeException(e); 
		}
		return graph;
	}
	
	public static Graph<String,DefaultEdge> importDefaultGraphGML (Graph<String,DefaultEdge> graph, String filename) {
		VertexProvider<String> vp1 = (label, attributes) -> label;
		EdgeProvider<String, DefaultEdge> ep1 = (from, to, label, attributes) -> new DefaultEdge();
		GmlImporter<String, DefaultEdge> gmlImporter = new GmlImporter<>(vp1, ep1);
		try {
			gmlImporter.importGraph(graph, readFile(filename));
		} catch (ImportException e) {
			throw new RuntimeException(e);
		}
		return graph;
	}
	
	
	public static Graph<DefaultVertex,RelationshipEdge> importGraphGML (Graph<DefaultVertex,RelationshipEdge> graph, String filename) {
		VertexProvider<DefaultVertex> vp1 = (label, attributes) -> new DefaultVertex(label, attributes);
		EdgeProvider<DefaultVertex, RelationshipEdge> ep1 = (from, to, label, attributes) -> new RelationshipEdge(from,
				to, attributes);
		GmlImporter<DefaultVertex, RelationshipEdge> gmlImporter = new GmlImporter<>(vp1, ep1);
		try {
			gmlImporter.importGraph(graph, readFile(filename));
		} catch (ImportException e) {
			throw new RuntimeException(e);
		}
		return graph;
	}
	
	public static Graph<DefaultVertex,RelationshipDirectedEdge> importDirectedGraphGML (Graph<DefaultVertex,RelationshipDirectedEdge> graph, String filename) {
		VertexProvider<DefaultVertex> vp1 = (label, attributes) -> new DefaultVertex(label, attributes);
		EdgeProvider<DefaultVertex, RelationshipDirectedEdge> ep1 = (from, to, label, attributes) -> new RelationshipDirectedEdge(from,
				to, attributes);
		GmlImporter<DefaultVertex, RelationshipDirectedEdge> gmlImporter = new GmlImporter<>(vp1, ep1);
		try {
			gmlImporter.importGraph(graph, readFile(filename));
		} catch (ImportException e) {
			throw new RuntimeException(e);
		}
		return graph;
	}
	
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
    
//////////////////////////////////////////////
/// GRAPH MEASURES
/////////////////////////////////////////////
    
    static <V,E> double assortativityCoefficient (Graph <V, E> graph) {
        // from: https://github.com/Infeligo/jgrapht-metrics/blob/master/src/main/java/org/jgrapht/metrics/AssortativityCoefficientMetric.java
    	double edgeCount = graph.edgeSet().size();
        double n1 = 0, n2 = 0, dn = 0;

        for (E e : graph.edgeSet()) {
            int d1 = graph.degreeOf(graph.getEdgeSource(e));
            int d2 = graph.degreeOf(graph.getEdgeTarget(e));

            n1 += d1 * d2;
            n2 += d1 + d2;
            dn += d1 * d1 + d2 * d2;
        }
        n1 /= edgeCount;
        n2 = (n2 / (2 * edgeCount)) * (n2 / (2 * edgeCount));
        dn /= (2 * edgeCount);
        
        return (n1 - n2) / (dn - n2);
    }
    
    static public <V,E> Set <V> getTreeCentroidPoints (Graph <V,E> graph) {
		// graph must be a Tree
		if (GraphTests.isTree(graph)==false) {
			return new HashSet <> ();
		}
        Set<Entry<V, Double>> set = getCutPointWeights(graph).entrySet();
        List<Entry<V, Double>> list = new ArrayList<Entry<V, Double>>(set);
		list = list.stream().sorted((e1,e2) -> (e1.getValue()).compareTo(e2.getValue())).collect(Collectors.toList());
		double size = list.get(0).getValue();
		return (list.stream().filter(e -> e.getValue().doubleValue() == size).collect(Collectors.toSet())).stream().map(e -> e.getKey()).collect(Collectors.toSet());
    }
    
	static public <V,E> Map <V,Double> getCutPointWeights (Graph <V,E> graph) {
		// graph must be a Tree
		Map <V,Double> weights = new HashMap <> (); 
		if (GraphTests.isTree(graph)==false) {
			return weights;
		}
		BiconnectivityInspector <V, E> insp =
				new BiconnectivityInspector <> (graph);
		DijkstraShortestPath <V, E> paths = 
				new DijkstraShortestPath <> (graph);

		Iterator <V> it1 = insp.getCutpoints().iterator();
		while (it1.hasNext()) {
			V v1 = it1.next();
			Iterator <V> it2 = Graphs.neighborListOf(graph, v1).iterator();
            int size = 0;
			while (it2.hasNext()) {
                V v2 = it2.next();
    			List <E> s = new ArrayList <>();
                Iterator <V> it3 = graph.vertexSet().iterator();
                while (it3.hasNext()) {
                	V v3 = it3.next();
                    if (paths.getPath(v2, v3).getVertexList().contains(v1)==false && graph.degreeOf(v3)==1) {
        	            s.addAll( paths.getPath(v2, v3).getEdgeList().stream().filter(e -> s.contains(e)==false ).collect(Collectors.toList())); 
                    }
                }
                if(s.size() >= size) { 
                	size = s.size()+1; 
                }
			}
            weights.put(v1,new Double(size));			
		}
		return weights;
	}
    
}
    
	
