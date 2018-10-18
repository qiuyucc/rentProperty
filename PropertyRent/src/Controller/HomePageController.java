package Controller;

import Model.*;
import View.smallWindowUnit;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

import javafx.event.ActionEvent;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;


public class HomePageController implements Initializable {

	@FXML
	private FlowPane fp;
	
	@FXML
	private ScrollPane sp;

	@FXML
	private AnchorPane drawer_anchorpane;

	@FXML
	private Pane pane1;

	@FXML
	private JFXComboBox<String> combox_Type;

	@FXML
	private TextField txt_streetNumber;

	@FXML
	private TextField txt_streetName;

	@FXML
	private TextField txt_suburb;

	@FXML
	private JFXComboBox<String> combox_bedroomNum;

	@FXML
	private ImageView image_add;

	@FXML
	private CheckBox check_default;

	@FXML
	private JFXButton btn_add;

	@FXML
	private ComboBox<String> combox_suburb,combox_type,combox_status;
	
	@FXML
	private ComboBox<Integer> combox_num; 
	
	@FXML
	private TextArea txt_description;

	@FXML
	private DatePicker timer_lastMaintainence;

	@FXML
	private Label label_maintainDate;

	@FXML
	private Pane pane2;

	@FXML
	private Pane pane3;
	
	@FXML
    private Button btn_search,btn_reload;

	@FXML
	private JFXDrawer drawer;

	@FXML
	private JFXHamburger hamburger;

	@FXML
	private JFXButton btn_upload;
	
	@FXML 
	private Pane searchPane;

	// Store the image
	private FileInputStream pic;
	private FileChooser fileChooser;
	private File file;
	private Image image;
	String url = null;
	// private Desktop desktop = Desktop.getDesktop();
	public static ArrayList<rentalProperty> propertylist = new ArrayList<rentalProperty>();
	final String DB_NAME = "db_property";
	final String TABLE_NAME1 = "RENTAL_PROPERTY";
	final String TABLE_NAME2 = "RENTAL_RECORD";
	//combox defined
	ObservableList<String> Typelist = FXCollections.observableArrayList("Apartment", "Premium Suite");
	ObservableList<String> Numlist = FXCollections.observableArrayList("1", "2", "3");
	ObservableList<Integer> Numlist2 = FXCollections.observableArrayList(1,2,3);
	ObservableList<String> statuslist = FXCollections.observableArrayList("Available", "Rented", "Maintenance");
	ObservableList<String> suburblist = FXCollections.observableArrayList();
	smallWindowUnit swu = new smallWindowUnit();
	
	CreatePropertyTable cpt = new CreatePropertyTable();
	CreateRecordTable crt = new CreateRecordTable();
	DeletePropertyTable dpt = new DeletePropertyTable();
    DeleteRecordTable drt = new DeleteRecordTable();
    // initial the file choose for add property panel
	public void ini_addProperty() {
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters()
				.addAll(new ExtensionFilter("Image Files", "*.jpeg", "*.JPEG", "*.png", "*.jpg", "*.gif"));
		label_maintainDate.setVisible(false);
		timer_lastMaintainence.setVisible(false);
		combox_Type.setItems(Typelist);
		combox_bedroomNum.setItems(Numlist);
		image = new Image("/images/no-image.jpg");
		image_add.setImage(image);
	}
	// initial the search component
	public void ini_searchProperty() throws Exception
	{
		 combox_type.setItems(Typelist);
		 combox_num.setItems(Numlist2);
		 combox_status.setItems(statuslist);
		 
		 if (suburblist.isEmpty()) {
				try (Connection con = connectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
					String query = "SELECT DISTINCT suburb FROM " + TABLE_NAME1;
					try (ResultSet resultSet = stmt.executeQuery(query)) {
						while (resultSet.next()) {
							String s = resultSet.getString("suburb");
							suburblist.add(s);
						}
					} catch (SQLException e) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle(e.getClass().getSimpleName());
						alert.setHeaderText("Error!");
						alert.setContentText(e.getMessage());
						alert.showAndWait();
					}
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle(e.getClass().getSimpleName());
					alert.setHeaderText("Error!");
					alert.setContentText(e.getMessage());
					alert.showAndWait();
				}
			}
		 
