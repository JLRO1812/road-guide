package graph;

import java.util.ArrayList;

public class AdjListGraph<K extends Comparable<K>,E> implements IGraph<K, E>{
	private ArrayList<Vertex<K,E>> graph;
	private ArrayList<ArrayList<Edge>> adjList;
	private boolean direct;

	AdjListGraph(boolean direct){
		this.direct = direct;
		graph = new ArrayList<Vertex<K,E>>(); 
		adjList = new ArrayList<ArrayList<Edge>>(); 
	}

	@Override
	public double[][] getWeigthMatrix() {
		double[][] wMatrix =  new double[graph.size()][graph.size()];
		
		for (int i = 0; i < graph.size(); i++) {
			for (int j = 0; j < adjList.get(i).size(); j++) {
				wMatrix[i][adjList.get(i).get(j).getIndex()] = adjList.get(i).get(j).getWeigth(); 
			}
			for (int n = 0; n < wMatrix.length; n++) {
				if(n==i)
					wMatrix[i][n]=0;
				else if(wMatrix[i][n]==0){
					wMatrix[i][n]=IGraph.INFINITE;
				}
			}
		}
		
		return wMatrix;
	}

	@Override
	public void addVertex(Vertex<K,E> v) {
		v.setIndex(graph.size());
		graph.add(v);
		adjList.add(new ArrayList<Edge>());
	}

	@Override
	public Vertex<K,E> getVertex(int index) {
		return graph.get(index);
	}

	@Override
	public Vertex<K, E> getVertex(K key) {
		for (int i = 0; i < graph.size(); i++) {
			if(graph.get(i).getKey().compareTo(key)==0)
				return graph.get(i);
		}
		return null;
	}
	
	@Override
	public void deleteVertex(Vertex<K,E> v) {
		int index = v.getIndex();
		if(graph.get(index)!=null) {
			graph.remove(index);
			adjList.remove(index);
			for (int i = 0; i < adjList.size(); i++) {
				for (int j = 0; j < adjList.get(i).size(); j++) {
					if(adjList.get(i).get(j).getIndex() == index)
						deleteEdge(graph.get(i), v);
					if(adjList.get(i).get(j).getIndex()>index)
						adjList.get(i).get(j).setIndex(adjList.get(i).get(j).getIndex()-1);
				}
			}
			
			updatedIndex(index);
		}
	}

	@Override
	public void addEdge(Vertex<K,E> u, Vertex<K,E> v) {
		if(graph.get(u.getIndex()) != null && graph.get(v.getIndex()) != null) {
			if(direct) 
				adjList.get(u.getIndex()).add(new Edge(1, v.getIndex()));
			else {
				adjList.get(u.getIndex()).add(new Edge(1, v.getIndex()));
				adjList.get(v.getIndex()).add(new Edge(1, u.getIndex()));
			}
		}
	}
	
	@Override
	public void addEdge(Vertex<K,E> u, Vertex<K,E> v, double w) {
		if(graph.get(u.getIndex()) != null && graph.get(v.getIndex()) != null) {
			if(direct) 
				adjList.get(u.getIndex()).add(new Edge(w, v.getIndex()));
			else {
				adjList.get(u.getIndex()).add(new Edge(w, v.getIndex()));
				adjList.get(v.getIndex()).add(new Edge(w, u.getIndex()));
			}
		}
	}

	@Override
	public void deleteEdge(Vertex<K,E> u, Vertex<K,E> v) {
		if(graph.get(u.getIndex())!=null) {
			for (int i = 0; i < adjList.get(u.getIndex()).size(); i++) {
				if(adjList.get(u.getIndex()).get(i).getIndex()==v.getIndex()) {
					adjList.get(u.getIndex()).remove(i);
					break;
				}
			}
		}
	}
	
	@Override
	public ArrayList<Vertex<K,E>> getAdjacents(Vertex<K,E> v) {
		ArrayList<Vertex<K,E>> adjacents = new ArrayList<Vertex<K,E>>();
		for (int i = 0; i < adjList.get(v.getIndex()).size(); i++) {
			adjacents.add(graph.get(adjList.get(v.getIndex()).get(i).getIndex()));
		}
		return adjacents;
	}
	
	@Override
	public ArrayList<Edge> getEdges(Vertex<K,E> v) {
		return adjList.get(v.getIndex());
	}

	@Override
	public boolean isDirected() {
		return direct;
	}

	@Override
	public ArrayList<Vertex<K,E>> getGraph() {
		return graph;
	}
	
	private void updatedIndex(int index) {
		for (int i = index; i<graph.size(); i++) {
			graph.get(i).setIndex(i);
		}
	}
}
