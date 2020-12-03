package interfaces;

import java.util.ArrayList;

import graph.Edge;
import graph.Vertex;
public interface IGraph <K extends Comparable<K>,E>{
	
	public static final double INFINITE = Double.POSITIVE_INFINITY;
	
	public ArrayList<Vertex<K,E>> getGraph();
	public double[][] getWeigthMatrix();
	public void addVertex(Vertex<K,E> v);
	public Vertex<K,E> getVertex(int index);
	public Vertex<K,E> getVertex(K key);
	public void deleteVertex(Vertex<K,E> v);
	public void addEdge(Vertex<K,E> u, Vertex<K,E> v);
	public void addEdge(Vertex<K,E> u, Vertex<K,E> v, double w);
	public void deleteEdge(Vertex<K,E> u, Vertex<K,E> v);
	public boolean isDirected(); 
	public ArrayList<Vertex<K,E>> getAdjacents(Vertex<K,E> v);
	public ArrayList<Edge> getEdges(Vertex<K,E> v);
	public ArrayList<Double> getWeightAdjList(Vertex<K,E> v);
	public int searchIndex(K key) ;
}
