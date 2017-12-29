package view.title;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
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
import view.ApplicationWindow;
import view.ScalingButton;

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
	private ScalingButton continueGameButton;
	/** The "new fixed game" button */ 
	private ScalingButton newFixedGameButton;
	/** The "new random game" button */
	private ScalingButton newRandomGameButton;
	/** The "options" button */
	private ScalingButton optionsButton;
	/** The "exit" button */
	private ScalingButton exitButton;

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

		TitlePane titlePane = new TitlePane();

		JPanel leftBorder = new JPanel();
		JPanel rightBorder = new JPanel();
		JPanel bottomBorder = new JPanel();
		buttonPane = new JPanel();

		continueGameButton = new ScalingButton("Continue game");
		continueGameButton.addActionListener(new ContinueGameButtonListener(mainWindow));
		continueGameButton.setEnabled(false);
		buttons.add(continueGameButton);
		newFixedGameButton = new ScalingButton("New fixed game");
		newFixedGameButton.addActionListener(new NewFixedGameButtonListener(mainWindow));
		buttons.add(newFixedGameButton);
		newRandomGameButton = new ScalingButton("New random game");
		newRandomGameButton.addActionListener(new NewRandomGameButtonListener(mainWindow));
		buttons.add(newRandomGameButton);
		optionsButton = new ScalingButton("Options");
		optionsButton.addActionListener(new OptionsButtonListener(mainWindow));
		buttons.add(optionsButton);
		exitButton = new ScalingButton("Exit");
		exitButton.addActionListener(new ExitButtonListener());
		buttons.add(exitButton);

		buttonPane.setLayout(new GridLayout(0, 1));
		for (JButton button : buttons) {
			button.setAlignmentX(Component.CENTER_ALIGNMENT);
			buttonPane.add(button);
		}
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.weighty = 0.3;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;
		add(titlePane, c);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridheight = 4;
		c.gridx = 1;
		c.gridy = 1;
		add(buttonPane, c);
		c = new GridBagConstraints();
		c.gridheight = 5;
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.15;
		add(leftBorder, c);
		c = new GridBagConstraints();
		c.gridheight = 5;
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.15;
		add(rightBorder, c);
		c = new GridBagConstraints();
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.1;
		add(bottomBorder, c);

		/*titlePane.setBackground(Color.WHITE);
		leftBorder.setBackground(Color.WHITE);
		rightBorder.setBackground(Color.WHITE);
		bottomBorder.setBackground(Color.WHITE);
		buttonPane.setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);*/
	}
}