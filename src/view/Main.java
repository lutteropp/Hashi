package view;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.MouseInputUser;
import model.GameBoard;
import model.GridNode;
import model.LevelGenerator;

public class Main {

	private DrawingBoard drawingBoard;
	private JButton button;

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 * @throws IOException 
	 */
	private void createAndShowGUI() throws IOException {
		JFrame frame = new JFrame("");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		int width = 15;
		int height = 5;
		//ArrayList<GridNode> nodes = LevelGenerator.getFixedLevelWidth15Height5();
		ArrayList<GridNode> nodes = LevelGenerator.generateLevel(width, height);
		
		Assets.loadAssets();
		
		GameBoard board = new GameBoard(width, height, nodes);
		drawingBoard = new DrawingBoard(board);
		contentPane.add(drawingBoard, BorderLayout.CENTER);

		frame.setContentPane(contentPane);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

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
