// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.*;
import org.jgrapht.alg.cycle.*;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/*
 * > Duvidas sobre como eh a implementacao de alguma classe ou metodo?
 * 
 * @link https://jgrapht.org/javadoc/ <- JavaDoc JGraphT
 * @link https://github.com/rcpoison/jgrapht <- GitHub JGraphT
 */
/**
 * Classe que possui exemplos de criacao de um grafo simples, utilizando a
 * biblioteca JGraphT, e a busca de algumas das informacoes deste grafo, como:
 * seus conjuntos de vertices e arestas, arestas que estao relacionadas a um
 * determinado vertice e os ciclos simples encontrados no grafo criado.
 */
public class MySimpleGraph {

	// constante contendo uma quebra de linha (capturada de acordo com o SO)
	private static final String ln = System.lineSeparator();

	/**
	 * Main: Metodo principal de execucao. O parametro args nao eh usado ​​neste
	 * sistema. Neste metodo eh inicializado um grafo e realizada sua criacao, como
	 * tambem utiliza-se de outros metodos para que se consiga obter informacoes
	 * relacionadas a este grafo.
	 * 
	 * @param args parametro padrao do metodo main
	 */
	public static void main(String[] args) {
		// Inicializa a variavel que ira receber o grafo o qual sera criar.
		/*
		 * Nota: utiliza-se o recurso <Generics>, definindo para este grafo que seus
		 * vertices sao objetos String e suas arestas sao do tipo DefaultEdge
		 * ("Aresta Padrao").
		 * 
		 * > Tudo sobre Generics >> http://bit.ly/ArtigoSobreGenerics
		 * 
		 * > Usando Generics em Java >> http://bit.ly/GenericsJava
		 */
		Graph<String, DefaultEdge> g = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

		// adiciona vertices ao grafo g.
		g.addVertex("MA");
		g.addVertex("PI");
		g.addVertex("CE");
		g.addVertex("RN");
		g.addVertex("PB");
		g.addVertex("PE");
		g.addVertex("AL");
		g.addVertex("SE");
		g.addVertex("BA");

		// adiciona arestas ao grafo g.
		g.addEdge("MA", "PI");
		g.addEdge("PI", "CE");
		g.addEdge("PI", "PE");
		g.addEdge("PI", "BA");
		g.addEdge("CE", "PE");
		g.addEdge("CE", "PB");
		g.addEdge("CE", "RN");
		g.addEdge("PE", "PB");
		g.addEdge("RN", "PB");
		g.addEdge("PE", "BA");
		g.addEdge("PE", "AL");
		g.addEdge("BA", "AL");
		g.addEdge("BA", "SE");
		g.addEdge("AL", "SE");

		// Exibe informacoes sobre o conjunto de vertices de um grafo e tambem do seu
		// conjunto de arestas utilizando os metodos vertexSet e edgeSet.
		System.out.println("Vértices: " + g.vertexSet());
		System.out.println("Arestas: " + g.edgeSet());

		// Exibe informacoes sobre as arestas relacionadas a um determinado grafo.
		System.out.println(ln + "Arestas que possuem o vértice PE como Terminal: " + g.edgesOf("PE"));

		// Utiliza os metodos getCyleBasis e getCycle para capturar e exibir todos os
		// ciclos simples do grafo criado.
		PatonCycleBase<String, DefaultEdge> c = new PatonCycleBase<String, DefaultEdge>(g);
		System.out.println(ln + "Ciclos Basicos do grafo: " + (c.getCycleBasis()).getCycles());

		// Analisa a ordem dos vertices na aresta eh importante.
		/*
		 * Nota: em um grafo simples, a aresta ab eh igual a aresta ba, ou seja, a ordem
		 * de exibicao nao importa.
		 */
		DefaultEdge e1 = g.getEdge("CE", "PI");
		DefaultEdge e2 = g.getEdge("PI", "CE");
		System.out.println(ln + "A ordem de vertices na aresta nao importa: " + e1.equals(e2));
		
		System.out.println(GraphTests.isTree(g));

	}

}
