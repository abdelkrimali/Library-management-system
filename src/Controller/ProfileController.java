package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import data.Bibliothequaire;
import dbase.Tools;
import dbase.Utility;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class ProfileController implements Initializable{
	@FXML
	private TextField txt_email;
	@FXML
	private TextField txt_nom;
	@FXML
	private TextField txt_prenom;
	@FXML
	private JFXButton btn;
	@FXML
	private PasswordField currentpass;
	@FXML
	private PasswordField newpass;
	@FXML
	private PasswordField confirmpass;
	@FXML
	private Label nom_et_prenom;
	static String nom;
	static String prenom;
	
	String user;
	String pass;
	Connection con;
    Statement stmt;
    ResultSet rs;
    String url,s;
    Bibliothequaire bib=new Bibliothequaire();
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

	String recuper()
	{
		LoginController l=new LoginController();
		return l.pass;
	}
	
    void afficher_detaille() throws SQLException 
	  {
		  
				String u=LoginController.user;
				String p=LoginController.pass;
	            Setconnection();
	            String select="select * from Bibliothequaire where nom_utilisateur ='"+u+"' and mot_de_pass ='"+p+"'";
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            if(rs.next())
	            {
	            	txt_email.setText(rs.getString(5));
	            	txt_nom.setText(rs.getString(1));
	            	txt_prenom.setText(rs.getString(2));
	            	nom_et_prenom.setText((rs.getString(1)+" "+rs.getString(2)).toUpperCase());
	     }
	  }
    void setval() throws Utility
    {
    	bib.setEmail(txt_email.getText());
    	//bib.setPass(LoginController.pass);
    	bib.setNom_utilisateur(LoginController.user);
    	if(currentpass.getText().equals(LoginController.pass))
    	{
    		if(confirmpass.getText().equals(newpass.getText()))
    				{
    					bib.setPass(confirmpass.getText());
    				}
    		else{
    			throw new Utility("Les deux champs ne sont pas les meme ");
    		}
    	}
    	else
    	{
    		throw new Utility("Le Mot passe est incorrect! ");
    	}
    }
    @FXML 
    void update_profile(ActionEvent event) throws Utility
    {
    	setval();
    	bib.update();
    	confirmpass.setText("");
    	newpass.setText("");
    	currentpass.setText("");
    	
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			afficher_detaille();
			nom=txt_nom.getText();
			prenom=txt_prenom.getText();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

