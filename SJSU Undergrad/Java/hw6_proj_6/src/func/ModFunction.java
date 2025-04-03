package func;

public class ModFunction implements DoubleFunctionOfTwoInts
{

	@Override
	public double fOfXY(int x, int y) 
	{
		if(y == 0)
		{
			return x % 1;
		}
		else
		{
			return x % y;
		}
	}

	@Override
	public String getName() 
	{
		return "Mod";
	}

}
