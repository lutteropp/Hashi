package control.title;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;

/**
 * A listener for the Options button on the title screen. When invoked, it switches to the options screen.
 * 
 * @author Sarah Lutteropp
 */
public class OptionsButtonListener implements ActionListener {
	private MainWindow mainWindow;
	
	public OptionsButtonListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mainWindow.showOptionsWindow();
	}
}