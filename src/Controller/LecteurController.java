package Controller;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import data.Emprunteur;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
public class LecteurController  implements Initializable{
	Emprunteur e=new Emprunteur();
	String ss="";
	static String test;
	Connection con;
    Statement stmt;
    ResultSet rs;
    String url,s;
    PreparedStatement pst;
    
    @FXML
    private TableView<Emprunteur> list_emprunt;
    @FXML
    private TableColumn<Emprunteur, String> mail_emprunt;
    @FXML
    private TableColumn<Emprunteur, String> tel_emprunt;
    @FXML
    private TableColumn<Emprunteur, String> nom_emprunt;
    @FXML
    private TableColumn<Emprunteur, String> dept_emprunt;
    @FXML
    private TableColumn<Emprunteur, String> num_emprunt;
    @FXML
    private TableColumn<Emprunteur, String> prenom_emprunt;
    @FXML
    private JFXButton btnsupp;
    @FXML
    private JFXButton btnmodif;

    
	 @FXML
	    private ImageView image,img_detail;

	    @FXML
	    private ComboBox<String> cbxdept;

	    @FXML
	    private TextField txtemail;

	    @FXML
	    private TextField txtnum_tel;

	    @FXML
	    private Button btnbrowse;

	    @FXML
	    private TextField txtimage;

	    @FXML
	    private TextField txtnum_inscr;

	    @FXML
	    private TextField txtprenom;

	    @FXML
	    private TextField txtnom;
	    @FXML
	    private JFXTextField searchnum_inscr;
	    @FXML
	    private JFXTextField txt_recherch;
		
	    @FXML
	    private Label lblnom;
	    @FXML
	    private Label lblinscr;
	    @FXML
	    private Label lblprenom;
	    @FXML
	    private Label lbltel;
	    @FXML
	    private Label lblmail;
	    @FXML
	    private Label lbldept;
	    @FXML
	    private Label lblimg; 
        Image img;
        ObservableList<String>lisdept=FXCollections.observableArrayList("MI","SM","Informatique","Mathematique","Physique","Chimie");
        ObservableList<Emprunteur>data=FXCollections.observableArrayList();
void getallrow()
{
	try
	{
		Setconnection();
		 String select="select num_inscr,nom_emprunt,prenom_emprunt,departement,num_tel,email from emprunteur ";
            stmt=con.createStatement();
            rs=stmt.executeQuery(select);
            while(rs.next())
            {
            	data.add(new Emprunteur(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
		
	}catch(SQLException ex)
	{
		ex.printStackTrace();
	}
	num_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("num_inscr"));
	nom_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("nom"));
	prenom_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("prenom"));
	dept_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("departement"));
	mail_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("email"));
	tel_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("num_tel"));
	list_emprunt.setItems(data);
}

