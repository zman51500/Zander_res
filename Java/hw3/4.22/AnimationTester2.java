import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/*
 * @author Zander Bonnet
 * @version 1.0
 * Creates a AnimationTester2 object that displays moving cars that wrap
 */
public class AnimationTester2 {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 500);

		ArrayList<MoveableShape2> shapes = new ArrayList<MoveableShape2>();
		ArrayList<JLabel> objects = new ArrayList<JLabel>();

		final MoveableShape2 shape = new CarShape2(0, 0, CAR_WIDTH);
		shapes.add(shape);
		final MoveableShape2 shape2 = new CarShape2(0, 0, CAR_WIDTH);
		shapes.add(shape2);
		final MoveableShape2 shape3 = new CarShape2(0, 0, CAR_WIDTH);
		shapes.add(shape3);

		for (MoveableShape2 m : shapes) {
			ShapeIcon2 icon = new ShapeIcon2(m, ICON_WIDTH, ICON_HEIGHT);
			final JLabel label = new JLabel(icon);
			objects.add(label);
			frame.add(label);
		}

		frame.setLayout(new FlowLayout(FlowLayout.LEFT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		final int DELAY = 10;
		// Milliseconds between timer ticks
		Timer t = new Timer(DELAY, event -> {
			for (MoveableShape2 m : shapes) {
				m.move();
				int x = m.getX();
				if (x > frame.getBounds().getWidth()) {
					m.setX(0);
				}
			}
			frame.repaint();
		});
		t.start();

	}

	private static final int ICON_WIDTH = 400;
	private static final int ICON_HEIGHT = 100;
	private static final int CAR_WIDTH = 100;
}
