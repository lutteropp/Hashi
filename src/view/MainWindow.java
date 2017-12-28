package view;

import java.util.ArrayList;

import javax.swing.JFrame;

import control.game.KeyInputUser;
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
		titleScreenGUI = new TitleScreenGUI(this);
	}

	public void showTitleWindow() {
		if (gameBoardGUI != null) {
			gameBoardGUI.stopMusic();
		}
		titleScreenGUI.loopMusic();

		setContentPane(titleScreenGUI);
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		titleScreenGUI.requestFocus();
		setVisible(true);
	}

	public void showFixedGameWindow() {
		if (titleScreenGUI != null) {
			titleScreenGUI.stopMusic();
			titleScreenGUI.setContinueGameButtonEnabled(true);
		}
		ArrayList<GridNode> nodes = LevelGenerator.getFixedLevelWidth15Height5();
		GameBoard board = new GameBoard(15, 5, nodes);
		gameBoardGUI = new GameBoardGUI(board, this);
		gameBoardGUI.loopMusic();

		setContentPane(gameBoardGUI);
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		gameBoardGUI.requestFocus();
		setVisible(true);
	}

	public void showRandomGameWindow(int width, int height) {
		if (titleScreenGUI != null) {
			titleScreenGUI.stopMusic();
			titleScreenGUI.setContinueGameButtonEnabled(true);
		}
		ArrayList<GridNode> nodes = LevelGenerator.generateLevel(width, height);
		GameBoard board = new GameBoard(width, height, nodes);
		gameBoardGUI = new GameBoardGUI(board, this);

		gameBoardGUI.loopMusic();
		setContentPane(gameBoardGUI);
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		gameBoardGUI.requestFocus();
		setVisible(true);
	}
	
	public void showRunningGameWindow() {
		if (gameBoardGUI == null) {
			throw new RuntimeException("There is no currently running game");
		}
		if (titleScreenGUI != null) {
			titleScreenGUI.stopMusic();
			titleScreenGUI.setContinueGameButtonEnabled(true);
		}
		
		gameBoardGUI.loopMusic();
		setContentPane(gameBoardGUI);
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		gameBoardGUI.requestFocus();
		setVisible(true);
	}

	public void showOptionsWindow() {

	}

	public void showGameFinishedWindow() {
		if (titleScreenGUI != null) {
			titleScreenGUI.setContinueGameButtonEnabled(false);
		}
	}
}
