package Model;

import javafx.scene.image.Image;

public class apartment extends rentalProperty {

	public apartment(int streetNumber, String streetName, String suburb, int numberOfBedrooms,String propertyType, String url, String description) {
		super(streetNumber, streetName, suburb, numberOfBedrooms,propertyType,url, description);
		super.setPropertyID("A_" + super.getStreetNumber() + "_" + super.getStreetName() + "_" + super.getSuburb());
		propertyType = "Apartment";
}
	}
