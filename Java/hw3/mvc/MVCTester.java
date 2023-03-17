import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author zanderbonnet
 * @version 1.0
 * 
 * Creates a MVCTester model
 * THE VIEW COMPONENT
 */
public class MVCTester {

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		TextTracker tracker = new TextTracker();
		final TextView view = new TextView();
		TextController cont = new TextController(view, tracker);

		TextField field = new TextField(10);

		JButton addButton = new JButton("ADD");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont.addLine(field.getText());
				cont.update();
				field.setText("");
			}
		});

		frame.setLayout(new FlowLayout());
		frame.add(field);
		frame.add(addButton);
		frame.add(view.getView());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
