package graph;

public class Edge implements Comparable<Edge>{

	private double weigth;
	private int index;
	
	public Edge(double weigth, int index) {
		this.weigth = weigth;
		this.index = index;
	}
	
	public double getWeigth() {
		return weigth;
	}
	
	public void setWeigth(double w) {
		weigth = w;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	@Override
	public int compareTo(Edge arg0) {
		if(weigth<arg0.getWeigth())
			return (int) (weigth-arg0.getWeigth());
		else if(weigth>arg0.getWeigth())
			return (int) (weigth-arg0.getWeigth());
		else
			return 0;
	}
}
