package ui;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import model.Loader;

public class ReportPage {

	private static String[] STATE = {"Cerrada por derrumbes", "Cerrada por vertimientos", "Cerrada por desbordamientos", "Cerrada por accidentes"
			, "Cerraba por mal tiempo", "Cerrada por mantenimiento"};
	
	private Loader loader;
	
	@FXML
    private Button reportButton;
	
    @FXML
    private ChoiceBox<String> cityOneChoiceBox;

    @FXML
    private ChoiceBox<String> cityTwoChoiceBox;

    @FXML
    private ChoiceBox<String> stateRoad;

    public ReportPage(Loader loader) {
		this.loader = loader;
	}
    
    @FXML
    void initialize() {
    	initializeChoiceBox();
    }
    
    @FXML
    void load(ActionEvent event) {
    	ArrayList<String> list = new ArrayList<String>();
    	int size = loader.getTrackGraph().getAdjacents(loader.getTrackGraph().getVertex(cityOneChoiceBox.getValue())).size();
    	for (int i = 0; i < size; i++) {
			list.add(loader.getTrackGraph().getAdjacents(loader.getTrackGraph().getVertex(cityOneChoiceBox.getValue())).get(i).getElement().toString());
			System.out.println(list.get(i));
		}
    	cityTwoChoiceBox.setItems(FXCollections.observableArrayList(list));
    	cityTwoChoiceBox.setValue(list.get(0));
    	cityTwoChoiceBox.setDisable(false);
    	stateRoad.setDisable(false);
    	reportButton.setDisable(false);
    }
    
    @FXML
    void report(ActionEvent event) {
    	cityTwoChoiceBox.setDisable(true);
    	stateRoad.setDisable(true);
    	reportButton.setDisable(true);
    	loader.getTrackGraph().deleteEdge(loader.getTrackGraph().getVertex(cityOneChoiceBox.getValue()), loader.getTrackGraph().getVertex(cityTwoChoiceBox.getValue()));
    	loader.getTollsGraph().deleteEdge(loader.getTrackGraph().getVertex(cityOneChoiceBox.getValue()), loader.getTrackGraph().getVertex(cityTwoChoiceBox.getValue()));
    }
    
    void initializeChoiceBox() {
    	ArrayList<String> graph = loader.getTollsGraph().showGraph();
    	cityOneChoiceBox.setItems(FXCollections.observableArrayList(graph));
    	cityOneChoiceBox.setValue(graph.get(0));
    	stateRoad.setItems(FXCollections.observableArrayList(STATE));
    	stateRoad.setValue(STATE[0]);
    }
}