		 combox_suburb.setItems(suburblist);
	}
	//load record to arraylist from database
   public void loadRecordData()throws Exception
   {
	   if (propertylist.get(0).getRentRecord().isEmpty()) {
			try (Connection con = connectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
				
				String s1, s2, s3, s4, s5;
				double s6 = 0, s7 = 0;
				DateTime dt = new DateTime();
				String query = "SELECT * FROM " + TABLE_NAME2;
				try (ResultSet resultSet = stmt.executeQuery(query)) {
					while (resultSet.next()) {
						s1 = resultSet.getString("propertyId");
						s2 = resultSet.getString("RentId");
						s3 = resultSet.getString("RentDate");
						
						s4 = resultSet.getString("EstReturnDate");
					
						if(resultSet.getString("ActReturnDate").isEmpty()) 
						{
							s5 = null;
						}
						else 
						{
							s5 = resultSet.getString("ActReturnDate");
						}
						
						s6 = resultSet.getDouble("RentalFee");
						s7 = resultSet.getDouble("LateFee");
						
						String day1 = s3.substring(0, 2);
						String month1 = s3.substring(3, 5);
						String year1 = s3.substring(6, 10);
						int newDay1 = Integer.parseInt(day1);
						int newMonth1 = Integer.parseInt(month1);
						int newYear1 = Integer.parseInt(year1);
						DateTime s32 = new DateTime(newDay1, newMonth1, newYear1);
						
						String day2 = s4.substring(0, 2);
						String month2 = s4.substring(3, 5);
						String year2 = s4.substring(6, 10);
						int newDay2 = Integer.parseInt(day2);
						int newMonth2 = Integer.parseInt(month2);
						int newYear2 = Integer.parseInt(year2);
						DateTime s42 = new DateTime(newDay2, newMonth2, newYear2);
						
						String day3 = s3.substring(0, 2);
						String month3 = s3.substring(3, 5);
						String year3 = s3.substring(6, 10);
						int newDay3 = Integer.parseInt(day3);
						int newMonth3 = Integer.parseInt(month3);
						int newYear3 = Integer.parseInt(year3);
						DateTime s52 = new DateTime(newDay3, newMonth3, newYear3);
						

						for (int i = 0; i < propertylist.size(); i++) {
							if (propertylist.get(i).getPropertyId().equals(s1)) {
								rentalRecord rr = new rentalRecord(propertylist.get(i), s2, s32,
										s42, s52, s6, s7);
								propertylist.get(i).getRentRecord().add(rr);
								break;
													}
							
						}
					
						
						//System.out.println(propertylist.get(index).getPropertyId() + ": " +propertylist.get(index).getRentRecord().get(index).getRentalFee());
					}
				} catch (SQLException e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle(e.getClass().getSimpleName());
					alert.setHeaderText("Error!");
					alert.setContentText(e.getMessage());
					alert.showAndWait();
				}
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle(e.getClass().getSimpleName());
				alert.setHeaderText("Error!");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
		}
   }
   
   //load property data to arraylist
	public void loadpropertyData() throws Exception
	{
		if (propertylist.isEmpty()) {
			Connection con = connectionTest.getConnection(DB_NAME);
			Statement stmt = con.createStatement();
			{
				String query = "SELECT * FROM RENTAL_PROPERTY";
				ResultSet resultSet = stmt.executeQuery(query);
				while (resultSet.next()) {
				    
					// String id = resultSet.getString("propertyId");
					int snum = resultSet.getInt("streetNumber");
					String sname = resultSet.getString("streetName");
					String sub = resultSet.getString("suburb");
					int bnum = resultSet.getInt("numOfBedrooms");
					String type = resultSet.getString("propertyType");
					String status = resultSet.getString("propertyStatus");
					String url2 = resultSet.getString("url");
					String desc = resultSet.getString("description");
					String mainDate = resultSet.getString("maintenanceDate");
                   
					String day, month, year;
					
					DateTime MaintenanceDate=null;
					if (mainDate == null) {
                             MaintenanceDate = null;
					} else {
						day = mainDate.substring(0, 2);
						month = mainDate.substring(3, 5);
						year = mainDate.substring(6, 10);
						int newDay = Integer.parseInt(day);
						int newMonth = Integer.parseInt(month);
						int newYear = Integer.parseInt(year);
						MaintenanceDate = new DateTime(newDay, newMonth, newYear);
					}

					if (type.equals("Premium Suite")) {
					
						rentalProperty ps = new premiumSuite(snum, sname, sub, 3, type, url2, desc, MaintenanceDate);
						propertylist.add(ps);
						 //System.out.println(ps.getUrl() + ps.getPropertyType());
					} else {
						
						propertylist.add(new apartment(snum, sname, sub, bnum, type, url2, desc));
				
					}
					}
			}
			}
	}
	     //display property by looping and show in one unit by calling funtion swu.getPane
		public void ini_displayProperty() throws Exception {
             
			fp.getChildren().removeAll(fp.getChildren());
			for (int i = 0; i < propertylist.size(); i++) {
                 
				fp.getChildren().add(swu.getPane(propertylist.get(i)));
				//int index = i;
				
			}
		}

	

	public void initialize(URL url, ResourceBundle rb) {
		
		drawer.open();
		try {
			loadpropertyData();
			loadRecordData();
			ini_searchProperty();
		} catch (Exception e2) {
			
			e2.printStackTrace();
		}
		if (!Main.isWelcomeScreenLoaded) {
			loadWelcomeScreen();
		}

		try {
			VBox box = FXMLLoader.load(getClass().getResource("/View/HomePage.fxml"));
			drawer.setSidePane(box);
			for (Node node : box.getChildren()) {
				if (node.getAccessibleText() != null) {
					node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
						switch (node.getAccessibleText()) {
						//case for button on drawer 
						case "btn_add":
							ini_addProperty();
							pane1.setVisible(true);
							sp.setVisible(false);
							fp.setVisible(false);
							pane3.setVisible(false);
							searchPane.setVisible(false);

							break;
						case "btn_displayProperty":

							try {
								ini_displayProperty();
							} catch (Exception e1) {

								e1.printStackTrace();
							}
							drawer.close();
							pane1.setVisible(false);
							fp.setVisible(true);
							sp.setVisible(true);
							pane3.setVisible(false);
							searchPane.setVisible(true);
							break;
						
						case "btn_save" :
							
							try {
								onClick_btn_save();	
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
							
						case "btn_import" :
							try {
								onClick_btn_import();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
							
						case "btn_export":
							try {
								onClick_btn_export ();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
							
							
						case "btn_exit":
							System.exit(0);
							break;
						}
					});
				}
			}
              //GUI design for hamburger to realize menu section
			HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburger);
			transition.setRate(-1);
			hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
				transition.setRate(transition.getRate() * -1);
				transition.play();

				if (drawer.isClosed()) {
					drawer.open();
					searchPane.setVisible(false);
				} else
					drawer.close();

			});
		} catch (IOException ex) {
			Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);

		}
	}
  //welcome gui design 
	private void loadWelcomeScreen() {
		Main.isWelcomeScreenLoaded = true;
		try {
			// Load splash screen view FXML
			StackPane pane = FXMLLoader.load(Main.class.getResource(("/View/welcome.fxml")));
			// Add it to root container (Can be StackPane, AnchorPane etc)
			drawer_anchorpane.getChildren().setAll(pane);

			// Load splash screen with fade in effect
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), pane);
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.setCycleCount(1);

			// Finish splash with fade out effect
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), pane);
			fadeOut.setFromValue(1);
			fadeOut.setToValue(0);
			fadeOut.setCycleCount(1);

			fadeIn.play();

			// After fade in, start fade out
			fadeIn.setOnFinished((e) -> {
				fadeOut.play();
			});

			// After fade out, load actual content
			fadeOut.setOnFinished((e) -> {
				try {
					AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/View/Drawer.fxml")));
					drawer_anchorpane.getChildren().setAll(parentContent);
				} catch (IOException ex) {
					Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
				}
			});
		} catch (IOException ex) {
			Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void onSelect_combox_type(ActionEvent event) {
		if (combox_Type.getValue().equals("Premium Suite")) {
			timer_lastMaintainence.setVisible(true);
			label_maintainDate.setVisible(true);
			combox_bedroomNum.setValue("3");
			combox_bedroomNum.setDisable(true);

		} else if (combox_Type.getValue().equals("Apartment")) {
			timer_lastMaintainence.setVisible(false);
			label_maintainDate.setVisible(false);
			combox_bedroomNum.setDisable(false);
		}

	}

	// For image upload btn
	public void onClick_btn_upload(ActionEvent event) throws Exception {

		file = fileChooser.showOpenDialog(null);
		if (file != null) {
			image = new Image(file.toURI().toString(), 250, 200, true, true);
			image_add.setImage(image);
			image_add.setFitHeight(200);
			image_add.setFitWidth(250);
			image_add.setPreserveRatio(true);
			url = file.getName();
		} else {
			image = new Image("/images/no-image.jpg");
			image_add.setImage(image);
			url = "no-image.jpg";
		}
		System.out.println(url);
	}

	// Add property to arrayList
	public void onClick_btn_add(ActionEvent event) throws Exception {

		String type = combox_Type.getValue();
		try {
			if (type.equals("Apartment")) {
				String name = txt_streetName.getText();
				int num = Integer.parseInt(txt_streetNumber.getText());
				String sub = txt_suburb.getText();
				String description = txt_description.getText();
				int bnum = Integer.parseInt(combox_bedroomNum.getValue());
				String newUrl = "images/" + url;
				String propertyId = "A_" + num + "_" + name + "_" + sub;
				// labelText.setText(url);;
				System.out.println(newUrl);
				for (int i = 0; i < propertylist.size(); i++) {
					if (propertyId.compareTo(propertylist.get(i).getPropertyId()) == 0) {
						throw new Exception("The Property already exists.");
					}
				}

				rentalProperty ap = new apartment(num, name, sub, bnum, type, newUrl, description);
				propertylist.add(ap);
				JOptionPane.showMessageDialog(null, "New apartment has been added" + propertyId);
			} else {
				String name = txt_streetName.getText();
				int num = Integer.parseInt(txt_streetNumber.getText());
				String sub = txt_suburb.getText();
				String description = txt_description.getText();
				int bnum = Integer.parseInt(combox_bedroomNum.getValue());
				String newUrl = "images/" + url;
				// labelText.setText(url);;
				String propertyId = "S_" + num + "_" + name + "_" + sub;

				String time = timer_lastMaintainence.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				String day, month, year;
				day = time.substring(0, 2);
				month = time.substring(3, 5);
				year = time.substring(6, 10);
				int newDay = Integer.parseInt(day);
				int newMonth = Integer.parseInt(month);
				int newYear = Integer.parseInt(year);
				DateTime MaintenanceDate = new DateTime(newDay, newMonth, newYear);

				for (int i = 0; i < propertylist.size(); i++) {
					if (propertyId.compareTo(propertylist.get(i).getPropertyId()) == 0) {
						throw new Exception("The Property already exists.");
					}
				}

				rentalProperty ps = new premiumSuite(num, name, sub, 3, type, newUrl, description, MaintenanceDate);
				propertylist.add(ps);
				JOptionPane.showMessageDialog(null, "New Premium Suit has been added" + propertyId);

			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(e.getClass().getSimpleName());
			alert.setHeaderText("Error!");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}

	}
	//reload for clean search function
	public void onClick_btn_reload(ActionEvent event)
	{
		fp.getChildren().removeAll(fp.getChildren());
		for (int i = 0; i < propertylist.size(); i++) {
             
			fp.getChildren().add(swu.getPane(propertylist.get(i)));
			//int index = i;
			
		}
			
	} 
	 //search based on selection
	public void onClick_btn_search(ActionEvent event) throws Exception
	{
		//int index = -1;
		onClick_btn_save();	
		String type, status, suburb;
		int num;
	    boolean choice = true;
		type = this.combox_type.getValue();
		status = this.combox_status.getValue();
		suburb = this.combox_suburb.getValue();
		num  =this.combox_num.getValue();
		
		try(Connection con = connectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();)
		{
			String query = "Select * from " + TABLE_NAME1;
			
			if (combox_type.getSelectionModel().isEmpty() == false
					&& combox_type.getSelectionModel().getSelectedItem() != null) {
				if (choice) {
					query += " WHERE ";
					choice = false;
				} else {
					query += " AND ";
				}
				String liketype = combox_type.getSelectionModel().getSelectedItem();
				query += " propertyType LIKE '" + liketype + "'";
			}
			if (combox_num.getSelectionModel().isEmpty() == false
					&& combox_num.getSelectionModel().getSelectedItem() != null) {
				if (choice) {
					query += " WHERE ";
					choice = false;
				} else {
					query += " AND ";
				}
				int likenum = Integer.valueOf(combox_num.getSelectionModel().getSelectedItem());
				query += " numOfBedrooms LIKE '" + likenum + "'";

			}
			if (combox_status.getSelectionModel().isEmpty() == false
					&& combox_status.getSelectionModel().getSelectedItem() != null) {
				if (choice) {
					query += " WHERE ";
					choice = false;
				} else {
					query += " AND ";
				}
				String likeStatus = combox_status.getSelectionModel().getSelectedItem();
				query += " propertyStatus LIKE '" + likeStatus + "'";
			}
			if (combox_suburb.getSelectionModel().isEmpty() == false
					&& combox_suburb.getSelectionModel().getSelectedItem() != null) {
				if (choice) {
					query += " WHERE ";
					choice = false;
				} else {
					query += " AND ";
				}
				String likesuburb = combox_suburb.getSelectionModel().getSelectedItem();
				query += " suburb LIKE '" + likesuburb + "'";
			}
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				fp.getChildren().removeAll(fp.getChildren());
				while (resultSet.next()) {
					String id = resultSet.getString("propertyId");
				int index= 1;
					for (int i = 0; i <propertylist.size(); i++) {
						if (id.compareTo(propertylist.get(i).getPropertyId()) == 0) {
							index = i;
							break;
						}
					}	
							
						fp.getChildren().add(swu.getPane(propertylist.get(index)));
						
					
		}}
				catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(e.getClass().getSimpleName());
			alert.setHeaderText("Error!");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	} catch (Exception e) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(e.getClass().getSimpleName());
		alert.setHeaderText("Error!");
		alert.setContentText(e.getMessage());
		alert.showAndWait();
	}	
	}
   //drop two table for record and property then insert new data from arraylist to db
	public void onClick_btn_save() throws Exception {

		  dpt.deleteTable();
		  drt.deleteTable();
		  cpt.createTable();
		  crt.createTable();
		  insertPropertyTOdb();
		  insertRecordTOdb();
		 		
	}
	// loop property data to database
	public void insertPropertyTOdb() throws Exception
	{
		try (Connection con = connectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			@SuppressWarnings("unused")
			int result = 0;
			for (int i = 0; i < propertylist.size(); i++) {
				StringTokenizer stoken = new StringTokenizer(propertylist.get(i).toString(), ":");
				String query = "INSERT INTO " + TABLE_NAME1 + " VALUES (";
				int k = 0;
				while (stoken.hasMoreTokens()) {
					if (k == 1 || k == 4) {
						query += stoken.nextToken() + ",";
						k++;
					} else {
						query += "'" + stoken.nextToken() + "',";
						k++;
					}
				}
				if (k == 9) {
					query += "null)";
				} else {
					query = query.substring(0, query.length() - 1);
					query += ")";
				}
				result = stmt.executeUpdate(query);
			}
			
			con.commit();
			System.out.println("add successfully record");
		} catch (Exception e) {
			e.printStackTrace();
		//	Alert alert = new Alert(AlertType.INFORMATION);
		//	alert.setTitle(e.getClass().getSimpleName());
		//	alert.setHeaderText("Error!");
		//	alert.setContentText(e.getMessage());
		//	alert.showAndWait();
		}
	}
	// loop record data to database
	public void insertRecordTOdb() throws Exception
	{
		try (Connection con = connectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			@SuppressWarnings("unused")
			int result = 0;
			// divide toString into parts by using StringTokenizer
			for (int i = 0; i < propertylist.size(); i++) {
				for (int j = 0; j < propertylist.get(i).getRentRecord().size(); j++) {
					StringTokenizer stoken = new StringTokenizer(propertylist.get(i).getRentRecord().get(j).toString(),
							":");
					String query = "INSERT INTO " + TABLE_NAME2 + " VALUES (" + "'"
							+ propertylist.get(i).getPropertyId() + "',";
					int k = 0;
					while (stoken.hasMoreTokens()) {
						if (k == 5 || k == 4) {
							query += stoken.nextToken() + ",";
							k++;
						} else {
							query += "'" + stoken.nextToken() + "',";
							k++;
						}
					}
					query = query.substring(0, query.length() - 1);
					query += ")";
					result = stmt.executeUpdate(query);
				}
			}
			System.out.println("add successfully record");
			con.commit();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(e.getClass().getSimpleName());
			alert.setHeaderText("Error!");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	public void onClick_btn_import ()throws Exception 
	{
		  dpt.deleteTable();
		  drt.deleteTable();
		  cpt.createTable();
		  crt.createTable();
		  readData();
	}
	//read data from txt file which exported by user
	public void readData() throws Exception
	{   String tokenUse ;
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("txt files", "*.txt"));
		File f = fc.showOpenDialog(null);
		if (f != null) {
		} else {
			return;
		}
		File file1 = new File(f.getAbsolutePath());
		try (Scanner input = new Scanner(file1)) {
			while (input.hasNextLine()) {
				@SuppressWarnings("unused")
				int result=0;
				try (Connection con = connectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
					StringTokenizer stoken = new StringTokenizer(input.nextLine(), ":");
					String query;
					int k = 0;
					if (stoken.countTokens() > 6) {
						// read the property line
						 tokenUse = stoken.nextToken();
						query = "INSERT INTO " + TABLE_NAME1 + " VALUES (" + "'" + tokenUse + "',";
						while (stoken.hasMoreTokens()) {
							if (k == 0 || k == 3) {
								query += stoken.nextToken() + ",";
								k++;
							} else {
								query += "'" + stoken.nextToken() + "',";
								k++;
							}
						}
						if (k == 8) {
							query += "null)";
						} else {
							query = query.substring(0, query.length() - 1);
							query += ")";
						}
					} else {
						tokenUse = stoken.nextToken();
						query = "INSERT INTO " + TABLE_NAME2 + " VALUES (" + "'" + tokenUse + "',";
						while (stoken.hasMoreTokens()) {
							if (k == 5 || k == 4) {
								query += stoken.nextToken() + ",";
								k++;
							} else {
								query += "'" + stoken.nextToken() + "',";
								k++;
							}
						}
						query = query.substring(0, query.length() - 1);
						query += ")";
					}
					result = stmt.executeUpdate(query);
					con.commit();
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle(e.getClass().getSimpleName());
					alert.setHeaderText("Error!");
					alert.setContentText(e.getMessage());
					alert.showAndWait();
				}
			}
		} catch (FileNotFoundException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(e.getClass().getSimpleName());
			alert.setHeaderText("Error!");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}
// export data to txt file
	public void onClick_btn_export () throws Exception
	{

		String path;
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog(null);
		if (selectedDirectory == null) {
			// No Directory selected
			path = "dataCopy.txt";
		} else {
			path = selectedDirectory.getAbsolutePath() + "/dataCopy.txt";
		}
		File copyFile = new File(path);
		try {
			PrintWriter output = new PrintWriter(copyFile);
			for (int i = 0; i < propertylist.size(); i++) {
				output.write(propertylist.get(i).toString() + "\n");
				for (int j = 0; j < propertylist.get(i).getRentRecord().size(); j++) {
					output.write(propertylist.get(i).getRentRecord().get(j).toString() + "\n");
				}
			}
			
			output.close();
		} catch (FileNotFoundException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(e.getClass().getSimpleName());
			alert.setHeaderText("Error!");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}	
	}

	

}
