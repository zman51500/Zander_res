import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * @author Zander Bonnet
 * @version 1.0
 * Creates a ZoomTester so you can display the zoom window
 */
public class ZoomTester {
	private static CarIcon icon;
	private static JLabel label;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		final int FIELD_WIDTH = 20;

		JButton zoomIn = new JButton("Zoom In");
		zoomIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int newWidth = (int) (1.1 * icon.getIconWidth());
				if (newWidth > 190)
					newWidth = 190;
				icon.setIconWidth(newWidth);
				label.repaint();
			}
		});

		JButton zoomOut = new JButton("Zoom Out");
		zoomOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int newWidth = (int) (0.9 * icon.getIconWidth());
				if (newWidth < 10)
					newWidth = 10;
				icon.setIconWidth(newWidth);
				label.repaint();
			}
		});

		icon = new CarIcon(100);
		label = new JLabel(icon);

		frame.setLayout(new FlowLayout());
		frame.add(zoomIn);
		frame.add(zoomOut);
		frame.add(label);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}
}
