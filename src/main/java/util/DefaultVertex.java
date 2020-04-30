// Teoria dos Grafos - UFCG
// Updated JGraphT 1.4.0
// Vértice com id e mapa de atributos, incluindo o label (rótulo)

package util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
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
		   label = (att.get("label")).getValue();
	    } catch (Exception e1) {
			try {
				   label = (att.get("ID")).getValue(); 
			    } catch (Exception e2) {
				   label = id;
			    }
	    }
		return label;
	}
	
	public Map<String,Attribute> getAtts () {
		return att;
	}
	
	public Object getAtt(String field) {
		return att.get(field); 
	}

	public void setAttrs (Map<String, Attribute> attrs) {
		if (attrs != null) {
			this.att = attrs;
		}
	}
	
	public void setAtt (String key, Attribute value) {
		this.att.put(key, value);
	}
	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(DefaultVertex v) {
		if (this != null && v != null) {
			String s1 = this.getId();
			String s2 = v.getId();
			return s1.equals(s2) && this.getAtts().equals(v.getAtts());
		} else {
			return this == v;
		}
	}

	public String toString() {
		return this.getLabel();
	}
	
	public String toStringAtt() {
		String s = new String("id:" + getId());
		Iterator <String> keys = att.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			Attribute a = att.get(key);
			s = s + "," + key + ":" + a.toString();
		}
		return s;
	} 

}