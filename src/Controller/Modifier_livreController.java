package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import data.Ouvrage;
import dbase.Tools;
import dbase.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

public class Modifier_livreController implements Initializable {
	Connection con;
    Statement stmt;
    ResultSet rs;
    String url,s;
    Ouvrage o=new Ouvrage();
	@FXML
	private TextField txt_auteur;
	@FXML
	private TextField txt_titre;
	@FXML
	private ComboBox<String> cbx_categ;
	@FXML
	private ComboBox<String> cbx_type;
	@FXML
	private Label lbllivre;
	@FXML
	private Label lblnum;
	 ObservableList<String>liscateg=FXCollections.observableArrayList("Policier","Historique","Lettre","Scientifique");
	 ObservableList<String>listype=FXCollections.observableArrayList("Livre","Magazine","Memoire");
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbx_categ.setItems(liscateg);
		cbx_type.setItems(listype);
		try {
			affiche();
		} catch (Utility e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 private void SetUrl(){
	        url="jdbc:mysql://localhost:3306/bibliotheque"
	                +"?useUnicode=true&characterEncoding=UTF-8";
	    }
	
	void Setconnection()
	    {
	        try{
	        SetUrl();
	      con=DriverManager.getConnection(url,"root","");
	        }
	        catch(SQLException ex){
	            ex.printStackTrace();
	        }
	    }
	public String recuper()
    {
        LivreController lm =new LivreController();
        return lm.test;
    }
	@FXML
	void affiche()throws Utility,Exception
	{
		String num=recuper();
		
        Setconnection();
        String select="select * from ouvrage where refe_ouvre='"+num+"' or titre='"+num+"'";
        stmt=con.createStatement();
        rs=stmt.executeQuery(select);
        if(rs.next())
        {
        	txt_titre.setText(rs.getString("titre"));
        	txt_auteur.setText(rs.getString("auteur"));
        	cbx_categ.getSelectionModel().select(rs.getString(4));
        	cbx_type.getSelectionModel().select(rs.getString(6));
       
        	lbllivre.setText(rs.getString("titre").toUpperCase());	
        }
        else
        {
        	new Tools().msgbox("Aucune resultat");
        }   
	}
	
	void setvalues() throws Utility
	{
		o.setReference(recuper());
		o.setTitre(txt_titre.getText());
		o.setAuteur(txt_auteur.getText());
		o.setCateg(cbx_categ.getSelectionModel().getSelectedItem());
		o.setType(cbx_type.getSelectionModel().getSelectedItem());
	}
	@FXML
	void modifier(ActionEvent e) throws Utility
	{
		setvalues();
		o.update();
	}
	public void close(MouseEvent event) throws IOException
	{
		((Node)event.getSource()).getScene().getWindow().hide();

		//System.exit(0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
