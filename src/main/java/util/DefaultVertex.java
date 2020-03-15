// Teoria dos Grafos - UFCG
// Vértice com id e mapa de atributos, incluindo o label (rótulo)

package util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.jgrapht.nio.Attribute;

public class DefaultVertex implements Serializable {

	private static final long serialVersionUID = -4861285584479124799L;
	private String id;
	private Map<String, Attribute> att;

    // Construtores
	public DefaultVertex(String id, Map<String, Attribute> att) {
		this.id = id;
		this.att = att;
	}
	public DefaultVertex(String id) {
		this.id = id;
		this.att = new HashMap <String,Attribute> ();
	}

	// Métodos de Acesso
	public String getId() {
		return id;
	}
	
	public String getLabel() {
		String label;
		try {
		   label = (att.get("label")).toString(); 
	    } catch (Exception e1) {
			try {
				   label = (att.get("ID")).toString(); 
			    } catch (Exception e2) {
				   label = id;
			    }
	    }
		return label;
	}

	public void setAttrs (Map<String, Attribute> attrs) {
		this.att = attrs;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(DefaultVertex v) {
		String s1 = this.getId();
		String s2 = v.getId();
		if (s2 instanceof String)
		return s1.equals(s2);
		else return false;
	}

	public String toString() {
		return this.getLabel();
	}

}