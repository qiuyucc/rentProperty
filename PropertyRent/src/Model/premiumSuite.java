package Model;

import java.sql.Date;

import javafx.scene.image.Image;

public class premiumSuite extends rentalProperty {
	public premiumSuite(int streetNumber, String streetName, String suburb, int numberOfBedrooms, String propertyType,
			String url, String description,DateTime maintenanceDate) {
		super(streetNumber, streetName, suburb, numberOfBedrooms,propertyType,url, description);
		super.setPropertyID("S_" + super.getStreetNumber() + "_" + super.getStreetName() + "_" + super.getSuburb());
		propertyType = "Premium Suite";
		super.setMaintenanceDate(maintenanceDate);
}
	}
