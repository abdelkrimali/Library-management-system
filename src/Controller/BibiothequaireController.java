package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import data.Bibliothequaire;
import data.Emprunteur;
import dbase.Tools;
import dbase.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.PasswordField;

public class BibiothequaireController implements Initializable {
	@FXML
	private TableView<Bibliothequaire> list_bib;
	@FXML
    private TableColumn<Bibliothequaire, String> tel_bib;
   	@FXML
    private TableColumn<Bibliothequaire, String>nom_bib;
   	@FXML
    private TableColumn<Bibliothequaire, String> prenom_bib;
   	@FXML
    private TableColumn<Bibliothequaire, String> email_bib;
	@FXML
	private TextField txtuser;
	@FXML
	private Button btnbrowse;
	@FXML
	private TextField txtprenom;
	@FXML
	private TextField txtnum_tel;
	@FXML
	private JFXTextField txt_recherch;
	@FXML
	private TextField txtemail;
	@FXML
	private TextField txtimage;
	@FXML
	private TextField txtnom;
	@FXML
	private ImageView image;
	@FXML
	private PasswordField txtpass;
	   @FXML
	    private Tab tabajout_bib;
	Image img;
	Connection con;
    Statement stmt;
    ResultSet rs;
    String url,s;
    PreparedStatement pst;
	Bibliothequaire bib=new Bibliothequaire();
	 ObservableList<Bibliothequaire>data=FXCollections.observableArrayList();
	 private	 void SetUrl(){
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
	
	public void btnbrowseaction(ActionEvent event)
	{	
		new Tools().btnbrowseaction(event, txtimage, img, image);
	}
	void setvalue() throws Utility
	{
		bib.setNom(txtnom.getText());
		bib.setPrenom(txtprenom.getText());
		bib.setNom_utilisateur(txtuser.getText());
		bib.setPass(txtpass.getText());
		bib.setTel(txtnum_tel.getText());
		bib.setEmail(txtemail.getText());
		bib.setImg(txtimage.getText());
	}

	@FXML
	void ajouter(ActionEvent e) throws Utility
	{
		setvalue();
		bib.add();
		data.clear();
		cleartext();
		getallrow();
	}
	
	void getallrow()
	{
		try
		{
			Setconnection();
			 String select="select nom_bib,prenom_bib,telephone,email from bibliothequaire ";
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	data.add(new Bibliothequaire(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
	            }
			
		}catch(SQLException ex)
		{
			new Tools().alertmsgbox(ex.getMessage());
		}
		nom_bib.setCellValueFactory(new PropertyValueFactory<Bibliothequaire,String>("nom"));
		prenom_bib.setCellValueFactory(new PropertyValueFactory<Bibliothequaire,String>("prenom"));
		email_bib.setCellValueFactory(new PropertyValueFactory<Bibliothequaire,String>("tel"));
		tel_bib.setCellValueFactory(new PropertyValueFactory<Bibliothequaire,String>("email"));
		
		list_bib.setItems(data);
	}
	@FXML
	void search(KeyEvent event)
	{
		try
		{
			data.clear();
			Setconnection();
			 String select="select nom_bib,prenom_bib,telephone,email from bibliothequaire where nom_bib like '%"+txt_recherch.getText()+"%'"
		 		+ " or prenom_bib  like '%"+txt_recherch.getText()+"%'"; ;
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            while(rs.next())
	            {
	            	data.add(new Bibliothequaire(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
	            }
		}catch(SQLException ex)
		{
			new Tools().alertmsgbox(ex.getMessage());
		}
		nom_bib.setCellValueFactory(new PropertyValueFactory<Bibliothequaire,String>("nom"));
		prenom_bib.setCellValueFactory(new PropertyValueFactory<Bibliothequaire,String>("prenom"));
		email_bib.setCellValueFactory(new PropertyValueFactory<Bibliothequaire,String>("tel"));
		tel_bib.setCellValueFactory(new PropertyValueFactory<Bibliothequaire,String>("email"));
		list_bib.setItems(data);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getallrow();
//		if(!LoginController.user.equals("admin"))
//		{
//			tabajout_bib.setDisable(true);
//		}
		
		
	}
	void cleartext()
	{
		txtnom.setText("");
		txtprenom.setText("");
		txtuser.setText("");
		txtpass.setText("");
		txtnum_tel.setText("");
		txtimage.setText("");
		txtemail.setText("");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
