package view;

import java.io.IOException;

import view.game.GameAssets;

/**
 * The main class, responsible for creating and showing the respective GUI windows.
 * @author Sarah Lutteropp
 */
public class Main {
	
	private MainWindow window;

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 * @throws IOException 
	 */
	private void createAndShowGUI() throws IOException {
		GameAssets.loadAssets();
		window = new MainWindow();
		window.showTitleWindow();
	}

	/**
	 * The mandatory main function. This only creates a thread for displaying the GUI.
	 * @param args The command line parameters. Please note that these will be ignored.
	 */
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new Main().createAndShowGUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
