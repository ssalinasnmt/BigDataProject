package weather;

import org.voltdb.VoltType;

public class WeatherPoint
{
	// TODO: We probably need to keep time that this forecast was valid.

	public int maxTemp;
	public int minTemp;
	public int dewPoint;
	public int precipitationProbability12hour;
	public int liquidPrecipitationAmount;
	public int snowfallAmount;
	public int cloudCoverAmount;
	public int relativeHumidity;
	public int windSpeed;
	public int windDirection;
	public int windGustSpeed;
	public int probabilityTornado;
	public int probabilityHail;
	public int probabilityDamagingThunderstormWinds;
	public int probabilityExtremeTornadoes;
	public int probabilityExtremeHail;
	public int probabilityExtremeThunderstormWinds;
	public int probabilitySevereThunderstorm;
	public int probabilityExtremeSevereThunderstorm;
	public int maxRelativeHumidity;
	public int minRelativeHumidity;

	public void printWeatherPoint()
	{
		// This prints out all data stored in a weather point.

		System.out.println("Printing weather point.");
		System.out.println("\tMax Temperature: " + maxTemp);
		System.out.println("\tMin Temperature: " + minTemp);
		System.out.println("\tDew Point Temperature: " + dewPoint);
		System.out.println("\tPrecipitation Probability for 12 hour: " + precipitationProbability12hour);
		System.out.println("\tLiquid Precipitation Amount (in hundreths of an inch): " + liquidPrecipitationAmount);
		System.out.println("\tSnowfall Amount (in hundreths of an inch): " + snowfallAmount);
		System.out.println("\tCloud Cover Amount: " + cloudCoverAmount);
		System.out.println("\tRelative Humidity: " + relativeHumidity);
		System.out.println("\tWind Speed: " + windSpeed);
		System.out.println("\tWind Direction: " + windDirection);
		System.out.println("\tWind Gust Speed: " + windGustSpeed);
		System.out.println("\tProbability of Tornadoes: " + probabilityTornado);
		System.out.println("\tProbability of Hail: " + probabilityHail);
		System.out.println("\tProbability of Damaging Thunderstorm Winds" + probabilityDamagingThunderstormWinds);
		System.out.println("\tProbability of Extreme Tornadoes: " + probabilityExtremeTornadoes);
		System.out.println("\tProbability of Extreme Hail" + probabilityExtremeHail);
		System.out.println("\tProbability of Extreme Thunderstorm Winds: " + probabilityExtremeThunderstormWinds);
		System.out.println("\tProbability of Severe Thunderstorm Winds: " + probabilitySevereThunderstorm);
		System.out.println("\tProbability of Extreme Severe Thunderstorm Winds" + probabilityExtremeThunderstormWinds);
		System.out.println("\tProbability of Extreme Hyper Severe Intense Crazy Over 9000 Damaging Severe Extreme Ultra Thunderstorm Winds: 0 (we hope)");
		System.out.println("\tMaximum Relative Humidity: " + maxRelativeHumidity);
		System.out.println("\tMinimum Relative Humidity: " + minRelativeHumidity);
		System.out.println();
	}
}
