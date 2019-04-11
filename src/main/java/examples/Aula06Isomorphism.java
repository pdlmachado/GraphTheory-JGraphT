// Teoria dos Grafos - UFCG

package examples;

import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.GraphMapping;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.alg.isomorphism.*;

public class Aula06Isomorphism {
	// Importa Grafo Simples no formato GML com r�tulo nos v�rtices e nas arestas

	public static void main(String[] args) {

	
	    //Import G1	
	    Graph<DefaultVertex, RelationshipEdge> g1 = new SimpleGraph<>(RelationshipEdge.class);
	    MyJGraphTUtil.importGraphGML(g1,"./src/main/java/graphs/K3-3.gml");
        MyJGraphTUtil.printGraph(g1,"Grafo G1:");
	    
	    //Import G2
	    Graph<DefaultVertex, RelationshipEdge> g2 = new SimpleGraph<>(RelationshipEdge.class);
	    MyJGraphTUtil.importGraphGML(g2,"./src/main/java/graphs/K3-3-Isomorfico.gml");
        MyJGraphTUtil.printGraph(g2,"Grafo G2:");
	    
	    //Import G3
	    Graph<DefaultVertex, RelationshipEdge> g3 = new SimpleGraph<>(RelationshipEdge.class);
	    MyJGraphTUtil.importGraphGML(g3,"./src/main/java/graphs/cubo-rotulado.gml");
        MyJGraphTUtil.printGraph(g3,"Grafo G3:");
	    
	    //Import G4
	    Graph<DefaultVertex, RelationshipEdge> g4 = new SimpleGraph<>(RelationshipEdge.class);
	    MyJGraphTUtil.importGraphGML(g4,"./src/main/java/graphs/nao-isomorficocubo.gml");
        MyJGraphTUtil.printGraph(g4,"Grafo G4:");
	    
	    //Import Petersen
	    Graph<DefaultVertex, RelationshipEdge> p = new SimpleGraph<>(RelationshipEdge.class);
	    MyJGraphTUtil.importGraphGML(p,"./src/main/java/graphs/petersen-labelled.gml");
        MyJGraphTUtil.printGraph(p,"Grafo de Petersen:");
	    
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
	    System.out.println("\nQuais os possíveis automorfismo para o grafo de Petersen?");
		Iterator <GraphMapping <DefaultVertex,RelationshipEdge>> it = auto_p.getMappings();
	    while (it.hasNext()) {
	    	System.out.println(it.next());
	    }
		
	}
}
