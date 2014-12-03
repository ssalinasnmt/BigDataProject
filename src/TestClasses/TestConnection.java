package TestClasses;

import java.util.LinkedList;

import voltDb.VoltDbConnection;
import weather.WeatherPoint;

/**
 * Simple test program for the VoltDB database
 * 
 * @author william
 */
public class TestConnection
{

	public static void main(String[] args)
	{
		/* Open VoltDB connection */
		VoltDbConnection conn = new VoltDbConnection("weatherpoints");
		System.out.println("Opened connection to VoltDB");

		/* Create a weather point */
		WeatherPoint wp = new WeatherPoint();
		wp.dewPoint = 50;
		wp.maxTemp = 9001;
		wp.minTemp = 36;
		System.out.println("Built weatherpoint");

		/* Insert weather point into database */
		conn.addRecord(wp);
		System.out.println("Wrote weatherpoint to VoltDB");

		/* Get contents of database */
		System.out.println("All database entries: ");
		LinkedList <WeatherPoint> rs = conn.getAllWeatherPoints();
		for (WeatherPoint pt: rs)
		{
			// TODO: Add toString method for WeatherPoint
			System.out.print("maxTemp = " + pt.maxTemp);
			System.out.print(" minTemp = " + pt.minTemp);
			System.out.print(" dewPoint = " + pt.dewPoint);
			System.out.print(" precipitationProbability12hour = " + pt.precipitationProbability12hour);
			System.out.print(" liquidPrecipitationAmount = " + pt.liquidPrecipitationAmount);
			System.out.print(" snowfallAmount = " + pt.snowfallAmount);
			System.out.print(" cloudCoverAmount = " + pt.cloudCoverAmount);
			System.out.print(" relativeHumidity = " + pt.relativeHumidity);
			System.out.print(" windSpeed = " + pt.windSpeed);
			System.out.print(" windDirection = " + pt.windDirection);
			System.out.print(" windGustSpeed = " + pt.windGustSpeed);
			System.out.print(" probabilityTornado = " + pt.probabilityTornado);
			System.out.print(" probabilityHail = " + pt.probabilityHail);
			System.out.print(" probabilityDamagingThunderstormWinds = " + pt.probabilityDamagingThunderstormWinds);
			System.out.print(" probabilityExtremeTornadoes = " + pt.probabilityExtremeTornadoes);
			System.out.print(" probabilityExtremeHail = " + pt.probabilityExtremeHail);
			System.out.print(" probabilityExtremeThunderstormWinds = " + pt.probabilityExtremeThunderstormWinds);
			System.out.print(" probabilitySevereThunderstorm = " + pt.probabilitySevereThunderstorm);
			System.out.print(" probabilityExtremeSevereThunderstorm = " + pt.probabilityExtremeSevereThunderstorm);
			System.out.print(" maxRelativeHumidity = " + pt.maxRelativeHumidity);
			System.out.print(" minRelativeHumidity = " + pt.minRelativeHumidity + "\n");
		}

		System.out.println("\nSpecific entries: ");
		rs = conn.getWeatherPointsWhere("maxTemp=9001");
		for (WeatherPoint pt: rs)
		{
			// TODO: Add toString method for WeatherPoint
			System.out.print("maxTemp = " + pt.maxTemp);
			System.out.print(" minTemp = " + pt.minTemp);
			System.out.print(" dewPoint = " + pt.dewPoint);
			System.out.print(" precipitationProbability12hour = " + pt.precipitationProbability12hour);
			System.out.print(" liquidPrecipitationAmount = " + pt.liquidPrecipitationAmount);
			System.out.print(" snowfallAmount = " + pt.snowfallAmount);
			System.out.print(" cloudCoverAmount = " + pt.cloudCoverAmount);
			System.out.print(" relativeHumidity = " + pt.relativeHumidity);
			System.out.print(" windSpeed = " + pt.windSpeed);
			System.out.print(" windDirection = " + pt.windDirection);
			System.out.print(" windGustSpeed = " + pt.windGustSpeed);
			System.out.print(" probabilityTornado = " + pt.probabilityTornado);
			System.out.print(" probabilityHail = " + pt.probabilityHail);
			System.out.print(" probabilityDamagingThunderstormWinds = " + pt.probabilityDamagingThunderstormWinds);
			System.out.print(" probabilityExtremeTornadoes = " + pt.probabilityExtremeTornadoes);
			System.out.print(" probabilityExtremeHail = " + pt.probabilityExtremeHail);
			System.out.print(" probabilityExtremeThunderstormWinds = " + pt.probabilityExtremeThunderstormWinds);
			System.out.print(" probabilitySevereThunderstorm = " + pt.probabilitySevereThunderstorm);
			System.out.print(" probabilityExtremeSevereThunderstorm = " + pt.probabilityExtremeSevereThunderstorm);
			System.out.print(" maxRelativeHumidity = " + pt.maxRelativeHumidity);
			System.out.print(" minRelativeHumidity = " + pt.minRelativeHumidity + "\n");
		}
	}

}
