import java.awt.*;

/*
 * @author Zander Bonnet
 * @version 1.0
 * The moveableShap interface
 */
public interface MoveableShape2 {
	/**
	 * Draws the shape.
	 * 
	 * @param g2 the graphics context
	 */
	void draw(Graphics2D g2);

	/**
	 * Moves the shape. It is up to the shape to move itself, for example by
	 * tracking the time since its last movement, its position, and velocity.
	 */
	void move();

	/**
	 * gets the x value
	 */
	int getX();
	
	/**
	 * sets the x value
	 */
	void setX(int x);
}
