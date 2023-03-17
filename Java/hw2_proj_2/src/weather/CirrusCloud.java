package weather;

public class CirrusCloud extends Cloud
{
	public CirrusCloud(float b, float t)
	{
		super(b, t);
	}
	
	@Override
	public String rain()
	{
		return "I cannot make rain";
	}
}
