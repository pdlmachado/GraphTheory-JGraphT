// Teoria dos Grafos - UFCG
// Aresta para grafos não-direcionados com atributos, incluindo um label (rótulo)

package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.DefaultAttribute;

import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;

public class RelationshipEdge extends DefaultEdge {
	private static final long serialVersionUID = 8238755873387699328L;
	private Map<String, Attribute> att;
	boolean directed;

	// Construtores
	public RelationshipEdge (boolean d) {
		// To unify RelationshipEdge and RelationshipDirectedEdge
		super();
		directed = d;
		att = new HashMap <String,Attribute> ();
	}
	
	public RelationshipEdge () {
		super();
		//directed = d;
		att = new HashMap <String,Attribute> ();		
	}
	
	public RelationshipEdge (String label) {
		super();
		att = new HashMap <String,Attribute> ();
		att.put("label",new DefaultAttribute<String>(label,AttributeType.STRING));
	}
	
    // Métodos de Acesso	

	public String getLabel() {
		Object o = att.get("label"); 
		if (o == null) { 
			return ("{" + getSource() + "," + getTarget() + "}"); 
		} else 
			return o.toString(); 
	}
	public Object getNeighbor(Object v) {
		if (v.equals(getSource())) { 
			return getTarget(); 
		} else 
			return getSource(); 
	}
	
	public Object getV1() {
		return getSource();
	}
	
	public Object getV2() {
		return getTarget();
	}                           
	
	public Map<String,Attribute> getAtts () {
		return att;
	}
	
	public Object getAtt(String field) {
		return att.get(field); 
	}
	
	public void setAttrs (Map<String, Attribute> attrs) {
		this.att = attrs;
	}
	
	public void setAtt (String key, Attribute value) {
		this.att.put(key, value);
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
		result = prime * result + ((getTarget() == null) ? 0 : getTarget().hashCode());
		return result;
	}

	public boolean equals(RelationshipEdge e) {
		boolean result = this.getLabel().equals(e.getLabel());
		if ((this.getSource() != null) && (this.getTarget() != null) 
				&& (e.getSource() != null) && (e.getTarget() != null)) {
			result = result &&
					((this.getSource().equals(e.getSource()) && this.getTarget().equals(e.getTarget())) ||
					 (this.getSource().equals(e.getTarget()) && this.getTarget().equals(e.getSource())));
		} else result = (this == e);
		return result;
	}

	public String toString() {
		Object o = att.get("label"); // captura o label da aresta
		if (o == null) { // analisa se este eh nulo. se for...
			return ("{" + getSource() + "," + getTarget() + "}"); // retorna uma representacao no formato "{v1,v2}"
		} else // caso contrario...
			return o.toString();
	}
	
	public String toStringAtt() {
		String s = new String(getSource().toString());
		s = s + "," + getTarget().toString();
		Iterator <String> keys = att.keySet().iterator();
		while (keys.hasNext()) {
			s = s + "," + att.get(keys.next());
		}
		return s;
	} 
}
