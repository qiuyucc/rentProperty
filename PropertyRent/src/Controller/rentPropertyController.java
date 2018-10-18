package Controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Model.DateTime;
import Model.RentException;
import Model.rentalProperty;
import View.smallWindowUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class rentPropertyController implements Initializable{

    @FXML
    private ImageView imagelistView;

    @FXML
    private Label label_Id;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_rent;

    @FXML
    private TextField txt_cid;

    @FXML
    private TextField txt_days;

    @FXML
    private DatePicker timer_start;
    
    smallWindowUnit swu = new smallWindowUnit();
    rentalProperty rp =swu.tempropertylist.get(0);
    String message;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		label_Id.setMaxWidth(300);
		label_Id.setText(rp.getPropertyId());
		label_Id.setWrapText(true);
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
		imagelistView.setImage(image);
	}
	
	
	public void onClick_btn_back(ActionEvent event) throws Exception {
		Stage stage = (Stage) btn_back.getScene().getWindow();
		stage.close();
	}
    
	public void onClik_btn_rent(ActionEvent event) throws RentException
	{
	
		try {
		String customerId = txt_cid.getText();
		
		int dayOfRent = Integer.parseInt(txt_days.getText());
		
		String time = timer_start.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String day, month, year;
		day = time.substring(0, 2);
		month = time.substring(3, 5);
		year = time.substring(6, 10);
		int newDay = Integer.parseInt(day);
		int newMonth = Integer.parseInt(month);
		int newYear = Integer.parseInt(year);
		DateTime time_start = new DateTime(newDay, newMonth, newYear);
		
		
		if(customerId !=null && time_start != null && txt_days.getText() !=null ) 
		{
			rp.rent(customerId, time_start, dayOfRent);
			JOptionPane.showMessageDialog(null,"Rent successfully");
			Stage stage = (Stage) btn_back.getScene().getWindow();
			stage.close();
		}
		else 
		{
			JOptionPane.showMessageDialog(null,"Please enter correct info");
		}
		
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
