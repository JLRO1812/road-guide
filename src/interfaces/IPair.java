package interfaces;

import structures.Pair;

public interface IPair <K,V>{

	void setFirst(K key);
	void setSecond(V Value);
	K getKey();
	V getValue();
	int compareTo(Pair arg0);
}
