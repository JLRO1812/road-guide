package graph;

import java.util.ArrayList;

public class AdjMatrixGraph<K extends Comparable<K>,E> implements IGraph<K,E>{

	private static int MAX_AMOUNT = 1000;
	
	private ArrayList<Vertex<K,E>> graph;
	private int[][] adjMatrix;
	private boolean direct;
	
	public AdjMatrixGraph(boolean direct) {
		this.direct = direct;
		
		adjMatrix = new int[MAX_AMOUNT][MAX_AMOUNT];
		graph = new ArrayList<Vertex<K,E>>();
	}
	
	@Override
	public ArrayList<Vertex<K, E>> getGraph() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double[][] getWeigthMatrix() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void addVertex(Vertex<K, E> v) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Vertex<K, E> getVertex(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Vertex<K, E> getVertex(K key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void deleteVertex(Vertex<K, E> v) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addEdge(Vertex<K, E> u, Vertex<K, E> v) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addEdge(Vertex<K, E> u, Vertex<K, E> v, double w) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteEdge(Vertex<K, E> u, Vertex<K, E> v) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isDirected() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public ArrayList<Vertex<K, E>> getAdjacents(Vertex<K, E> v) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Edge> getEdges(Vertex<K, E> v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Double> getWeightAdjList(Vertex<K, E> v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int searchIndex(K key) {
		// TODO Auto-generated method stub
		return 0;
	}
}
