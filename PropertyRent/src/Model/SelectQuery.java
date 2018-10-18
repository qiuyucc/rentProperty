package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectQuery {
	public static void main(String[] args) {
		// select the all information from the table
		final String DB_NAME = "db_property";
		final String TABLE_NAME = "RENTAL_PROPERTY";

		// use try-with-resources Statement
		try (Connection con = connectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {

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
				System.out.println(url2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
