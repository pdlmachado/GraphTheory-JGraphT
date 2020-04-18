package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Stream;

import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DefaultVertexTest {

	static Map<String, Attribute> att1;
	static Map<String, Attribute> att2;
	static DefaultVertex v0;
	static DefaultVertex v1;
	static DefaultVertex v2;
	
	
	@BeforeAll
    static void beforeAll() {
		att1 = new HashMap <> ();
		att1.put("label",new DefaultAttribute<String>("label1",AttributeType.STRING));
		att1.put("anyint", new DefaultAttribute<Integer>(0,AttributeType.INT)); 
		att2 = new HashMap <> ();
		att2.put("anyfloat",new DefaultAttribute<Double>(1.0,AttributeType.FLOAT));
		att2.put("anyobject", new DefaultAttribute<Object>(new HashSet<Object>(),AttributeType.UNKNOWN)); 
		v0 = new DefaultVertex("v0");
		v1 = new DefaultVertex("v1",att1);
		v2 = new DefaultVertex("v2",att2);
    }
	
	@BeforeEach
    void beforeEach() {
		beforeAll();
    }
		
	// getId
	static Stream <Arguments> getIdDataProvider () {
		return Stream.of(
				Arguments.of(v0,"v0"),
				Arguments.of(v1,"v1"),
				Arguments.of(v2,"v2")
				);
	}
	
	@ParameterizedTest
	@MethodSource("getIdDataProvider")
	void getIdTest (DefaultVertex v, String id) {
		assertEquals(id,v.getId());
	}
	
	// getLabel
	static Stream <Arguments> getLabelDataProvider () {
		return Stream.of(
				Arguments.of(v0,"v0"),
				Arguments.of(v1,"label1"),
				Arguments.of(v2,"v2"));
	}
	
	@ParameterizedTest
	@MethodSource("getLabelDataProvider")
	void getLabelTest (DefaultVertex v, String label) {
		assertEquals(label,v.getLabel().toString());
	}
	
	// getAtts
	static Stream <Arguments> getAttsDataProvider () {
		return Stream.of(
				Arguments.of(v0,new HashMap <> ()),
				Arguments.of(v1,att1),
				Arguments.of(v2,att2)
				);
	}
	
	@ParameterizedTest
	@MethodSource("getAttsDataProvider")
	void getAttsTest (DefaultVertex v, Map<String, Attribute> att) {
		assertEquals(att,v.getAtts());
	}
	
	// getAtts
	static Stream <Arguments> getAttDataProvider () {
		return Stream.of(
				Arguments.of(v0,"id",null),
				Arguments.of(v1,"label",new DefaultAttribute<String>("label1",AttributeType.STRING)),
				Arguments.of(v1,"anyint", new DefaultAttribute<Integer>(0,AttributeType.INT)),
				Arguments.of(v2,"anyfloat", new DefaultAttribute<Double>(1.0,AttributeType.FLOAT)),
				Arguments.of(v2,"anyobject", new DefaultAttribute<Object>(new HashSet<Object>(),AttributeType.UNKNOWN)),
				Arguments.of(v2,"invalid",null)
				);
	}
	
	@ParameterizedTest
	@MethodSource("getAttDataProvider")
	void getAttTest (DefaultVertex v, String key, Attribute value) {
		assertEquals(value,v.getAtt(key));
	}
	
	// setAttrs
	static Stream <Arguments> setAttrsDataProvider () {
		return Stream.of(
				Arguments.of(v0,att1),
				Arguments.of(v1,att2),
				Arguments.of(v2,att1),
				Arguments.of(v2,new HashMap <> ())
				);
	}
	
	@ParameterizedTest
	@MethodSource("setAttrsDataProvider")
	void setAttrsTest (DefaultVertex v, Map<String, Attribute> att) {
		Map<String, Attribute> oldatt = v.getAtts();
		v.setAttrs(att);
		assertEquals(att,v.getAtts());
		assertNotSame(oldatt,v.getAtts());
	}
	
	@Test
	void setAttrsNullTest () {
		DefaultVertex v = new DefaultVertex("v");
		Map<String, Attribute> att = v.getAtts();
		v.setAttrs(null);
		assertSame(att,v.getAtts(),"Not Set atts to null");
	}
	
	// setAtt
	static Stream <Arguments> setAttDataProvider () {
		return Stream.of(
				Arguments.of(v0,"test",new DefaultAttribute<String>("label0",AttributeType.STRING)),
				Arguments.of(v1,"label",new DefaultAttribute<String>("label1alt",AttributeType.STRING)),
				Arguments.of(v1,"anyint", new DefaultAttribute<Integer>(20,AttributeType.INT)),
				Arguments.of(v2,"anyfloat", new DefaultAttribute<Double>(-1.0,AttributeType.FLOAT)),
				Arguments.of(v2,"anyobject", new DefaultAttribute<Object>(new ArrayList<Object>(),AttributeType.UNKNOWN)),
				Arguments.of(v2,"invalid",null)
				);
	}
	
	@ParameterizedTest
	@MethodSource("setAttDataProvider")
	void setAttTest (DefaultVertex v, String key, Attribute value) {
		v.setAtt(key, value);
		assertEquals(value,v.getAtt(key));
	}
	
	// equals
	static Stream <Arguments> equalsDataProvider () {
		return Stream.of(
				Arguments.of(v0,v0,true),
				Arguments.of(v1,v1,true),
				Arguments.of(new DefaultVertex("v0"),v0, true),
				//Arguments.of(null, v0, false), // null pointer exception as null cannot receive calls.
				Arguments.of(v2, null, false),
				Arguments.of(new DefaultVertex("v8",att1), v1, false),
				Arguments.of(v1, new DefaultVertex("v8",att1), false),
				Arguments.of(v1, new DefaultVertex("v1",att2), false)
				);
	}
	
	@ParameterizedTest
	@MethodSource("equalsDataProvider")
	void equalsTest (DefaultVertex vertex1, DefaultVertex vertex2, boolean expected) {
		assertTrue(vertex1.equals(vertex2)==expected);
	}
	
}
