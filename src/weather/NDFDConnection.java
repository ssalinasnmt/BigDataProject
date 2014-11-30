package weather;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

		// Next, the parameters that we wish to pull from the NDFD.  The available parameters are as follows:
		// Source: http://graphical.weather.gov/xml/docs/elementInputNames.php
		// 
		// maxt - Maximum Temperature
		// mint - Minimum Temperature
		// temp - 3 Hourly Temperature
		// dew - Dewpoint Temperature
		// appt - Apparent Temperature
		// pop12 - 12 Hour Probability of Precipitation
		// qpf - Liquid Precipitation Amount
		// snow - Snowfall Amount
		// sky - Cloud Cover Amount
		// rh - Relative Humidity
		// wspd - Wind Speed
		// wdir - Wind Direction
		// wx - Weather
		// icons - Weather Icons
		// waveh - Wave Height
		// incw34 - Probabilistic Tropical Cyclone Wind Speed >34 Knots (Incremental)
		// incw50 - Probabilistic Tropical Cyclone Wind Speed >50 Knots (Incremental)
		// incw64 - Probabilistic Tropical Cyclone Wind Speed >64 Knots (Incremental)
		// cumw34 - Probabilistic Tropical Cyclone Wind Speed >34 Knots (Cumulative)
		// cumw50 - Probabilistic Tropical Cyclone Wind Speed >50 Knots (Cumulative)
		// cumw64 - Probabilistic Tropical Cyclone Wind Speed >64 Knots (Cumulative)
		// wgust - Wind Gust
		// critfireo - Fire Weather from Wind and Relative Humidity
		// dryfireo - Fire Weather from Dry Thunderstorms
		// conhazo - Convective Hazard Outlook
		// ptornado - Probability of Tornadoes
		// phail - Probability of Hail
		// ptstmwinds - Probability of Damaging Thunderstorm Winds
		// pxtornado - Probability of Extreme Tornadoes
		// pxhail - Probability of Extreme Hail
		// pxtstmwinds - Probability of Extreme Thunderstorm Winds
		// ptotsvrtstm - Probability of Severe Thunderstorms
		// pxtotsvrtstm - Probability of Extreme Severe Thunderstorms
		// tmpabv14d - Probability of 8- To 14-Day Average Temperature Above Normal
		// tmpblw14d - Probability of 8- To 14-Day Average Temperature Below Normal
		// tmpabv30d - Probability of One-Month Average Temperature Above Normal
		// tmpblw30d - Probability of One-Month Average Temperature Below Normal
		// tmpabv90d - Probability of Three-Month Average Temperature Above Normal
		// tmpblw90d - Probability of Three-Month Average Temperature Below Normal
		// prcpabv14d - Probability of 8- To 14-Day Total Precipitation Above Median
		// prcpblw14d - Probability of 8- To 14-Day Total Precipitation Below Median
		// prcpabv30d - Probability of One-Month Total Precipitation Above Median
		// prcpblw30d - Probability of One-Month Total Precipitation Below Median
		// prcpabv90d - Probability of Three-Month Total Precipitation Above Median
		// prcpblw90d - Probability of Three-Month Total Precipitation Below Median
		// precipa_r - Real-time Mesoscale Analysis Precipitation
		// sky_r - Real-time Mesoscale Analysis GOES Effective Cloud Amount
		// td_r - Real-time Mesoscale Analysis Dewpoint Temperature
		// temp_r - Real-time Mesoscale Analysis Temperature
		// wdir_r - Real-time Mesoscale Analysis Wind Direction
		// wspd_r - Real-time Mesoscale Analysis Wind Speed
		// wwa - Watches, Warnings, and Advisories
		// iceaccum - Ice Accumulation
		// maxrh - Maximum Relative Humidity
		// minrh - Minimum Relative Humidity

		String[] parameters = { "maxt", "mint", "dew", "pop12", "qpf", "snow", "sky", "rh", "wspd", "wdir", "wgust", "ptornado", "phail", "ptstmwinds", "pxtornado", "pxhail", "pxtstmwinds", "ptotsvrtstm", "pxtotsvrtstm", "maxrh", "minrh" };

		String requestURLString = "http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdXMLclient.php?zipCodeList=" + zipcodeList + "&product=time-series&begin=" + beginTime + "&end=" + endTime;
		for (String s: parameters)
		{
			requestURLString = requestURLString.concat("&" + s + "=" + s);
		}

		// At this point, the request URL is ready.
		System.out.println("Request URL: " + requestURLString);

		// Instantiate a URL with it.
		URL requestURL = null;

		try
		{

			System.out.println("Attempting connection to NDFD.");
			requestURL = new URL(requestURLString);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(requestURL.openStream());

			doc.getDocumentElement().normalize();

			// Ready to look for our data points at this stage.

			NodeList points = doc.getElementsByTagName("parameters");

			for (int pointIndex = 0;pointIndex < points.getLength();pointIndex++)
			{
				Node point = points.item(pointIndex);
				NodeList data = point.getChildNodes();
				for (int i = 0;i < data.getLength();i++)
				{
					Node attribute = data.item(i);

					System.out.println("Examining node " + attribute.getNodeName());

					if (attribute.getNodeName().compareTo("temperature") == 0)
					{
						System.out.println("Found a temperature.");
						// Temperature can be max, min, or dew point.

					}
					else if (attribute.getNodeName().compareTo("precipitation") == 0)
					{
						System.out.println("Found a precipitation.");
					}
					else if (attribute.getNodeName().compareTo("wind-speed") == 0)
					{
						System.out.println("Found a wind speed.");
					}
					else if (attribute.getNodeName().compareTo("direction") == 0)
					{
						System.out.println("Found a direction.");
					}
					else if (attribute.getNodeName().compareTo("cloud-amount") == 0)
					{
						System.out.println("Found a cloud amount.");
					}
					else if (attribute.getNodeName().compareTo("probability-of-precipitation") == 0)
					{
						System.out.println("Found a probability of precipitation.");
					}
					else if (attribute.getNodeName().compareTo("convective-hazard") == 0)
					{
						System.out.println("Found a convective hazard.");
					}
					else if (attribute.getNodeName().compareTo("humidity") == 0)
					{
						System.out.println("Found a humidity.");
					}
					else
					{
						System.out.println("Ignoring: " + attribute.getNodeName());
					}

				}
			}

		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}

	}

	public LinkedList <WeatherPoint> pullData()
	{
		LinkedList <WeatherPoint> newPoints = new LinkedList <WeatherPoint>();

		return newPoints;
	}
}
