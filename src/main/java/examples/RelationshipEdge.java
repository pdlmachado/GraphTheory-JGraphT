// Teoria dos Grafos - UFCG
// Aresta para grafos não-direcionados com atributos, incluindo um label (rótulo)

package examples;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.Attribute;
import org.jgrapht.io.AttributeType;
import org.jgrapht.io.DefaultAttribute;

public class RelationshipEdge extends DefaultEdge {
	private static final long serialVersionUID = 8238755873387699328L;
	private Object v1;
	private Object v2;
	private Map<String, Attribute> att;

	// Construtores
	public RelationshipEdge(Object v1, Object v2, Map<String, Attribute> att) {
		this.v1 = v1;
		this.v2 = v2;
		this.att = att;
	}
	public RelationshipEdge(Object v1, Object v2, String label) {
		this.v1 = v1;
		this.v2 = v2;
		att = new HashMap <String,Attribute> ();
		att.put("label",new DefaultAttribute<String>(label,AttributeType.STRING));		
	}

    // Métodos de Acesso	
	public String getLabel() {
		Object o = att.get("label"); 
		if (o == null) { 
			return ("{" + v1 + "," + v2 + "}"); 
		} else 
			return o.toString(); 
	}
	public Object getNeighbor(Object v) {
		if (v.equals(v1)) { 
			return v2; 
		} else 
			return v1; 
	}
	public Object getV1() {
		return v1;
	}
	public Object getV2() {
		return v2;
	}
	
	public Object getEdgeSource() {
		return v1;
	}
	public Object getEdgeTarget() {
		return v2;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
		result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
		return result;
	}

	public boolean equals(RelationshipEdge e) {
		return (this.getLabel()).equals(e.getLabel());
	}

	public String toString() {
		Object o = att.get("label"); // captura o lable da aresta
		if (o == null) { // analisa se este eh nulo. se for...
			return ("{" + v1 + "," + v2 + "}"); // retorna uma representacao no formato "{v1,v2}"
		} else // caso contrario...
			return (att.get("label")).toString() + "->{" + v1 + "," + v2 + "}"; // retorna uma representacao no formato "lable->{v1,v2}"
	}

}
