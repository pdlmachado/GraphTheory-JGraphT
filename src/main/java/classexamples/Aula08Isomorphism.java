// Teoria dos Grafos - UFCG

package classexamples;

import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.GraphMapping;
import org.jgrapht.graph.SimpleGraph;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;

import org.jgrapht.alg.isomorphism.*;

public class Aula08Isomorphism {
	// Importa Grafo Simples no formato GML com r�tulo nos v�rtices e nas arestas
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	


	public static void main(String[] args) {

	    isomorphismTest("K3-3.gml","K3-3-isomorfico.gml");
        isomorphismTest("cubo-rotulado.gml","nao-isomorficocubo.gml");
        symmetryTest("petersen-labelled.gml");
        symmetryTest("K3-3.gml");
        symmetryTest("asym.gml");
        symmetryTest("10cities.gml");
	    
	}
	
	public static void isomorphismTest (String filename1, String filename2) {
	    //Import G1	
		System.out.println(">Teste Isomorfismo<");
	    Graph<DefaultVertex, RelationshipEdge> g1 = 
	    		new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), VertexEdgeUtil.createRelationshipEdgeSupplier(),false);
	    ImportUtil.importGraphGML(g1, graphpathname + filename1);
        PrintUtil.printGraph(g1,"Grafo" + filename1);
	    
	    //Import G2
	    Graph<DefaultVertex, RelationshipEdge> g2 = 
	    		new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), VertexEdgeUtil.createRelationshipEdgeSupplier(),false);
	    ImportUtil.importGraphGML(g2, graphpathname + filename2);
        PrintUtil.printGraph(g2,"Grafo" + filename2);
	    VF2GraphIsomorphismInspector <DefaultVertex,RelationshipEdge> iso1_2 = 
	    		new VF2GraphIsomorphismInspector <> (g1,g2);
	    if (iso1_2.isomorphismExists()) {
	    	System.out.println(filename1 + " eh isomorfico a " + filename2 + "? sim" + NL + "Possiveis bijecoes:");
		    Iterator <GraphMapping <DefaultVertex,RelationshipEdge>> it = iso1_2.getMappings();
		    while (it.hasNext()) {
		    	System.out.print(it.next());
		    }
		    System.out.println(NL);
	    } else System.out.println(filename1 + " eh isomorfico a " + filename2 + "? nao" + NL);
        
	}
	
	public static void symmetryTest (String filename) {
	    Graph<DefaultVertex, RelationshipEdge> p = 
	    		new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), VertexEdgeUtil.createRelationshipEdgeSupplier(),false);
	    System.out.println(">Teste de Simetria<");
	    ImportUtil.importGraphGML(p, graphpathname + filename);
        PrintUtil.printGraph(p,"Grafo " + filename);
	    VF2GraphIsomorphismInspector <DefaultVertex,RelationshipEdge> auto_p = 
	    		new VF2GraphIsomorphismInspector <> (p,p);
	    System.out.println("\nQuais os possíveis automorfismos para o grafo " + filename + "?");
	    int count = 0;
		Iterator <GraphMapping <DefaultVertex,RelationshipEdge>> it = auto_p.getMappings();
	    while (it.hasNext()) {
	    	System.out.print(it.next());
	    	count++;
	    }
	    System.out.println();
	    if (count == 1) {
	    	System.out.println(filename + " eh assimétrico" + NL);
	    } else {
	    	System.out.println(filename + " eh simétrico" + NL);
	    }
	}
}
