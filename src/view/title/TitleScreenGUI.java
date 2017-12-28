package view.title;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The title screen GUI. Modified from https://stackoverflow.com/a/21226098
 * 
 * @author Sarah Lutteropp
 */
public class TitleScreenGUI extends JPanel {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = 4550458197071990473L;

	private JButton newFixedGameButton;
	private JButton newRandomGameButton;
	private JButton optionsButton;
	private JButton exitButton;

	/**
	 * Create the title screen.
	 */
	public TitleScreenGUI() {
		newFixedGameButton = new JButton("New fixed game");
		newRandomGameButton = new JButton("New random game");
		optionsButton = new JButton("Options");
		exitButton = new JButton("Exit");
		this.add(newFixedGameButton);
		this.add(newRandomGameButton);
		this.add(optionsButton);
		this.add(exitButton);
	}
}