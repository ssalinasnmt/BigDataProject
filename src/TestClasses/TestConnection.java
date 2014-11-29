package TestClasses;

import java.sql.ResultSet;
import java.sql.SQLException;

import voltDb.VoltDbConnection;
import weather.WeatherPoint;

/**
 * Simple test program for the VoltDB database
 * @author william
 *
 */
public class TestConnection {

	public static void main(String[] args) {
		/* Open VoltDB connection */
		VoltDbConnection conn = new VoltDbConnection("weatherpoints");
		System.out.println("Opened connection to VoltDB");
		
		/* Create a weather point */
		WeatherPoint wp = new WeatherPoint();
		wp.cloudAmount = 50;
		wp.maxTemp = 9001;
		wp.mintemp = 42;
		System.out.println("Built weatherpoint");
		
		/* Insert weather point into database */
		conn.addRecord(wp);
		System.out.println("Wrote weatherpoint to VoltDB");
		
		/* Get contents of database */
		ResultSet rs = conn.getWeatherPoints();
		try {
			while(rs.next()) {
				System.out.println("maxTemp = " + rs.getInt("maxTemp"));
			}
		} catch (SQLException e) {
			System.out.println("Error printing contents of table");
			e.printStackTrace();
		}
	}

}
