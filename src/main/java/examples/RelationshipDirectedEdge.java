// Teoria dos Grafos - UFCG

package examples;

import java.util.HashMap;
import java.util.Map;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.Attribute;
import org.jgrapht.io.AttributeType;
import org.jgrapht.io.DefaultAttribute;

/*
 * > Duvidas sobre como eh a implementacao de alguma classe ou metodo?
 * 
 * @link https://jgrapht.org/javadoc/ <- JavaDoc JGraphT
 * @link https://github.com/rcpoison/jgrapht <- GitHub JGraphT
 */
/**
 * Entidade que representa uma aresta direcionada. A classe extende DefaultEdge
 * (aresta padrao). Classe possui os metodos basicos: getLable, getSource,
 * getTarget, getNeighbour, equals, hashCode e toString.
 */
public class RelationshipDirectedEdge extends DefaultEdge {
	// Arcos com labels e demais atributos - Grafos Direcionados

	/**
	 * Atributo responsavel pela compatibilidade da serializacao.
	 */
	private static final long serialVersionUID = -7887238603558924507L;

	// Atributos da classe RelationshipDirectedEdge.
	private Object v1;
	private Object v2;
	private Map<String, Attribute> att;

	/**
	 * Construtor da classe RelationshipDirectedEdge. Inicialisa os atributos da
	 * classe.
	 * 
	 * @param v1  vertice cauda da aresta
	 * @param v2  vertice cabeca da aresta
	 * @param att atributos da aresta (muito utilizado para importacao de grafos)
	 */
	public RelationshipDirectedEdge(Object v1, Object v2, Map<String, Attribute> att) {
		this.v1 = v1;
		this.v2 = v2;
		this.att = att;
	}
	public RelationshipDirectedEdge (Object v1, Object v2, String label) {
		this.v1 = v1;
		this.v2 = v2;
		att = new HashMap <String,Attribute> ();
		att.put("label",new DefaultAttribute<String>(label,AttributeType.STRING));		
	}

	/**
	 * Responsavel por retornar o lable de um determinado vertice.
	 * 
	 * @return string contendo uma representacao textual do lable da aresta.
	 */
	public String getLabel() {
		Object o = att.get("label"); // captura o label da aresta
		if (o == null) { // analisa se este eh nulo. se for...
			return ("(" + v1 + ":" + v2 + ")"); // retorna um label na forma "(cauda:cabeca)"
		} else // caso contrario...
			return o.toString(); // retorna o proprio label capturado.
	}

	/**
	 * Retorna o vertice cauda da aresta.
	 */
	public Object getSource() {
		return v1;
	}

	/**
	 * Retorna o vertive cabeca da aresta.
	 */
	public Object getTarget() {
		return v2;
	}

	/**
	 * Retorna o vertice relacionado (vizunho) de um determinado vertice.
	 * 
	 * @param v vertice o qual deseja que se retorne o seu vizinho.
	 * @return vertice vizinho do vertice passado como parametro.
	 */
	public Object getNeighbour(Object v) {
		if (v.equals(v1)) {
			return v2;
		} else
			return v1;
	}

	/**
	 * Representacao inteire de uma dada aresta direcionada.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
		result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
		return result;
	}

	/**
	 * Realiza a comparacao de duas arestas. Duas arestas sao ditas iguais se
	 * possuem o mesmo lable.
	 * 
	 * @param e aresta a qual deseja-se comparar.
	 * @return boolean sinalizando se duas arestas sao iguais (true) ou nao (false).
	 */
	public boolean equals(RelationshipDirectedEdge e) {
		return (this.getLabel()).equals(e.getLabel());
	}

	/**
	 * Representacao textual de uma determinada aresta.
	 */
	@Override
	public String toString() {
		Object o = att.get("label"); // captura o lable da aresta
		if (o == null) { // analisa se este eh nulo. se for...
			return ("(" + v1 + ":" + v2 + ")"); // retorna uma descricao no formato "(cauda:cabeca)"
		} else // caso contrario...
			return o.toString() + "->(" + v1 + ":" + v2 + ")"; // retorna uma descricao no formato
																// "lable->(cauda:cabeca)"
	}

}
