package util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;


import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.IntegerIdProvider;
import org.jgrapht.nio.gml.GmlExporter;

public class ExportUtil {
	
///////////////////////////////////
//  GML
///////////////////////////////////

	public static void exportDefaultGML (Graph <DefaultVertex, DefaultEdge> graph, String filename)  {
		
        GmlExporter<DefaultVertex, DefaultEdge> exporter = new GmlExporter<>();
        exporter.setEdgeIdProvider(new IntegerIdProvider<>());
        exporter.setParameter(GmlExporter.Parameter.EXPORT_VERTEX_LABELS, true);
        exporter.setParameter(GmlExporter.Parameter.EXPORT_CUSTOM_VERTEX_ATTRIBUTES, true);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        exporter.exportGraph(graph, os);     
        try {
        	String res = new String(os.toByteArray(), "UTF-8");
			write(filename,res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
	}
	
	public static void exportGML (Graph <DefaultVertex, RelationshipEdge> graph, String filename)  {
		
        GmlExporter<DefaultVertex, RelationshipEdge> exporter = new GmlExporter<>();
        exporter.setEdgeIdProvider(new IntegerIdProvider<>());
        exporter.setParameter(GmlExporter.Parameter.EXPORT_VERTEX_LABELS, true);
        exporter.setParameter(GmlExporter.Parameter.EXPORT_EDGE_LABELS, true);
        exporter.setParameter(GmlExporter.Parameter.EXPORT_CUSTOM_VERTEX_ATTRIBUTES, true);
        exporter.setParameter(GmlExporter.Parameter.EXPORT_CUSTOM_EDGE_ATTRIBUTES, true);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        exporter.exportGraph(graph, os);     
        try {
        	String res = new String(os.toByteArray(), "UTF-8");
			write(filename,res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
	}
	

////////////////////////////////////////
//File Writer
////////////////////////////////////////
	
	static void write (String filename, String graph_str) throws IOException {
      	try (FileWriter writer = new FileWriter(filename);
                BufferedWriter bw = new BufferedWriter(writer)) {
                   bw.write(graph_str);
           } catch (IOException e) {
                      System.err.format("IOException: %s%n", e);
           }
	}
}
