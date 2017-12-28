package view;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.base.GameBoard;
import model.base.GridNode;
import model.generator.LevelGenerator;
import view.game.Assets;
import view.game.DrawingBoardGUI;

/**
 * The main class, responsible for creating and showing the GUI.
 * @author Sarah Lutteropp
 */
public class Main {
	/**
	 * The game field.
	 */
	private DrawingBoardGUI drawingBoard;

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 * @throws IOException 
	 */
	private void createAndShowGUI() throws IOException {
		Assets.loadAssets();
		JFrame frame = new JFrame("Hashiwokakero");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		final int width = 15;
		final int height = 5;
		//ArrayList<GridNode> nodes = LevelGenerator.getFixedLevelWidth15Height5();
		ArrayList<GridNode> nodes = LevelGenerator.generateLevel(width, height);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		GameBoard board = new GameBoard(width, height, nodes);
		drawingBoard = new DrawingBoardGUI(board);
		contentPane.add(drawingBoard, BorderLayout.CENTER);

		frame.setContentPane(contentPane);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
