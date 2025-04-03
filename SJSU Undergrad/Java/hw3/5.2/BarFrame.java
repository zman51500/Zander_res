import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A class that implements an Observer object that displays a barchart view of a
 * data model.
 * 
 * Taken from book
 */
public class BarFrame extends JFrame implements ChangeListener, MouseListener

{
	/**
	 * Constructs a BarFrame object
	 * 
	 * @param dataModel the data that is displayed in the barchart
	 */
	public BarFrame(DataModel dataModel) {
		this.dataModel = dataModel;
		a = dataModel.getData();

		setLocation(0, 200);
		setLayout(new BorderLayout());

		Icon barIcon = new Icon() {
			public int getIconWidth() {
				return ICON_WIDTH;
			}

			public int getIconHeight() {
				return ICON_HEIGHT;
			}

			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g;

				g2.setColor(Color.red);

				double max = (a.get(0)).doubleValue();
				for (Double v : a) {
					double val = v.doubleValue();
					if (val > max)
						max = val;
				}

				double barHeight = getIconHeight() / a.size();

				int i = 0;
				for (Double v : a) {
					double value = v.doubleValue();

					double barLength = getIconWidth() * value / max;

					Rectangle2D.Double rectangle = new Rectangle2D.Double(0, barHeight * i, barLength, barHeight);
					i++;
					g2.fill(rectangle);
				}
			}
		};

		add(new JLabel(barIcon));
		addMouseListener(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	/**
	 * Called when the data in the model is changed.
	 * 
	 * @param e the event representing the change
	 */
	public void stateChanged(ChangeEvent e) {
		a = dataModel.getData();
		repaint();
	}

	private ArrayList<Double> a;
	private DataModel dataModel;
	private static final int ICON_WIDTH = 200;
	private static final int ICON_HEIGHT = 200;

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		double x = e.getX();
		double y = e.getY();
		// System.out.println(e.getPoint());
		if (y <= 75)
			dataModel.update(0, x);
		else if (y <= 125 && y > 75)
			dataModel.update(1, x);
		else if (y <= 175 && y > 125)
			dataModel.update(2, x);
		else if (y <= 225 && y > 175)
			dataModel.update(3, x);
	}

	@Override
	/**
	 * Called when there is a mouse press event
	 * 
	 * @param e the event representing the change
	 */
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}