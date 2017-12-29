package view;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jb2011.lnf.beautyeye.BeautyEyeLookAndFeelCross;

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
	private HelpGUI optionsGUI;
	/**
	 * Listener that returns to the title screen when the Escape key is released.
	 */
	private KeyInputUser keyInput;

	public ApplicationWindow()
			throws ParseException, UnsupportedLookAndFeelException, MalformedURLException, IOException {

		// SynthLookAndFeel laf = new SynthLookAndFeel();
		// laf.load(new File("assets/laf.xml").toURI().toURL());
		UIManager.setLookAndFeel(new BeautyEyeLookAndFeelCross());

		setTitle("Hashiwokakero");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationByPlatform(true);
		keyInput = new KeyInputUser(this);
		titleScreenGUI = new TitleScreenGUI(this);
		generatorGUI = new GeneratorGUI(this, keyInput);
		optionsGUI = new HelpGUI(this, keyInput);

		this.setBackground(Color.WHITE);
	}

	public void showTitleWindow() {
		if (gameBoardGUI != null) {
			gameBoardGUI.stopMusic();
		}
		titleScreenGUI.loopMusic();

		setContentPane(titleScreenGUI);
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
		gameBoardGUI = new GameBoardGUI(board, this, keyInput);
		gameBoardGUI.loopMusic();

		setContentPane(gameBoardGUI);
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
		gameBoardGUI = new GameBoardGUI(board, this, keyInput);

		gameBoardGUI.loopMusic();
		setContentPane(gameBoardGUI);
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
		gameBoardGUI.requestFocus();
		setVisible(true);
	}

	public void showOptionsWindow() {
		setContentPane(optionsGUI);
		optionsGUI.requestFocus();
		setVisible(true);
	}

	public void showGameFinishedWindow() {
		SoundAssets.winningMusic.play();
		if (titleScreenGUI != null) {
			titleScreenGUI.setContinueGameButtonEnabled(false);
		}
		JOptionPane.showMessageDialog(this, 
                "You won the game!", 
                "CONGRATULATIONS!", 
                JOptionPane.INFORMATION_MESSAGE);
		this.showTitleWindow();
	}

	public void showGeneratorWindow() {
		setContentPane(generatorGUI);
		generatorGUI.requestFocus();
		setVisible(true);
	}
}
