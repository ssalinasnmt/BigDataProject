package weather;

import java.util.LinkedList;

public class Populator
{

	public static void main(String[] args)
	{
		// Set up the connection to VoltDB
		// TODO: Will

		// Set up connection to NDFD
		// TODO: Sean
		NDFDConnection ndfd = new NDFDConnection();

		// Pull data from NDFD
		// TODO: Sean
		LinkedList <WeatherPoint> newData = ndfd.pullData();

		// Add new data received from NDFD to voltDB
		// TODO: Will

		// Done
	}

}
