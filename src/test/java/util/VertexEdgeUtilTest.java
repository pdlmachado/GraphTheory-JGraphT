/**
 * 
 */
package util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author patricia
 *
 */


class VertexEdgeUtilTest {

	static Graph <DefaultVertex, RelationshipEdge> undirected;
	static Set <DefaultVertex> vU;
	static Set <RelationshipEdge> eU;
	static DefaultVertex v0;
	static DefaultVertex v1;
	static DefaultVertex v2;
	static RelationshipEdge e01;
	static RelationshipEdge e02;
	static RelationshipEdge e12;
	
	static Graph <DefaultVertex, RelationshipDirectedEdge> directed;
	static Set <DefaultVertex> vD;
	static Set <RelationshipDirectedEdge> eD;	
	static RelationshipDirectedEdge ed01;
	static RelationshipDirectedEdge ed02;
	static RelationshipDirectedEdge ed12;
	
	static Graph<String,RelationshipEdge> pseudograph;
	static Set <RelationshipEdge> pE;
	static RelationshipEdge e1;
	static RelationshipEdge e2;
	static RelationshipEdge e3;
	static RelationshipEdge e4;
	
	///////////////////////////////////////////
	// Test Data
	static void buildUndirectedGraph () {
		Map<String, Attribute> att1;
		Map<String, Attribute> att2;
		undirected = new SimpleGraph <> (RelationshipEdge.class);
		att1 = new HashMap <> ();
		att1.put("label", new DefaultAttribute<String>("label1",AttributeType.STRING));
		att1.put("anyint", new DefaultAttribute<Integer>(0,AttributeType.INT)); 
		att2 = new HashMap <> ();
		att2.put("anyfloat", new DefaultAttribute<Double>(1.0,AttributeType.FLOAT));
		att2.put("anyobject", new DefaultAttribute<Object>(new HashSet<Object>(),AttributeType.UNKNOWN)); 
		v0 = new DefaultVertex("v0");
		v1 = new DefaultVertex("v1", att1);
		v2 = new DefaultVertex("v2", att2);
		undirected.addVertex(v0);
		undirected.addVertex(v1);
		undirected.addVertex(v2);
		e01 = new RelationshipEdge("e01");
		undirected.addEdge(v0, v1, e01);
		e12 = new RelationshipEdge("e02");
		undirected.addEdge(v1, v2, e12);
		e02 = new RelationshipEdge("e02");
		undirected.addEdge(v2, v0, e02);
		vU = undirected.vertexSet();
		eU = undirected.edgeSet();
	}
	
	static void buildDirectedGraph () {
		directed = new DefaultDirectedGraph <> (RelationshipDirectedEdge.class);
		directed.addVertex(v0);
		directed.addVertex(v1);
		directed.addVertex(v2);
		ed01 = new RelationshipDirectedEdge("ed01");
		directed.addEdge(v0, v1, ed01);
		ed12 = new RelationshipDirectedEdge("ed02");
		directed.addEdge(v1, v2, ed12);
		ed02 = new RelationshipDirectedEdge("ed02");
		directed.addEdge(v0, v2, ed02);
		vD = directed.vertexSet();
		eD = directed.edgeSet();
	}
	
	static void buildPseudoGraph() {
		pseudograph = new Pseudograph <String,RelationshipEdge> (RelationshipEdge.class);
		pseudograph.addVertex("a");
		pseudograph.addVertex("b");
		pseudograph.addVertex("c");
		e1 = new RelationshipEdge("ab1");
		pseudograph.addEdge("a","b",e1);
		e2 = new RelationshipEdge("ab2");
		pseudograph.addEdge("a","b",e2);
		e3 = new RelationshipEdge("ac");
		pseudograph.addEdge("a","c",e3);
		e4 = new RelationshipEdge("bb");
		pseudograph.addEdge("b","b",e4);
		pE = pseudograph.edgeSet();
	}
	
