package graph;


public class Pair <K, V extends Comparable<V>> implements IPair<K,V>, Comparable <Pair> {
		private K key;
		private V value;
		
	public Pair(K f, V s) {
		key=f;
		value=s;
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
	public String toString() {
		return "(first: " + key + ", second: " + value + ")";
	}

	@Override
	public void setFirst(K first) {
		this.key=first;
		
	}

	@Override
	public void setSecond(V second) {
		this.value=second;
	}


	@Override
	public int compareTo(Pair arg0) {
		V aux= (V) arg0.getValue();
		if(value.compareTo(aux)>0) {
			return 1;
		}else if(value.compareTo(aux)<0){
			return -1;
		}else {
			return 0;
		}
	}

}
