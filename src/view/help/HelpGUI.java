package view.help;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import control.title.KeyInputUser;
import view.ApplicationWindow;
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
	 * Create the GUI for showing the game instructions.
	 * 
	 * @param mainWindow
	 *            The main window
	 * @parem KeyInputUser The key listener to get back to the main window
	 */
	public HelpGUI(ApplicationWindow mainWindow, KeyInputUser keyInput) {
		addKeyListener(keyInput);
		this.setLayout(new GridLayout(0, 3));
		JPanel rulesPanel = new JPanel(new GridLayout(0, 1));
		rulesPanel.add(new ScalingLabel("Rules:"));
		rulesPanel.add(new ScalingLabel("1. All nodes must form a connected graph."));
		rulesPanel.add(new ScalingLabel(
				"2. Each node must have as exactly as many connections to other nodes as specified by its number."));
		rulesPanel.add(new ScalingLabel("3. Connection wires are straight-line only and can not cross each other."));

		JPanel generatorSettingsPanel = new JPanel(new GridLayout(0, 1));
		generatorSettingsPanel.add(new ScalingLabel("Generator settings:"));
		generatorSettingsPanel.add(new ScalingLabel("Width: The width of the generated game grid."));
		generatorSettingsPanel.add(new ScalingLabel("Height: The height of the generated game grid."));
		generatorSettingsPanel.add(new ScalingLabel("Filling: Percentage of the grid area to be filled with nodes."));
		generatorSettingsPanel.add(new ScalingLabel(
				"Outer extension probability: Probability to create a new node during the generation process, as opposed to connecting two already existing nodes."));
		
		JPanel controlsPanel = new JPanel(new GridLayout(0, 1));
		controlsPanel.add(new ScalingLabel("Controls:"));
		controlsPanel.add(new ScalingLabel("Press the ESC key to return to the main menu."));
		controlsPanel.add(new ScalingLabel("Double-click on a node to fully connect it to all reachable neighbors."));
		controlsPanel.add(new ScalingLabel("Click on a connection wire to remove it."));
		controlsPanel.add(new ScalingLabel("To connect two nodes, first click on one node, then on the other node."));
		
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
	}
}
