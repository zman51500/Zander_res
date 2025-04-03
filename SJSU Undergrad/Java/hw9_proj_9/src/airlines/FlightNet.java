package airlines;

import java.util.*;

public class FlightNet extends HashSet<Airport>
{
	public boolean nameIsAvailable(String name)
	{
		for(Airport a: this)
		{
			if(a.getName().equals(name))
			{
				return false;
			}
		}
		return true;
	}
	
	public void connect(Airport a1, Airport a2)
	{
		a1.connectTo(a2);
		a2.connectTo(a1);
	}
	
	public void disconnect(Airport a1, Airport a2)
	{
		a1.disconnectFrom(a2);
		a2.disconnectFrom(a1);
	}
	
	public void removeAndDisconnect(Airport removeMe)
	{
		List<Airport> remove = removeMe.getConnections();
		for(Airport a: remove)
		{
			disconnect(a, removeMe);
		}
		this.remove(removeMe);
	}
	
	public Airport getAirportNearXY(int x, int y, int maximumDistance)
	{
		for(Airport a: this)
		{
			int xLocation = a.getX();
			int yLocation = a.getY();
			if(xLocation <= x + maximumDistance && xLocation >= x - maximumDistance)
			{
				if(yLocation <= y + maximumDistance && yLocation >= y - maximumDistance)
				{
					return a;
				}
			}
		}
		return null;
	}
	
	public  static void main(String[] args)
	{
		Airport a1 = new Airport("LAX", 11 , 20);
		Airport a2 = new Airport("SFO", 10, 10);
		FlightNet test = new FlightNet();
		test.add(a1);
		test.add(a2);
		System.out.println(test.getAirportNearXY(10, 10, 10));
	}
}
