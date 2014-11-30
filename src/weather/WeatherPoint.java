package weather;

public class WeatherPoint
{
	// TODO: Check which of these can be pulled via forecast data.

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

}
