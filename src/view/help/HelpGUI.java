package view.help;

import javax.swing.JPanel;

import control.title.KeyInputUser;
import view.ApplicationWindow;

/**
 * The help instructions GUI.
 * 
 * @author Sarah Lutteropp
 */
public class HelpGUI extends JPanel {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = 4753107236165358560L;	
	/**
	 * Create the GUI for showing the game instructions.
	 * @param mainWindow The main window
	 * @parem KeyInputUser The key listener to get back to the main window
	 */
	public HelpGUI(ApplicationWindow mainWindow, KeyInputUser keyInput) {
		addKeyListener(keyInput);
	}
}
