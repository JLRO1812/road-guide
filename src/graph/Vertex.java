package graph;

public class Vertex <K extends Comparable<K>,E>{

	private K key;
	private E element;
	private int index;
	
	public Vertex(K key, E element) {
		this.key = key;
		this.element = element;
	}
	
	public Vertex(K key, E element, int index) {
		this.key = key;
		this.element = element;
		this.index = index;
	}

	public E getElement() {
		return element;
	}
	
	public void setElement(E element) {
		this.element = element;
	}
	
	public K getKey() {
		return key;
	}

	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
}
