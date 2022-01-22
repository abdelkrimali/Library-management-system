package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = new BorderPane();
			Parent root=FXMLLoader.load(getClass().getResource("/frames/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			//primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	public static void main(String[] args) {
		launch(args);
		
	}
}
