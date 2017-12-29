package control.title;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;

/**
 * A listener for the continue button on the title screen. When invoked, it
 * switches to the game screen.
 * 
 * @author Sarah Lutteropp
 */
public class ContinueGameButtonListener implements ActionListener {
	/**
	 * The main window of the program
	 */
	private MainWindow mainWindow;

	/**
	 * Create a listener for the continue button on the title screen
	 * 
	 * @param mainWindow
	 *            The main window of the program
	 */
	public ContinueGameButtonListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		mainWindow.showRunningGameWindow();
	}

}
