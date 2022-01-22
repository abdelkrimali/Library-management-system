package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dbase.Tools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class SettingController implements Initializable{

	
	@FXML JFXTextField jouremprunt;
	@FXML JFXTextField delaisanction;
	@FXML JFXTextField validesanction;
	@FXML JFXButton btnsave;
	static String emprunt ;
	static String sanction;
	static String avant_sanction;
	static  String nbremprunt,nbrsanction,nbravant;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		affiche();
	}
	
	
	public void close(MouseEvent event) throws IOException
	{
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	@FXML
	void modifier(ActionEvent ev)
	{
		try
		{
			PrintWriter w=new PrintWriter("setting.txt");
			w.println(jouremprunt.getText());
			w.println(delaisanction.getText());
			w.println(validesanction.getText());
			
			w.close();
			new Tools().msgbox("Modification Enregistré");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	 void affiche()
	{
		BufferedReader br=null;
		try
		{
			br=new BufferedReader(new FileReader("E://javafx//Moderne//setting.txt"));
			
			String line=br.readLine();
			if(line!=null)
			{
				emprunt=line;
				line=br.readLine();
				sanction=line;
				line=br.readLine();
				avant_sanction=line;
				
				
			}
			jouremprunt.setText(emprunt);
			validesanction.setText(avant_sanction);
			delaisanction.setText(sanction);
			
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	
		
		
	}

