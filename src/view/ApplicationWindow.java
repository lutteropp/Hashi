package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import control.title.KeyInputUser;
import model.base.GameBoard;
import model.base.GridNode;
import model.generator.LevelGenerator;
import view.game.GameBoardGUI;
import view.generator.GeneratorGUI;
import view.options.OptionsGUI;
import view.title.TitleScreenGUI;

/**
 * The main window of the program. This is the JFrame that shows everything to
 * the user.
 * 
 * @author Sarah Lutteropp
 *
 */
public class ApplicationWindow extends JFrame {
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
	/**
	 * The random level generator.
	 */
	private GeneratorGUI generatorGUI;
	/**
	 * The options screen.
	 */
	private OptionsGUI optionsGUI;
	/**
	 * Listener that returns to the title screen when the Escape key is released.
	 */
	private KeyInputUser keyInput;

	public ApplicationWindow() {
		setTitle("Hashiwokakero");
		//setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationByPlatform(true);
		titleScreenGUI = new TitleScreenGUI(this);
		generatorGUI = new GeneratorGUI(this);
		optionsGUI = new OptionsGUI(this);
		keyInput = new KeyInputUser(this);

		titleScreenGUI.addKeyListener(keyInput);
		generatorGUI.addKeyListener(keyInput);
		optionsGUI.addKeyListener(keyInput);

		this.setBackground(Color.WHITE);
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
		gameBoardGUI.addKeyListener(keyInput);
		gameBoardGUI.loopMusic();

		setContentPane(gameBoardGUI);
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		gameBoardGUI.requestFocus();
		setVisible(true);
	}

	public void showRandomGameWindow(int width, int height, double gridUsage, double pOuterExtension) {
		if (titleScreenGUI != null) {
			titleScreenGUI.stopMusic();
			titleScreenGUI.setContinueGameButtonEnabled(true);
		}
		ArrayList<GridNode> nodes = LevelGenerator.generateLevel(width, height, gridUsage, pOuterExtension);
		GameBoard board = new GameBoard(width, height, nodes);
		gameBoardGUI = new GameBoardGUI(board, this);
		gameBoardGUI.addKeyListener(keyInput);

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
		setContentPane(optionsGUI);
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		optionsGUI.requestFocus();
		setVisible(true);
	}

	public void showGameFinishedWindow() {
		if (titleScreenGUI != null) {
			titleScreenGUI.setContinueGameButtonEnabled(false);
		}
	}

	public void showGeneratorWindow() {
		setContentPane(generatorGUI);
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		generatorGUI.requestFocus();
		setVisible(true);
	}
}
