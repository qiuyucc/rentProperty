package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateRecordTable {
	
	public static void main (String[] args) throws SQLException {
		
		final String DB_NAME = "db_property";
		final String TABLE_NAME = "RENTAL_RECORD";
		
		
		try (Connection con = connectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE RENTAL_RECORD ("
										+ "propertyId VARCHAR(25) NOT NULL,"
										+ "RentId VARCHAR(255) NOT NULL,"
										+ "RentDate VARCHAR(25) NOT NULL," 
										+ "EstReturnDate VARCHAR(25) NOT NULL,"
										+ "ActReturnDate VARCHAR(25) NOT NULL,"
										+ "RentalFee Float NOT NULL," 
										+ "LateFee Float NOT NULL)");
																		
			if(result == 0) {
				System.out.println("Table " + TABLE_NAME + " has been created successfully");
			} else {
				System.out.println("Table " + TABLE_NAME + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void createTable() throws Exception
	{
		final String DB_NAME = "db_property";
		final String TABLE_NAME = "RENTAL_RECORD";
		
		
		try (Connection con = connectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE RENTAL_RECORD ("
										+ "propertyId VARCHAR(25) NOT NULL,"
										+ "RentId VARCHAR(255) NOT NULL,"
										+ "RentDate VARCHAR(25) NOT NULL," 
										+ "EstReturnDate VARCHAR(25) NOT NULL,"
										+ "ActReturnDate VARCHAR(25) NOT NULL,"
										+ "RentalFee Float NOT NULL," 
										+ "LateFee Float NOT NULL)");
																		
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
