package examples;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class ConnectivityDirectedGraph2 {
	// Conectividade de Grafos Directionados

	public static void main(String[] args) {

	    DefaultDirectedGraph<DefaultVertex,RelationshipDirectedEdge> graphgml = new DefaultDirectedGraph<>(RelationshipDirectedEdge.class);
        MyJGraphTUtil.importDirectedGraphGML(graphgml, "./src/main/java/graphs/grid.gml");    		
 	    
	    Set <DefaultVertex> V = new HashSet <DefaultVertex>(graphgml.vertexSet());
	    Set <DefaultEdge> E = new HashSet <DefaultEdge>(graphgml.edgeSet());
	    
 	    System.out.println("Ordem do Grafo G: " + V.size());
 	    System.out.println("Tamanho do Grafo G: " + E.size());
	    System.out.println("V(G): " + V.toString());
	    System.out.println("E(G): " + E.toString());
	    
	    AllDirectedPaths <DefaultVertex,RelationshipDirectedEdge> p = new AllDirectedPaths <> (graphgml);
	    
        DefaultVertex source;
        DefaultVertex target;
        System.out.println("Q2.1. Não é possível deslocamento nos seguintes sentidos:");
	    for (int i=1;i<=20;i++) {
	    	source = MyJGraphTUtil.getVertexfromLabel(V,(new Integer(i)).toString());
	    	for (int j=1;j<=20;j++) {
		    	target = MyJGraphTUtil.getVertexfromLabel(V,(new Integer(j)).toString());
		    	List<GraphPath<DefaultVertex,RelationshipDirectedEdge>> l =  p.getAllPaths(source,target,true,null); 
		    	if (l.isEmpty()) {
		    		System.out.print(source.toString() + "->" + target.toString() + " | ");
		    	}
	    	}
	    }
	    	
        System.out.println("\nQ2.2. Cruzamentos que não são acessíveis:");
	    for (int i=1;i<=20;i++) {
	    	boolean eh_acessivel = false;
	    	target = MyJGraphTUtil.getVertexfromLabel(V,(new Integer(i)).toString());
	    	for (int j=1;j<=20;j++) {
		    	source = MyJGraphTUtil.getVertexfromLabel(V,(new Integer(j)).toString());
		    	List<GraphPath<DefaultVertex,RelationshipDirectedEdge>> l =  p.getAllPaths(source,target,true,null); 
		    	if (l.isEmpty()==false) {
		    		eh_acessivel = true;
		    	}
	    	}
	    	if (eh_acessivel == false) {
	    		System.out.println(target.toString() + " | ");
	    	}
	    }

        System.out.println("Q2.3. Tamanho m�nimo, m�dio e m�ximo dos percursos de um quarteir�o ao outro, quando existir:");
	    for (int i=1;i<=20;i++) {
	    	source = MyJGraphTUtil.getVertexfromLabel(V,(new Integer(i)).toString());
	    	for (int j=1;j<=20;j++) {
	    		if (i!=j) {
	    			target = MyJGraphTUtil.getVertexfromLabel(V,(new Integer(j)).toString());
	    			List<GraphPath<DefaultVertex,RelationshipDirectedEdge>> l =  p.getAllPaths(source,target,true,null); 
	    			if (l.isEmpty()==false) {
	    				TreeSet <Integer> tamanhos = new  TreeSet <> ();
	    				l.forEach(pa -> tamanhos.add(pa.getLength()));
	    				Integer medio = (tamanhos.stream().mapToInt(Integer::intValue).sum())/tamanhos.size();
	    				System.out.println(source.toString()+"->"+target.toString()+
	    						                     ": minimo="+tamanhos.first()+
	    						                     "; maximo="+tamanhos.last()+
	    						                     "; medio="+medio+" paths: " + l);
	    			} 
	    		}
	    	}
	    }    
	}
}
