package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TrackPage {

    @FXML
    private Label rutaCorta;

    @FXML
    private Label distanciaTotal;

    @FXML
    private Label numeroPeajes;
    
    private String txtRuta;
    private String txtDistancia;
    private String txtPeajes;
	
    public TrackPage(String txtRuta, String txtDistancia, String txtPeajes) {
		this.txtRuta = txtRuta;
		this.txtDistancia = txtDistancia;
		this.txtPeajes = txtPeajes;
	}
    
    @FXML
    void initialize() {
    	rutaCorta.setText(txtRuta);
    	distanciaTotal.setText(txtDistancia);
    	numeroPeajes.setText(txtPeajes);
    }
}
