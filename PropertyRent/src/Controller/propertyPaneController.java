package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Model.apartment;
import Model.rentalProperty;
import View.smallWindowUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class propertyPaneController implements Initializable {

	
	    @FXML
	    private ImageView imagedisplay;

	    @FXML
	    private Label label_des, label_info;

	    @FXML
	    private ListView<String> recordlistView;

	    @FXML
	    private Button btn_rent,btn_back;

	    @FXML
	    private Button btn_return;

	    @FXML
	    private Button btn_maintain;

	    @FXML
	    private Button btn_complete;
	    
	    
	    @FXML
	    private VBox recordbox;
	
	    smallWindowUnit swu = new smallWindowUnit();
	    rentalProperty rp =swu.tempropertylist.get(0);
	    String message;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		label_des.setMaxWidth(300);
		label_des.setText(rp.getDescription());
		label_des.setWrapText(true);
		String path = null;
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
		imagedisplay.setImage(image);
		
		if(rp instanceof apartment) 
		{
			message = String.format("%-34s%1s\n", "Property ID:", rp.getPropertyId())
					+ String.format("%-35s%1s\n", "Address:",
							rp.getStreetNumber() + " " + rp.getStreetName() + " " + rp.getSuburb())
					+ String.format("%-38s%1s\n", "Type:", rp.getPropertyType())
					+ String.format("%-34s%1s\n", "Bedroom:", rp.getNumOfBedrooms())
					+ String.format("%-37s%1s\n", "Status:", rp.getProrpertyStatus());
		}
		else
		{
			message = String.format("%-34s%1s\n", "Property ID:", rp.getPropertyId())
					+ String.format("%-35s%1s\n", "Address:",
							rp.getStreetNumber() + " " + rp.getStreetName() + " " + rp.getSuburb())
					+ String.format("%-38s%1s\n", "Type:", rp.getPropertyType())
					+ String.format("%-34s%1s\n", "Bedroom:", rp.getNumOfBedrooms())
					+ String.format("%-37s%1s\n", "Status:", rp.getProrpertyStatus())
					+ String.format("%-37s%1s\n", "Last Maintenance Date:", rp.getMaintenanceDate());
		}
			label_info.setMaxWidth(300);
			label_info.setText(message);
			label_info.setWrapText(true);
			ObservableList<String> recordlist = FXCollections.observableArrayList();
			for(int i = 0; i<rp.getRentRecord().size(); i++) 
			{
				recordlist.add(rp.getRentRecord().get(i).getDetails());
				//System.out.println(rp.getRentRecord().get(i).getDetails());
			}
			
			recordlistView.getItems().addAll(recordlist);
			
			btn_rent.setOnAction(e->{
				
				try {		
					Stage primaryStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/view/rentProperty.fxml"));
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.show();
					Stage stage = (Stage) btn_back.getScene().getWindow();
					stage.close();
				} catch (IOException e1) {					
					e1.printStackTrace();
				}
				
			});
			
             btn_return.setOnAction(e->{
				
				try {		
					Stage primaryStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/view/returnProperty.fxml"));
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.show();	
					Stage stage = (Stage) btn_back.getScene().getWindow();
					stage.close();
				} catch (IOException e1) {					
					e1.printStackTrace();
				}				
			});
             
             btn_complete.setOnAction(e->{
 				
 				try {		
 					Stage primaryStage = new Stage();
 					Parent root = FXMLLoader.load(getClass().getResource("/view/completeMaintain.fxml"));
 					Scene scene = new Scene(root);
 					primaryStage.setScene(scene);
 					primaryStage.show();	
 					Stage stage = (Stage) btn_back.getScene().getWindow();
 					stage.close();
 				} catch (IOException e1) {					
 					e1.printStackTrace();
 				}				
 			});
             
             
			//label_record.setText(recordlist.toString());
			//label_record.setMaxWidth(400);
			//label_record.setWrapText(true);		
	} 
	
	public void onClick_btn_back(ActionEvent event) throws Exception {
		Stage stage = (Stage) btn_back.getScene().getWindow();
		stage.close();
	}
	
	public void onClick_btn_maintain(ActionEvent event) throws Exception
	{ 
		try 
	   {
		rp.performMaintenance();
		JOptionPane.showMessageDialog(null, "This property in under maintainance");
	    }
		catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(e.getClass().getSimpleName());
			alert.setHeaderText("Error!");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			}
		
	}
	
	

}
