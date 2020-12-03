package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Loader {

	private String[] cities;
	private String[][] tracks;
	private String[][] tolls;

	public static final String CADRESS="data/Cities.txt";
	public static final String TOADRESS="data/Tolls.txt";
	public static final String TRADRESS="data/Tracks.txt";
	
	public Loader() throws IOException {
		cities=loadInArray(CADRESS);
		int length=cities.length;
		tolls=loadInMatrix(TOADRESS, length, length);
		tracks=loadInMatrix(TRADRESS, length, length);
	}
	
	public String[] loadInArray(String adress) throws IOException {
		ArrayList<String> list= new ArrayList<String>();
		BufferedReader brN= new BufferedReader(new FileReader(adress));
		return brN.readLine().split(",");
	}
	

	public String[][] loadInMatrix(String adress, int row, int column) throws IOException {
		BufferedReader brN= new BufferedReader(new FileReader(adress));
		String[][] matrix = new String[row][column];
 		String[] msg;
		int r=0;
		while(r<row) {
			msg=brN.readLine().split(",");
			for(int i=0; i<column; i++) {
				matrix[r][i]=msg[i];
			}
			r++;
		}
		for(int i=0; i<row; i++) {
			for(int j=0; j<column; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		return matrix;
	}
	
}
