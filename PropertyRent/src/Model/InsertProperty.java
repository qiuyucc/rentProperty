package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;


import javafx.scene.image.Image;

public class InsertProperty {
	
	public static void main(String[] args)
    {
   	 final String DB_Name = "db_property";
   	 final String Table_name = "RENTAL_PROPERTY";
   	 
   	//use try-with-resources Statement
		try (Connection con = connectionTest.getConnection(DB_Name);
				Statement stmt = con.createStatement();
		) {
			String query = "INSERT INTO " + Table_name
					+ " VALUES ('A_61_Bourke St_Southbank', 61, 'Bourke St', 'Southbank', 1, 'Apartment','Available','images/1.jpg','Offering a gracious concierge, a fully equipped gymnasium, lap pool, tennis court, fabulous outdoor BBQ garden entertaining area for the residents enjoyment.',null)"
					+ " ,('A_27_Bourke St_Southbank', 27, 'Bourke St', 'Southbank', 2, 'Apartment', 'Available','images/2.jpg','This great sized functional studio has everything you need for comfortable city living!',null)"
					+ " ,('A_33_Bourke St_Southbank', 33, 'Bourke St', 'Southbank', 2, 'Apartment','Available','images/3.jpg','Tastefully furnished throughout and conveniently located close to parks, public transport, cafes and plenty more!',null)"
					+ " ,('A_35_Bourke St_Southbank', 35, 'Bourke St', 'Southbank', 3, 'Apartment', 'Available','images/4.jpg','Featuring good sized lounge room/bedroom, large kitchen/dining with quality appliances and functional bathroom with laundry facilities.',null)"
					+ " ,('A_41_City Rd_Dockland', 41, 'City Rd', 'Dockland', 2, 'Apartment', 'Available','images/5.jpg','The perfect mix of comfort, class and convenience! Securely nestled on the first floor of this charming Manhattan style complex, you will surely feel at home',null)"
					+ " ,('A_25_City Rd_Dockland', 25, 'City Rd', 'Dockland', 3, 'Apartment', 'Available','images/6.jpg','These spectacular apartments have been architecturally designed, boast indulgent views and bespoke interior design, in a light filled and liberating space . ',null)"
					+ " ,('A_79_City Rd_Dockland', 79, 'City Rd', 'Dockland', 3, 'Apartment', 'Available',null,'Within walking distance to Southern Cross station, crown casino, Southbank, restaurants, shops and cafes this fabulous location will impress! Ideal for students. ',null)"
					+ " ,('A_68_City Rd_Dockland', 68, 'City Rd', 'Dockland', 3, 'Apartment', 'Available','images/8.jpg','These spectacular apartments have been architecturally designed, boast indulgent views and bespoke interior design, in a light filled and liberating space.',null)"
					+ " ,('A_19_City Rd_Dockland', 19, 'City Rd', 'Dockland', 2, 'Apartment', 'Available','images/9.jpg','Newly refurbished with brand new carpets, paint and blinds, this incredibly spacious one bedroom apartment needs to be inspected to be truly appreciated',null)"
					+ " ,('A_19_Coventry St_Boxhill', 19, 'Coventry St', 'Boxhill', 2, 'Apartment', 'Available','images/10.jpg','This remarkable one bedroom apartment offers quality fittings and intelligent design. Situated in Australis complex. ',null)"
					+ " ,('S_5_Coventry St_Boxhill', 5, 'Coventry St', 'Boxhill', 3, 'Premium Suite', 'Available','images/11.jpg','Offering a gracious concierge, a fully equipped gymnasium, lap pool, tennis court, fabulous outdoor BBQ garden entertaining area for the residents enjoyment','05/10/2018')"
					+ " ,('S_42_Coventry St_Boxhill', 42, 'Coventry St', 'Boxhill', 3, 'Premium Suite', 'Available','images/12.jpg','Tastefully furnished throughout and conveniently located close to parks, public transport, cafes and plenty more!','05/10/2018')"
					+ " ,('S_29_Kilda St_Fitzory', 29, 'Kilda St', 'Fitzory', 3, 'Premium Suite', 'Available','images/13.jpg','These spectacular apartments have been architecturally designed, boast indulgent views and bespoke interior design, in a light filled and liberating space ','02/10/2018')"
					+ " ,('S_41_Kilda St_Fitzory', 41, 'Kilda St', 'Fitzory', 3, 'Premium Suite', 'Available','images/2.jpg','Offering a gracious concierge, a fully equipped gymnasium, lap pool, tennis court, fabulous outdoor BBQ garden entertaining area for the residents enjoyment,.','02/10/2018')"
					+ " ,('S_75_Kilda St_Fitzory', 75, 'Kilda St', 'Fitzory', 3, 'Premium Suite', 'Available','images/14.jpg','Accommodation features open plan living/dining area with floor to ceiling windows.','03/09/2018')";

			int result = stmt.executeUpdate(query);
			
			con.commit();
			
			System.out.println("Insert into table " + Table_name + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
	
	
	// insert table Rental_property
	/*public static void main(String[] args)
     {
    	 final String DB_Name = "db_property";
    	 final String Table_name = "RENTAL_PROPERTY";
    	 
    	//use try-with-resources Statement
 		try (Connection con = connectionTest.getConnection(DB_Name);
 				Statement stmt = con.createStatement();
 		) {
 			String query = "INSERT INTO " + Table_name
					+ " VALUES ('A_61_Bourke St_Southbank', 61, 'Bourke St', 'Southbank', 1, 'Apartment','Available','images/1.jpg','Offering a gracious concierge, a fully equipped gymnasium, lap pool, tennis court, fabulous outdoor BBQ garden entertaining area for the residents enjoyment, welcome a luxurious lifestyle one may enjoy.',null)"
					+ " ,('A_27_Bourke St_Southbank', 27, 'Bourke St', 'Southbank', 2, 'Apartment', 'Available','images/2.jpg','This great sized functional studio has everything you need for comfortable city living!',null)"
					+ " ,('A_33_Bourke St_Southbank', 33, 'Bourke St', 'Southbank', 2, 'Apartment','Available','images/3.jpg','Tastefully furnished throughout and conveniently located close to parks, public transport, cafes and plenty more!',null)"
					+ " ,('A_35_Bourke St_Southbank', 33, 'Bourke St', 'Southbank', 3, 'Apartment', 'Available','images/4.jpg','Featuring good sized lounge room/bedroom, large kitchen/dining with quality appliances and functional bathroom with laundry facilities.',null)"
					+ " ,('A_41_City Rd_Dockland', 41, 'City Rd', 'Dockland', 2, 'Apartment', 'Available','images/5.jpg','The perfect mix of comfort, class and convenience! Securely nestled on the first floor of this charming Manhattan style complex, you will surely feel at home',null)"
					+ " ,('A_25_City Rd_Dockland', 25, 'City Rd', 'Dockland', 3, 'Apartment', 'Available','images/6.jpg','These spectacular apartments have been architecturally designed, boast indulgent views and bespoke interior design, in a light filled and liberating space that is sure to take your breath away. ',null)"
					+ " ,('A_79_City Rd_Dockland', 79, 'City Rd', 'Dockland', 1, 'Apartment', 'Available','images/7.jpg','Within walking distance to Southern Cross station, crown casino, Southbank, restaurants, shops and cafes this fabulous location will impress! Ideal for students and executives alike as a city base or home away from home.',null)"
					+ " ,('A_68_City Rd_Dockland', 68, 'City Rd', 'Dockland', 3, 'Apartment', 'Available','images/8.jpg','These spectacular apartments have been architecturally designed, boast indulgent views and bespoke interior design, in a light filled and liberating space that is sure to take your breath away.',null)"
					+ " ,('A_19_City Rd_Dockland', 19, 'City Rd', 'Dockland', 2, 'Apartment', 'Available','images/9.jpg','Newly refurbished with brand new carpets, paint and blinds, this incredibly spacious one bedroom apartment needs to be inspected to be truly appreciated',null)"
					+ " ,('A_19_Coventry St_Boxhill', 19, 'Coventry St', 'Boxhill', 2, 'Apartment', 'Available','images/10.jpg','This remarkable one bedroom apartment offers quality fittings and intelligent design. Situated in Australis complex, this apartment is brilliantly located to enjoy Melbourne¡¯s famous inner city lifestyle. ',null)"
					+ " ,('S_5_Coventry St_Boxhill', 5, 'Coventry St', 'Boxhill', 3, 'Premium Suite', 'Available','images/11.jpg','Offering a gracious concierge, a fully equipped gymnasium, lap pool, tennis court, fabulous outdoor BBQ garden entertaining area for the residents enjoyment, welcome a luxurious lifestyle one may enjoy.','05/10/2018')"
					+ " ,('S_42_Coventry St_Boxhill', 42, 'Coventry St', 'Boxhill', 3, 'Premium Suite', 'Available','images/12.jpeg','Tastefully furnished throughout and conveniently located close to parks, public transport, cafes and plenty more!','05/10/2018')"
					+ " ,('S_29_Kilda St_Fitzory', 29, 'Kilda St', 'Fitzory', 3, 'Premium Suite', 'Available','images/13.jpg','These spectacular apartments have been architecturally designed, boast indulgent views and bespoke interior design, in a light filled and liberating space that is sure to take your breath away.','02/10/2018')"
					+ " ,('S_41_Kilda St_Fitzory', 41, 'Kilda St', 'Fitzory', 3, 'Premium Suite', 'Available','images/14.jpg','Offering a gracious concierge, a fully equipped gymnasium, lap pool, tennis court, fabulous outdoor BBQ garden entertaining area for the residents enjoyment, welcome a luxurious lifestyle one may enjoy.','02/10/2018')"
					+ " ,('S_75_Kilda St_Fitzory', 75, 'Kilda St', 'Fitzory', 3, 'Premium Suite', 'Available','images/15.jpg','Accommodation features open plan living/dining area with floor to ceiling windows.','03/09/2018')";

 			int result = stmt.executeUpdate(query);
 			
 			con.commit();
 			
 			System.out.println("Insert into table " + Table_name + " executed successfully");
 			System.out.println(result + " row(s) affected");

 		} catch (Exception e) {
 			System.out.println(e.getMessage());
 		}
     }
	
	*/
	
	
}




	