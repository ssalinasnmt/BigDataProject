package voltDb;

import java.sql.*;

import weather.WeatherPoint; 

public class VoltDbConnection {
	private static String driver = "org.voltdb.jdbc.Driver";
	private static String url = "jdbc:voltdb://localhost:21212";
	
	private Connection conn = null;
	private String tableName = null; // Table to use for SQL statements
	
	/**
	 * Creates a new connection to the VoltDB server
	 * @param tableName Name of table that this connection should execute statements on.
	 */
	public VoltDbConnection(String tableName) {
		this.tableName = tableName;
		
		/* Load driver and set up database connection */
        try {
        	Class.forName(driver);
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.err.println("Error creating JDBC connection");
		} catch (ClassNotFoundException e) {
			System.err.println("Error linking in VoltDB driver");
		}
	}
	
	/**
	 * Inserts a new entry into the database
	 * @param pt Data to insert
	 */
	public void addRecord(WeatherPoint pt) {
		String sql  = "INSERT INTO " + tableName + "(maxTemp, mintemp, dewPointTemp, heatIndexTemp, windChillTemp, rainAmount, snowAmount, probabilityOfPrecipitation, outlookPercent, tornadoPercent, hailPercent, damagingThunderstormWindPercent, extremeTornadoesPercent, extremeHailPercent, extremeThunderstormWindsPercent, severeThunderstormPercent, extremeSevereThunderstormPercent, sustainedWindSpeed, cumulative34WindSpeed, gustWindSpeed, windDirection, cloudAmount)" + " VALUES (" +
						+ pt.maxTemp + ", " +
						+ pt.mintemp + ", " +
						+ pt.dewPointTemp + ", " +
						+ pt.heatIndexTemp + ", " +
						+ pt.windChillTemp + ", " +
						+ pt.rainAmount + ", " +
						+ pt.snowAmount + ", " +
						+ pt.probabilityOfPrecipitation + ", " +
						+ pt.outlookPercent + ", " +
						+ pt.tornadoPercent + ", " +
						+ pt.hailPercent + ", " +
						+ pt.damagingThunderstormWindPercent + ", " +
						+ pt.extremeTornadoesPercent + ", " +
						+ pt.extremeHailPercent + ", " +
						+ pt.extremeThunderstormWindsPercent + ", " +
						+ pt.severeThunderstormPercent + ", " +
						+ pt.extremeSevereThunderstormPercent + ", " +
						+ pt.sustainedWindSpeed + ", " +
						+ pt.cumulative34WindSpeed + ", " +
						+ pt.gustWindSpeed + ", " +
						+ pt.windDirection + ", " +
						+ pt.cloudAmount +
						")";
		System.out.println("Attempting to run following SQL query: " + sql);
		
		try {
	        Statement query = conn.createStatement();
	        query.executeUpdate(sql);
        } catch (SQLException e) {
        	System.err.println("Couldn't run SQL statement");
        	e.printStackTrace();
        }
	}
}
