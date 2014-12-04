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
	public LinkedList <WeatherPoint> pullData()
	{
		LinkedList <WeatherPoint> newPoints = new LinkedList <WeatherPoint>();

		// This will make a new connection to the NDFD database.

		// Start and End times to pull for.  Note, this has to be in the future (because it's a forecast database)
		String beginTime = "2014-12-04T00:00:00";
		String endTime = "2014-12-05T00:00:00";

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
				System.out.println("Parsing " + points.getLength() + " data points.");

				WeatherPoint wp = new WeatherPoint();

				Node point = points.item(pointIndex);
				NodeList data = point.getChildNodes();
				for (int childNodeIndex = 0;childNodeIndex < data.getLength();childNodeIndex++)
				{
					Node attribute = data.item(childNodeIndex);

					// System.out.println("Examining node " + attribute.getNodeName());

					if (attribute.getNodeName().compareTo("temperature") == 0)
					{
						System.out.println("Found a temperature.");
						// Temperature can be max, min, or dew point.
						for (int nodeIndex = 0;nodeIndex < attribute.getAttributes().getLength();nodeIndex++)
						{
							Node n = attribute.getAttributes().item(nodeIndex);
							if (n.getNodeName().compareTo("type") == 0)
							{
								System.out.println("Found type attribute. >" + n.getNodeValue() + "<");
								if (n.getNodeValue().compareTo("maximum") == 0)
								{
									System.out.println("Found maximum.");
									wp.maxTemp = this.getValue(attribute);
									System.out.println("Maximum: " + wp.maxTemp);
								}
								else if (n.getNodeValue().compareTo("minimum") == 0)
								{
									System.out.println("Found minimum");
									wp.minTemp = this.getValue(attribute);
								}
								else if (n.getNodeValue().compareTo("dew point") == 0)
								{
									System.out.println("Found dew point.");
									wp.dewPoint = this.getValue(attribute);
								}
								else
								{
									System.out.println("Found something else? Given " + n.getNodeValue());
								}
								break;
							}
						}
					}
					else if (attribute.getNodeName().compareTo("precipitation") == 0)
					{
						System.out.println("Found a precipitation.");
						// Precipitation can be liquid or snow
						for (int nodeIndex = 0;nodeIndex < attribute.getAttributes().getLength();nodeIndex++)
						{
							Node n = attribute.getAttributes().item(nodeIndex);
							if (n.getNodeName().compareTo("type") == 0)
							{
								if (n.getNodeValue().compareTo("liquid") == 0)
								{
									System.out.println("Found liquid.");
									wp.liquidPrecipitationAmount = this.getValue(attribute);
								}
								else if (n.getNodeValue().compareTo("snow") == 0)
								{
									System.out.println("Found snow");
									wp.snowfallAmount = this.getValue(attribute);
								}
								else
								{
									System.out.println("Found something else? Given " + n.getNodeValue());
								}
								break;
							}
						}
					}
					else if (attribute.getNodeName().compareTo("wind-speed") == 0)
					{
						System.out.println("Found a wind speed.");

						// Wind Speed can be sustained or gust
						for (int nodeIndex = 0;nodeIndex < attribute.getAttributes().getLength();nodeIndex++)
						{
							Node n = attribute.getAttributes().item(nodeIndex);
							if (n.getNodeName().compareTo("type") == 0)
							{
								if (n.getNodeValue().compareTo("sustained") == 0)
								{
									System.out.println("Found sustained.");
									wp.windSpeed = this.getValue(attribute);
								}
								else if (n.getNodeValue().compareTo("gust") == 0)
								{
									System.out.println("Found gust");
									wp.windGustSpeed = this.getValue(attribute);
								}
								else
								{
									System.out.println("Found something else? Given " + n.getNodeValue());
								}
								break;
							}
						}
					}
					else if (attribute.getNodeName().compareTo("direction") == 0)
					{
						System.out.println("Found a direction.");
						// Direction is only wind direction.
						for (int nodeIndex = 0;nodeIndex < attribute.getAttributes().getLength();nodeIndex++)
						{
							Node n = attribute.getAttributes().item(nodeIndex);
							if (n.getNodeName().compareTo("type") == 0)
							{
								if (n.getNodeValue().compareTo("wind") == 0)
								{
									System.out.println("Found wind direction.");
									wp.windDirection = this.getValue(attribute);
								}
								else
								{
									System.out.println("Found something else? Given " + n.getNodeValue());
								}
								break;
							}
						}
					}
					else if (attribute.getNodeName().compareTo("cloud-amount") == 0)
					{
						System.out.println("Found a cloud amount.");
						// Cloud amount can be only total cloud cover
						for (int nodeIndex = 0;nodeIndex < attribute.getAttributes().getLength();nodeIndex++)
						{
							Node n = attribute.getAttributes().item(nodeIndex);
							if (n.getNodeName().compareTo("type") == 0)
							{
								if (n.getNodeValue().compareTo("total") == 0)
								{
									System.out.println("Found total cloud amount.");
									wp.cloudCoverAmount = this.getValue(attribute); 
								}
								else
								{
									System.out.println("Found something else? Given " + n.getNodeValue());
								}
								break;
							}
						}
					}
					else if (attribute.getNodeName().compareTo("probability-of-precipitation") == 0)
					{
						System.out.println("Found a probability of precipitation.");
						// Probability of Precipitation is only 12-hour
						for (int nodeIndex = 0;nodeIndex < attribute.getAttributes().getLength();nodeIndex++)
						{
							Node n = attribute.getAttributes().item(nodeIndex);
							if (n.getNodeName().compareTo("type") == 0)
							{
								if (n.getNodeValue().compareTo("12 hour") == 0)
								{
									System.out.println("Found 12 hour probability of precipiation.");
									wp.precipitationProbability12hour = this.getValue(attribute);
								}
								else
								{
									System.out.println("Found something else? Given " + n.getNodeValue());
								}
								break;
							}
						}
					}
					else if (attribute.getNodeName().compareTo("convective-hazard") == 0)
					{
						System.out.println("Found a convective hazard.");
						// Convective Hazards can be many things.
						for (int nodeIndex = 0;nodeIndex < attribute.getAttributes().getLength();nodeIndex++)
						{
							Node n = attribute.getAttributes().item(nodeIndex);
							if (n.getNodeName().compareTo("type") == 0)
							{
								if (n.getNodeValue().compareTo("tornadoes") == 0)
								{
									System.out.println("Found tornadoes.");
									wp.probabilityTornado = this.getValue(attribute);
								}
								else if (n.getNodeValue().compareTo("hail") == 0)
								{
									System.out.println("Found hail");
									wp.probabilityHail = this.getValue(attribute);
								}
								else if (n.getNodeValue().compareTo("damaging thunderstorm winds") == 0)
								{
									System.out.println("Found damaging thunderstorm winds");
									wp.probabilityDamagingThunderstormWinds = this.getValue(attribute);
								}
								else if (n.getNodeValue().compareTo("extreme tornadoes") == 0)
								{
									System.out.println("Found extreme tornadoes");
									wp.probabilityExtremeTornadoes = this.getValue(attribute);
								}
								else if (n.getNodeValue().compareTo("extreme hail") == 0)
								{
									System.out.println("Found extreme hail");
									wp.probabilityExtremeHail = this.getValue(attribute);
								}
								else if (n.getNodeValue().compareTo("extreme thunderstorm winds") == 0)
								{
									System.out.println("Found extreme thunderstorm winds");
									wp.probabilityExtremeThunderstormWinds = this.getValue(attribute);
								}
								else if (n.getNodeValue().compareTo("severe thunderstorms") == 0)
								{
									System.out.println("Found severe thunderstorms");
									wp.probabilitySevereThunderstorm = this.getValue(attribute);
								}
								else if (n.getNodeValue().compareTo("extreme severe thunderstorms") == 0)
								{
									System.out.println("Found extreme severe thunderstorms");
									wp.probabilityExtremeSevereThunderstorm = this.getValue(attribute);
								}
								else
								{
									System.out.println("Found something else? Given " + n.getNodeValue());
								}
								break;
							}
						}
					}
					else if (attribute.getNodeName().compareTo("humidity") == 0)
					{
						System.out.println("Found a humidity.");
						// Humidity can be relative, max, or min.
						for (int nodeIndex = 0;nodeIndex < attribute.getAttributes().getLength();nodeIndex++)
						{
							Node n = attribute.getAttributes().item(nodeIndex);
							if (n.getNodeName().compareTo("type") == 0)
							{
								if (n.getNodeValue().compareTo("relative") == 0)
								{
									System.out.println("Found relative.");
									wp.relativeHumidity = this.getValue(attribute);
								}
								else if (n.getNodeValue().compareTo("minimum relative") == 0)
								{
									System.out.println("Found minimum");
									wp.minRelativeHumidity = this.getValue(attribute);
								}
								else if (n.getNodeValue().compareTo("maximum relative") == 0)
								{
									System.out.println("Found maximum.");
									wp.maxRelativeHumidity = this.getValue(attribute);
								}
								else
								{
									System.out.println("Found something else? Given " + n.getNodeValue());
								}
								break;
							}
						}
					}
					else
					{
						System.out.println("Ignoring: " + attribute.getNodeName());
					}

				}

				newPoints.add(wp);
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

		return newPoints;
	}

	private int getValue(Node n)
	{
		int ret = -1;
		for (int i = 0;i < n.getChildNodes().getLength();i++)
		{
			Node a = n.getChildNodes().item(i);
			// System.out.println("Looking at child " + i + ", node name " + a.getNodeName() + ", node value " + a.getNodeValue()+", text content "+a.getTextContent());
			if (a.getNodeName().compareTo("value") == 0)
			{
				ret = Integer.parseInt(a.getTextContent());
				break;
			}

		}
		// System.out.println("getValue: Returning "+ret);
		return ret;
	}
}
