package interfaces;

public interface BinarySearchTreeI<K extends Comparable<K>, V> {

	public void insert(K key, V value);
	public V search(K key);
	public INode<K,V> delete(K key);
	public String inOrder(boolean parameter);
	public String preOrder(boolean parameter);
	public INode<K,V> getLess();
	public INode<K,V> getHigher();
	public int getSize();
}
