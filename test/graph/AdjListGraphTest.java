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
	
	void setup3() {
		adjl = new AdjListGraph<String, String>(false);
		ga = new GraphAlgorithms<>();
		adjl.addVertex(new Vertex<String, String>("A", "a"));
		adjl.addVertex(new Vertex<String, String>("B", "b"));
		adjl.addVertex(new Vertex<String, String>("C", "c"));
		adjl.addVertex(new Vertex<String, String>("D", "d"));
		adjl.addVertex(new Vertex<String, String>("E", "e"));
		adjl.addEdge(adjl.getVertex("A"), adjl.getVertex("B"), 5);
		adjl.addEdge(adjl.getVertex("A"), adjl.getVertex("E"), 10);
		adjl.addEdge(adjl.getVertex("B"), adjl.getVertex("C"), 7);
		adjl.addEdge(adjl.getVertex("B"), adjl.getVertex("E"), 4);
		adjl.addEdge(adjl.getVertex("C"), adjl.getVertex("D"), 2);
		adjl.addEdge(adjl.getVertex("D"), adjl.getVertex("E"), 1);
	}
	
	void setup4() {
		adjl = new AdjListGraph<>(false);
		ga = new GraphAlgorithms<>();
		adjl.addVertex(new Vertex<String, String>("A", "a"));
		adjl.addVertex(new Vertex<String, String>("B", "b"));
		adjl.addVertex(new Vertex<String, String>("C", "c"));
		adjl.addVertex(new Vertex<String, String>("D", "d"));
		adjl.addVertex(new Vertex<String, String>("E", "e"));
		adjl.addVertex(new Vertex<String, String>("F", "f"));

		adjl.addEdge(adjl.getVertex("A"), adjl.getVertex("B"), 8);
		adjl.addEdge(adjl.getVertex("A"), adjl.getVertex("C"), 9);
		adjl.addEdge(adjl.getVertex("A"), adjl.getVertex("D"), 7);
		adjl.addEdge(adjl.getVertex("B"), adjl.getVertex("C"), 11);
		adjl.addEdge(adjl.getVertex("B"), adjl.getVertex("E"), 3);
		adjl.addEdge(adjl.getVertex("C"), adjl.getVertex("E"), 2);
		adjl.addEdge(adjl.getVertex("C"), adjl.getVertex("F"), 4);
		adjl.addEdge(adjl.getVertex("C"), adjl.getVertex("D"), 12);
		adjl.addEdge(adjl.getVertex("D"), adjl.getVertex("F"), 5);
		adjl.addEdge(adjl.getVertex("E"), adjl.getVertex("F"), 10);

		
	}
	
	void setup5() {
		adjl = new AdjListGraph<>(false);
		ga = new GraphAlgorithms<>();
		adjl.addVertex(new Vertex<String, String>("A", "a"));
		adjl.addVertex(new Vertex<String, String>("B", "b"));
		adjl.addVertex(new Vertex<String, String>("C", "c"));
		adjl.addVertex(new Vertex<String, String>("D", "d"));
		adjl.addVertex(new Vertex<String, String>("E", "e"));
		
		adjl.addEdge(adjl.getVertex("A"), adjl.getVertex("D"), 5);
		adjl.addEdge(adjl.getVertex("B"), adjl.getVertex("C"), 5);
		adjl.addEdge(adjl.getVertex("B"), adjl.getVertex("D"), 3);
		adjl.addEdge(adjl.getVertex("C"), adjl.getVertex("D"), 10);
		adjl.addEdge(adjl.getVertex("D"), adjl.getVertex("E"), 2);
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
	
	@Test
	void floydWarshallTest() {
		setup3();
		double[][] matrix = ga.floydWarshall(adjl);
		String reply = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				reply += matrix[i][j] + " ";
			}
			reply += "\n";
		}
		
		assertEquals("0.0 5.0 12.0 10.0 9.0 \n"
				+ "5.0 0.0 7.0 5.0 4.0 \n"
				+ "12.0 7.0 0.0 2.0 3.0 \n"
				+ "10.0 5.0 2.0 0.0 1.0 \n"
				+ "9.0 4.0 3.0 1.0 0.0 \n", reply);
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
	
	@Test 
	void primTest() {
		setup4();
		Vertex<String,String> v= new Vertex<String, String>("A", "a");
		ga.prim(adjl, v);
		ArrayList<String> s=ga.getList();
		assertEquals(s.get(0),"A -7-> D");
		assertEquals(s.get(1),"D -5-> F");
		assertEquals(s.get(2),"F -4-> C");
		assertEquals(s.get(3),"C -2-> E");
		assertEquals(s.get(4),"E -3-> B");
		
		v= new Vertex<String, String>("C", "c");
		ga.prim(adjl, v);
		s=ga.getList();
		
		assertEquals(s.get(0),"C -2-> E");
		assertEquals(s.get(1),"E -3-> B");
		assertEquals(s.get(2),"B -8-> A");
		assertEquals(s.get(3),"A -7-> D");
		assertEquals(s.get(4),"D -5-> F");

		setup5();
		v= new Vertex<String, String>("A", "a");
		ga.prim(adjl, v);
		s=ga.getList();
		assertEquals(s.get(0),"A -5-> D");
		assertEquals(s.get(1),"D -2-> E");
		assertEquals(s.get(2),"D -3-> B");
		assertEquals(s.get(3),"B -5-> C");
		
		setup5();
		v= new Vertex<String, String>("D", "d");
		ga.prim(adjl, v);
		s=ga.getList();
		assertEquals(s.get(0),"D -2-> E");
		assertEquals(s.get(1),"D -3-> B");
		assertEquals(s.get(2),"B -5-> C");
		assertEquals(s.get(3),"D -5-> A");
		
		setup5();
		v= new Vertex<String, String>("B", "b");
		ga.prim(adjl, v);
		s=ga.getList();
		assertEquals(s.get(0),"B -3-> D");
		assertEquals(s.get(1),"D -2-> E");
		assertEquals(s.get(2),"D -5-> A");
		assertEquals(s.get(3),"D -10-> C");
		
		setup5();
		v= new Vertex<String, String>("E", "e");
		ga.prim(adjl, v);
		s=ga.getList();
		assertEquals(s.get(0),"E -2-> D");
		assertEquals(s.get(1),"D -3-> B");
		assertEquals(s.get(2),"B -5-> C");
		assertEquals(s.get(3),"D -5-> A");
	}
	
	@Test
	void kruskalTest() {
		setup3();
		ArrayList<ArrayList<Edge>> aux = ga.kruskal(adjl);
		String reply = "";
		for (int i = 0; i < aux.size(); i++) {
			for (int j = 0; j < aux.get(i).size(); j++) {
				reply += adjl.getGraph().get(i).getElement() + "->" + adjl.getGraph().get(aux.get(i).get(j).getIndex()).getElement() + "||";
			}
			reply += "\n";
		}
		assertEquals("a->b||\n"
				+ "b->e||b->a||\n"
				+ "c->d||\n"
				+ "d->e||d->c||\n"
				+ "e->d||e->b||\n", reply);
		
		setup2();
		aux = ga.kruskal(adjl);
		reply = "";
		for (int i = 0; i < aux.size(); i++) {
			for (int j = 0; j < aux.get(i).size(); j++) {
				reply += adjl.getGraph().get(i).getElement() + "->" + adjl.getGraph().get(aux.get(i).get(j).getIndex()).getElement() + "||";
			}
			reply += "\n";
		}
		assertEquals("a->c||a->b||\n"
				+ "b->a||\n"
				+ "c->a||c->d||\n"
				+ "d->e||d->c||\n"
				+ "e->d||\n", reply);
	}
}
