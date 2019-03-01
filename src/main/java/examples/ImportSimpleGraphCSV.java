// Teoria dos Grafos - UFCG

package examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.CSVFormat;
import org.jgrapht.io.CSVImporter;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;

/*
 * > Duvidas sobre como eh a implementacao de alguma classe ou metodo?
 * 
 * @link https://jgrapht.org/javadoc/ <- JavaDoc JGraphT
 * @link https://github.com/rcpoison/jgrapht <- GitHub JGraphT
 */
/**
 * Classe que possui exemplo da importacao de um grafo simples que esteja salvo
 * em um arquivo de formato CSV.
 */
public class ImportSimpleGraphCSV {
	// Importa Grafo Simples no formato CSV sem rotulos nas arestas.

	/**
	 * Constante que armazena uma quebra de linha de acordo com o SO.
	 */
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
		// Inicializa a variavel que ira armazenar lables e atributos dos vertives.
		/*
		 * Nota: Eh utilizado as expressoes labda (->) inseridas no Java 8 para realizar
		 * essa atividade
		 * 
		 * Lambda Expressions - link: http://bit.ly/LambdaExpressions (em ingles)
		 * 
		 * Como usar funcoes lambda em Java - link: http://bit.ly/ExpressoesLambdaEmJava
		 */
		VertexProvider<Object> vp = (label, attributes) -> label;

		// Inicializa a variavel que ira armazenar lables e atributos das arestas.
		EdgeProvider<Object, DefaultEdge> ep = (from, to, label, attributes) -> new DefaultEdge();

		// Inicializa a variavel que ira armazenar o grafo importado. O grafos sera
		// composto pelos vertices e arestas do grafo provedor.
		CSVImporter<Object, DefaultEdge> csvImporter = new CSVImporter<>(vp, ep);
		csvImporter.setFormat(CSVFormat.EDGE_LIST); // definindo o formado do arquivo CSV para lista de arestas.

		// Inicializa a variavel que armazana o grafo simples com base no grafo
		// importado.
		Graph<Object, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

		// Bloco responsavel pela importacao do grafo.
		try { // tenta realizar a importacao do graf...
			csvImporter.importGraph(graph, ImportGraph.readFile("./src/graphs/csv-example.txt")); // chama o metodo de importacao...
		} catch (ImportException e) { // caso seja lancada alguma excessao durante esse processo...
			throw new RuntimeException(e); // ela sera retornada para o usuario...
		}
		
		// Informacoes textuais sobre o grafo importado.
		System.out.println("Grafo importado do arquivo CSV:" + ln
				+ "Arestas: " + graph.edgeSet() + ln
				+ "Vertices: " + graph.vertexSet());

	}

}
