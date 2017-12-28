package view.title;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.title.ExitButtonListener;
import control.title.NewFixedGameButtonListener;
import control.title.NewRandomGameButtonListener;
import control.title.OptionsButtonListener;
import control.title.TitleGUIResizeListener;
import jaco.mp3.player.MP3Player;
import view.MainWindow;

// Play music using https://sourceforge.net/projects/jacomp3player/
// See example here: https://sites.google.com/site/teachmemrxymon/java/how-to-use-mp3player-class

/**
 * The title screen GUI.
 * 
 * @author Sarah Lutteropp
 */
public class TitleScreenGUI extends JPanel {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = 4550458197071990473L;
	
	private JLabel titleImage;

	private JPanel buttonPane;

	private ArrayList<JButton> buttons;

	private JButton newFixedGameButton;
	private JButton newRandomGameButton;
	private JButton optionsButton;
	private JButton exitButton;
	
	private MP3Player player;

	public void loopMusic() {
		player.setRepeat(true);
		player.play();
	}
	
	public void stopMusic() {
		player.stop();
	}

	/**
	 * Create the title screen.
	 */
	public TitleScreenGUI(MainWindow mainWindow) {
		player = new MP3Player(new File("assets/DST-Omicron.mp3"));
		buttons = new ArrayList<JButton>();

		JPanel titlePane = new JPanel(new GridBagLayout());
		titleImage = new JLabel("Hashiwokakero");
		titleImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		titlePane.add(titleImage);

		JPanel leftBorder = new JPanel();
		JPanel rightBorder = new JPanel();
		JPanel bottomBorder = new JPanel();
		buttonPane = new JPanel();

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
	}
}