package view.title;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jaco.mp3.player.MP3Player;

// Play music using https://sourceforge.net/projects/jacomp3player/
// See example here: https://sites.google.com/site/teachmemrxymon/java/how-to-use-mp3player-class

/**
 * The title screen GUI. Modified from https://stackoverflow.com/a/21226098
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
	public TitleScreenGUI() {
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
		buttons.add(newFixedGameButton);
		newRandomGameButton = new JButton("New random game");
		buttons.add(newRandomGameButton);
		optionsButton = new JButton("Options");
		buttons.add(optionsButton);
		exitButton = new JButton("Exit");
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

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				for (JButton button : buttons) {
					button.setFont(button.getFont()
							.deriveFont((float) (Math.min(button.getHeight() / 2, button.getWidth()) / 2)));
				}
				titleImage.setFont(buttons.get(0).getFont());
			}
		});
	}
}