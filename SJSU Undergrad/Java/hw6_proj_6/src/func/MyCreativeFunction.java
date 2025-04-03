package func;

public class MyCreativeFunction implements DoubleFunctionOfTwoInts
{

//	@Override
//	public double fOfXY(int x, int y) 
//	{
//		Complex c1 = new Complex(x, y);
//		Complex c2 = new Complex(x/200f, y/200f);
//		Complex mult = Complex.multiply(c1, c2);
//		
//		for(int n = 1; n <=1000; n++)
//		{
//			Complex comp = new Complex(mult.getReal() / n , mult.getImaginary()/n);
//			if(n%5 == 0)
//			{
//				return comp.norm();
//			}
//			else if(n%4 == 0)
//			{
//				return comp.getReal();
//			}
//			else if(n%3 == 0)
//			{
//				return comp.getImaginary();
//			}
//			else if(n%2 == 0)
//			{
//				return comp.getReal() / comp.getImaginary();
//			}
//			else
//			{
//				return comp.getReal() * comp.getImaginary(); 
//			}
//		}
//		return 155;
//	}
	
//	public double fOfXY(int x, int y) 
//	{
//		Complex base = new Complex(x/1, y/1);
//		Complex c = new Complex(x/2, y/2);
//		if (x < 10 && x > -10)
//		{
//			return Math.asin(Math.tan(base.norm())) + 155;
//		}
//		else if(y < 10 && y > -10)
//		{
//			return Math.asin(Math.tan(c.norm())) + 155;
//		}
//		else if(x >= 10 && y >= 10)
//		{
//			 base = new Complex(x + 100 , y + 100);
//			 c = new Complex(x/2, y/2);
//			return base.norm() % c.norm();
//		}
//		else if(x <= -10 && y <= -10)
//		{
//			 base = new Complex(x + 100, y + 100);
//			 c = new Complex(x/2, y/2);
//			return base.norm() % c.norm();
//		}
//		else
//		{
//		for(int n = 1; n <= 100; n++)
//		{
//			if(c.norm() % base.norm() > 10)
//			{
//				c = Complex.multiply(c , c);
//				//c = Complex.add(c, c);
//				c = Complex.multiply(c , c);
//				if(c.norm() < 255)
//				{
//					return c.norm();
//				}
//				else
//				{
//					return (c.norm() * c.norm()) / n;
//				}
//			}
//			else
//			{
//				c = new Complex(c.norm(), c.norm());
//			}
//			c = Complex.multiply(c, base);
//			base = Complex.multiply(base, c);
//		}	
//		}
//		return 200;
//	}
	
	

	@Override
	public String getName() 
	{
		return "Creative";
	}

	@Override
	public double fOfXY(int x, int y) 
	{
		Complex base = new Complex(x + 100 , y + 100);
		Complex c = new Complex(x/2, y/2);
		if(base.norm() % c.norm() > 10)
			return base.norm() % c.norm();
		else
			return c.norm() % base.norm() + 155;
	}
}
