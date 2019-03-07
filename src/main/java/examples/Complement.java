// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.*;
import org.jgrapht.generate.*;
import org.jgrapht.graph.*;

public final class Complement {
    public static void main(String[] args) {
    	
		Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
		
		g.addVertex("MA");  	g.addVertex("PI");  	g.addVertex("CE");  
		g.addVertex("RN");		g.addVertex("PB");  	g.addVertex("PE");  	
		g.addVertex("AL");  	g.addVertex("SE");		g.addVertex("BA");
		
		g.addEdge("MA","PI");  	g.addEdge("PI","CE");  	g.addEdge("PI","PE");
		g.addEdge("PI","BA");	g.addEdge("CE","PE");	g.addEdge("CE","PB");
		g.addEdge("CE","RN");	g.addEdge("PE","PB");	g.addEdge("RN","PB");
		g.addEdge("PE","BA");	g.addEdge("PE","AL");	g.addEdge("BA","AL");
		g.addEdge("BA","SE");	g.addEdge("AL","SE");
		
		System.out.println("Vértices: " + g.vertexSet());
		System.out.println("Arestas: " + g.edgeSet());

        Graph<String, DefaultEdge> complement = new SimpleGraph<>(DefaultEdge.class);

        ComplementGraphGenerator<String, DefaultEdge> cGenerator =
            new ComplementGraphGenerator<>(g);

        cGenerator.generateGraph(complement);
		System.out.println("Arestas: " + complement.edgeSet());	
    }
}
    

