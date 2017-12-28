package view;

import java.util.ArrayList;

import javax.swing.JFrame;

import model.base.GameBoard;
import model.base.GridNode;
import model.generator.LevelGenerator;
import view.game.GameBoardGUI;
import view.title.TitleScreenGUI;

public class MainWindow extends JFrame {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = -4506625565792229187L;
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
		if (gameBoardGUI != null) {
			gameBoardGUI.stopMusic();
		}
		titleScreenGUI = new TitleScreenGUI(this);
		titleScreenGUI.loopMusic();

		setContentPane(titleScreenGUI);
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public void showFixedGameWindow() {
		if (titleScreenGUI != null) {
			titleScreenGUI.stopMusic();
		}
		ArrayList<GridNode> nodes = LevelGenerator.getFixedLevelWidth15Height5();
		GameBoard board = new GameBoard(15, 5, nodes);
		gameBoardGUI = new GameBoardGUI(board);
		gameBoardGUI.loopMusic();

		setContentPane(gameBoardGUI);
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public void showRandomGameWindow(int width, int height) {
		if (titleScreenGUI != null) {
			titleScreenGUI.stopMusic();
		}
		ArrayList<GridNode> nodes = LevelGenerator.generateLevel(width, height);
		GameBoard board = new GameBoard(width, height, nodes);
		gameBoardGUI = new GameBoardGUI(board);

		gameBoardGUI.loopMusic();
		setContentPane(gameBoardGUI);
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public void showOptionsWindow() {

	}
}
