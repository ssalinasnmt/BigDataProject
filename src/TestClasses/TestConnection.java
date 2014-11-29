package TestClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

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
		//conn.addRecord(wp);
		//System.out.println("Wrote weatherpoint to VoltDB");
		
		/* Get contents of database */
		LinkedList<WeatherPoint> rs = conn.getAllWeatherPoints();
		for(WeatherPoint pt : rs) {
			System.out.print("maxTemp = " + pt.maxTemp);
			System.out.print(" mintemp = " + pt.mintemp);
			System.out.print(" dewPointTemp = " + pt.dewPointTemp);
			System.out.print(" heatIndexTemp = " + pt.heatIndexTemp);
			System.out.print(" windChillTemp = " + pt.windChillTemp);
			System.out.print(" rainAmount = " + pt.rainAmount);
			System.out.print(" snowAmount = " + pt.snowAmount);
			System.out.print(" probabilityOfPrecipitation = " + pt.probabilityOfPrecipitation);
			System.out.print(" outlookPercent = " + pt.outlookPercent);
			System.out.print(" tornadoPercent = " + pt.tornadoPercent);
			System.out.print(" hailPercent = " + pt.hailPercent);
			System.out.print(" damagingThunderstormWindPercent = " + pt.damagingThunderstormWindPercent);
			System.out.print(" extremeTornadoesPercent = " + pt.extremeTornadoesPercent);
			System.out.print(" extremeHailPercent = " + pt.extremeHailPercent);
			System.out.print(" extremeThunderstormWindsPercent = " + pt.extremeThunderstormWindsPercent);
			System.out.print(" severeThunderstormPercent = " + pt.severeThunderstormPercent);
			System.out.print(" extremeSevereThunderstormPercent = " + pt.extremeSevereThunderstormPercent);
			System.out.print(" sustainedWindSpeed = " + pt.sustainedWindSpeed);
			System.out.print(" cumulative34WindSpeed = " + pt.cumulative34WindSpeed);
			System.out.print(" gustWindSpeed = " + pt.gustWindSpeed);
			System.out.print(" windDirection = " + pt.windDirection);
			System.out.print(" cloudAmount = " + pt.cloudAmount + "\n"); 
		}
	}

}
