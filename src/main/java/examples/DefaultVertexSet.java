// Teoria dos Grafos - UFCG

package examples;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * > Duvidas sobre como eh a implementacao de alguma classe ou metodo?
 * 
 * @link https://jgrapht.org/javadoc/ <- JavaDoc JGraphT
 * @link https://github.com/rcpoison/jgrapht <- GitHub JGraphT
 */
/**
 * Essa classe extende a classe HashSet e foi criada gracas a necessidade de
 * lidar com casos peculiares das atividades praticas. A classe tambem
 * implementa Comparable.
 * 
 * @param <V> o tipo dos elementos que seram contidos por esse Set
 */
public class DefaultVertexSet<V> extends HashSet<V> {
	// Para um conjunto de Vertices retorna o objeto vertice identificado pelo label

	/**
	 * Atrinbuto responsavel por manter a compatibilidade de serializacao.
	 */
	private static final long serialVersionUID = 2559544664561671961L;

	/**
	 * Retorna um vertice cujo lable eh igual ao lable passado como parametro.
	 * 
	 * @param V     conjunto de vertices a ser analisado
	 * @param label rotulo do vertice o qual deseja-se encontrar
	 * @return um DefaultVertex cujo lable eh igual ao lable passado como parametro.
	 * @exception {@link IllegalArgumentException} lanca quando nao ha o lable
	 *            desejado no conjunto de vertices passado como parametro.
	 */
	public static DefaultVertex getVertexfromLabel(Set<DefaultVertex> V, String label) {
		// invoca o iterator em no conjunto de vertices passado como parametro.
		Iterator<DefaultVertex> it = V.iterator();
		while (it.hasNext()) { //continua enquando houver vertices para serem analisados no conjunto.
			DefaultVertex v = it.next(); // salva o vertice em uma variavel adequada
			if ((v.getLabel()).equals(label)) { // analisa de o lable do vertice "da vez" eh igual ao lable passado como parametro.
				return v; // retorna o vertice "da vez" se este passar pela analise. 
			}
		}
		// excessao que sera lancada caso o lable passado nao exista no conjunto.
		throw new IllegalArgumentException("O lable passado como parametro nao e valido.");
	}

	/**
	 * Realiza a comparacao entre o conjunto vertices passado como parametro.
	 * 
	 * @param s conjunto de vertices a ser analisado
	 * @return int basico de acordo com o tamanho dos vertices
	 */
	public int compareTo(DefaultVertexSet<V> s) {
		if ((this.size()) < s.size()) {
			return -1;
		} else if ((this.size()) == s.size()) {
			return 0;
		} else
			return 1;
	}

}
