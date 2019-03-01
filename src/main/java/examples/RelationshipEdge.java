//Teoria dos Grafos - UFCG

package examples;

import java.util.Map;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.Attribute;

/*
 * > Duvidas sobre como eh a implementacao de alguma classe ou metodo?
 * 
 * @link https://jgrapht.org/javadoc/ <- JavaDoc JGraphT
 * @link https://github.com/rcpoison/jgrapht <- GitHub JGraphT
 */
/**
 * Entidade que representa uma aresta de relacao. A aresta possui dois vertices
 * que identificam suas extremidades e atributos relacionados. Tambem possui os
 * metodos basicos: getLable, getV1, getV2, getNeighbour, equals, hashCode e
 * toString.
 */
public class RelationshipEdge extends DefaultEdge {
	// Arestas com labels e demais atributos - Grafos Nao-direcionados

	/**
	 * Atributo responsavel pela compatibilidade da serializacao.
	 */
	private static final long serialVersionUID = 8238755873387699328L;

	// atributos da classe RelationshipEdge.
	private Object v1;
	private Object v2;
	private Map<String, Attribute> att;

	/**
	 * Construtor da classe RelationshipEdge. Instancializa os atributos da classe.
	 * 
	 * @param v1  vertice 1
	 * @param v2  vertice 2
	 * @param att atributos da aresta
	 */
	public RelationshipEdge(Object v1, Object v2, Map<String, Attribute> att) {
		this.v1 = v1;
		this.v2 = v2;
		this.att = att;
	}

	/**
	 * Retorna o lable de uma aresta de relacao.
	 * 
	 * @return string contendo o lable da aresta
	 */
	public Object getLabel() {
		Object o = att.get("label"); // captura o lable de uma aresta
		if (o == null) { // analisa se este eh nulo. se for verdade...
			return ("{" + v1 + "," + v2 + "}"); // retorna como descricao "{v1,v2}"
		} else // caso contrario...
			return o; // retorna o lable capturado.
	}

	/**
	 * Responsavel por retornar o vertice que esta salvo como v1.
	 * 
	 * @return o objeto que esta salvo como vertice v1.
	 */
	public Object getV1() {
		return v1;
	}

	/**
	 * Retorna o vertice que esta salvo como v2.
	 * 
	 * @return o objeto que esta salvo como vertice v2.
	 */
	public Object getV2() {
		return v2;
	}

	/**
	 * Retorna o vizinho de um vertice v passado como parametro.
	 * 
	 * @param v vertice o qual deseja-se saber qual seu vizinho
	 * @return vizinho do vertice passado como parametro.
	 */
	public Object getNeighbour(Object v) {
		if (v.equals(v1)) { // compara o vertice passado como paramentro com o vertice v1. caso sejam
							// iguais...
			return v2; // retorna que seu vizinho eh v2
		} else // caso contrario...
			return v1; // retorna que seu vizinho eh v1
	}

	/**
	 * Representacao inteira de uma determinada aresta.
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
	 * Realiza a comparacao entre duas arestas. Duas arestas sao ditas iguais caso
	 * possuam o mesmo lable.
	 * 
	 * @param e aresta a qual deseja-se comparar.
	 * @return boolean sinalizando se duas arestas sao iguais (true) ou nao (false).
	 */
	public boolean equals(RelationshipEdge e) {
		return (this.getLabel()).equals(e.getLabel());
	}

	/**
	 * Representacao textual de uma determinada aresta
	 */
	@Override
	public String toString() {
		Object o = att.get("label"); // captura o lable da aresta
		if (o == null) { // analisa se este eh nulo. se for...
			return ("{" + v1 + "," + v2 + "}"); // retorna uma representacao no formato "{v1,v2}"
		} else // caso contrario...
			return (att.get("label")).toString() + "->{" + v1 + ";" + v2 + "}"; // retorna uma representacao no formato "lable->{v1,v2}"
	}

}
