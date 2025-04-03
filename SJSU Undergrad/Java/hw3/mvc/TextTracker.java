import java.util.ArrayList;

/**
 * @author zanderbonnet
 * @version 1.0
 * 
 * Creates a text controller
 * THE MODEL COMPONENT
 */
public class TextTracker {
	private ArrayList<String> lines;

	/**
	 * creates the textTracker to manage the text
	 */
	TextTracker() {
		lines = new ArrayList<String>();
	}

	/**
	 * adds a line to the tracked text
	 * 
	 * @param String s the line to add
	 */
	public void addLine(String s) {
		lines.add(s);
	}
	
	/**
	 * gets the text in window
	 * 
	 * @return ArrayList<String> lines the lines in the text
	 */
	public ArrayList<String> getText() {
		return lines;
	}

	/**
	 * gets a specific line based on the index
	 * 
	 * @param int i the index
	 * @return String lines.get(i) the line at index i
	 */
	public String getLine(int i) {
		return lines.get(i);
	}
}
