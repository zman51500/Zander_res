package weather;

public class Cloud 
{
	private float top;
	private float bottom;
	
	public Cloud(float b, float t)
	{
		bottom = b;
		top = t;
	}
	
	public float getHeight()
	{
		return top - bottom;
	}

	public String rain()
	{
		return "It is raining";
	}
}
	