package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import assets.GraphicalGameAssets;
import assets.SoundAssets;
import control.title.KeyInputUser;
import model.base.GameBoard;
import model.base.GridNode;
import model.generator.LevelGenerator;
import view.game.GameBoardGUI;
import view.generator.GeneratorGUI;
import view.help.HelpGUI;
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
	private HelpGUI helpGUI;
	/**
	 * Listener that returns to the title screen when the Escape key is released.
	 */
	private KeyInputUser keyInput;

	/**
	 * Create the main window of the program.
	 * 
	 * @throws IOException
	 */
	public ApplicationWindow() throws IOException {
		setTitle("Hashiwokakero");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationByPlatform(true);
		keyInput = new KeyInputUser(this);
		titleScreenGUI = new TitleScreenGUI(this);
		generatorGUI = new GeneratorGUI(this, keyInput);
		helpGUI = new HelpGUI(this, keyInput);
	}

	/**
	 * Switch to the title screen.
	 */
	public void showTitleWindow() {
		if (gameBoardGUI != null) {
			gameBoardGUI.stopMusic();
		}
		if (generatorGUI != null) {
			generatorGUI.resetButtonColors();
		}
		titleScreenGUI.resetButtonColors();
		titleScreenGUI.loopMusic();

		setContentPane(titleScreenGUI);
		titleScreenGUI.requestFocus();
		setVisible(true);
	}

	/**
	 * Switch to the game sceen and start a fixed game.
	 */
	public void showFixedGameWindow() {
		if (titleScreenGUI != null) {
			titleScreenGUI.stopMusic();
			titleScreenGUI.setContinueGameButtonEnabled(true);
		}
		ArrayList<GridNode> nodes = LevelGenerator.getFixedLevelWidth15Height5();
		GameBoard board = new GameBoard(15, 5, nodes);
		gameBoardGUI = new GameBoardGUI(board, this, keyInput);
		gameBoardGUI.loopMusic();

		setContentPane(gameBoardGUI);
		gameBoardGUI.requestFocus();
		setVisible(true);
	}

	/**
	 * Switch to the game screen and start a random game.
	 * 
	 * @param width
	 *            The wanted width of the game grid.
	 * @param height
	 *            The wanted height of the game grid.
	 * @param gridUsage
	 *            The wanted percentage of the grid area to be filled with nodes.
	 * @param pOuterExtension
	 *            The probability for creating a new node instead of connecting to
	 *            existing nodes during generation. Has to be > 0 and <= 1.
	 */
	public void showRandomGameWindow(int width, int height, double gridUsage, double pOuterExtension) {
		if (titleScreenGUI != null) {
			titleScreenGUI.stopMusic();
			titleScreenGUI.setContinueGameButtonEnabled(true);
		}
		ArrayList<GridNode> nodes = LevelGenerator.generateLevel(width, height, gridUsage, pOuterExtension);
		GameBoard board = new GameBoard(width, height, nodes);
		gameBoardGUI = new GameBoardGUI(board, this, keyInput);

		gameBoardGUI.loopMusic();
		setContentPane(gameBoardGUI);
		gameBoardGUI.requestFocus();
		setVisible(true);
	}

	/**
	 * Switch to the game window of an already running game.
	 */
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
		gameBoardGUI.requestFocus();
		setVisible(true);
	}

	/**
	 * Switch to the help instructions screen.
	 */
	public void showHelpWindow() {
		setContentPane(helpGUI);
		helpGUI.requestFocus();
		setVisible(true);
	}

	/**
	 * Finish a game: Inform the user that he, she, or it has won the game and switch back to the title screen.
	 */
	public void showGameFinishedWindow() {
		gameBoardGUI.setBackgroundImage(GraphicalGameAssets.getBigCatImage());
		gameBoardGUI.repaint();
		SoundAssets.winningMusic.play();
		if (titleScreenGUI != null) {
			titleScreenGUI.setContinueGameButtonEnabled(false);
		}
		JOptionPane.showMessageDialog(null, "You won the game!", "Congrats!", JOptionPane.INFORMATION_MESSAGE);
		this.showTitleWindow();
		gameBoardGUI.setBackgroundImage(null);
		gameBoardGUI.repaint();
	}

	/**
	 * Switch to the generator screen.
	 */
	public void showGeneratorWindow() {
		setContentPane(generatorGUI);
		generatorGUI.requestFocus();
		setVisible(true);
	}
}
