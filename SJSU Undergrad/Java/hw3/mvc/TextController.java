/**
 * @author zanderbonnet
 * @version 1.0
 * 
 * Creates a text controller
 * THE CONTROLLER COMPONENT
 */
public class TextController {
	private TextView textView;
	private TextTracker textTracker;

	/**
	 * creates the textController to manage the text
	 * 
	 * @param TextView view the textview object used by the controller
	 * @param TextTracker tracker the texttracker object used by the controller
	 */
	public TextController(TextView view, TextTracker tracker) {
		textView = view;
		textTracker = tracker;
	}

	/**
	 * adds a line to the textTracker
	 * 
	 * @param String s the line to be added
	 */
	public void addLine(String s) {
		textTracker.addLine(s);
	}

	/**
	 * updates the textView
	 */
	public void update() {
		textView.addLines(textTracker);
		textView.getView();
	}

}
