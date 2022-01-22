package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import data.Emprunt;
import data.Exemplaire;
import dbase.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HomeController implements Initializable{
	Connection con;
    Statement stmt;
    ResultSet rs;
    String url,s,idexemple;
    String test,lect;
    String sanction;
    String issanction;
    Exemplaire exempl=new Exemplaire();
    Emprunt emprunt=new Emprunt();
    JFXButton btnemprunter;
    String nbremprunt,nbrsanction,nbravant;
    

    
    void affiche()
	{
		BufferedReader br=null;
		try
		{
			br=new BufferedReader(new FileReader("E://javafx//Moderne//setting.txt"));
			
			String line=br.readLine();
			if(line!=null)
			{
				nbremprunt=line;
				line=br.readLine();
				nbrsanction=line;
				line=br.readLine();
				nbravant=line;
				
				//new Tools().msgbox((nbremprunt + nbravant+nbrsanction));
			}
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
    
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    long sretard=0;
   
	@FXML
    private TextField txtref;

    @FXML
    private JFXTextField txtetud;

    @FXML
    private Label lbldept;

    @FXML
    private AnchorPane home;

    @FXML
    private Label dateretour;

    @FXML
    private JFXTabPane tab;

    @FXML
    private JFXTextField txtnum;

    @FXML
    private Label lblnom;

    @FXML
    private Label datepret;

    @FXML
    private Label today;

    @FXML
    private TextField txttitre;

    @FXML
    private Label lblprenom;

    @FXML
    private Label lblref;

    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtdept;

    @FXML
    private Label retard;
    @FXML
	private TableView<Exemplaire> tblexemple;
	@FXML
	private TableColumn<Exemplaire,String> id_exemple;
	@FXML
	private TableColumn<Exemplaire,String>etat_exemple;
    
    ObservableList<Exemplaire>data=FXCollections.observableArrayList();
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
    
    void aficheparam()
    {
    	new Tools().msgbox(SettingController.emprunt +"\n"+SettingController.sanction+"\n"+SettingController.avant_sanction);
    }
    
    @FXML
    void afficher_detaille(KeyEvent evt) throws IOException, SQLException
	  {
    	if(evt.getCode()==KeyCode.ENTER)
		{
    		
				String num=txtnum.getText();
	            Setconnection();
	            String select="select * from emprunteur where num_inscr='"+num+"'";
	            String s="select titre from ouvrage where refe_ouvre='"+txtref.getText()+"'";
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            if(rs.next())
	            {
	            	
	            	txtnom.setText((rs.getString("nom_emprunt")+" "+rs.getString("prenom_emprunt")).toUpperCase());
	            	txtdept.setText(rs.getString("departement"));
	            	lect=rs.getString("num_inscr");
	            	issanction=rs.getString(8);
	            }
	            else
	            {
	            	new Tools().msgbox("Aucune resultat");
	            	txtnum.setText("");
	            	txtnom.setText("");
	            	txtdept.setText("");
	            }   
		}
	  }
   
    @FXML
	void afficher_livre(KeyEvent evt) throws IOException, SQLException
	{
		if(evt.getCode()==KeyCode.ENTER)
		{
			String ref=txtref.getText();
            Setconnection();
            String select="select titre from ouvrage where refe_ouvre ='"+ref+"'";
           // String s="select titre from ouvrage where refe_ouvre='"+txtref.getText()+"'";
            stmt=con.createStatement();
            rs=stmt.executeQuery(select);
            if(rs.next())
            {


            	tblexemple.setVisible(true);
            	txttitre.setText(rs.getString(1).toUpperCase());
            	
            }
            else
            {
            	new Tools().msgbox("Aucune Livre Trouvé");
            	txttitre.setText("");
            	txtref.setText("");
            	
            } 
           
			
		}
		
		getallrow_exemplaire();
	}
   
    void getallrow_exemplaire()
    {data.clear();
    	try
	 	{
	 		Setconnection();
	 		 String select="select * from exemplaire where id_oeuvre='"+txtref.getText()+"' and disponible="+0;
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
	 	id_exemple.setCellValueFactory(new PropertyValueFactory<Exemplaire,String>("id"));
	 	etat_exemple.setCellValueFactory(new PropertyValueFactory<Exemplaire,String>("etat"));
	 	
	 	
	 	tblexemple.setItems(data);
    	
    }
   
    void setinformation()
	{
		tblexemple.setOnMouseClicked(new EventHandler<MouseEvent>() {

			
			@Override
			public void handle(MouseEvent arg0) {
				exempl=tblexemple.getItems().get(tblexemple.getSelectionModel().getSelectedIndex());
				test=exempl.getId();	
			}
		});
	}
   
    void setvalue() throws ParseException
    {
    	int jr=Integer.parseInt(nbremprunt);
    	;
    	 Calendar cl=Calendar.getInstance();
    	// Calendar cl=Calendar.getInstance();
    	 String date_emprunt=cl.get(Calendar.YEAR)+"-"+(cl.get(Calendar.MONTH)+1)+"-"+cl.get(Calendar.DAY_OF_MONTH);
    	 cl.add(Calendar.DATE,jr);
    	 String dat_retour_prevu=cl.get(Calendar.YEAR)+"-"+(cl.get(Calendar.MONTH)+1)+"-"+cl.get(Calendar.DAY_OF_MONTH);
    	
    	emprunt.setId_emprunt(emprunt.getAutonumber());
    	emprunt.setId_exemple(test);
    	emprunt.setId_emprunteur(lect);
    	
    	
    	emprunt.setDate_emprunt(date_emprunt);
    	emprunt.setDate_retoureffective("0000-00-00");
    	emprunt.setDate_retourprevu(dat_retour_prevu);
    	
    }
    @FXML
    void emprunter(ActionEvent event) throws ParseException
    {
    	setvalue();
    	
    	if(issanction.equals("0"))
    	{
    		if(!have_emprunt())
    		{
    		emprunt.add();
    		setindisponible();
    		cleartext();
        	getallrow_exemplaire();
        	tblexemple.setVisible(false);
        	}
    		else
    		{
    			new Tools().msgbox("Vous Avez deja un livre non rendu");
    			cleartext();
    		}
    	}
    	else
    	{
    		new Tools().alertmsgbox("Vous etes Sanctionner");
    		cleartext();
    	}
    }
    	
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		affiche();
		setinformation();
		
		cleartext();
		chercher_desanctioner();
		//new Tools().alertmsgbox(nbravant);
		

		 
	
	}
	 @FXML
	    void afficher_emprunt(KeyEvent evt) throws IOException, SQLException, ParseException
		  {
		 Calendar cl=Calendar.getInstance();
		
	    	if(evt.getCode()==KeyCode.ENTER)
			{
	    		
					String num=txtetud.getText();
		            Setconnection();
		            String select="select * from emprunt_data where num_inscr='"+num+"' and date_retoureffective='0000-00-00'";
		         
		            stmt=con.createStatement();
		            rs=stmt.executeQuery(select);
		            if(rs.next())
		            {
		            	idexemple=rs.getString(10);
		            	today.setText(cl.get(Calendar.YEAR)+"-"+(cl.get(Calendar.MONTH)+1)+"-"+cl.get(Calendar.DAY_OF_MONTH));
		            	lblnom.setText((rs.getString(2).toUpperCase()));
		            	lblprenom.setText(rs.getString(3).toUpperCase());
		            	lbldept.setText(rs.getString(4));
		            	lblref.setText(rs.getString(8));
		            	datepret.setText(rs.getString(5));
		            	dateretour.setText(rs.getString(6));
		            	
		            	retard.setText("01 (jours)");
		            	
		            	Date date1=sdf.parse(rs.getString("date_retourprevu"));
		            	Date date2=sdf.parse(today.getText());
		            	sretard=(date2.getTime()-date1.getTime())/86400000;
		            	if(sretard>0)
		            	{
		            		retard.setText(String.valueOf(sretard)+" (jours)");
		            	}
		            	else
		            	{
		            		retard.setText("00 (jours)");
		            	}
		   	
		            }
		            else
		            {
		            	new Tools().msgbox("Aucune resultat");
		            	cleartext();
		            	
		            }  
		           
			}
	    	
		  }
	 void cleartext()
	 {
		 lblnom.setText("");
     	lblprenom.setText("");
     	lbldept.setText("");
     	lblref.setText("");
     	datepret.setText("");
     	dateretour.setText("");
     	retard.setText("");
     	today.setText("");
     	txtnum.setText("");
     	txtnom.setText("");
     	txtdept.setText("");
     	txtref.setText("");
     	txttitre.setText("");
     	tblexemple.setVisible(false);
     	
	 }
	 void sanctioner()
	 {
		 String num=txtetud.getText();
		// new Tools().msgbox(num);
		 String select="update emprunteur set sanction="+1+" where num_inscr='"+num+"'";
		 boolean issanction=dbase.Go.runNonQuery(select);
		 if(issanction)
		 {
			 new Tools().msgbox("Sanction Enregistré");
		 }else
		 {
			 new Tools().msgbox("Erreur");
			 
		 } 
	 }
	 void setindisponible()
	 {
		 
		 String select ="update exemplaire set disponible="+1+" where id='"+test+"'";
		 boolean isdisponible=dbase.Go.runNonQuery(select);
		 if(isdisponible)
		 {
			System.out.println("Exemplaire indisponible");
			 
		 }else{
			 new Tools().msgbox("Erreur");
		 }
	 }
	 void setdisponible()
	 {
		
		 String select ="update exemplaire set disponible="+0+" where id='"+idexemple+"'";
		 boolean isdisponible=dbase.Go.runNonQuery(select);
		 if(isdisponible)
		 {
			 System.out.println("Exemplaire disponible");
			 
		 }else{
			 new Tools().msgbox("Erreur");
		 }
	 }
	 
	 @FXML
	 void rendre_livre(ActionEvent evet)
	 {
		 Calendar cl=Calendar.getInstance();
		 String dat_retour_effective=cl.get(Calendar.YEAR)+"-"+(cl.get(Calendar.MONTH)+1)+"-"+cl.get(Calendar.DAY_OF_MONTH);
		 String num=txtetud.getText();
		 String select="update emprunt set date_retoureffective='"+dat_retour_effective+"' where id_emprunteur='"+num+"'";
		 boolean isrendu=dbase.Go.runNonQuery(select);
		
		 if(isrendu)
		 {
			 setdisponible();
			 new Tools().msgbox("Livre rendu");
			
			 getallrow_exemplaire();
			 cleartext();
		 }
		 else{
			 new Tools().alertmsgbox("Echec");
		 }
		 
		 if(sretard>=Integer.parseInt(nbravant)){
		 	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Sanction emprunteur ");
	        alert.setContentText("Voulez-vous vraiment Sanctioner cette Emprunteur" + " ?");
	        Optional<ButtonType> answer = alert.showAndWait();
	        if (answer.get() == ButtonType.OK) {
	        	sanctioner();
	        	cleartext();
	        	
	        }else {
	          cleartext();
	            
	        }
		 }
	 }
	 
	 boolean have_emprunt()
	 {
		 try
	        {
	            Setconnection();
	            Statement stmt=con.createStatement();
	            String check="select emprunteur.num_inscr from emprunt ,emprunteur where emprunteur.num_inscr=emprunt.id_emprunteur and "
	            		+ " emprunt.date_retoureffective='0000-00-00' and emprunteur.num_inscr='"+lect+"'";
	            ResultSet rs=stmt.executeQuery(check);
	            while(rs.next())
	            {
	                return true;
	            }
	            
	        }
	        catch(SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	        return false;
	 }
	 
	 void chercher_desanctioner()
	 {
		 Calendar cl=Calendar.getInstance();
		 String today=(cl.get(Calendar.YEAR)+"-"+(cl.get(Calendar.MONTH)+1)+"-"+cl.get(Calendar.DAY_OF_MONTH));
		
	
     	if(sretard>0)
     	{
     		retard.setText(String.valueOf(sretard)+" (jours)");
     	}
     	else
     	{
     		retard.setText("00 (jours)");
     	}
		 
		 try
		 {
			
			 Setconnection();
			// String select="select id_emprunteur,date_retoureffective from emprunt where date_retoureffective>date_retourprevu";
			 String select="select num_inscr,date_retoureffective from emprunteur,emprunt WHERE emprunteur.num_inscr=emprunt.id_emprunteur and "
					 
					+" date_retoureffective>date_retourprevu"
					+" and emprunteur.sanction="+1;
			 stmt=con.createStatement();
			 rs=stmt.executeQuery(select);
			 while(rs.next())
			 {
				 Date date1=sdf.parse(rs.getString(2));
				 Date date2=sdf.parse(today);
				 sretard=(date2.getTime()-date1.getTime())/86400000;
				 if(sretard>=Long.parseLong(nbravant))
				 {
					 desanctioner(rs.getString(1));
					 System.out.println(rs.getString(1));
				 }

			 }
			 
		 }catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
	 }
	 void desanctioner(String username)
	 {
		
		 String select="update emprunteur set sanction="+0+" where num_inscr='"+username+"'";
		 boolean issanction=dbase.Go.runNonQuery(select);
		 if(issanction)
		 {
			 new Tools().msgbox("DeSanction Enregistré");
		 }else
		 {
			 new Tools().msgbox("Erreur");
			 
		 } 
	 }
	
	 
	 
	 
	 
	 
	 
	 
	 
	 }
	   
	   
	   

    



