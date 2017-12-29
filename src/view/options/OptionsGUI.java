package view.options;

import javax.swing.JPanel;

import control.title.KeyInputUser;
import view.ApplicationWindow;

/**
 * The general options GUI.
 * 
 * @author Sarah Lutteropp
 */
public class OptionsGUI extends JPanel {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = 4753107236165358560L;	
	/**
	 * Create the GUI for choosing the parameters for the random generator
	 * @param mainWindow The main window
	 * @parem KeyInputUser The key listener to get back to the main window
	 */
	public OptionsGUI(ApplicationWindow mainWindow, KeyInputUser keyInput) {
		addKeyListener(keyInput);
	}
}
