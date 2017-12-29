package view.generator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

import control.generator.GenerateButtonListener;
import control.generator.SliderChangeListener;
import control.title.KeyInputUser;
import view.ApplicationWindow;
import view.ScalingButton;
import view.ScalingLabel;

/**
 * The GUI for setting the level generator parameters.
 * 
 * @author Sarah Lutteropp
 */
public class GeneratorGUI extends JPanel {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = 4753107236165358560L;

	/** The button for starting the level generation */
	private JButton generateButton;
	/** The slider for choosing the width of the generated game grid */
	private JSlider widthSlider;
	/** The slider for choosing the height of the generated game grid */
	private JSlider heightSlider;
	/**
	 * The slider for choosing which percentage of the grid should be filled with
	 * nodes
	 */
	private JSlider fillingSlider;
	/**
	 * The slider for choosing the probability of creating a new node (in contrast
	 * to connecting two already existing nodes) during level generation
	 */
	private JSlider outerExtensionSlider;
	/** Value of the width slider to be shown */
	private ScalingLabel widthValueLabel;
	/** Value of the height slider to be shown */
	private ScalingLabel heightValueLabel;
	/** Value of the filling slider to be shown */
	private ScalingLabel fillingValueLabel;
	/** Value of the outer extension slider to be shown */
	private ScalingLabel outerExtensionValueLabel;

	/**
	 * Create the GUI for choosing the parameters for the random generator
	 * 
	 * @param mainWindow
	 *            The main window
	 * @parem KeyInputUser The key listener to get back to the main window
	 */
	public GeneratorGUI(ApplicationWindow mainWindow, KeyInputUser keyListener) {
		generateButton = new ScalingButton("Generate Level");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int maxWidth = (int) Math.floor(screenSize.getWidth() / 100) - 1;
		int maxHeight = (int) Math.floor(screenSize.getHeight() / 100) - 1;

		widthSlider = new JSlider(3, maxWidth, maxWidth / 2);
		heightSlider = new JSlider(3, maxHeight, maxHeight / 2);
		fillingSlider = new JSlider(15, 25, 20); // 25% is the maximum value because we don't want to place nodes
												// directly next to each other... this would look ugly
		outerExtensionSlider = new JSlider(1, 100, 55);
		
		widthSlider.addKeyListener(keyListener);
		heightSlider.addKeyListener(keyListener);
		fillingSlider.addKeyListener(keyListener);
		outerExtensionSlider.addKeyListener(keyListener);
		this.addKeyListener(keyListener);
		
		ScalingLabel widthCaptionLabel =   new ScalingLabel("  Width    ");
		ScalingLabel heightCaptionLabel =  new ScalingLabel("  Height   ");
		ScalingLabel fillingCaptionLabel = new ScalingLabel("  Filling  ");
		
		JPanel outerExtensionCaptionPanel = new JPanel(new GridLayout(3, 1));
		outerExtensionCaptionPanel.add(    new ScalingLabel("   Outer   "));
		outerExtensionCaptionPanel.add(    new ScalingLabel(" extension "));
		outerExtensionCaptionPanel.add(    new ScalingLabel("probability"));
		
		//ScalingLabel outerExtensionCaptionLabel = new ScalingLabel("Outer extension probability: ");
		widthValueLabel = new ScalingLabel("15");
		heightValueLabel = new ScalingLabel("10");
		fillingValueLabel = new ScalingLabel("0.20");
		outerExtensionValueLabel = new ScalingLabel("0.55");
		widthSlider.addChangeListener(new SliderChangeListener(widthValueLabel, widthSlider, 1.0));
		heightSlider.addChangeListener(new SliderChangeListener(heightValueLabel, heightSlider, 1.0));
		double doubleScaling = 0.01;
		fillingSlider.addChangeListener(new SliderChangeListener(fillingValueLabel, fillingSlider, doubleScaling));
		outerExtensionSlider.addChangeListener(
				new SliderChangeListener(outerExtensionValueLabel, outerExtensionSlider, doubleScaling));
		generateButton.addActionListener(
				new GenerateButtonListener(mainWindow, widthSlider, heightSlider, fillingSlider, outerExtensionSlider));
		
		JPanel topBorder = new JPanel();
		JPanel leftBorder = new JPanel();
		JPanel rightBorder = new JPanel();
		JPanel bottomBorder = new JPanel();
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.weighty = 0.1;
		c.gridwidth = 5;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;
		add(topBorder, c);
		c = new GridBagConstraints();
		c.gridheight = 5;
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.1;
		add(leftBorder, c);
		c = new GridBagConstraints();
		c.gridheight = 5;
		c.gridx = 4;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.1;
		add(rightBorder, c);
		c = new GridBagConstraints();
		c.gridwidth = 5;
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.1;
		add(bottomBorder, c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.1;
		c.fill = GridBagConstraints.BOTH;
		add(widthCaptionLabel, c);
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.5;
		c.weighty = 0.3;
		c.fill = GridBagConstraints.BOTH;
		add(widthSlider, c);
		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.weightx = 0.1;
		c.fill = GridBagConstraints.BOTH;
		add(widthValueLabel, c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0.1;
		c.fill = GridBagConstraints.BOTH;
		add(heightCaptionLabel, c);
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.weightx = 0.5;
		c.weighty = 0.3;
		c.fill = GridBagConstraints.BOTH;
		add(heightSlider, c);
		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 2;
		c.weightx = 0.1;
		c.fill = GridBagConstraints.BOTH;
		add(heightValueLabel, c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 0.1;
		c.fill = GridBagConstraints.BOTH;
		add(fillingCaptionLabel, c);
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.weightx = 0.5;
		c.weighty = 0.3;
		c.fill = GridBagConstraints.BOTH;
		add(fillingSlider, c);
		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 3;
		c.weightx = 0.1;
		c.fill = GridBagConstraints.BOTH;
		add(fillingValueLabel, c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 0.1;
		c.fill = GridBagConstraints.BOTH;
		add(outerExtensionCaptionPanel, c);
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 4;
		c.weightx = 0.5;
		c.weighty = 0.3;
		c.fill = GridBagConstraints.BOTH;
		add(outerExtensionSlider, c);
		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 4;
		c.weightx = 0.1;
		c.fill = GridBagConstraints.BOTH;
		add(outerExtensionValueLabel, c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 3;
		c.weighty = 0.4;
		c.fill = GridBagConstraints.BOTH;
		add(generateButton, c);
		
		topBorder.setLayout(new GridLayout());
		topBorder.add(new ScalingLabel("New random game"));
		
		Color background = new Color(90, 220, 220);
		topBorder.setBackground(background);
		leftBorder.setBackground(background);
		rightBorder.setBackground(background);
		bottomBorder.setBackground(background);
		
		this.requestFocus();
	}
}
