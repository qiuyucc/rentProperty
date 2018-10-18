package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatePropertyTable {
	
	public static void main (String[] args) throws SQLException {
		
		final String DB_NAME = "db_property";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		
		
		try (Connection con = connectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE RENTAL_PROPERTY ("
										+ "propertyId varchar(25) PRIMARY KEY NOT NULL,"
										+ "streetNumber int NOT NULL,"
										+ "streetName VARCHAR(25) NOT NULL," 
										+ "suburb VARCHAR(20) NOT NULL,"
										+ "numOfBedrooms INT NOT NULL,"
										+ "propertyType VARCHAR(20) NOT NULL," 
										+ "propertyStatus VARCHAR(20) NOT NULL,"
										+ "url varchar(50),"
										+ "description VARCHAR(255) NOT NULL,"
										+ "maintenanceDate VARCHAR(20))");
								
			if(result == 0) {
				System.out.println("Table " + TABLE_NAME + " has been created successfully");
			} else {
				System.out.println("Table " + TABLE_NAME + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void createTable() throws SQLException 
	{
		final String DB_NAME = "db_property";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		
		
		try (Connection con = connectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE RENTAL_PROPERTY ("
										+ "propertyId varchar(25) PRIMARY KEY NOT NULL,"
										+ "streetNumber int NOT NULL,"
										+ "streetName VARCHAR(25) NOT NULL," 
										+ "suburb VARCHAR(20) NOT NULL,"
										+ "numOfBedrooms INT NOT NULL,"
										+ "propertyType VARCHAR(20) NOT NULL," 
										+ "propertyStatus VARCHAR(20) NOT NULL,"
										+ "url varchar(50),"
										+ "description VARCHAR(255) NOT NULL,"
										+ "maintenanceDate VARCHAR(20))");
								
			if(result == 0) {
				System.out.println("Table " + TABLE_NAME + " has been created successfully");
			} else {
				System.out.println("Table " + TABLE_NAME + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
 
}
