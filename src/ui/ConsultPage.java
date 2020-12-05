package ui;

import java.io.IOException;
import java.util.ArrayList;

import graph.Edge;
import graph.GraphAlgorithms;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.Loader;

public class ConsultPage {
    
	public static final String TRACKPAGE_FXML = "trackPage.fxml";
	
	private Loader loader;
	
	@FXML
    private ChoiceBox<String> origenChoiceBox;

    @FXML
    private ChoiceBox<String> destinoChoiceBox;

	public ConsultPage(Loader loader) {
		this.loader = loader;
	}
    
	@FXML
	void initialize() {
		initializeChoiceBox();
	}
	
    @FXML
    void consult(ActionEvent event) throws IOException {
    	loadNewStage(TRACKPAGE_FXML, new TrackPage(rutaMasCorta(), distanciaMasCorta(), rutaPeajeMasCorta()));
    }
    
    private String rutaMasCorta() {
    	GraphAlgorithms<String, String> ga =  new GraphAlgorithms<>();
    	ArrayList<String> list = (ArrayList<String>)ga.dijkstra(loader.getTrackGraph(), loader.getTrackGraph().getVertex(origenChoiceBox.getValue()), true);
		
    	String reply = "[" + list.get(loader.getTrackGraph().getVertex(destinoChoiceBox.getValue()).getIndex()) + "] ";
    	System.out.println(reply);
		return reply;
    }
    
    private String rutaPeajeMasCorta() {
    	GraphAlgorithms<String, String> ga =  new GraphAlgorithms<>();
    	ArrayList<String> list = (ArrayList<String>)ga.dijkstra(loader.getTollsGraph(), loader.getTollsGraph().getVertex(origenChoiceBox.getValue()), true);
		
    	String reply = "[" + list.get(loader.getTollsGraph().getVertex(destinoChoiceBox.getValue()).getIndex()) + "] ";
    	System.out.println(reply);
		return reply;
    }
    
    private String distanciaMasCorta() {
    	GraphAlgorithms<String, String> ga =  new GraphAlgorithms<>();
    	ArrayList<Edge> list = (ArrayList<Edge>)ga.dijkstra(loader.getTrackGraph(), loader.getTrackGraph().getVertex(origenChoiceBox.getValue()), false);
		
    	String reply = "[ " + (int)(list.get(loader.getTrackGraph().getVertex(destinoChoiceBox.getValue()).getIndex()).getWeigth()) + "km ] ";
    	System.out.println(reply);
		return reply;
    }
    
    void loadNewStage(String fxml, Object obj) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
		fxmlLoader.setController(obj);
		Parent parent = fxmlLoader.load();
	
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("track-page");
		stage.show();
    }
    
    void initializeChoiceBox() {
    	ArrayList<String> graph = loader.getTollsGraph().showGraph();
    	origenChoiceBox.setItems(FXCollections.observableArrayList(graph));
    	origenChoiceBox.setValue(graph.get(0));
    	destinoChoiceBox.setItems(FXCollections.observableArrayList(graph));
    	destinoChoiceBox.setValue(graph.get(0));
    }
}