@FXML
void getcustomrow(ActionEvent event )
{
	try
	{
		data.clear();
		Setconnection();
		 String select="select num_inscr,nom_emprunt,prenom_emprunt,departement,num_tel,email from emprunteur where nom_emprunt like '%"+txt_recherch.getText()+"%'"
		 		+ " or num_inscr  like '%"+txt_recherch.getText()+"%'"; 
            stmt=con.createStatement();
            rs=stmt.executeQuery(select);
            while(rs.next())
            {
            	data.add(new Emprunteur(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
		
	}catch(SQLException ex)
	{
		new Tools().alertmsgbox(ex.getMessage());
	}
	num_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("num_inscr"));
	nom_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("nom"));
	prenom_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("prenom"));
	dept_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("departement"));
	mail_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("email"));
	tel_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("num_tel"));
	list_emprunt.setItems(data);
}
@FXML
void search(KeyEvent event )
{
	try
	{
		
		data.clear();
		Setconnection();
		 String select="select num_inscr,nom_emprunt,prenom_emprunt,departement,num_tel,email from emprunteur where nom_emprunt like '%"+txt_recherch.getText()+"%'"
		 		+ " or num_inscr  like '%"+txt_recherch.getText()+"%'"; 
            stmt=con.createStatement();
            rs=stmt.executeQuery(select);
            while(rs.next())
            {
            	data.add(new Emprunteur(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
		
	}catch(SQLException ex)
	{
		ex.printStackTrace();
	}
	num_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("num_inscr"));
	nom_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("nom"));
	prenom_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("prenom"));
	dept_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("departement"));
	mail_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("email"));
	tel_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunteur,String>("num_tel"));
	list_emprunt.setItems(data);
}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbxdept.setItems(lisdept);
		btnmodif.setDisable(true);
		btnsupp.setDisable(true);
		getallrow();
		setinformation();
	}
	void setinformation()
	{
		list_emprunt.setOnMouseClicked(new EventHandler<MouseEvent>() {

			
			@Override
			public void handle(MouseEvent arg0) {
				e=list_emprunt.getItems().get(list_emprunt.getSelectionModel().getSelectedIndex());
				
				test=e.getNum_inscr();
				btnmodif.setDisable(false);
				btnsupp.setDisable(false);
			}
		});
	}
	@FXML
	void supp(ActionEvent ev)
	{
		
		 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Suppression emprunteur ");
	        alert.setContentText("Voulez-vous vraiment supprimer cette emprunteur" + " ?");
	        Optional<ButtonType> answer = alert.showAndWait();
	        if (answer.get() == ButtonType.OK) {
	        	e.delete();
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
	void refresh(ActionEvent ev)
	{
		data.clear();
		getallrow();
		btnmodif.setDisable(true);
		btnsupp.setDisable(true);
		txt_recherch.setText("");
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
	public void btnbrowseaction(ActionEvent event)
	{	
		new Tools().btnbrowseaction(event, txtimage, img, image);
	}
	void setvalues() throws Utility
	{
		e.setNum_inscr(txtnum_inscr.getText());
		e.setNom(txtnom.getText());
		e.setPrenom(txtprenom.getText());
		e.setNum_tel(txtnum_tel.getText());
		e.setImage(txtimage.getText());
		e.setEmail(txtemail.getText());
		e.setDepartement(cbxdept.getSelectionModel().getSelectedItem());
	}
	@FXML
	void enregistrer(ActionEvent event) throws Utility {
		  setvalues();
		  e.add();
		  txtnum_inscr.setText("");
		  txtnom.setText("");
		  txtprenom.setText("");
		  txtnum_tel.setText("");
		  txtimage.setText("");
		  txtemail.setText("");
		  cbxdept.getSelectionModel().select("");
		  image.setImage(null);
		  
		  data.clear();
		  getallrow();
	    }
	@FXML
    void afficher_detaille(ActionEvent event) throws Utility,Exception
	  {
		  
				String num=searchnum_inscr.getText();
	            Setconnection();
	            String select="select * from emprunteur where num_inscr='"+num+"' or nom_emprunt='"+num+"'";
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            if(rs.next())
	            {
	            	lblinscr.setText(rs.getString("num_inscr"));
	            	lblnom.setText(rs.getString("nom_emprunt"));
	            	lblprenom.setText(rs.getString("prenom_emprunt"));
	            	lbldept.setText(rs.getString("departement"));
	            	lblmail.setText(rs.getString("email"));
	            	if(rs.getString("image")!=null){
	            		if(rs.getString("image").length()==0)
	            		{
	            			img_detail.setImage(null);
	            			lblimg.setText("Aucune image enregisté ");
	            		}
	            	img=new Image(rs.getString("image"));
	            	img_detail.setImage(img);
	            	lblimg.setText("");
	            	}
	            }
	            else
	            {
	            	new Tools().msgbox("Aucune resultat");
	            	lblinscr.setText("");
	            	lblnom.setText("");
	            	lblprenom.setText("");
	            	lbldept.setText("");
	            	lblmail.setText("");
	            	lblimg.setText("");
	            	img_detail.setImage(null);
	            }   
	  }
	@FXML
    void afficher_details(KeyEvent event) throws Utility,Exception
	  {
		if(event.getCode()==KeyCode.ENTER)
		{
				String num=searchnum_inscr.getText();
	            Setconnection();
	            String select="select * from emprunteur where num_inscr='"+num+"' or nom_emprunt='"+num+"'";
	            stmt=con.createStatement();
	            rs=stmt.executeQuery(select);
	            if(rs.next())
	            {
	            	lblinscr.setText(rs.getString("num_inscr"));
	            	lblnom.setText(rs.getString("nom_emprunt"));
	            	lblprenom.setText(rs.getString("prenom_emprunt"));
	            	lbldept.setText(rs.getString("departement"));
	            	lblmail.setText(rs.getString("email"));
	            	if(rs.getString("image")!=null){
	            		if(rs.getString("image").length()==0)
	            		{
	            			img_detail.setImage(null);
	            			lblimg.setText("Aucune image enregisté ");
	            		}
	            	img=new Image(rs.getString("image"));
	            	img_detail.setImage(img);
	            	lblimg.setText("");
	            	}
	            }
	            else
	            {
	            	new Tools().msgbox("Aucune resultat");
	            	lblinscr.setText("");
	            	lblnom.setText("");
	            	lblprenom.setText("");
	            	lbldept.setText("");
	            	lblmail.setText("");
	            	lblimg.setText("");
	            	img_detail.setImage(null);
	            }   
		}
	  }
	@FXML
	void openmodif(ActionEvent event) throws IOException
	{
		new Tools().openForm("Modifier_emprunteur.fxml");
		
	}
	void refresh()
	{
		lblinscr.setText("");
    	lblnom.setText("");
    	lblprenom.setText("");
    	lbldept.setText("");
    	lblmail.setText("");
    	img_detail.setImage(null);
    	
	}
	
	
      
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

