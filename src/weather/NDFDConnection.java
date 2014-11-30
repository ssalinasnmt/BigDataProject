package weather;

import java.util.LinkedList;

public class NDFDConnection
{
	public NDFDConnection()
	{
		// This will make a new connection to the NDFD database.

		// Start and End times to pull for.  Note, this has to be in the future (because it's a forecast database)
		String beginTime = "2014-12-01T00:00:00";
		String endTime = "2014-12-02T00:00:00";

		// This is the list of zip codes that we want to pull.
		// Specify multiple with the + operator. For instance, 87110+87111+87112
		String zipcodeList = "87801";

		String[] parameters = { "maxt", "mint", "dew" };
		String requestURL = "http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdXMLclient.php?zipCodeList=" + zipcodeList + "&product=time-series&begin=" + beginTime + "&end=" + endTime;
		for (String s: parameters)
		{
			requestURL = requestURL.concat("&" + s + "=" + s);
		}

		// At this point, the request URL is ready.

	}

	public LinkedList <WeatherPoint> pullData()
	{
		LinkedList <WeatherPoint> newPoints = new LinkedList <WeatherPoint>();

		return newPoints;
	}
}
