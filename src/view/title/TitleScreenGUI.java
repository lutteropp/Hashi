package view.title;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import assets.SoundAssets;
import control.title.ContinueGameButtonListener;
import control.title.ExitButtonListener;
import control.title.NewFixedGameButtonListener;
import control.title.NewRandomGameButtonListener;
import control.title.OptionsButtonListener;
import control.title.TitleGUIResizeListener;
import view.ApplicationWindow;

/**
 * The title screen GUI.
 * 
 * @author Sarah Lutteropp
 */
public class TitleScreenGUI extends JPanel {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = 4550458197071990473L;

	/** The title image on top */
	private JLabel titleImage;

	/** The panel containing all the buttons on the title screen */
	private JPanel buttonPane;

	/** All buttons on the title screen in one list*/
	private ArrayList<JButton> buttons;

	/** The "continue game" button */
	private JButton continueGameButton;
	/** The "new fixed game" button */ 
	private JButton newFixedGameButton;
	/** The "new random game" button */
	private JButton newRandomGameButton;
	/** The "options" button */
	private JButton optionsButton;
	/** The "exit" button */
	private JButton exitButton;

	/**
	 * Has the music been stopped? This flag is used for avoiding interrupting an
	 * already playing music just to start it again
	 */
	private boolean hasStopped;

	/**
	 * Play background music.
	 */
	public void loopMusic() {
		if (hasStopped) {
			SoundAssets.titleMusic.setRepeat(true);
			SoundAssets.titleMusic.play();
			hasStopped = false;
		}
	}

	/**
	 * Stop playing background music.
	 */
	public void stopMusic() {
		SoundAssets.titleMusic.stop();
		hasStopped = true;
	}

	/**
	 * Set whether the continue game button should be enabled or disabled.
	 * 
	 * @param enabled
	 *            Is the continue game button enabled?
	 */
	public void setContinueGameButtonEnabled(boolean enabled) {
		continueGameButton.setEnabled(enabled);
	}

	/**
	 * Create the title screen.
	 * 
	 * @param mainWindow
	 *            The main window of the program
	 */
	public TitleScreenGUI(ApplicationWindow mainWindow) {
		hasStopped = true;
		buttons = new ArrayList<JButton>();

		JPanel titlePane = new JPanel(new GridBagLayout());
		titleImage = new JLabel("Hashiwokakero");
		titleImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		titlePane.add(titleImage);

		JPanel leftBorder = new JPanel();
		JPanel rightBorder = new JPanel();
		JPanel bottomBorder = new JPanel();
		buttonPane = new JPanel();

		continueGameButton = new JButton("Continue game");
		continueGameButton.addActionListener(new ContinueGameButtonListener(mainWindow));
		continueGameButton.setEnabled(false);
		buttons.add(continueGameButton);
		newFixedGameButton = new JButton("New fixed game");
		newFixedGameButton.addActionListener(new NewFixedGameButtonListener(mainWindow));
		buttons.add(newFixedGameButton);
		newRandomGameButton = new JButton("New random game");
		newRandomGameButton.addActionListener(new NewRandomGameButtonListener(mainWindow));
		buttons.add(newRandomGameButton);
		optionsButton = new JButton("Options");
		optionsButton.addActionListener(new OptionsButtonListener(mainWindow));
		buttons.add(optionsButton);
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitButtonListener());
		buttons.add(exitButton);

		buttonPane.setLayout(new GridLayout(0, 1));
		for (JButton button : buttons) {
			button.setAlignmentX(Component.CENTER_ALIGNMENT);
			buttonPane.add(button);
		}

		this.setLayout(new BorderLayout());
		add(titlePane, BorderLayout.NORTH);
		add(buttonPane, BorderLayout.CENTER);
		add(leftBorder, BorderLayout.WEST);
		add(rightBorder, BorderLayout.EAST);
		add(bottomBorder, BorderLayout.SOUTH);

		this.addComponentListener(new TitleGUIResizeListener(buttons, titleImage));
		titlePane.setBackground(Color.WHITE);
		leftBorder.setBackground(Color.WHITE);
		rightBorder.setBackground(Color.WHITE);
		bottomBorder.setBackground(Color.WHITE);
		buttonPane.setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
	}
}