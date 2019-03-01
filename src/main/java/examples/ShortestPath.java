// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import org.jgrapht.alg.shortestpath.*;
import org.jgrapht.alg.spanning.PrimMinimumSpanningTree;

/*
 * > Duvidas sobre como eh a implementacao de alguma classe ou metodo?
 * 
 * @link https://jgrapht.org/javadoc/ <- JavaDoc JGraphT
 * @link https://github.com/rcpoison/jgrapht <- GitHub JGraphT
 */
/**
 * Classe que possui exemplos de criacao de um grafo ponderado, utilizando-se da
 * biblioteca JGraphT, e a utilizacao dos algoritimos de Dijkstra, utilizado
 * para encontrar o menor caminho entre dois vertices, e Primm, utilizado para
 * que seja encontrada a arvore geradora minima de um grafo.
 */
public class ShortestPath {

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
		 * vertices sao objetos String e suas arestas sao do tipo DefaultWeightedEdge
		 * ("Aresta Ponderada Padrao").
		 * 
		 * > Tudo sobre Generics >> http://bit.ly/ArtigoSobreGenerics
		 * 
		 * > Usando Generics em Java >> http://bit.ly/GenericsJava
		 */
		SimpleWeightedGraph<String, DefaultWeightedEdge> g = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

		// Inicializacao dos vertices do grafo
		g.addVertex("Fra");
		g.addVertex("Man");
		g.addVertex("Wur");
		g.addVertex("Stu");
		g.addVertex("Kas");
		g.addVertex("Num");
		g.addVertex("Mun");
		g.addVertex("Aug");
		g.addVertex("Erf");
		g.addVertex("Kar");

		// Inicializacao das arestas do grafo.
		g.setEdgeWeight(g.addEdge("Fra", "Man"), 85);
		g.setEdgeWeight(g.addEdge("Fra", "Wur"), 217);
		g.setEdgeWeight(g.addEdge("Fra", "Kas"), 173);
		g.setEdgeWeight(g.addEdge("Man", "Kar"), 80);
		g.setEdgeWeight(g.addEdge("Wur", "Erf"), 186);
		g.setEdgeWeight(g.addEdge("Wur", "Num"), 103);
		g.setEdgeWeight(g.addEdge("Num", "Stu"), 183);
		g.setEdgeWeight(g.addEdge("Kas", "Mun"), 502);
		g.setEdgeWeight(g.addEdge("Num", "Mun"), 167);
		g.setEdgeWeight(g.addEdge("Kar", "Aug"), 250);
		g.setEdgeWeight(g.addEdge("Aug", "Mun"), 84);

		// Inicializacao da variavel que contera o algoritmo de DijkstraShortestPath.
		DijkstraShortestPath<String, DefaultWeightedEdge> dsp = new DijkstraShortestPath<>(g);

		// print que exibe o menor caminho entre os dois vertices passados como
		// parametro no metodo getPath da classe DijkstraShortestPath.
		System.out.println(dsp.getPath("Fra", "Mun"));

		// possui o mesmo comportamento da operacao anterior, porem, utiliza-se dos
		// metodos getPaths e getPath.
		System.out.println((dsp.getPaths("Fra")).getPath("Stu"));
		System.out.println((dsp.getPaths("Fra")).getPath("Aug"));
		System.out.println((dsp.getPaths("Fra")).getPath("Kas"));
		System.out.println((dsp.getPaths("Fra")).getPath("Erf"));

		// Demostra a implementacao do metodo getPathWeight da classe
		// DijkstraShortestPath para exibir o peso do caminho para cada exemplo dado
		// como parametro.
		System.out.println("Fra-Man: " + dsp.getPathWeight("Fra", "Man"));
		System.out.println("Fra-Wur: " + dsp.getPathWeight("Fra", "Wur"));
		System.out.println("Fra-Kas: " + dsp.getPathWeight("Fra", "Kas"));
		System.out.println("Fra-Kar: " + dsp.getPathWeight("Fra", "Kar"));
		System.out.println("Fra-Erf: " + dsp.getPathWeight("Fra", "Erf"));
		System.out.println("Fra-Num: " + dsp.getPathWeight("Fra", "Num"));
		System.out.println("Fra-Stu: " + dsp.getPathWeight("Fra", "Stu"));
		System.out.println("Fra-Mun: " + dsp.getPathWeight("Fra", "Mun"));
		System.out.println("Fra-Aug: " + dsp.getPathWeight("Fra", "Aug"));

		// Demonstra a utilizacao do metodo getPaths.
		System.out.println(dsp.getPaths("Fra"));

		// Inicializa a variavel que contera o algoritmo de PrimMinimumSpanningTree
		// encontrado na JGraphT.
		PrimMinimumSpanningTree<String, DefaultWeightedEdge> pmst = new PrimMinimumSpanningTree<>(g);

		// Exibe a menor arvore geradora de um grafo utilizando o metodo getSpanningTree
		// da classe PrimMinimumSpanningTree.
		System.out.println(ln + "PrimMinimumSpanningTree (O(|E|+|V|log(|V|))): \n" + pmst.getSpanningTree());

	}

}
