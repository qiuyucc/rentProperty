package Model;

import java.sql.Date;
import java.util.ArrayList;

import javafx.scene.image.Image;

import Model.apartment;
import Model.DateTime;
import Model.premiumSuite;
import Model.rentalRecord;


public class rentalProperty {

	private String propertyId;
	private int streetNumber;
	private String streetName;
	private String suburb;
	private int numOfBedrooms;
	private String propertyStatus;
	private String propertyType;
	private String url;
	private String description;
	private DateTime maintenanceDate;
	private ArrayList<rentalRecord> rentRecord;
	
	// Constructor
	
	public rentalProperty(int streetNumber,String streetName, String suburb, int numOfBedrooms, String propertyType, String url, String description ) 
	{
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.suburb = suburb;
		this.numOfBedrooms = numOfBedrooms;
		this.propertyStatus = "Available";
		this.propertyType = propertyType;
		this.url=url;
		this.description = description;	
		
		rentRecord = new ArrayList<rentalRecord>(10);
	}
	
	// Setter & getter
	
	public String getUrl() 
	{
		return url;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDecription(String description) 
	{
		this.description = description;
	}
	
	public DateTime getMaintenanceDate() 
	{
		return maintenanceDate;
	}
	
	public void setMaintenanceDate(DateTime maintenanceDate) 
	{
		this.maintenanceDate= maintenanceDate;
	}
	
	public String getProrpertyStatus() 
	{
		return propertyStatus;
	}
	
	public void setPropertyStatus(String propertyStaus) 
	{
		this.propertyStatus= propertyStaus;
	}
	
	public String getPropertyType() 
	{
		return propertyType;
	}
	
	public void setPropertyType(String propertyType) 
	{
		this.propertyType = propertyType;
	}
	
	public String getPropertyId(){
		return propertyId;
	}
	
	public void setPropertyID(String propertyId) 
	{
		this.propertyId = propertyId;
	}
	
	public int getStreetNumber()
	{
		return streetNumber;
	}
	
	public void setStreetNumber(int StreetNumber) 
	{
		this.streetNumber = StreetNumber;
	}
	
	public String getStreetName() 
	{
		return streetName;
	}
	
	public void setStreetName(String streetName) 
	{
		this.streetName = streetName;
	}
	
	public String getSuburb()
	{
		return suburb;
	}
	public void setSuburb(String suburb) 
	{
		this.suburb = suburb ;
	}
	
	

	public int getNumOfBedrooms() 
	{
		return numOfBedrooms;
	}
	
	public void setNumOfBedrooms(int numOfBedrooms) 
	{
		this.numOfBedrooms = numOfBedrooms;
	}
	
	public rentalRecord getRentRecord(int number) {
		return this.rentRecord.get(number);
	}

	public ArrayList<rentalRecord> getRentRecord() {
		return this.rentRecord;
	}
	
	public void setPropertyStatusToRented() {
		this.propertyStatus = "Rented";
	}

	public void setPropertyStatusToAvailable() {
		this.propertyStatus = "Available";
	}

	public void setPropertyStatusToMaintenance() {
		this.propertyStatus = "Maintenance";
	}
	
	public void returnProperty(DateTime returnDate) throws Exception {
		if (DateTime.diffDays(returnDate, rentRecord.get(0).getRentDate()) < 0) {
			throw new Exception("Invalid check out date.");
		} else {
			rentRecord.get(0).setActualReturnDate(returnDate);
			rentRecord.get(0).setRentalFee();
			rentRecord.get(0).setLateFee();
			setPropertyStatusToAvailable();
			rentRecord.get(0).getActualReturnDate();
			rentRecord.get(0).getRentalFee();
			rentRecord.get(0).getLateFee();
			rentRecord.get(0).setIsReturn();
		}
	}

	public void rent(String customerId, DateTime rentDate, int numOfRentDay) throws Exception {
		DateTime eReturnDate = new DateTime(rentDate, numOfRentDay);
		if (this.propertyStatus.equals("Available")) {
			if (this instanceof premiumSuite) {
				premiumSuite PS = (premiumSuite) this;
				
				if (DateTime.diffDays(PS.getMaintenanceDate(), eReturnDate) + 10 > 0) {
					rentalRecord rr = new rentalRecord(this, customerId, rentDate, eReturnDate);
					ArrayList<rentalRecord> newRentalRecord = new ArrayList<rentalRecord>(10);
					newRentalRecord.add(rr);
					for (int i = 0; i < this.rentRecord.size(); i++) {
						newRentalRecord.add(this.rentRecord.get(i));
					}
					this.rentRecord = newRentalRecord;
					this.propertyStatus = "Rented";
				} else {
					throw new Exception("The estimated return day is not before the next maintenance day.");
				}
			} else if (this instanceof apartment) {
				DateTime standardDay = new DateTime(06, 01, 2018);// standard day 06/01/2018 is Sunday
				int remainder = (DateTime.diffDays(rentDate, standardDay)) % 7;
				
				if ((remainder >= 0 && remainder <= 4) && (numOfRentDay <= 28 && numOfRentDay >= 2)) {
					rentalRecord RR = new rentalRecord(this, customerId, rentDate, eReturnDate);
					ArrayList<rentalRecord> newRentalRecord = new ArrayList<rentalRecord>(10);
					newRentalRecord.add(RR);
					for (int i = 0; i < this.rentRecord.size(); i++) {
						newRentalRecord.add(this.rentRecord.get(i));
					}
					this.rentRecord = newRentalRecord;
					this.propertyStatus = "Rented";
				} else if ((remainder >= 5 && remainder <= 6) && (numOfRentDay <= 28 && numOfRentDay >= 3)) {
					rentalRecord RR = new rentalRecord(this, customerId, rentDate, eReturnDate);
					ArrayList<rentalRecord> newRentalRecord = new ArrayList<rentalRecord>(10);
					newRentalRecord.add(RR);
					for (int i = 0; i < this.rentRecord.size(); i++) {
						newRentalRecord.add(this.rentRecord.get(i));
					}
					this.rentRecord = newRentalRecord;
					this.propertyStatus = "Rented";

				} else {
					throw new Exception("The rent days are too long or too short.");
				}
			}
		} else {
			throw new Exception("The status of this property is not available.");
		}
	}
	
	public void performMaintenance() throws Exception {
		if (this.propertyStatus.equals("Available")) {
			this.propertyStatus = "Maintenance";
		} else {
			throw new Exception("The property is not available currently.");
		}
	}
	
	public void  completeMaintenance(DateTime completionDate) throws Exception{
		if (this.propertyStatus == "Maintenance") {
			if (this instanceof premiumSuite) {
			this.maintenanceDate = completionDate;
			this.propertyStatus ="Available";
		}else 
		{
			throw new Exception("This property is not under maintenance");
		}
			}
		else if (this.propertyStatus == "Maintenance") {
			if(this instanceof apartment) {
			this.propertyStatus ="Available";
			
		} 
		}else
		{
			throw new Exception("This property is not under maintenance");
		}
	}



		public String toString() {
			if (this instanceof apartment) {
				return this.propertyId + ":" + this.streetNumber + ":" + this.streetName + ":" + this.suburb + ":"
						+ this.numOfBedrooms + ":" + this.propertyType + ":" + this.propertyStatus + ":" + this.url + ":"
						+ this.description;
			} else
				return this.propertyId + ":" + this.streetNumber+ ":" + this.streetName + ":" + this.suburb + ":"
						+ this.numOfBedrooms + ":" + this.propertyType + ":" + this.propertyStatus + ":" + this.url + ":"
						+ this.description + ":" + this.maintenanceDate;
		}

	

public String getDetails() {
	String message;
	message = String.format("%-25s%1s\n", "Property ID:", this.propertyId)
			+ String.format("%-25s%1s\n", "Address:", this.streetNumber + " " + this.streetName + " " + this.suburb)
			+ String.format("%-25s%1s\n", "Type:", this.propertyType)
			+ String.format("%-25s%1s\n", "Bedroom:", this.getNumOfBedrooms())
			+ String.format("%-25s%1s\n", "Status:", this.getNumOfBedrooms());
	if (this instanceof premiumSuite) {
		premiumSuite PS = (premiumSuite) this;
		message += String.format("%-25s%1s\n", "Last Maintenance:", PS.getMaintenanceDate().getFormattedDate());

	}
	if (this.rentRecord.size() > 0) {
		message += "RENTAL RECORD";
		message += this.rentRecord.get(0).getDetails();
	} else {
		message += String.format("%-25s%1s\n", "RENTAL RECORD:", "empty");
	}
	
	for (int i = 1; i < this.rentRecord.size(); i++) {
		message += this.rentRecord.get(i).getDetails();
	}
	return message;

}
}