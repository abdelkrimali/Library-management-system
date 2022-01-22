package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import data.Emprunt;
import data.Emprunteur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class StatistiqueController implements Initializable {
	Connection con;
    Statement stmt;
    ResultSet rs;
    String url,s;
	@FXML
	private TableView <Emprunt>table_emprunt;
	@FXML
	private TableColumn <Emprunt,String>num_etud;
	@FXML
	private TableColumn <Emprunt,String>nom;
	@FXML
	private TableColumn <Emprunt,String>prenom;
	@FXML
	private TableColumn <Emprunt,String>reference;
	@FXML
	private TableColumn <Emprunt,String> titre;
	@FXML
	private TableColumn<Emprunt,String> date_emprunt;
	@FXML
	private TableColumn <Emprunt,String>date_retour;
	@FXML Label lbllivre;
	@FXML Label lblneuf;
	@FXML Label lblbon;
	@FXML Label lblvieux;
	@FXML Label lblexemple;
	@FXML Label lblexempleemprunte;
	@FXML Label lblexemplerendu;
	@FXML Label lblretard;
	@FXML Label lblsanction;
	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
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
	 ObservableList<Emprunt>data=FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getallrow();
		lbllivre.setText(nombre_livre());
		lblexemple.setText(nombre_exemplaire());
		lblexempleemprunte.setText(nombre_exemplaire_emprunte());
		lblexemplerendu.setText(nombre_exemplaire_rendu());
		lblretard.setText(retard());
		lblsanction.setText(sanctione());
		lblneuf.setText(exemple_neuf());
		lblbon.setText(exemple_bon());
		lblvieux.setText(exemple_vieu());
	}
	void getallrow()
	{
		try
		{
			Setconnection();
			String select="select num_inscr,nom,prenom,refe_ouvre,titre,date_emprunt,date_retourprevu from emprunt_data";
			stmt=con.createStatement();
			rs=stmt.executeQuery(select);
			while(rs.next())
			{
				data.add(new Emprunt(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7)));
			}

		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		num_etud.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("id_emprunteur"));
		nom.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("prenom"));
		reference.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("reference"));
		titre.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("titre"));
		date_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("date_emprunt"));
		date_retour.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("date_retourprevu"));
		table_emprunt.setItems(data);
	}
	String nombre_exemplaire()
	{
		try
		{
			Setconnection();
			String select="select count(id) from exemplaire";
			stmt=con.createStatement();
			rs=stmt.executeQuery(select);
			if(rs.next())
			{
				return rs.getString(1);
				
			}
				
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return "";
	}
	String nombre_exemplaire_emprunte()
	{
		try
		{
			Setconnection();
			String select="select count(id_exemplaire) from emprunt where date_retoureffective='0000-00-00'";
			stmt=con.createStatement();
			rs=stmt.executeQuery(select);
			if(rs.next())
			{
				return rs.getString(1);
				
			}
				
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return "";
	}
	String nombre_exemplaire_rendu()
	{
		
		try
		{
			Setconnection();
			String select="select count(id_exemplaire) from emprunt where date_retoureffective!='0000-00-00'";
			stmt=con.createStatement();
			rs=stmt.executeQuery(select);
			if(rs.next())
			{
				return rs.getString(1);
				
			}
				
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return "";
	}
	String nombre_livre()
	{
		try
		{
			Setconnection();
			String select="select count(refe_ouvre) from ouvrage";
			stmt=con.createStatement();
			rs=stmt.executeQuery(select);
			if(rs.next())
			{
				return rs.getString(1);
				
			}
	
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return "";
	}
	String retard()
	{
		
		try
		{
			Setconnection();
			String select="select count(id_emprunt ) from emprunt where date_retoureffective>date_retourprevu";
			stmt=con.createStatement();
			rs=stmt.executeQuery(select);
			if(rs.next())
			{
				return rs.getString(1);
				
			}
	
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return "";
	}
	String sanctione()
	{
		try
		{
			Setconnection();
			String select="select count(num_inscr) from emprunteur where sanction=1";
			stmt=con.createStatement();
			rs=stmt.executeQuery(select);
			if(rs.next())
			{
				return rs.getString(1);
				
			}
	
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return "";
	}
	String exemple_bon()
	{
		try
		{
			Setconnection();
			String select="select count(id) from exemplaire  where etat='Bon'";
			stmt=con.createStatement();
			rs=stmt.executeQuery(select);
			if(rs.next())
			{
				return rs.getString(1);
				
			}
	
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return "";
	}
	String exemple_neuf()
	{
		try
		{
			Setconnection();
			String select="select count(id) from exemplaire  where etat='Neuf'";
			stmt=con.createStatement();
			rs=stmt.executeQuery(select);
			if(rs.next())
			{
				return rs.getString(1);
				
			}
	
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return "";
	}
	String exemple_vieu()
	{
		try
		{
			Setconnection();
			String select="select count(id) from exemplaire  where etat='Vieux'";
			stmt=con.createStatement();
			rs=stmt.executeQuery(select);
			if(rs.next())
			{
				return rs.getString(1);
				
			}
	
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return "";
	}
	

}
