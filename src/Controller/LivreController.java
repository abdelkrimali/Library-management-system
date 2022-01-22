package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import data.Emprunteur;
import data.Exemplaire;
import data.Ouvrage;
import dbase.Tools;
import dbase.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class LivreController implements Initializable {
	Connection con;
    Statement stmt;
    ResultSet rs;
    String url,s;
    static String test;
	Ouvrage ouv=new Ouvrage();
	Exemplaire e=new Exemplaire();
	  @FXML
	    private TableView<Ouvrage> list_ouvrage;
	   @FXML
	    private TableColumn<Ouvrage, String> auteur;

	    @FXML
	    private TableColumn<Ouvrage, String> ref;

	    @FXML
	    private TableColumn<Ouvrage, String> titre;
	    @FXML
	    private TableColumn<Ouvrage, String> typ;

	    @FXML
	    private TableColumn<Ouvrage, String> categ;
	    
	@FXML
	private TextField txtauteur;
	@FXML
	private TextField txttitre;
	@FXML
	private JFXTextField txt_recherch;
	@FXML
	private ComboBox<String> cbxcateg;
	@FXML
	private Label lbltitre;
	@FXML
	private Label lblauteur;
	@FXML
	private Label lblcateg;
	@FXML
	private Label lbltype;
	@FXML
	private JFXTextField txtsearch;
	@FXML
	private TextField txtref;
	@FXML
	private ComboBox<String> cbxtype;
	 @FXML
	    private JFXButton btnsupp;
	 @FXML
	    private JFXButton btnexemp;
	    @FXML
	    private JFXButton btnmodif;
	 ObservableList<String>liscateg=FXCollections.observableArrayList("Policier","Historique","Lettre","Scientifique");
	 ObservableList<String>listype=FXCollections.observableArrayList("Livre","Magazine","Memoire");
	 ObservableList<Ouvrage>data=FXCollections.observableArrayList();
	 void getallrow()
	 {
	 	try
	 	{
	 		Setconnection();
	 		 String select="select * from Ouvrage ";
	             stmt=con.createStatement();
	             rs=stmt.executeQuery(select);
	             while(rs.next())
	             {
	             	data.add(new Ouvrage(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
	             }
	 		
	 	}catch(SQLException ex)
	 	{
	 		ex.printStackTrace();
	 	}
	 	ref.setCellValueFactory(new PropertyValueFactory<Ouvrage,String>("reference"));
	 	titre.setCellValueFactory(new PropertyValueFactory<Ouvrage,String>("titre"));
	 	auteur.setCellValueFactory(new PropertyValueFactory<Ouvrage,String>("auteur"));
	 	categ.setCellValueFactory(new PropertyValueFactory<Ouvrage,String>("categ"));
	 	typ.setCellValueFactory(new PropertyValueFactory<Ouvrage,String>("type"));
	 	
	 	list_ouvrage.setItems(data);
	 }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbxcateg.setItems(liscateg);
		cbxtype.setItems(listype);
		btnmodif.setDisable(true);
		btnsupp.setDisable(true);
		btnexemp.setDisable(true);
		
		getallrow();
		setinformation();
		
	}
	@FXML
	void refresh(ActionEvent ev)
	{
		data.clear();
		getallrow();
		btnmodif.setDisable(true);
		btnsupp.setDisable(true);
		btnexemp.setDisable(true);
		txt_recherch.setText("");
	}
	@FXML
	void openmodif(ActionEvent event) throws IOException
	{
		new Tools().openForm("Modifier_livre.fxml");
		
	}
	@FXML
	void openexemple(ActionEvent event) throws IOException
	{
		new Tools().openForm("Exemplaire.fxml");
		
	}
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
	void setvalue() throws Utility
	{
		ouv.setReference(txtref.getText());
		ouv.setTitre(txttitre.getText());
		ouv.setAuteur(txtauteur.getText());
		ouv.setType(cbxtype.getSelectionModel().getSelectedItem());
		ouv.setCateg(cbxcateg.getSelectionModel().getSelectedItem());
	}
	@FXML
	void save(ActionEvent event) throws Utility
	{
		setvalue();
		ouv.add();
		add_exemple();
		txtref.setText("");
		txttitre.setText("");
		txtauteur.setText("");
		cbxcateg.getSelectionModel().select("Selectioner une  categorie");
		cbxtype.getSelectionModel().select("Selectioner un type");
		data.clear();
		getallrow();
		
	}
	@FXML
	void affiche_datail(ActionEvent evt) throws Utility,Exception
	{
		String ref=txtsearch.getText();
		
		Setconnection();
		String select="select titre , auteur,categorie,typeoeuvre from ouvrage where refe_ouvre='"+ref+"' or titre='"+ref+"'";
		stmt=con.createStatement();
		rs=stmt.executeQuery(select);
		if(rs.next())
		{
			lbltitre.setText(rs.getString(1));
			lblauteur.setText(rs.getString(2));
			lblcateg.setText(rs.getString(3));
			lbltype.setText(rs.getString(4));
		}else
		{
			new Tools().msgbox("aucune resultat!");
			lbltitre.setText("");
			lblauteur.setText("");
			lblcateg.setText("");
			lbltype.setText("");
		}
	}
	void setinformation()
	{
		list_ouvrage.setOnMouseClicked(new EventHandler<MouseEvent>() {

			
			@Override
			public void handle(MouseEvent arg0) {
				ouv=list_ouvrage.getItems().get(list_ouvrage.getSelectionModel().getSelectedIndex());
				
				test=ouv.getReference();
				btnmodif.setDisable(false);
				btnsupp.setDisable(false);
				btnexemp.setDisable(false);
			}
		});
	}
	@FXML
	void supp(ActionEvent ev)
	{
		
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Suppression emprunteur ");
	        alert.setContentText("Voulez-vous vraiment supprimer cette Ouvrage" + " ?");
	        Optional<ButtonType> answer = alert.showAndWait();
	        if (answer.get() == ButtonType.OK) {
	        	ouv.delete();
	        	txt_recherch.setText("");
	        	
	        }else {
	           
	            txt_recherch.setText("");
	        }
	        data.clear();
			getallrow();
			btnmodif.setDisable(true);
			btnsupp.setDisable(true);
	  }
	@FXML
	void search(KeyEvent event )
	{
		try
		{
			
			data.clear();
			Setconnection();
			 String select="select * from ouvrage where refe_ouvre like '%"+txt_recherch.getText()+"%'"
			 		+ " or auteur  like '%"+txt_recherch.getText()+"%'"
			 		+ " or typeoeuvre  like '%"+txt_recherch.getText()+"%'"
			 		+ " or categorie  like '%"+txt_recherch.getText()+"%'"
			 		+ " or titre  like '%"+txt_recherch.getText()+"%'"; 
			 stmt=con.createStatement();
             rs=stmt.executeQuery(select);
             while(rs.next())
             {
             	data.add(new Ouvrage(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
             }
            
 		
 	}catch(SQLException ex)
 	{
 		ex.printStackTrace();
 	}
 	ref.setCellValueFactory(new PropertyValueFactory<Ouvrage,String>("reference"));
 	titre.setCellValueFactory(new PropertyValueFactory<Ouvrage,String>("titre"));
 	auteur.setCellValueFactory(new PropertyValueFactory<Ouvrage,String>("auteur"));
 	categ.setCellValueFactory(new PropertyValueFactory<Ouvrage,String>("categ"));
 	typ.setCellValueFactory(new PropertyValueFactory<Ouvrage,String>("type"));
 	
 	list_ouvrage.setItems(data);
	}
	
	@FXML
	void searche(ActionEvent event )
	{
		try
		{
			
			data.clear();
			Setconnection();
			 String select="select * from ouvrage where refe_ouvre like '%"+txt_recherch.getText()+"%'"
			 		+ " or auteur  like '%"+txt_recherch.getText()+"%'"
			 		+ " or typeoeuvre  like '%"+txt_recherch.getText()+"%'"
			 		+ " or categorie  like '%"+txt_recherch.getText()+"%'"
			 		+ " or titre  like '%"+txt_recherch.getText()+"%'"; 
			 stmt=con.createStatement();
             rs=stmt.executeQuery(select);
             if(rs.next())
             {
             	data.add(new Ouvrage(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
             }else{
            	 new Tools().msgbox("Aucun Ouvrage Trouvé");
             }
 		
 	}catch(SQLException ex)
 	{
 		ex.printStackTrace();
 	}
 	ref.setCellValueFactory(new PropertyValueFactory<Ouvrage,String>("reference"));
 	titre.setCellValueFactory(new PropertyValueFactory<Ouvrage,String>("titre"));
 	auteur.setCellValueFactory(new PropertyValueFactory<Ouvrage,String>("auteur"));
 	categ.setCellValueFactory(new PropertyValueFactory<Ouvrage,String>("categ"));
 	typ.setCellValueFactory(new PropertyValueFactory<Ouvrage,String>("type"));
 	
 	list_ouvrage.setItems(data);
	}
	
	void setvalexemple()
	{
		e.setOuvreid(txtref.getText());
		e.setId(e.getAutonumber());
		e.setEtat("Neuf");
	}
	void add_exemple()
	{
		setvalexemple();
		e.add();
	}
	
	
	

}
