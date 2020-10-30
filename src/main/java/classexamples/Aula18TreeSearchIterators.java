package classexamples;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.ClosestFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.RandomWalkIterator;

import util.DefaultVertex;
import util.ImportUtil;
import util.PrintUtil;
import util.RelationshipEdge;
import util.VertexEdgeUtil;

public class Aula18TreeSearchIterators {
	
	private static final String NL = System.getProperty("line.separator");
	private static final String sep = System.getProperty("file.separator");
	// path do folder onde os grafos a serem carregados estão armazenados
	private static final String graphpathname = "." + sep + "src" + sep + "main" + sep +"java" + sep + "graphs" + sep;
	
	public static void main(String[] args) {
		Graph<DefaultVertex, RelationshipEdge> basegraph = 
				new SimpleGraph <> (VertexEdgeUtil.createDefaultVertexSupplier(), 
						VertexEdgeUtil.createRelationshipEdgeSupplier(), false);				
		basegraph = ImportUtil.importGraphGML(basegraph, graphpathname + "treesearchbase.gml");
	    PrintUtil.printGraph(basegraph);
		DefaultVertex startVertex = VertexEdgeUtil.getVertexfromLabel(basegraph.vertexSet(), "0");
		
		
		BreadthFirstIterator <DefaultVertex, RelationshipEdge> bfs = new BreadthFirstIterator <> (basegraph,startVertex);
		System.out.println("BreadthFirstIterator - ordem dos vértices visitados, partindo de " + startVertex);
        while (bfs.hasNext()) {
        	DefaultVertex v = bfs.next();
        	System.out.print(v);
        	if (bfs.hasNext()) {
        		System.out.print(",");
        	} else {
        		System.out.print(NL+NL);
        	}
        }
        
        
		DepthFirstIterator <DefaultVertex, RelationshipEdge> dfs = new DepthFirstIterator <> (basegraph,startVertex);
		System.out.println("DepthFirstIterator - ordem dos vértices visitados, partindo de " + startVertex);
        while (dfs.hasNext()) {
        	DefaultVertex v = dfs.next();
        	System.out.print(v);
        	if (dfs.hasNext()) {
        		System.out.print(",");
        	} else {
        		System.out.print(NL+NL);
        	}
        }
        
		ClosestFirstIterator <DefaultVertex, RelationshipEdge> cfs = new ClosestFirstIterator <> (basegraph,startVertex);
		System.out.println("ClosestFirstIterator - ordem dos vértices visitados, partindo de " + startVertex);
        while (cfs.hasNext()) {
        	DefaultVertex v = cfs.next();
        	System.out.print(v);
        	if (cfs.hasNext()) {
        		System.out.print(",");
        	} else {
        		System.out.print(NL+NL);
        	}
        }
        
        
		RandomWalkIterator <DefaultVertex, RelationshipEdge> rfs = new RandomWalkIterator <> (basegraph, startVertex);
		int count = basegraph.vertexSet().size();
		System.out.println("RandomWalkIterator - ordem dos vértices visitados), partindo de " + startVertex);
        while (rfs.hasNext()&&(count>=1)) {
        	DefaultVertex v = rfs.next();
        	System.out.print(v);
        	count--;
        	if (rfs.hasNext() && count != 0) {
        		System.out.print(",");
        	} else {
        		System.out.print(NL+NL);
        	}

        } 
	}
}
