package func;

public class ComplexNormFunction implements DoubleFunctionOfTwoInts
{

	@Override
	public double fOfXY(int x, int y) 
	{
		Complex c = new Complex(x, y);
		return c.norm();
	}

	@Override
	public String getName() 
	{
		return "Complex Norm";
	}


}
