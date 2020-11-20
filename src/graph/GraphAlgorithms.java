package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class GraphAlgorithms<K extends Comparable<K>,E> {

	public ArrayList<Vertex<K,E>> BFS(IGraph<K,E> graph) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] isVisited = new boolean[graph.getGraph().size()];
		ArrayList<Vertex<K,E>> visitedNodes = new ArrayList<>();
		
		if(!graph.getGraph().isEmpty()) {
			
			queue.add(0);	
			int index;
			Vertex<K,E> current;
			while(!queue.isEmpty()) {
				
				index = queue.poll();
				current = graph.getGraph().get(index);
				
				if(!isVisited[index]) {
					visitedNodes.add(current);
					isVisited[index]=true;
				}
				
				for(int i=0; i<current.getAdjacents();i++) {
					int indexTemp = current.getNeiborgIndex(i);
					if(!isVisited[indexTemp])
						queue.add(indexTemp);
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
}
