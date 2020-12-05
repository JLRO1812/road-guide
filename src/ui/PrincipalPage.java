package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.Loader;

public class PrincipalPage {

	public static final String CONSULT_FXML = "ConsultPage.fxml";
	public static final String REPORT_FXML = "ReportPage.fxml";
	
	private Loader loader;
	private ConsultPage consultObject;
	private ReportPage reportObject;
	
	public PrincipalPage() throws IOException {
		loader = new Loader();
		consultObject = new ConsultPage(loader);
		reportObject =  new ReportPage(loader);
	}
	
	@FXML
	void initialize() throws IOException {
		selectedConsult();
		load(CONSULT_FXML, consultObject);
	}
	
    @FXML
    private MenuItem consultButton;

    @FXML
    private MenuItem reportButton;

    @FXML
    private AnchorPane principalPane;

    @FXML
    void consult(ActionEvent event) throws IOException {
    	System.out.println("CONSULT");
    	selectedConsult();
    	load(CONSULT_FXML, consultObject);
    }

    @FXML
    void report(ActionEvent event) throws IOException {
    	System.out.println("REPORT");
    	selectedReport();
    	load(REPORT_FXML, reportObject);
    }
    
    private void selectedReport() {
    	reportButton.setDisable(true);
    	consultButton.setDisable(false);
    }
    
    private void selectedConsult() {
    	reportButton.setDisable(false);
    	consultButton.setDisable(true);
    }
    
    public void load(String txt, Object obj) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(txt));
		fxmlLoader.setController(obj);
		Parent parent = fxmlLoader.load();
		principalPane.getChildren().setAll(parent);
    }
    
    public static void loadAlert(AlertType type, String title, String middle, String bot) {
 		Alert alert = new Alert(type);
 		alert.setContentText(bot);
 		alert.setHeaderText(middle);
 		alert.setTitle(title);
 		alert.show();
 	}
}
