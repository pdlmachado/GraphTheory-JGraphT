// Teoria dos Grafos - UFCG

package examples;

import java.util.HashMap;
import java.util.Map;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.Attribute;
import org.jgrapht.io.AttributeType;
import org.jgrapht.io.DefaultAttribute;

public class RelationshipDirectedEdge extends DefaultEdge {

	private static final long serialVersionUID = -7887238603558924507L;

	// Atributos da classe RelationshipDirectedEdge
	private Object source;
	private Object target;
	private Map<String, Attribute> att;

	public RelationshipDirectedEdge(Object s, Object t, Map<String, Attribute> att) {
		this.source = s;
		this.target = t;
		this.att = att;
	}
	public RelationshipDirectedEdge (Object s, Object t, String label) {
		this.source = s;
		this.target = t;
		att = new HashMap <String,Attribute> ();
		att.put("label",new DefaultAttribute<String>(label,AttributeType.STRING));		
	}

	public String getLabel() {
		Object o = att.get("label"); // captura o label da aresta
		if (o == null) { // analisa se este eh nulo. se for...
			return ("(" + source + ":" + target + ")"); // retorna um label na forma "(cauda:cabeca)"
		} else // caso contrario...
			return o.toString(); // retorna o proprio label capturado.
	}

	public Object getSource() {
		return source;
	}

	public Object getTarget() {
		return target;
	}


	public Object getNeighbour(Object v) {
		if (v.equals(source)) {
			return target;
		} else
			return source;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}

	public boolean equals(RelationshipDirectedEdge e) {
		return (this.getLabel()).equals(e.getLabel());
	}

    @Override
	public String toString() {
		Object o = att.get("label"); // captura o label da aresta
		if (o == null) { // analisa se este eh nulo. se for...
			return ("(" + source + ":" + target + ")"); // retorna uma descricao no formato "(cauda:cabeca)"
		} else // caso contrario...
			return o.toString() + "->(" + source + ":" + target + ")"; // retorna uma descricao no formato
																// "label->(cauda:cabeca)"
	}

}
