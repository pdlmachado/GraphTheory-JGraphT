package util;

import java.util.Collection;

import javax.swing.JFrame;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultListenableGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.orthogonal.mxOrthogonalLayout;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxStyleUtils;

public class DrawUtil {

	public enum layout_type {CIRCLE,ORGANIC,HIERARCHICAL,ORTHOGONAL};
	
	// Graphic view for directed graphs
	public static <V,E> void createAndShowGui(Graph <V,E> graph, String frameLabel, 
			                                  boolean directed, 
			                                  boolean danglingEdges, 
			                                  boolean labelsVisible, 
			                                  boolean labelsClipped, 
			                                  layout_type layoutType
			) {

		JFrame frame = new JFrame(frameLabel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ListenableGraph<V,E> g = new DefaultListenableGraph <> (graph);
		JGraphXAdapter<V,E> graphAdapter = new JGraphXAdapter<V,E>(g);
		graphAdapter.setAllowDanglingEdges(danglingEdges);
		graphAdapter.setLabelsVisible(labelsVisible);
		graphAdapter.setLabelsClipped(labelsClipped);
		graphAdapter.setCellsMovable(true);
		graphAdapter.setCellsSelectable(true);
		graphAdapter.setEdgeLabelsMovable(true);

		mxIGraphLayout layout;
		switch (layoutType) {
			case CIRCLE : layout = new mxCircleLayout(graphAdapter); break;
			case ORGANIC: layout = new mxFastOrganicLayout(graphAdapter);break;
			case HIERARCHICAL : layout = new mxHierarchicalLayout(graphAdapter);break;
			case ORTHOGONAL: layout = new mxOrthogonalLayout(graphAdapter);break;
			default: layout = new mxCircleLayout(graphAdapter);break;
		}
		layout.execute(graphAdapter.getDefaultParent());

		mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
		mxGraphModel graphModel  = (mxGraphModel)graphComponent.getGraph().getModel(); 
		Collection<Object> cells =  graphModel.getCells().values(); 
		if (directed == false) {
			mxStyleUtils.setCellStyles(graphComponent.getGraph().getModel(), 
				    cells.toArray(), mxConstants.STYLE_ENDARROW, mxConstants.NONE);
		}
        frame.add(graphComponent);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	
	}
}
