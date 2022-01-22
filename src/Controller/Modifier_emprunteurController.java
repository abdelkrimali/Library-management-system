package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import data.Emprunteur;
import dbase.Tools;
import dbase.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Modifier_emprunteurController implements Initializable{
	Connection con;
    Statement stmt;
    ResultSet rs;
    String url,s;
    PreparedStatement pst;
    Emprunteur e=new Emprunteur();
	
	LecteurController lc=new LecteurController();
	@FXML
	private TextField txtprenom;
	@FXML
	private Button btnbrowse;
	@FXML
	private TextField txtnom;
	@FXML
	private TextField txtnum_tel;
	@FXML
	private TextField txtemail;
	@FXML
	private ComboBox<String> cbxdept;
	@FXML
	private TextField txtimage;
	@FXML
	private Label nometprenom;
	@FXML
	private Label lblimg;
	@FXML
	private Label lblnum;
	@FXML
	private ImageView image;
	 Image img;
	 ObservableList<String>lisdept=FXCollections.observableArrayList("MI","SM","Informatique","Mathematique","Physique","Chimie");
	 @Override
		public void initialize(URL location, ResourceBundle resources) {
		 cbxdept.setItems(lisdept);
			txtnom.setText(recuper());
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
	@FXML
	void affiche()throws Utility,Exception
	{
		String num=recuper();
        Setconnection();
        String select="select * from emprunteur where num_inscr='"+num+"' or nom_emprunt='"+num+"'";
        stmt=con.createStatement();
        rs=stmt.executeQuery(select);
        if(rs.next())
        {
        	lblnum.setText(rs.getString("num_inscr"));
        	txtnom.setText(rs.getString("nom_emprunt"));
        	txtprenom.setText(rs.getString("prenom_emprunt"));
        	nometprenom.setText((rs.getString("nom_emprunt")+" "+rs.getString("prenom_emprunt")).toUpperCase());
        	txtemail.setText(rs.getString("email"));
        	txtnum_tel.setText(rs.getString("num_tel"));
        	cbxdept.getSelectionModel().select(rs.getString(4));
        	if(rs.getString("image")!=null){
        		if(rs.getString("image").length()==0)
        		{
        			image.setImage(null);
        			lblimg.setText("Aucune image enregisté ");
        		}
        	img=new Image(rs.getString("image"));
        	image.setImage(img);
        	txtimage.setText(rs.getString("image"));
        	//lblimg.setText("");
        	}
        }
        else
        {
        	new Tools().msgbox("Aucune resultat");
        }   
	}

	// Event Listener on Button[#btnbrowse].onAction
	 public void btnbrowseaction(ActionEvent event)
		{	
			new Tools().btnbrowseaction(event, txtimage, img, image);
			
		}
	
	
	public void close(MouseEvent event) throws IOException
	{
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	void setvalues() throws Utility
	{
		
		e.setNum_inscr(recuper());
		e.setNom(txtnom.getText());
		e.setPrenom(txtprenom.getText());
		e.setNum_tel(txtnum_tel.getText());
		e.setImage(txtimage.getText());
		e.setEmail(txtemail.getText());
		e.setDepartement(cbxdept.getSelectionModel().getSelectedItem());
	}
	
	public String recuper()
    {
        LecteurController lm =new LecteurController();
        return lm.test;
    }
	
	@FXML
	void modifier(ActionEvent event) throws Utility {
		setvalues();
		e.update(); 
		  txtnom.setText("");
		  txtprenom.setText("");
		  txtnum_tel.setText("");
		  txtimage.setText("");
		  txtemail.setText("");
		  cbxdept.getSelectionModel().select("");
		  image.setImage(null);

	    }
	
}
