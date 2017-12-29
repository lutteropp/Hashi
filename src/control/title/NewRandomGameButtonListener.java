package control.title;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ApplicationWindow;

/**
 * A listener for the NewRandomGame button on the title screen. When invoked, it
 * switches to the screen for choosing the level generator's parameters.
 * 
 * @author Sarah Lutteropp
 */
public class NewRandomGameButtonListener implements ActionListener {
	/**
	 * The main window of the program
	 */
	private ApplicationWindow mainWindow;

	/**
	 * Create a listener for the NewRandomGame button on the title screen.
	 * 
	 * @param mainWindow
	 *            The main window of the program
	 */
	public NewRandomGameButtonListener(ApplicationWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainWindow.showGeneratorWindow();
	}
}