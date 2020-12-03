package model;

import java.io.IOException;

import org.junit.Test;

public class LoaderTest {

	private Loader loader;
	
	public void setup1() throws IOException {
		loader= new Loader();
	}
	
	@Test
	public void loadInArrayTest() throws IOException {
		setup1();
		String adress="data/Cities.txt";
		String[] cities=loader.loadInArray(loader.CADRESS);
		System.out.println(cities);
		System.out.println(loader.loadInMatrix(loader.TOADRESS, cities.length, cities.length));
		System.out.println(loader.loadInMatrix(loader.TRADRESS, cities.length, cities.length));
	}
}
