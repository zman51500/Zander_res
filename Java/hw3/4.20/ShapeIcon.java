import java.awt.*;
import java.util.*;
import javax.swing.*;

/*
 * @author Zander Bonnet
 * @version 1.0
 * Creates a ShapeIcon
 */
public class ShapeIcon implements Icon {
	/**
	 * Draws the shape.
	 * 
	 * @param MoveableShape shape the shape to be created
	 * @param int           width the width of the icon
	 * @param int           height teh height of the icon
	 */
	public ShapeIcon(MoveableShape shape, int width, int height) {
		this.shape = shape;
		this.width = width;
		this.height = height;
	}

	/**
	 * gets the icon width
	 * 
	 * @return int width tthe width of the object
	 */
	public int getIconWidth() {
		return width;
	}

	/**
	 * get icon height
	 * 
	 * @param g2 the graphics context
	 */
	public int getIconHeight() {
		return height;
	}

	/**
	 * paints the icon
	 * 
	 * @param Component c the component
	 * @param Graphics  g the graphics component
	 * @param int       x the x coordinate
	 * @param int       y the y coordinate
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		shape.draw(g2);
	}

	private int width;
	private int height;
	private MoveableShape shape;
}
