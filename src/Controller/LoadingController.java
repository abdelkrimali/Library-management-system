package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class LoadingController implements Initializable{

	@FXML
    private Circle c3;

    @FXML
    private Circle c1;

    @FXML
    private Circle c2;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		setrotate(c1,true,360,10);
		setrotate(c2,true,360,8);
		setrotate(c3,true,360,04);
		
	}
	
	
	int rotate=0;
	private void setrotate(Circle c,boolean reverse,int angle,int dure)
	{
		
		RotateTransition rotatet=new RotateTransition(Duration.seconds(dure),c);
		rotatet.setAutoReverse(reverse);
		rotatet.setByAngle(angle);
		rotatet.setDelay(Duration.seconds(0));
		rotatet.setRate(3);
		rotatet.setCycleCount(18);
		rotatet.play();
	}
	
	
	
	

}
