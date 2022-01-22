package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import com.jfoenix.controls.JFXComboBox;

import data.Exemplaire;
import dbase.Tools;

public class Ajout_exemplaireController implements Initializable{
	Exemplaire e=new Exemplaire();
	@FXML AnchorPane home;
	@FXML
	private JFXComboBox<String> cbxetat;
	@FXML
	private TextField txtid;
	@FXML
	private JFXButton btnadd;
	@FXML
	private JFXButton btnannul;
	 
	ObservableList<String>listetat=FXCollections.observableArrayList("Neuf","Bon","Vieux");
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cbxetat.setItems(listetat);
		txtid.setText(e.getAutonumber());
		
	}
	private Stage getStage() {
        return (Stage) home.getScene().getWindow();
    }

    @FXML
    private void close(MouseEvent event) throws IOException {
        getStage().close();
    
    }
	void setvalue()
	{
		
		e.setId(txtid.getText());
		e.setOuvreid(ExemplaireController.test);
		e.setEtat(cbxetat.getSelectionModel().getSelectedItem());
	}
	@FXML
	void ajouter(ActionEvent event)
	{
		setvalue();
		e.add();
		txtid.setText(e.getAutonumber());
		
		
	}
}
