package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import data.Exemplaire;
import data.Ouvrage;
import dbase.Tools;
import dbase.Utility;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;

public class ExemplaireController implements Initializable {
	 Exemplaire e=new Exemplaire();
	 int  nombre;
	static String test;
	@FXML
	private Label auteur;
	@FXML AnchorPane home;
	@FXML
	private Label titre;
	@FXML
	private Label nbexem;
	@FXML
	private Label type;
	@FXML
	private Label categ;
	@FXML
	private Label ref;
	@FXML
	private TableView<Exemplaire> tblexemp;
	@FXML
	private TableColumn<Exemplaire,String> id;
	@FXML
	private TableColumn<Exemplaire,String>etat;
	@FXML
	private JFXButton btnadd;
	@FXML
	private JFXButton btnmodif;
	@FXML
	private JFXButton btnsupp;
	Connection con;
    Statement stmt;
    ResultSet rs;
    String url,s;
    String id_exemple;
    ObservableList<Exemplaire>data=FXCollections.observableArrayList();
   
    
    void setinformation()
	{
    	tblexemp.setOnMouseClicked(new EventHandler<MouseEvent>() {

			
			@Override
			public void handle(MouseEvent arg0) {
				e=tblexemp.getItems().get(tblexemp.getSelectionModel().getSelectedIndex());
				
				id_exemple=e.getId();
				btnmodif.setDisable(false);
				btnsupp.setDisable(false);
			}
		});
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
	 void getallrow()
	 {
		 data.clear();
	 	try
	 	{
	 		Setconnection();
	 		 String select="select * from exemplaire where id_oeuvre='"+ref.getText()+"'";
	             stmt=con.createStatement();
	             rs=stmt.executeQuery(select);
	             while(rs.next())
	             {
	             	data.add(new Exemplaire(rs.getString(1), rs.getString(3)));
	             }
	 		
	 	}catch(SQLException ex)
	 	{
	 		ex.printStackTrace();
	 	}
	 	id.setCellValueFactory(new PropertyValueFactory<Exemplaire,String>("id"));
	 	etat.setCellValueFactory(new PropertyValueFactory<Exemplaire,String>("etat"));
	 	
	 	
	 	tblexemp.setItems(data);
	 }
	public String recuper()
    {
        LivreController lm =new LivreController();
        return lm.test;
    }
	@FXML
	void openajout(ActionEvent event) throws IOException
	{
		new Tools().openForm("Ajout_exemplaire.fxml");
		
	}
	void affiche()throws Utility,Exception
	{
		String num=recuper();
		test=num;
        Setconnection();
        String select="select * from ouvrage where refe_ouvre='"+num+"'";
        stmt=con.createStatement();
        rs=stmt.executeQuery(select);
        if(rs.next())
        {
        	titre.setText(rs.getString("titre"));
        	auteur.setText(rs.getString("auteur"));
        	categ.setText(rs.getString(4));
        	type.setText(rs.getString(5));
        	ref.setText(rs.getString(1));
        	
        }
        else
        {
        	new Tools().msgbox("Aucune resultat");
        }   
	}
	void affichenb() throws SQLException
	{
		String num=recuper();
		test=num;
        Setconnection();
        String select="select count(id) from exemplaire where id_oeuvre='"+num+"'";
       stmt=con.createStatement();
       rs=stmt.executeQuery(select);
       if(rs.next())
       {
    	   	nbexem.setText(rs.getString(1));
    	    nombre=Integer.parseInt(rs.getString(1));
    	   
       }
	}
	
	private Stage getStage() {
        return (Stage) home.getScene().getWindow();
    }

    @FXML
    private void close(MouseEvent event) throws IOException {
        getStage().close();
    
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setinformation();
		try {
			
			affiche();
			affichenb();
			getallrow();
			//btnadd.setDisable(true);
			btnmodif.setDisable(true);
			btnsupp.setDisable(true);
		} catch (Utility e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	@FXML
	void supp_exemplaire(ActionEvent e)
	{
	Exemplaire ex=new Exemplaire();
			ex.setId(id_exemple);
			ex.delete();
			getallrow();
			nombre=nombre-1;
			nbexem.setText(String.valueOf(nombre));
		
		
	}

}
