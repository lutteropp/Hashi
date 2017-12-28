package control.title;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;

/**
 * A listener for the exit button on the title screen.
 * @author Sarah Lutteropp
 */
public class ContinueGameButtonListener implements ActionListener {
	private MainWindow mainWindow;
	
	public ContinueGameButtonListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		mainWindow.showRunningGameWindow();
	}

}