	@BeforeAll
	static void beforeAll () {
		buildUndirectedGraph();
		buildDirectedGraph();
		buildPseudoGraph();
	}
		
    // getVertexfromLabelTest
	static Stream<Arguments> getVFLDataProvider () {
		return Stream.of (
				Arguments.of(vU, "label1", v1),
				Arguments.of(vU, "v1", null),
				Arguments.of(vU, "v0", v0),
				Arguments.of(vU, "v2", v2),
				Arguments.of(vU, "any", null)
			);
	}

	
	@ParameterizedTest
    @MethodSource("getVFLDataProvider")
	void getVertexfromLabelTest (Set <DefaultVertex> V, String label, DefaultVertex expected) {
		assertEquals(expected,VertexEdgeUtil.getVertexfromLabel(V, label));
		
	}
	
    // getVertexfromIdTest
	static Stream<Arguments> getVFIDataProvider () {
		return Stream.of (
				Arguments.of(vU, "label1", null),
				Arguments.of(vU, "v1", v1),
				Arguments.of(vU, "v0", v0),
				Arguments.of(vU, "v2", v2),
				Arguments.of(vU, "any", null)
			);
	}

	@ParameterizedTest
    @MethodSource("getVFIDataProvider")
	void getVertexfromIdTest (Set <DefaultVertex> V, String id, DefaultVertex expected) {
		assertEquals(expected,(VertexEdgeUtil.getVertexfromId(vU, id)));
		
	}
	
	// getEdgefromLabel
	static Stream<Arguments> getEFLDataProvider () {
		return Stream.of (
				Arguments.of(pE, "ab1", e1),
				Arguments.of(pE, "bb", e4),
				Arguments.of(pE, "fg", null),
				Arguments.of(pE, null, null)
			);
	}

	@ParameterizedTest
    @MethodSource("getEFLDataProvider")
	void getEdgeFromLabelTest (Set <RelationshipEdge> E, String label, RelationshipEdge expected) {
		assertEquals(expected,(VertexEdgeUtil.getEdgefromLabel(E,label)));
		
	}
	
	// getEdgefromVertexLabels
	static Stream<Arguments> getEFVLDataProvider () {
		return Stream.of(
				Arguments.of(eU, vU, "v0", "label1", e01),
				Arguments.of(eU, vU, "v2", "v0", e02),
				Arguments.of(eU, vU, "v2", "label1", e12),
				Arguments.of(eU, vU, "v0", "v0", null),
				Arguments.of(eU, vU, null, null, null),
				Arguments.of(null,null,null,null,null)
				);	
	}
	
	@ParameterizedTest
	@MethodSource("getEFVLDataProvider")
	void getEdgefromVertexLabels (Set<RelationshipEdge> E, Set<DefaultVertex> V, String l1, String l2, RelationshipEdge expected) {
		assertEquals(expected, VertexEdgeUtil.getEdgefromVertexLabels(E, V, l1, l2));
	}

	// getDEdgefromVertexLabels
	static Stream<Arguments> getDEFVLDataProvider () {
		return Stream.of(
				Arguments.of(eD, vD, "v0", "label1", ed01),
				Arguments.of(eD, vD, "v0", "v2", ed02),
				Arguments.of(eD, vD, "v2", "label1", null),
				Arguments.of(eD, vD, "label1", "v2", ed12),
				Arguments.of(eD, vD, null, null, null),
				Arguments.of(null,null,null,null,null)
				);	
	}
	
	@ParameterizedTest
	@MethodSource("getDEFVLDataProvider")
	void getDEdgefromVertexLabels (Set<RelationshipDirectedEdge> E, Set<DefaultVertex> V, String l1, String l2, RelationshipDirectedEdge expected) {
		assertEquals(expected, VertexEdgeUtil.getDEdgefromVertexLabels(E, V, l1, l2));
	}

}
