package view.help;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import assets.GraphicalGameAssets;
import control.title.KeyInputUser;
import view.ApplicationWindow;
import view.ScalingImagePanel;
import view.ScalingLabel;

/**
 * The help instructions GUI.
 * 
 * @author Sarah Lutteropp
 */
public class HelpGUI extends JPanel {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = 4753107236165358560L;

	/**
	 * Create the GUI for showing the help instructions.
	 * 
	 * @param mainWindow
	 *            The main window
	 * @parem KeyInputUser The key listener to get back to the main window
	 */
	public HelpGUI(ApplicationWindow mainWindow, KeyInputUser keyInput) {
		addKeyListener(keyInput);
		this.setLayout(new GridLayout(0, 3));
		JPanel rulesPanel = new ScalingImagePanel(GraphicalGameAssets.getGameRulesImage());
		JPanel generatorSettingsPanel = new ScalingImagePanel(GraphicalGameAssets.getGeneratorSettingsImage());
		JPanel controlsPanel = new ScalingImagePanel(GraphicalGameAssets.getGameControlsImage());
		
		JPanel emptyPanel = new JPanel();
		JPanel creditsPanel = new JPanel(new GridLayout(0,1));
		creditsPanel.add(new ScalingLabel("Copyright: Sarah Lutteropp (2017)"));
		
		this.add(rulesPanel);
		this.add(generatorSettingsPanel);
		this.add(controlsPanel);
		this.add(emptyPanel);
		this.add(creditsPanel);
		
		Color background = new Color(90, 220, 220);
		this.setBackground(background);
		emptyPanel.setBackground(background);
		creditsPanel.setBackground(background);
	}
}
