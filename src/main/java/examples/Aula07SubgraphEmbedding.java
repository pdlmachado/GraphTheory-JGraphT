package examples;

import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.GraphMapping;
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;
import org.jgrapht.graph.SimpleGraph;

public class Aula07SubgraphEmbedding {

	public static void main(String[] args) {

		//Import Base Graph
	    Graph<DefaultVertex, RelationshipEdge> base = new SimpleGraph<>(RelationshipEdge.class);
	    MyJGraphTUtil.importGraphGML(base,"./src/main/java/graphs/K3-3.gml");
        MyJGraphTUtil.printGraph(base,"Base Graph:");
	    
	    //Import G1
	    Graph<DefaultVertex, RelationshipEdge> g1 = new SimpleGraph<>(RelationshipEdge.class);
	    MyJGraphTUtil.importGraphGML(g1,"./src/main/java/graphs/bp1.gml");
        MyJGraphTUtil.printGraph(g1,"Graph G1:");
        
	    //Import G2
	    Graph<DefaultVertex, RelationshipEdge> g2 = new SimpleGraph<>(RelationshipEdge.class);
	    MyJGraphTUtil.importGraphGML(g2,"./src/main/java/graphs/K2-2.gml");
        MyJGraphTUtil.printGraph(g2,"Graph G2:");
        
        VF2SubgraphIsomorphismInspector <DefaultVertex, RelationshipEdge> embeddingChecker = 
        		new VF2SubgraphIsomorphismInspector <> (base,g1);
	    Iterator <GraphMapping <DefaultVertex,RelationshipEdge>> it = embeddingChecker.getMappings();
	    if (it.hasNext()) {
	    	System.out.println("G1 is embedded in Base Graph. An example of a mapping: ");
	    	System.out.println(it.next());
	    } else {
	    	System.out.println("G1 is not embedded in Base Graph");
	    }
	    embeddingChecker = new VF2SubgraphIsomorphismInspector <> (base,g2);
	    it = embeddingChecker.getMappings();
	    if (it.hasNext()) {
	    	System.out.println("G2 is embedded in Base Graph. An example of a mapping: ");
	    	System.out.println(it.next());
	    } else {
	    	System.out.println("G2 is not embedded in Base Graph");
	    }


	}

}
