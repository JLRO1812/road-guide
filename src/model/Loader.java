package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import graph.AdjListGraph;
import graph.Vertex;
import interfaces.IGraph;

public class Loader {

	public static final String CADRESS="data/Cities.txt";
	public static final String TOADRESS="data/Tolls.txt";
	public static final String TRADRESS="data/Tracks.txt";
	
	private IGraph<String, String> trackGraph;
	private IGraph<String, String> tollsGraph;
	
	public Loader() throws IOException {
		trackGraph= new AdjListGraph<String,String>(true);
		tollsGraph= new AdjListGraph<String,String>(true);
		loadCities(trackGraph, CADRESS);
		loadCities(tollsGraph, CADRESS);
		int size=trackGraph.getGraph().size();
		loadInMatrix(tollsGraph, TOADRESS, size);
		loadInMatrix(trackGraph, TRADRESS, size);
	}
	
	public void loadCities(IGraph<String, String> graph, String adress) throws IOException {
		BufferedReader brN= new BufferedReader(new FileReader(adress));
		String[] cities= brN.readLine().split(",");
		brN.close();
		
		for(int i=0; i<cities.length; i++) {
			graph.addVertex(new Vertex<String, String>(cities[i], cities[i].toLowerCase()));
		}
	}
	
	public void loadInMatrix(IGraph<String, String> graph, String adress, int size) throws IOException {
		BufferedReader brN= new BufferedReader(new FileReader(adress));
 		String[] msg;
		int r=0;
		while(r<size) {
			msg=brN.readLine().split(",");
			for(int i=0; i<size; i++) {
				graph.addEdge(graph.getVertex(r), graph.getVertex(i), Double.valueOf(msg[i]));
			}
			r++;
		}
		brN.close();
	}

	public IGraph<String, String> getTrackGraph() {
		return trackGraph;
	}

	public IGraph<String, String> getTollsGraph() {
		return tollsGraph;
	}	
}
