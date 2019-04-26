package examples;

import org.jgrapht.Graph;
import org.jgrapht.generate.BarabasiAlbertForestGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

public class Aula12ForestGenerator {
	public static void main(String[] args) {

		// Generating 1 single tree
		Graph<String, DefaultEdge> tree = new SimpleGraph<>(SupplierUtil.createStringSupplier(), 
		                                                      SupplierUtil.createDefaultEdgeSupplier(), false);
		BarabasiAlbertForestGenerator <String, DefaultEdge> treeGen = new BarabasiAlbertForestGenerator <> (1,20);
		treeGen.generateGraph(tree,null);
		MyJGraphTUtil.createAndShowGui(tree, "Tree", false, false, true, true, MyJGraphTUtil.layout_type.HIERARCHICAL);
		
		// Generating 1 forest with 5 tree
		Graph<String, DefaultEdge> forest = new SimpleGraph<>(SupplierUtil.createStringSupplier(), 
		                                                      SupplierUtil.createDefaultEdgeSupplier(), false);
		BarabasiAlbertForestGenerator <String, DefaultEdge> forestGen = new BarabasiAlbertForestGenerator <> (5,30);
		forestGen.generateGraph(forest,null);
		MyJGraphTUtil.createAndShowGui(forest, "Forest", false, false, true, true, MyJGraphTUtil.layout_type.HIERARCHICAL);

	}
}
