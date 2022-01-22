package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import dbase.Tools;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;

public class LoginController {
	@FXML
	private Pane cover;
	@FXML
	private Label lbl;
	@FXML
	private Label lblcon;
	@FXML
	private TextField txtuser;
	 @FXML
	 private PasswordField txtpass;
	
	@FXML
	private JFXButton btn=null;
	static String pass="mohammmed";
	static String user="mohammmed";
	
	
	
	
	public void close(MouseEvent event)
	{
		//((Node)event.getSource()).getScene().getWindow().hide();
		Platform.exit();
		//System.exit0);
	}
	public void login( ActionEvent event) throws IOException
	{
		String user=txtuser.getText();
		String password=txtpass.getText();
			Tools t=new Tools();
			
		boolean islog=dbase.Go.Chekuserandpass(user, password);
		if(islog)
		{
			this.pass=password;
			this.user=user;
			
			lblcon.setText("Bien Identifiée");
			((Node)event.getSource()).getScene().getWindow().hide();
			t.openForm("Dash.fxml");
			System.out.println("Welcome");
		}
		else
		{
			lblcon.setText("Nom Utilisateur Ou Mot Pass Invalide");
			txtuser.setText("");
			txtpass.setText("");
			txtuser.requestFocus();
		}
	}
	@FXML
	void connect(KeyEvent evt) throws IOException
	{
		if(evt.getCode()==KeyCode.ENTER)
		{
			String user=txtuser.getText();
			String password=txtpass.getText();
			boolean islog=dbase.Go.Chekuserandpass(user, password);
			if(islog)
			{
				this.pass=password;
				this.user=user;
				lblcon.setText("Bien Identifiée");
				((Node)evt.getSource()).getScene().getWindow().hide();
				new Tools().openForm("Dash.fxml");
				System.out.println("Welcome");
			}
			else
			{
				lblcon.setText("Nom Utilisateur Ou Mot Pass Invalide");
				txtuser.setText("");
				txtpass.setText("");
				txtuser.requestFocus();
			}
		}
		
	}
	
	
	
	
}















