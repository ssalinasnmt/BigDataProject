package weather;

public class WeatherPoint
{
	// TODO: Check which of these can be pulled via forecast data.

	/* These are the old ones we found.  
	// Temperature
	public int maxTemp;
	public int mintemp;
	public int dewPointTemp;
	public int heatIndexTemp;
	public int windChillTemp;

	// Precipitation
	public int rainAmount;
	public int snowAmount;

	// Probability of Precipitation
	public int probabilityOfPrecipitation;

	// Convective Hazard
	public int outlookPercent;
	public int tornadoPercent;
	public int hailPercent;
	public int damagingThunderstormWindPercent;
	public int extremeTornadoesPercent;
	public int extremeHailPercent;
	public int extremeThunderstormWindsPercent;
	public int severeThunderstormPercent;
	public int extremeSevereThunderstormPercent;

	// Wind Speed
	public int sustainedWindSpeed;
	public int cumulative34WindSpeed;
	public int gustWindSpeed;

	// Wind Direction
	public int windDirection;

	// Cloud Amounts
	public int cloudAmount;
	*/
	
	
	// New values we can pull from the NDFD
	
	// TODO: We probably need to keep time that this forecast was valid.
	
	public int maxTemp;
	public int minTemp;
	public int dewPoint;
	public int precipitationProbability12hour;
	public float liquidPrecipitationAmount;
	public float snowfallAmount;
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
}
