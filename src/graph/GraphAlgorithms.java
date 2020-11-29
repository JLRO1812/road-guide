package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class GraphAlgorithms<K extends Comparable<K>,E> {

	public ArrayList<Vertex<K,E>> BFS(IGraph<K,E> graph, Vertex<K,E> vertex) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] isVisited = new boolean[graph.getGraph().size()];
		ArrayList<Vertex<K,E>> visitedNodes = new ArrayList<>();
		
		if(!graph.getGraph().isEmpty()) {
			queue.add(vertex.getIndex());	
			int index;
			Vertex<K,E> current;
			while(!queue.isEmpty()) {
				
				index = queue.poll();
				current = graph.getGraph().get(index);
				
				if(!isVisited[index]) {
					visitedNodes.add(current);
					isVisited[index]=true;
				}
				
				ArrayList<Vertex<K,E>> aux = graph.getAdjacents(current);
				
				for(int i=0; i<aux.size();i++) {
					int indexTemp = aux.get(i).getIndex();
					if(!isVisited[indexTemp])
						queue.add(indexTemp);
				}
			}
		}
		return visitedNodes;
	}

	public ArrayList<Vertex<K,E>> DFS(IGraph<K,E> graph, Vertex<K,E> vertex) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] isVisited = new boolean[graph.getGraph().size()];
		ArrayList<Vertex<K,E>> visitedNodes = new ArrayList<>();
		
		if(!graph.getGraph().isEmpty()) {
			stack.push(vertex.getIndex());	
			int index;
			Vertex<K,E> current;
			while(!stack.isEmpty()) {
				
				index = stack.pop();
				current = graph.getGraph().get(index);
				
				if(!isVisited[index]) {
					visitedNodes.add(current);
					isVisited[index]=true;
				}
				
				ArrayList<Vertex<K,E>> aux = graph.getAdjacents(current);
				
				for(int i=aux.size()-1; i>=0;i--) {
					int indexTemp = aux.get(i).getIndex();
					if(!isVisited[indexTemp])
						stack.push(indexTemp);
				}
			}
		}
		return visitedNodes;
	}
	
	public Object dijkstra(IGraph<K,E> graph, Vertex<K,E> vertex, boolean travel) {
		int x = vertex.getIndex();
		
		PriorityQueue<Edge> pq;
		ArrayList<Integer> S = new ArrayList<Integer>();
		ArrayList<Edge> L = new ArrayList<Edge>();
		ArrayList<String> C = new ArrayList<String>();
		ArrayList<Vertex<K,E>> V = graph.getGraph();
		int[] vPositions = new int[V.size()];
		
		double[][] wMatrix = graph.getWeigthMatrix();
		
		for (int i = 0; i < V.size(); i++) {
			vPositions[i] = i;
			if(i==x) {
				L.add(new Edge(0.0, i)); 
				C.add(vertex.getKey().toString());
			}else {
				L.add(new Edge(IGraph.INFINITE, i));
				C.add("");
			}
		}
		
		pq = new PriorityQueue<Edge>(L);
		int u = pq.peek().getIndex();
		S.add(u);
		vPositions[u] = -1;
		
		while(S.size()<V.size()) {
			for (int i = 0; i < V.size(); i++) {
				if(vPositions[i]!=-1) {
					int v = vPositions[i];
					if(L.get(u).getWeigth() + wMatrix[u][v] < L.get(v).getWeigth()) {
						L.get(v).setWeigth(L.get(u).getWeigth() + wMatrix[u][v]);
						C.set(v, C.get(u) + "-" + V.get(v).getKey());
					}
				}
			}
			
			pq = new PriorityQueue<Edge>(L);
			for (int i = 0; i < S.size(); i++) {
				pq.remove();
			}
			u = pq.peek().getIndex();
			S.add(u);
			vPositions[u] = -1;
		}
		
		return (travel)?C:L;
	}
	
	public double[][] floydWarshall(IGraph<K,E> graph) {
		int size = graph.getGraph().size();
		double[][] matrix = new double[size][size];
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size;j++) {
				if(i!=j) {
					matrix[i][j]=IGraph.INFINITE;
				}else {
					matrix[i][j]=0;
				}
			}
		}
		
		for(int i=0; i<size; i++) {
			Vertex<K,E> startingNode = graph.getGraph().get(i);
			ArrayList<Edge> aux = graph.getEdges(startingNode);
			for(int j=0; j<aux.size();j++) {
				if(i!=aux.get(j).getIndex()) {
					matrix[i][aux.get(j).getIndex()] = aux.get(j).getWeigth();
				}
			}
		}
		
		for(int k=0; k<size; k++) {
			for(int i=0; (i<size); i++) {
				if(i!=k && matrix[i][k]<IGraph.INFINITE) {
					for(int j=0; (j<size); j++) {
						if(i!=j && matrix[k][j]<IGraph.INFINITE && j!=k) {
							double alt = matrix[i][k] + matrix[k][j];
							
							if(alt < matrix[i][j]) {
								matrix[i][j] = alt;
							}
						}
					}
				}
			}
		}
		
		return matrix;
	}
	
	public ArrayList<ArrayList<Edge>> kruskal(IGraph<K,E> graph){
		ArrayList<ArrayList<Edge>> edges = new ArrayList<ArrayList<Edge>>();
		int size = graph.getGraph().size();
		for (int i = 0; i < size; i++) {
			edges.add(graph.getEdges(graph.getGraph().get(i)));
		}
		
		ArrayList<ArrayList<Edge>> reply = new ArrayList<ArrayList<Edge>>();
		for (int i = 0; i < size; i++) {
			reply.add(new ArrayList<Edge>());
		}
		
		int cont = 0;
		while(cont < size - 1) {
			boolean first = true;
			int iMenor = 0;
			int jMenor = 0;
			for(int i = 0; i<edges.size() ; i++) {
				for(int j = 0; j<edges.get(i).size(); j++) {
					if(first) {
						iMenor = i;
						jMenor = j;
						first = false;
					}
					if(edges.get(i).get(j).getWeigth() < edges.get(iMenor).get(jMenor).getWeigth()) {
						iMenor = i;
						jMenor = j;
					}
				}
			}
			
			ArrayList<Vertex<K,E>> aux = DFS(new AdjListGraph<K,E>(graph.getGraph(), reply, false), graph.getGraph().get(iMenor));
			
			boolean cycle = false;
			for (int i = 0; i < aux.size() && !cycle; i++) {
				if(aux.get(i).getIndex() == edges.get(iMenor).get(jMenor).getIndex()) {
					int indexJ = edges.get(iMenor).get(jMenor).getIndex();
					edges.get(iMenor).remove(jMenor);
					if(!graph.isDirected()) {
						boolean delete = false;
						for (int i1 = 0; i1 < edges.get(indexJ).size() && !delete; i1++) {
							if(edges.get(indexJ).get(i1).getIndex() == iMenor) {
								reply.get(indexJ).add(edges.get(indexJ).get(i1));
								edges.get(indexJ).remove(i1);
								delete = true;
							}
						}
					}
					cycle = true;
				}
			}
			
			if(!cycle) {
				reply.get(iMenor).add(edges.get(iMenor).get(jMenor));
				cont++;
				int indexJ = edges.get(iMenor).get(jMenor).getIndex();
				edges.get(iMenor).remove(jMenor);
				if(!graph.isDirected()) {
					for (int i = 0; i < edges.get(indexJ).size(); i++) {
						if(edges.get(indexJ).get(i).getIndex() == iMenor) {
							reply.get(indexJ).add(edges.get(indexJ).get(i));
							edges.get(indexJ).remove(i);
							break;
						}
					}
				}
			}
		}
		return reply;
	}

	public ArrayList<String> prim(IGraph<K,E> graph, Vertex<K,E> root){
		BinarySearchTree <K,Double> tree= new BinarySearchTree <K,Double>();
		ArrayList <Vertex<K,E>> vertexList=graph.getGraph();
		ArrayList <String> list=new ArrayList<String>();
		ArrayList <Object> taken = new ArrayList<Object>();
		tree.insert(root.getKey(), Double.valueOf(vertexList.size()));
		Vertex<K,E> aux=root;
		root.setIndex(graph.searchIndex(root.getKey()));
		taken.add(root.getKey());
		while(vertexList.size()!=taken.size()) {
			PriorityQueue<Pair<K,Double>> pq= new PriorityQueue<Pair<K,Double>>();
			ArrayList<Vertex<K,E>> adjList=graph.getAdjacents(aux);
			ArrayList<Double> weight=graph.getWeightAdjList(aux);
			
			for(int i=0; i<adjList.size(); i++) {	
				pq.add(new Pair<K, Double>(adjList.get(i).getKey(),weight.get(i)));
			}
			
		boolean found=false;
		while(!pq.isEmpty() && !found) {
			Pair<K, Double>aux2= pq.poll();
			if(!checkKeys(taken, aux2.getKey())){
				K key=(K) aux2.getKey();
				Double value= (Double)aux2.getValue();
				tree.insert(key, value);
				list.add(aux.getKey()+" -"+value.intValue()+"-> "+aux2.getKey());
				if(graph.getAdjacents(graph.getVertex(key)).size()>1) {
					aux=graph.getVertex(key);
				}else {
					key=(K)taken.get(taken.size()-1);
					aux=graph.getVertex(key);
				}
				taken.add(aux2.getKey());
				found=true;
			}
		}	
		}
		return list;
	}
	
	private boolean checkKeys(ArrayList<Object> keys, Object key) {
		boolean found=false;
		for(int i=0; i<keys.size();i++) {
			if(keys.get(i).equals(key)) 
				return true;		
		}
		return found;
	}

}
