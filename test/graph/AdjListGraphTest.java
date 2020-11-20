package graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AdjListGraphTest {

	AdjListGraph<String, String> adjl;
	GraphAlgorithms<String, String> ga;
	
	void setup1() {
		adjl = new AdjListGraph<>(false);
		ga = new GraphAlgorithms<>();
		adjl.addVertex(new Vertex<String, String>("A", "a"));
		adjl.addVertex(new Vertex<String, String>("B", "b"));
		adjl.addVertex(new Vertex<String, String>("C", "c"));
		adjl.addVertex(new Vertex<String, String>("D", "d"));
		adjl.addVertex(new Vertex<String, String>("E", "e"));
		adjl.addVertex(new Vertex<String, String>("F", "f"));
		adjl.addVertex(new Vertex<String, String>("G", "g"));
		adjl.addEdge(adjl.getVertex("A"), adjl.getVertex("C"));
		adjl.addEdge(adjl.getVertex("C"), adjl.getVertex("D"));
		adjl.addEdge(adjl.getVertex("D"), adjl.getVertex("F"));
		adjl.addEdge(adjl.getVertex("D"), adjl.getVertex("E"));
		adjl.addEdge(adjl.getVertex("E"), adjl.getVertex("B"));
		adjl.addEdge(adjl.getVertex("E"), adjl.getVertex("G"));
	}
	
	void setup2() {
		adjl = new AdjListGraph<String, String>(false);
		ga = new GraphAlgorithms<>();
		adjl.addVertex(new Vertex<String, String>("A", "a"));
		adjl.addVertex(new Vertex<String, String>("B", "b"));
		adjl.addVertex(new Vertex<String, String>("C", "c"));
		adjl.addVertex(new Vertex<String, String>("D", "d"));
		adjl.addVertex(new Vertex<String, String>("E", "e"));
		adjl.addEdge(adjl.getVertex("A"), adjl.getVertex("C"), 1);
		adjl.addEdge(adjl.getVertex("A"), adjl.getVertex("B"), 3);
		adjl.addEdge(adjl.getVertex("A"), adjl.getVertex("D"), 4);
		adjl.addEdge(adjl.getVertex("B"), adjl.getVertex("C"), 5);
		adjl.addEdge(adjl.getVertex("B"), adjl.getVertex("E"), 8);
		adjl.addEdge(adjl.getVertex("C"), adjl.getVertex("D"), 2);
		adjl.addEdge(adjl.getVertex("D"), adjl.getVertex("E"), 1);
	}
	
	@Test
	void getWeigthMatrixTest() {
		setup1();
		double[][] matrix = adjl.getWeigthMatrix();
		String text = generateMatrixText(matrix);
		assertEquals("0.0  Infinity  1.0  Infinity  Infinity  Infinity  Infinity  \n"
				+ "Infinity  0.0  Infinity  Infinity  1.0  Infinity  Infinity  \n"
				+ "1.0  Infinity  0.0  1.0  Infinity  Infinity  Infinity  \n"
				+ "Infinity  Infinity  1.0  0.0  1.0  1.0  Infinity  \n"
				+ "Infinity  1.0  Infinity  1.0  0.0  Infinity  1.0  \n"
				+ "Infinity  Infinity  Infinity  1.0  Infinity  0.0  Infinity  \n"
				+ "Infinity  Infinity  Infinity  Infinity  1.0  Infinity  0.0", text.trim());
		
		setup2();
		matrix = adjl.getWeigthMatrix();
		text = generateMatrixText(matrix);
		assertEquals("0.0  3.0  1.0  4.0  Infinity  \n"
			+ "3.0  0.0  5.0  Infinity  8.0  \n"
			+ "1.0  5.0  0.0  2.0  Infinity  \n"
			+ "4.0  Infinity  2.0  0.0  1.0  \n"
			+ "Infinity  8.0  Infinity  1.0  0.0", text.trim());
	}
	
	@Test
	void BFSTest() {
		setup1();
		ArrayList<Vertex<String,String>> aux = ga.BFS(adjl, adjl.getVertex("A"));
		String reply = "";
		for (int i = 0; i < aux.size(); i++) {
			reply += aux.get(i).getKey() + "  ";
		}
		assertEquals("A  C  D  F  E  B  G", reply.trim());
		
		setup2();
		aux = ga.BFS(adjl, adjl.getVertex("A"));
		reply = "";
		for (int i = 0; i < aux.size(); i++) {
			reply += aux.get(i).getKey() + "  ";
		}
		assertEquals("A  C  B  D  E", reply.trim());
	}
	
	@Test
	void DFSTest() {
		setup1();
		ArrayList<Vertex<String,String>> aux = ga.DFS(adjl, adjl.getVertex("D"));
		String reply = "";
		for (int i = 0; i < aux.size(); i++) {
			reply += aux.get(i).getKey() + "  ";
		}
		assertEquals("D  C  A  F  E  B  G", reply.trim());
		
		setup2();
		aux = ga.DFS(adjl, adjl.getVertex("A"));
		reply = "";
		for (int i = 0; i < aux.size(); i++) {
			reply += aux.get(i).getKey() + "  ";
		}
		assertEquals("A  C  B  E  D", reply.trim());
	}
	
	@Test
	void dijkstraTest() {
		setup1();
		ArrayList<String> list = (ArrayList<String>)ga.dijkstra(adjl, adjl.getVertex("D"), true);
		String reply = "";
		for (int i = 0; i < list.size(); i++) {
			reply += "[" + list.get(i) + "] ";
		}
		assertEquals("[D-C-A] [D-E-B] [D-C] [D] [D-E] [D-F] [D-E-G]", reply.trim());
		ArrayList<Edge> edge = (ArrayList<Edge>)ga.dijkstra(adjl, adjl.getVertex("D"), false);
		reply = "";
		for (int i = 0; i < list.size(); i++) {
			reply += "[" + edge.get(i).getWeigth() + "] ";
		}
		assertEquals("[2.0] [2.0] [1.0] [0.0] [1.0] [1.0] [2.0]", reply.trim());
		
		setup2();
		list = (ArrayList<String>)ga.dijkstra(adjl, adjl.getVertex("B"), true);
		reply = "";
		for (int i = 0; i < list.size(); i++) {
			reply += "[" + list.get(i) + "] ";
		}
		assertEquals("[B-A] [B] [B-A-C] [B-A-C-D] [B-A-C-D-E]", reply.trim());

		
		edge = (ArrayList<Edge>)ga.dijkstra(adjl, adjl.getVertex("B"), false);
		reply = "";
		for (int i = 0; i < list.size(); i++) {
			reply += "[" + edge.get(i).getWeigth() + "] ";
		}
		assertEquals("[3.0] [0.0] [4.0] [6.0] [7.0]", reply.trim());
	}
	
	private String generateMatrixText(double [][]matrix) {
		String text = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				text += matrix[i][j] + "  "; 
			}
			text+="\n";
		}
		return text;
	}
}
