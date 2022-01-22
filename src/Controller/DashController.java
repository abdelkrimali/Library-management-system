package Controller;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import dbase.Tools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class DashController implements Initializable {
	@FXML AnchorPane home;
	@FXML AnchorPane leftmenu;
	@FXML JFXButton btnbibiliotheq;
	
	@FXML BorderPane test;
	 @FXML
	  private Label user;
	 	@FXML
	  private Label mdate;
	 	@FXML
	  private Label mtime;
	 Connection con;
	    Statement stmt;
	    ResultSet rs;
	    String url,s;
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
	    void afficher_detaille() throws SQLException 
		  {
			  
					String u=LoginController.user;
					String p=LoginController.pass;
		            Setconnection();
		            String select="select nom_bib,prenom_bib from Bibliothequaire where nom_utilisateur ='"+u+"' and mot_de_pass ='"+p+"'";
		            stmt=con.createStatement();
		            rs=stmt.executeQuery(select);
		            if(rs.next())
		            {
		            	
		            	user.setText((rs.getString(1)+" "+rs.getString(2)).toUpperCase());
		            }
		  }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AnchorPane pane;
		currentdate();
		
		try {
			pane = FXMLLoader.load(getClass().getResource("/frames/Home.fxml"));
			home.getChildren().setAll(pane);
			try {
				afficher_detaille();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		if(!LoginController.user.equals("admin"))
		{
			btnbibiliotheq.setDisable(true);
		}
	}
	
	public void show(ActionEvent event) throws IOException 
	{
		AnchorPane pane=FXMLLoader.load(getClass().getResource("/frames/Lecteur.fxml"));
		home.getChildren().setAll(pane);
		
	}
	public void showstat(ActionEvent event) throws IOException 
	{
		AnchorPane pane=FXMLLoader.load(getClass().getResource("/frames/Statistique.fxml"));
		home.getChildren().setAll(pane);
		
	}
	
	public void showhome(ActionEvent event) throws IOException 
	{
		AnchorPane pane=FXMLLoader.load(getClass().getResource("/frames/Home.fxml"));
		home.getChildren().setAll(pane);
		
	}
	public void close(MouseEvent event) throws IOException
	{
		((Node)event.getSource()).getScene().getWindow().hide();

		//System.exit(0);
	}
	private Stage getStage() {
        return (Stage) home.getScene().getWindow();
    }

    @FXML
    private void handleMenuClose(ActionEvent event) throws IOException {
        getStage().close();
        new Tools().openForm("Login.fxml");
    }
	@FXML
	void reduire(ActionEvent event) throws IOException
	{
		Stage stage=(Stage)home.getScene().getWindow();
		stage=(Stage)((Button)event.getSource()).getScene().getWindow();
		stage.setIconified(true);
		
		//stage.setMaximized(true);
	}
	@FXML
	void maximize(ActionEvent event) throws IOException
	{
		Stage stage=(Stage)home.getScene().getWindow();
		stage=(Stage)((Button)event.getSource()).getScene().getWindow();
	
		stage.setMaximized(true);
	}
    public void openprofile(ActionEvent event) throws Exception 
    {
    	AnchorPane pane=FXMLLoader.load(getClass().getResource("/frames/Profile.fxml"));
		home.getChildren().setAll(pane);
    	
    }
    public void openpbibliothequaire(ActionEvent event) throws Exception 
    {
    	AnchorPane pane=FXMLLoader.load(getClass().getResource("/frames/Bibiothequaire.fxml"));
		home.getChildren().setAll(pane);
    	
    }
    public void openlivre(ActionEvent event) throws Exception 
    {
    	AnchorPane pane=FXMLLoader.load(getClass().getResource("/frames/Livre.fxml"));
		home.getChildren().setAll(pane);
    	
    }
    public void openparam(ActionEvent event) throws Exception 
    {
    	new Tools().openForm("Setting.fxml");
    	
    }
   
    private void currentdate()
    {
        Thread clock;
      clock = new Thread(){
        public void run(){
            for(;;){
                
                //Set Current Date
                
                Calendar cal=new GregorianCalendar();
                int month=cal.get(Calendar.MONTH);
                int year=cal.get(Calendar.YEAR);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                if(month<10)
            {
                mdate.setText(day+"/0"+(month+1)+"/"+year);
            }
                else
            {
                 mdate.setText(day+"/"+(month+1)+"/"+year);
            }
                
               
                
            }
            
        }
        
    };
       clock.start();
    }
   
	
}
