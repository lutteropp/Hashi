package control.title;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ApplicationWindow;

/**
 * A listener for the Options button on the title screen. When invoked, it switches to the options screen.
 * 
 * @author Sarah Lutteropp
 */
public class HelpButtonListener implements ActionListener {
	private ApplicationWindow mainWindow;
	
	public HelpButtonListener(ApplicationWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mainWindow.showHelpWindow();
	}
}