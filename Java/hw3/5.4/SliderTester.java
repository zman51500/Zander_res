import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Zander Bonnet
 * @version 1.0
 * Creates the slider tester that changes the size of a car object using a slider
 * 
 */
public class SliderTester {
	private static CarIcon2 icon;
	private static JLabel label;
	private static final int BASE_SIZE = 100;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		final int FIELD_WIDTH = 20;
		final int SIZE_MAX = 15;
		final int SIZE_MIN = -5;
		final int SIZE_INT = 5;

		JSlider slider = new JSlider(JSlider.HORIZONTAL, SIZE_MIN, SIZE_MAX, SIZE_INT);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);

		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				double amount = source.getValue();
				if (amount == 5) {
					icon.setIconWidth(BASE_SIZE);
				} else if (amount > 5) {
					icon.setIconWidth(BASE_SIZE * (1 + ((amount - 5) / 10)));
				} else if (amount < 5) {
					icon.setIconWidth(BASE_SIZE * ((amount + 5) / 10));
				}
				label.repaint();
			}

		});

		icon = new CarIcon2();
		label = new JLabel(icon);

		frame.setLayout(new FlowLayout());
		frame.add(slider);
		frame.add(label);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

}
