package control.title;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;

/**
 * A listener for the NewRandomGame button on the title screen.
 * 
 * @author Sarah Lutteropp
 */
public class NewRandomGameButtonListener implements ActionListener {
	private MainWindow mainWindow;
	
	public NewRandomGameButtonListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO
	}
}