package func;

public class ComplexSquaredNormFunction implements DoubleFunctionOfTwoInts
{

	@Override
	public double fOfXY(int x, int y) 
	{
		Complex c1 = new Complex(x, y);
		Complex c2 = new Complex(x, y);
		Complex mult = Complex.multiply(c1, c2);
		return mult.norm();
	}

	@Override
	public String getName() 
	{
		return "Complex Squared Norm";
	}

}
