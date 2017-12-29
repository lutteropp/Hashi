package control.title;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.MainWindow;

/**
 * Listener that waits for the user pressing the ESCAPE key in order to return
 * to the title menu screen.
 * 
 * @author Sarah Lutteropp
 */
public class KeyInputUser implements KeyListener {
	/**
	 * The main window of the program
	 */
	private MainWindow mainWindow;

	/**
	 * Create a listener that waits for the user pressing the ESCAPE key in order to
	 * return to the title menu screen.
	 * 
	 * @param mainWindow
	 *            The main window of the program.
	 */
	public KeyInputUser(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			mainWindow.showTitleWindow();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
