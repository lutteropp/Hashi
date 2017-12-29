package control.title;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A listener for the exit button on the title screen. When invoked, it ends the
 * program.
 * 
 * @author Sarah Lutteropp
 */
public class ExitButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}

}
