import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * @author Zander Bonnet
 * @version 1.0
 * Creates a ClockTester to display the clock
 */
public class ClockTester {
	private static ClockIcon icon;
	private static JLabel label;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		icon = new ClockIcon(300);
		label = new JLabel(icon);
		
		Timer t = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent event)
			 {		 
			 frame.repaint();
			 }
		});
		t.start();
		
		frame.setLayout(new FlowLayout());
		frame.add(label);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}

}
