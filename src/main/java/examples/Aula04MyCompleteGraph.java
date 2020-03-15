// Teoria dos Grafos - UFCG
// Cria um Grafo Completo de n vértices
// Adaptada da classe CompleteGraphDemo disponível na JGraphT

package examples;

import org.jgrapht.*;
import org.jgrapht.generate.*;
import org.jgrapht.graph.*;
import org.jgrapht.util.SupplierUtil;

import java.util.*;

public final class Aula04MyCompleteGraph {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        System.out.print("Quantos vértices? ");
        int n = keyboard.nextInt();
        keyboard.close();
    	
        Graph<String, DefaultEdge> completeGraph = 
        		new SimpleGraph<>(MyJGraphTUtil.createStringVVertexSupplier(), SupplierUtil.createDefaultEdgeSupplier(), false);

        CompleteGraphGenerator<String, DefaultEdge> completeGenerator = 
        		new CompleteGraphGenerator<>(n);

        completeGenerator.generateGraph(completeGraph);
        
        Iterator<String> iter = completeGraph.vertexSet().iterator();
        while (iter.hasNext()) {
            String vertex = iter.next();
            System.out.println("Vizinhos de " + vertex + ": " + Graphs.neighborSetOf(completeGraph, vertex));
        }
    }
}
