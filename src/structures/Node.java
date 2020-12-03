package structures;

import interfaces.INode;

public class Node<K extends Comparable<K>,V> implements INode<K, V>{

	protected INode<K,V> father;
	protected INode<K,V> left;
	protected INode<K,V> right;
	protected K key;
	protected V value;
	protected int numberOfCopies;
	
	public Node(K k, V v, INode<K,V>f, INode<K,V>l, INode<K,V>r) {
		key = k;
		value = v;
		father = f;
		left = l;
		right = r;
		numberOfCopies = 1;
	}
	
	public Node(K k, V v, INode<K,V>f) {
		key = k;
		value = v;
		father = f;
		left = null;
		right = null;
		numberOfCopies = 1;
	}
	
	public Node(K k, V v) {
		key = k;
		value = v;
		father = null;
		left = null;
		right = null;
		numberOfCopies = 1;
	}
	
	@Override
	public void setFather(INode<K, V> f) {
		father = f;
	}

	@Override
	public void setLeft(INode<K, V> l) {
		left = l;
	}

	@Override
	public void setRight(INode<K, V> r) {
		right = r;
	}

	@Override
	public INode<K, V> getFather() {
		return father;
	}

	@Override
	public INode<K, V> getLeft() {
		return left;
	}

	@Override
	public INode<K, V> getRight() {
		return right;
	}
	
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}
	
	@Override
	public int childrensAmount() {
		return ((left!=null)&&(right!=null))?2:((left!=null)||(right!=null))?1:0;
	}
	
	@Override
	public int getNumberOfCopies() {
		return numberOfCopies;
	}

	@Override
	public void increaseNumberOfCopies() {
		numberOfCopies++;
	}

	@Override
	public void decreaseNumberOfCopies() {
		numberOfCopies--;
	}
	
	@Override
	public String toString() {
		return value + ", KEY: " + key;
	}
}
