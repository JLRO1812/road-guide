package interfaces;


public interface INode<K extends Comparable<K>, V> {
	
	public void setFather(INode<K,V> f);
	public void setLeft(INode<K,V> l);
	public void setRight(INode<K,V> r);
	public INode<K,V> getFather();
	public INode<K,V> getLeft();
	public INode<K,V> getRight();
	public K getKey();
	public V getValue();
	public int childrensAmount();
	public int getNumberOfCopies();
	public void increaseNumberOfCopies();
	public void decreaseNumberOfCopies();
}
