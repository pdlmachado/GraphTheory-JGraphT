// Teoria dos Grafos - UFCG
// Aresta para grafos não-direcionados com atributos, incluindo um label (rótulo)

package examples;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;

public class RelationshipEdge extends DefaultEdge {
	private static final long serialVersionUID = 8238755873387699328L;
	private Map<String, Attribute> att;

	// Construtores
	public RelationshipEdge () {
		super();
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
		return getSource();
	}

	public void setAttrs (Map<String, Attribute> attrs) {
		this.att = attrs;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
		result = prime * result + ((getTarget() == null) ? 0 : getTarget().hashCode());
		return result;
	}

	public boolean equals(RelationshipEdge e) {
		return (this.getLabel()).equals(e.getLabel());
	}
	

	public String toString() {
		Object o = att.get("label"); // captura o label da aresta
		if (o == null) { // analisa se este eh nulo. se for...
			return ("{" + getSource() + "," + getTarget() + "}"); // retorna uma representacao no formato "{v1,v2}"
		} else // caso contrario...
			return (att.get("label")).toString() + "->{" + getSource() + "," + getTarget() + "}"; // retorna uma representacao no formato "lable->{v1,v2}"
	}

}
