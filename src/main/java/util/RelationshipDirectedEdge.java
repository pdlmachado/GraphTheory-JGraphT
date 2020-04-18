// Teoria dos Grafos - UFCG

package util;

import java.util.HashMap;
import java.util.Map;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;

public class RelationshipDirectedEdge extends DefaultEdge {

	private static final long serialVersionUID = -7887238603558924507L;

	// Atributos da classe RelationshipDirectedEdge

	private Map<String, Attribute> att;
	
	public RelationshipDirectedEdge() {
		super();
		att = new HashMap <String,Attribute> ();
	}

	public RelationshipDirectedEdge (String label) {
		super();
		att = new HashMap <String,Attribute> ();
		att.put("label",new DefaultAttribute<String>(label,AttributeType.STRING));		
	}

	public String getLabel() {
		Object o = att.get("label"); // captura o label da aresta
		if (o == null) { // analisa se este eh nulo. se for...
			return ("(" + getSource() + ":" + getTarget() + ")"); // retorna um label na forma "(cauda:cabeca)"
		} else // caso contrario...
			return o.toString(); // retorna o proprio label capturado.
	}

	public Object getNeighbour(Object v) {
		if (v.equals(getSource())) {
			return getTarget();
		} else
			return getSource();
	}
	
	public Object getVSource() {
		return getSource();
	}
	
	public Object getVTarget() {
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
		result = prime * result + ((getTarget() == null) ? 0 : getTarget().hashCode());
		return result;
	}

	public boolean equals(RelationshipDirectedEdge e) {
		return (this.getLabel()).equals(e.getLabel());
	}

    @Override
	public String toString() {
		Object o = att.get("label"); // captura o label da aresta
		if (o == null) { // analisa se este eh nulo. se for...
			return ("(" + getSource() + ":" + getTarget() + ")"); // retorna uma descricao no formato "(cauda:cabeca)"
		} else // caso contrario...
			return o.toString() + "->(" + getSource() + ":" + getTarget() + ")"; // retorna uma descricao no formato
																// "label->(cauda:cabeca)"
	}

}
