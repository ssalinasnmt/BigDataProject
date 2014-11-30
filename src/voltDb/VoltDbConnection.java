package voltDb;

import java.sql.*;
import java.util.LinkedList;

import weather.WeatherPoint;

public class VoltDbConnection
{
	private static String driver = "org.voltdb.jdbc.Driver";
	private static String url = "jdbc:voltdb://localhost:21212";

	private Connection conn = null;
	private String tableName = null; // Table to use for SQL statements

	/**
	 * Creates a new connection to the VoltDB server
	 * 
	 * @param tableName Name of table that this connection should execute statements on.
	 */
	public VoltDbConnection(String tableName)
	{
		this.tableName = tableName;

		/* Load driver and set up database connection */
		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
		}
		catch (SQLException e)
		{
			System.err.println("Error creating JDBC connection");
		}
		catch (ClassNotFoundException e)
		{
			System.err.println("Error linking in VoltDB driver");
		}
	}

	/**
	 * Inserts a new entry into the database
	 * 
	 * @param pt Data to insert
	 */
	public void addRecord(WeatherPoint pt)
	{
		String sql = "INSERT INTO " + tableName + "(maxTemp, mintemp, dewPointTemp, heatIndexTemp, windChillTemp, rainAmount, snowAmount, probabilityOfPrecipitation, outlookPercent, tornadoPercent, hailPercent, damagingThunderstormWindPercent, extremeTornadoesPercent, extremeHailPercent, extremeThunderstormWindsPercent, severeThunderstormPercent, extremeSevereThunderstormPercent, sustainedWindSpeed, cumulative34WindSpeed, gustWindSpeed, windDirection, cloudAmount)" + " VALUES (" + +pt.maxTemp + ", " + +pt.mintemp + ", " + +pt.dewPointTemp + ", " + +pt.heatIndexTemp + ", " + +pt.windChillTemp + ", " + +pt.rainAmount + ", " + +pt.snowAmount + ", " + +pt.probabilityOfPrecipitation + ", " + +pt.outlookPercent + ", " + +pt.tornadoPercent + ", " + +pt.hailPercent + ", " + +pt.damagingThunderstormWindPercent + ", " + +pt.extremeTornadoesPercent + ", " + +pt.extremeHailPercent + ", " + +pt.extremeThunderstormWindsPercent + ", " + +pt.severeThunderstormPercent + ", " + +pt.extremeSevereThunderstormPercent + ", " + +pt.sustainedWindSpeed + ", " + +pt.cumulative34WindSpeed + ", " + +pt.gustWindSpeed + ", " + +pt.windDirection + ", " + +pt.cloudAmount + ")";
		System.out.println("Attempting to run following SQL query: " + sql);

		try
		{
			Statement query = conn.createStatement();
			query.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			System.err.println("Couldn't run SQL statement");
			e.printStackTrace();
		}
	}

	/**
	 * Gets all weatherpoints from the database.
	 * 
	 * @return
	 */
	public LinkedList <WeatherPoint> getAllWeatherPoints()
	{
		ResultSet res = null;
		LinkedList <WeatherPoint> wps = new LinkedList();
		String sql = "SELECT * FROM weatherpoints";

		/* Get result set */
		try
		{
			Statement query = conn.createStatement();
			res = query.executeQuery(sql);
		}
		catch (SQLException e)
		{
			System.err.println("Error running sql statement");
			e.printStackTrace();
		}

		/* Convert the ResultSet into an array of WeatherPoints */
		try
		{
			while (res.next())
			{
				wps.add(resultToWeatherPoint(res));
			}
		}
		catch (SQLException e)
		{
			System.err.println("Error reading ResultSet");
			e.printStackTrace();
		}

		return wps;
	}

	/**
	 * Selects a specfic set of weather points
	 * 
	 * @param where String containing a valid SQL WHERE clause.
	 * @return A list of all weather points matching the WHERE clause
	 */
	public LinkedList <WeatherPoint> getWeatherPointsWhere(String where)
	{
		ResultSet res = null;
		LinkedList <WeatherPoint> wps = new LinkedList();
		String sql = "SELECT * FROM weatherpoints WHERE " + where;

		/* Get result set */
		try
		{
			Statement query = conn.createStatement();
			res = query.executeQuery(sql);
		}
		catch (SQLException e)
		{
			System.err.println("Error running sql statement");
			e.printStackTrace();
		}

		/* Convert the ResultSet into an array of WeatherPoints */
		try
		{
			while (res.next())
			{
				wps.add(resultToWeatherPoint(res));
			}
		}
		catch (SQLException e)
		{
			System.err.println("Error reading ResultSet");
			e.printStackTrace();
		}

		return wps;
	}

	/**
	 * Converts the database entry pointed to by the current position of the result set into a WeatherPoint object
	 * 
	 * @param res
	 * @return
	 */
	private WeatherPoint resultToWeatherPoint(ResultSet res)
	{
		WeatherPoint wp = new WeatherPoint();

		try
		{
			wp.maxTemp = res.getInt("maxTemp");
			wp.mintemp = res.getInt("mintemp");
			wp.dewPointTemp = res.getInt("dewPointTemp");
			wp.heatIndexTemp = res.getInt("heatIndexTemp");
			wp.windChillTemp = res.getInt("windChillTemp");
			wp.rainAmount = res.getInt("rainAmount");
			wp.snowAmount = res.getInt("snowAmount");
			wp.probabilityOfPrecipitation = res.getInt("probabilityOfPrecipitation");
			wp.outlookPercent = res.getInt("outlookPercent");
			wp.tornadoPercent = res.getInt("tornadoPercent");
			wp.hailPercent = res.getInt("hailPercent");
			wp.damagingThunderstormWindPercent = res.getInt("damagingThunderstormWindPercent");
			wp.extremeTornadoesPercent = res.getInt("extremeTornadoesPercent");
			wp.extremeHailPercent = res.getInt("extremeHailPercent");
			wp.extremeThunderstormWindsPercent = res.getInt("extremeThunderstormWindsPercent");
			wp.severeThunderstormPercent = res.getInt("severeThunderstormPercent");
			wp.extremeSevereThunderstormPercent = res.getInt("extremeSevereThunderstormPercent");
			wp.sustainedWindSpeed = res.getInt("sustainedWindSpeed");
			wp.cumulative34WindSpeed = res.getInt("cumulative34WindSpeed");
			wp.gustWindSpeed = res.getInt("gustWindSpeed");
			wp.windDirection = res.getInt("windDirection");
			wp.cloudAmount = res.getInt("cloudAmount");
		}
		catch (SQLException e)
		{
			System.err.println("Error getting column");
			e.printStackTrace();
		}
		return wp;
	}
}
