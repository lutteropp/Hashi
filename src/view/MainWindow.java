package view;

import java.util.ArrayList;

import javax.swing.JFrame;

import model.base.GameBoard;
import model.base.GridNode;
import model.generator.LevelGenerator;
import view.game.GameBoardGUI;
import view.title.TitleScreenGUI;

public class MainWindow extends JFrame {
	/**
	 * The game field.
	 */
	private GameBoardGUI gameBoardGUI;
	/**
	 * The title screen.
	 */
	private TitleScreenGUI titleScreenGUI;
	
	public MainWindow() {
		setTitle("Hashiwokakero");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationByPlatform(true);
	}
	
	public void showTitleWindow() {
		titleScreenGUI = new TitleScreenGUI(this);
		titleScreenGUI.loopMusic();
		
		setContentPane(titleScreenGUI);
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	public void showFixedGameWindow() {
		titleScreenGUI.stopMusic();
		ArrayList<GridNode> nodes = LevelGenerator.getFixedLevelWidth15Height5();
		GameBoard board = new GameBoard(15, 5, nodes);
		gameBoardGUI = new GameBoardGUI(board);
		
		setContentPane(gameBoardGUI);
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	public void showRandomGameWindow(int width, int height) {
		titleScreenGUI.stopMusic();
		ArrayList<GridNode> nodes = LevelGenerator.generateLevel(width, height);
		GameBoard board = new GameBoard(width, height, nodes);
		gameBoardGUI = new GameBoardGUI(board);
		
		setContentPane(gameBoardGUI);
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	public void showOptionsWindow() {
		
	}
}
