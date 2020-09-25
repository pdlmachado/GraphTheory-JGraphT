// Teoria dos Grafos - UFCG

package classexamples;

import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.GraphMapping;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;

import org.jgrapht.alg.isomorphism.*;

public class Aula08Isomorphism {
	// Importa Grafo Simples no formato GML com r�tulo nos v�rtices e nas arestas
	
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;

	public static void main(String[] args) {

	
	    //Import G1	
	    Graph<DefaultVertex, RelationshipEdge> g1 = 
	    		new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), VertexEdgeUtil.createRelationshipEdgeSupplier(),false);
	    ImportUtil.importGraphGML(g1, graphpathname + "K3-3.gml");
        PrintUtil.printGraph(g1,"Grafo G1:");
	    
	    //Import G2
	    Graph<DefaultVertex, RelationshipEdge> g2 = 
	    		new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), VertexEdgeUtil.createRelationshipEdgeSupplier(),false);
	    ImportUtil.importGraphGML(g2, graphpathname + "K3-3-Isomorfico.gml");
        PrintUtil.printGraph(g2,"Grafo G2:");
	    
	    //Import G3
	    Graph<DefaultVertex, RelationshipEdge> g3 = 
	    		new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), VertexEdgeUtil.createRelationshipEdgeSupplier(),false);
	    ImportUtil.importGraphGML(g3, graphpathname + "cubo-rotulado.gml");
        PrintUtil.printGraph(g3,"Grafo G3:");
	    
	    //Import G4
	    Graph<DefaultVertex, RelationshipEdge> g4 = 
	    		new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), VertexEdgeUtil.createRelationshipEdgeSupplier(),false);
	    ImportUtil.importGraphGML(g4, graphpathname + "nao-isomorficocubo.gml");
        PrintUtil.printGraph(g4,"Grafo G4:");
	    
	    //Import Petersen
	    Graph<DefaultVertex, RelationshipEdge> p = 
	    		new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), VertexEdgeUtil.createRelationshipEdgeSupplier(),false);
	    ImportUtil.importGraphGML(p, graphpathname + "petersen-labelled.gml");
        PrintUtil.printGraph(p,"Grafo de Petersen:");
        
        //Import Grafo Assimetrico
	    Graph<DefaultVertex, RelationshipEdge> a = 
	    		new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), VertexEdgeUtil.createRelationshipEdgeSupplier(),false);
	    ImportUtil.importGraphGML(a, graphpathname + "asym.gml");
        PrintUtil.printGraph(a,"Grafo Assimétrico:");
        
        //Import Grafo Simétrico
	    Graph<DefaultVertex, RelationshipEdge> s = 
	    		new SimpleGraph<>(VertexEdgeUtil.createDefaultVertexSupplier(), VertexEdgeUtil.createRelationshipEdgeSupplier(),false);
	    ImportUtil.importGraphGML(s, graphpathname + "10cities.gml");
        PrintUtil.printGraph(s,"Grafo Assimétrico:");
	    
	    //Teste de Isomorfismo entre G1 e G2
	    VF2GraphIsomorphismInspector <DefaultVertex,RelationshipEdge> iso1_2 = 
	    		new VF2GraphIsomorphismInspector <> (g1,g2);
	    if (iso1_2.isomorphismExists()) {
	    	System.out.println("\nG1 eh isomorfico a G2? sim \nPossiveis bijecoes:");
		    Iterator <GraphMapping <DefaultVertex,RelationshipEdge>> it = iso1_2.getMappings();
		    while (it.hasNext()) {
		    	System.out.println(it.next());
		    }
	   
	    } else System.out.println("\nG1 eh isomorfico a G2? nao");
	    
	    //Teste de Isomorfismo entre G3 e G4
	    VF2GraphIsomorphismInspector <DefaultVertex,RelationshipEdge> iso3_4 = 
	    		new VF2GraphIsomorphismInspector <> (g3,g4);
	    if (iso3_4.isomorphismExists()) {
	    	System.out.println("\nG3 eh isomorfico a G4? sim \nPossiveis bijecoes:");
		    Iterator <GraphMapping <DefaultVertex,RelationshipEdge>> it = iso3_4.getMappings();
		    while (it.hasNext()) {
		    	System.out.println(it.next());
		    }
	   
	    } else System.out.println("\nG3 eh isomorfico a G4? nao");
	    
	    //Teste de Simetria do Grafo de Petersen
	    VF2GraphIsomorphismInspector <DefaultVertex,RelationshipEdge> auto_p = 
	    		new VF2GraphIsomorphismInspector <> (p,p);
	    System.out.println("\nQuais os possíveis automorfismos para o grafo de Petersen?");
		Iterator <GraphMapping <DefaultVertex,RelationshipEdge>> it = auto_p.getMappings();
	    while (it.hasNext()) {
	    	System.out.println(it.next());
	    }
	    
	    //Teste de Simetria do Grafo K3,3
	    VF2GraphIsomorphismInspector <DefaultVertex,RelationshipEdge> auto_k33 = 
	    		new VF2GraphIsomorphismInspector <> (g1,g1);
	    System.out.println("\nQuais os possíveis automorfismos para o grafo K3,3?");
		Iterator <GraphMapping <DefaultVertex,RelationshipEdge>> it2 = auto_k33.getMappings();
	    while (it2.hasNext()) {
	    	System.out.println(it2.next());
	    }
	    
	    //Teste de Simetria de asym
	    VF2GraphIsomorphismInspector <DefaultVertex,RelationshipEdge> auto_a = 
	    		new VF2GraphIsomorphismInspector <> (a,a);
	    System.out.println("\nQuais os possíveis automorfismos para o grafo asym?");
		Iterator <GraphMapping <DefaultVertex,RelationshipEdge>> it3 = auto_a.getMappings();
	    while (it3.hasNext()) {
	    	System.out.println(it3.next());
	    }
		
	    //Teste de Simetria de 10cities
	    VF2GraphIsomorphismInspector <DefaultVertex,RelationshipEdge> auto_s = 
	    		new VF2GraphIsomorphismInspector <> (s,s);
	    System.out.println("\nQuais os possíveis automorfismos para o grafo 10cities?");
		Iterator <GraphMapping <DefaultVertex,RelationshipEdge>> it4 = auto_s.getMappings();
	    while (it4.hasNext()) {
	    	System.out.println(it4.next());
	    }
	}
}
