import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 * @author zanderbonnet
 * @version 1.0
 * 
 * Creates a text view
 * THE VIEW COMPONENT
 */
public class TextView {
	JTextArea textArea;

	/**
	 * creates the textView to view the text
	 */
	public TextView() {
		textArea = new JTextArea(10, 15);
		textArea.setEditable(false);

	}

	/**
	 * returns the textArea to be displayed
	 * 
	 * @return TextArea textArea teh textArea to be displayed
	 */
	public JTextArea getView() {
		return textArea;
	}

	/**
	 * adds lines from a textTracker to the textViewr
	 * 
	 * @param TextTracker t the text tracker to display
	 */
	public void addLines(TextTracker t) {
		textArea.setText("");
		ArrayList<String> lines = t.getText();
		for (String s : lines) {
			textArea.append(s + "\n");
		}
	}
}
