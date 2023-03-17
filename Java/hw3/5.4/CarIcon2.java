
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * An icon that has the shape of a car.
 */
public class CarIcon2 implements Icon {
	private double width;
	private final int BASE_SIZE = 100;

	/**
	 * Constructs a car of a given width.
	 * 
	 * @param width the width of the car
	 */
	public CarIcon2(int aWidth) {
		width = aWidth;
	}

	public CarIcon2() {
		width = BASE_SIZE;
	}

	/**
	 * Changes the width of the car
	 * 
	 * @param width the width of the car
	 */
	public void setIconWidth(double aWidth) {
		width = aWidth;
	}

	/**
	 * get the width of the ico
	 * 
	 * @return int width
	 */
	public int getIconWidth() {
		return (int) width;
	}

	/**
	 * get the height of the ico
	 * 
	 * @return int width/2
	 */
	public int getIconHeight() {
		return (int) (width / 2);
	}
	
	/**
	 * paints the CarIcon
	 * 
	 * @param Component c the component to be painted
	 * @param Graphics  g the graphical component
	 * @param int       x the x value
	 * @param int
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D.Double body = new Rectangle2D.Double(x, y + width / 6, width - 1, width / 6);
		Ellipse2D.Double frontTire = new Ellipse2D.Double(x + width / 6, y + width / 3, width / 6, width / 6);
		Ellipse2D.Double rearTire = new Ellipse2D.Double(x + width * 2 / 3, y + width / 3, width / 6, width / 6);

		// the bottom of the front windshield
		Point2D.Double r1 = new Point2D.Double(x + width / 6, y + width / 6);
		// the front of the roof
		Point2D.Double r2 = new Point2D.Double(x + width / 3, y);
		// the rear of the roof
		Point2D.Double r3 = new Point2D.Double(x + width * 2 / 3, y);
		// the bottom of the rear windshield
		Point2D.Double r4 = new Point2D.Double(x + width * 5 / 6, y + width / 6);

		Line2D.Double frontWindshield = new Line2D.Double(r1, r2);
		Line2D.Double roofTop = new Line2D.Double(r2, r3);
		Line2D.Double rearWindshield = new Line2D.Double(r3, r4);

		g2.fill(frontTire);
		g2.fill(rearTire);
		g2.setColor(Color.red);
		g2.fill(body);
		g2.draw(frontWindshield);
		g2.draw(roofTop);
		g2.draw(rearWindshield);
	}
}
