package View;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Model.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class smallWindowUnit {

	
	private Button btn_detail;
	public static ArrayList<rentalProperty> tempropertylist = new ArrayList<rentalProperty>();
	public Pane getPane(rentalProperty rp) {
		
		StackPane spane = new StackPane();
		spane.setStyle("-fx-background-color:lightgrey;");
		Pane pane = new Pane();
		pane.setPrefSize(320, 250);
		spane.setPrefSize(320, 250);// set the size of the pane
		pane.setStyle("-fx-background-color:lightgrey;");
		String path=null;
		if(rp.getUrl() ==null) 
		{
			rp.setUrl("images/no-image.jpg");
			path="./images/no-image.jpg";
		}
		else 
		{
			path="./"+rp.getUrl();
			
		}
		Image image = new Image(path,320,150,false,false);
		//System.out.println(path + "" + rp.getPropertyId());
		ImageView pic=new ImageView(image);		
		
		
		btn_detail = new Button("Click me");
		btn_detail.setLayoutX(255);
		btn_detail.setLayoutY(160);
		btn_detail.setOnAction(e->{
			
			try {
				tempropertylist.clear();
				tempropertylist.add(rp);
				//System.out.println(rp.getDescription());
				//FXMLLoader Loader = new FXMLLoader();
				//propertyManageController info = Loader.getController();
			     //info.setDesc(rp.getDescription());
			    //info.setInfo(message);
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/view/propertyPanel.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.show();
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		Label dis = new Label();
		dis.setMaxWidth(320);
		//dis.setMaxWidth(150);
		dis.setLayoutY(160);
		dis.setId("description");
		dis.setWrapText(true);
		//dis.setPrefWidth(375);
		dis.setText(rp.getPropertyType() + " " + rp.getProrpertyStatus().toUpperCase() +"\n" +rp.getStreetNumber()+" " +rp.getStreetName()+ rp.getSuburb()+" \n" +rp.getDescription());
        spane.getChildren().add(pane);
        pane.getChildren().addAll(pic,dis,btn_detail);
        
		return spane;
	}

	

}
