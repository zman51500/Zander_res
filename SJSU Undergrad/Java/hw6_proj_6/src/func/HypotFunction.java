package func;

public class HypotFunction implements DoubleFunctionOfTwoInts
{

	@Override
	public double fOfXY(int x, int y) 
	{
		
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
			
	}

	@Override
	public String getName() {
		return "Hypotenuse";
	}

}
