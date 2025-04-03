import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/*
 * @author Zander Bonnet
 * @version 1.0
 * Creates a AnimationTester1 object that displays moving cars
 */
public class AnimationTester1 {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ArrayList<MoveableShape> shapes = new ArrayList<MoveableShape>();

		MoveableShape shape = new CarShape(0, 0, CAR_WIDTH);
		shapes.add(shape);
		MoveableShape shape2 = new CarShape(0, 0, CAR_WIDTH);
		shapes.add(shape2);
		MoveableShape shape3 = new CarShape(0, 0, CAR_WIDTH);
		shapes.add(shape3);

		frame.setLayout(new FlowLayout());
		for (MoveableShape m : shapes) {
			ShapeIcon icon = new ShapeIcon(m, ICON_WIDTH, ICON_HEIGHT);
			JLabel label = new JLabel(icon);
			frame.add(label);
		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		final int DELAY = 100;
		// Milliseconds between timer ticks
		Timer t = new Timer(DELAY, event -> {
			for (MoveableShape m : shapes)
				m.move();
			frame.repaint();
		});
		t.start();
	}

	private static final int ICON_WIDTH = 400;
	private static final int ICON_HEIGHT = 100;
	private static final int CAR_WIDTH = 100;
}
