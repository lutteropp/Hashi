package control.title;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;

/**
 * A listener for the NewFixedGame button on the title screen.
 * 
 * @author Sarah Lutteropp
 */
public class NewFixedGameButtonListener implements ActionListener {
	private MainWindow mainWindow;
	
	public NewFixedGameButtonListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mainWindow.showFixedGameWindow();
	}
}
