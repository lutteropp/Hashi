package control.title;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;

/**
 * A listener for the NewFixedGame button on the title screen. When invoked, it
 * starts a new fixed game.
 * 
 * @author Sarah Lutteropp
 */
public class NewFixedGameButtonListener implements ActionListener {
	/**
	 * The main window of the program
	 */
	private MainWindow mainWindow;

	/**
	 * Create a listener for the NewFixedGame button on the title screen.
	 * 
	 * @param mainWindow
	 *            The main window of the program
	 */
	public NewFixedGameButtonListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainWindow.showFixedGameWindow();
	}
}
