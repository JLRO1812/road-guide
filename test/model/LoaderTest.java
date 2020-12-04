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

	}
}
