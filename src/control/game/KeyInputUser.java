package control.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.MainWindow;

public class KeyInputUser implements KeyListener {
	private MainWindow mainWindow;

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
