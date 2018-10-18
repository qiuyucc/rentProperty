package Controller;


import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public static Boolean isWelcomeScreenLoaded = false;
	
	public static void main(String[] args) {
		launch(args);
	}
	public void start(Stage primaryStage)  throws Exception{
		try {
			//ResourceBundle resources = ResourceBundle.getBundle("Language/lang_pt");
			
			Parent root = FXMLLoader.load(Main.class.getResource("/View/Drawer.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/View/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("FlexiRent");
			primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	
	
}
