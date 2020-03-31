// Teoria dos Grafos - UFCG
// Cria um Grafo Completo de n vértices
// Adaptada da classe CompleteGraphDemo disponível na JGraphT

package classexamples;

import org.jgrapht.*;
import org.jgrapht.generate.*;
import org.jgrapht.graph.*;
import org.jgrapht.util.SupplierUtil;

import util.PrintUtil;
import util.VertexEdgeUtil;

import java.util.*;

public final class Aula04CompleteGraph {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        System.out.print("Quantos vértices? ");
        int n = keyboard.nextInt();
        keyboard.close();
    	
        Graph<String, DefaultEdge> completeGraph = 
        		new SimpleGraph<>(VertexEdgeUtil.createStringVVertexSupplier(), 
        				          SupplierUtil.createDefaultEdgeSupplier(), false);

        CompleteGraphGenerator<String, DefaultEdge> completeGenerator = 
        		new CompleteGraphGenerator<>(n);

        completeGenerator.generateGraph(completeGraph);
        
        PrintUtil.printGraph(completeGraph);
        
        Iterator<String> iter = completeGraph.vertexSet().iterator();
        while (iter.hasNext()) {
            String vertex = iter.next();
            System.out.println("Vizinhos de " + vertex + ": " + Graphs.neighborSetOf(completeGraph, vertex));
        }
    }
}
