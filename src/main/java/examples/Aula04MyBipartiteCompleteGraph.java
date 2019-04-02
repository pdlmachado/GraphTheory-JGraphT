// Teoria dos Grafos - UFCG
// Cria um Grafo Completo de n vértices
// Adaptada da classe CompleteGraphDemo disponível na JGraphT

package examples;

import org.jgrapht.*;
import org.jgrapht.generate.*;
import org.jgrapht.graph.*;
import org.jgrapht.util.SupplierUtil;

import java.util.*;

public final class Aula04MyBipartiteCompleteGraph {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        System.out.print("Quantos vértices na partição 1? ");
        int p1 = keyboard.nextInt();
        System.out.print("Quantos vértices na partição 2? ");
        int p2 = keyboard.nextInt();
        keyboard.close();
    	
        Graph<String, DefaultEdge> completeBGraph = 
        		new SimpleGraph<>(SupplierUtil.createStringSupplier(), 
        				          SupplierUtil.createDefaultEdgeSupplier(), false);

        CompleteBipartiteGraphGenerator<String, DefaultEdge> completeGenerator = 
        		new CompleteBipartiteGraphGenerator<>(p1,p2);

        completeGenerator.generateGraph(completeBGraph);
        
        Iterator<String> iter = completeBGraph.vertexSet().iterator();
        while (iter.hasNext()) {
            String vertex = iter.next();
            System.out.println("Vizinhos de " + vertex + ": " + Graphs.neighborSetOf(completeBGraph, vertex));
        }        
    }
}
