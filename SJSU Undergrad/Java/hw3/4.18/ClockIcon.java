import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

/*
 * @author Zander Bonnet
 * @version 1.0
 * Creates a ClockIcon
 */
public class ClockIcon implements Icon {

	private int radius;

	/**
	 * Creates the clock object
	 * 
	 * @param int r the radius of the clock
	 */
	public ClockIcon(int r) {
		radius = r;
	}

	/**
	 * sets the radius of the clockicon
	 * 
	 * @param int r the radius of the clock
	 */
	public void setRadius(int r) {
		radius = r;
	}

	/**
	 * gets the width of the clockIcon
	 * 
	 * @return int 2*radius the radius of the clockIcon
	 */
	public int getIconWidth() {
		return 2 * radius;
	}

	/**
	 * gets the height of the clockIcon
	 * 
	 * @return int 2*radius the radius of the clockIcon
	 */
	public int getIconHeight() {
		return 2 * radius;
	}

	/**
	 * paints the clockIcon
	 * 
	 * @param Component c the component to be painted
	 * @param Graphics  g the graphical component
	 * @param int       x the x value
	 * @param int       y the y value
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics g2 = (Graphics2D) g;
		Ellipse2D.Double ring = new Ellipse2D.Double(x, y, radius, radius);
		g2.setColor(Color.white);
		((Graphics2D) g2).fill(ring);
		int xcenter = x + radius / 2;
		int ycenter = y + radius / 2;

		GregorianCalendar time = new GregorianCalendar();
		Date t = time.getTime();
		int hour = t.getHours();
		int minute = t.getMinutes();
		int second = t.getSeconds();

		int xsecond = (int) (Math.cos(second * 3.14f / 30 - 3.14f / 2) * radius/3 + xcenter);
		int ysecond = (int) (Math.sin(second * 3.14f / 30 - 3.14f / 2) * radius/3 + ycenter);
		int xminute = (int) (Math.cos(minute * 3.14f / 30 - 3.14f / 2) * radius/4 + xcenter);
		int yminute = (int) (Math.sin(minute * 3.14f / 30 - 3.14f / 2) * radius/4 + ycenter);
		int xhour = (int) (Math.cos((hour * 30 + minute / 2) * 3.14f / 180 - 3.14f / 2) * radius/5 + xcenter);
		int yhour = (int) (Math.sin((hour * 30 + minute / 2) * 3.14f / 180 - 3.14f / 2) * radius/5 + ycenter);

		g2.setColor(Color.magenta);
		g2.drawLine(xcenter, ycenter, xsecond, ysecond);
		g2.setColor(Color.red);
		g2.drawLine(xcenter, ycenter, xminute, yminute);
		g2.drawLine(xcenter, ycenter, xminute, yminute);
		g2.setColor(Color.green);
		g2.drawLine(xcenter, ycenter, xhour, yhour);
		g2.drawLine(xcenter, ycenter, xhour, yhour);
		
		g2.setColor(Color.BLACK);
		g2.drawString("Cupertino, CA", xcenter + 50, ycenter);
	}

}
