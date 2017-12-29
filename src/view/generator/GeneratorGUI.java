package view.generator;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import control.generator.GenerateButtonListener;
import control.generator.SliderChangeListener;
import view.ApplicationWindow;
import view.ScalingButton;

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
	/** Caption of the width slider */
	private JLabel widthCaptionLabel;
	/** Caption of the height slider */
	private JLabel heightCaptionLabel;
	/** Caption of the filling slider */
	private JLabel fillingCaptionLabel;
	/** Caption of the outer extension slider */
	private JLabel outerExtensionCaptionLabel;
	/** Value of the width slider to be shown */
	private JLabel widthValueLabel;
	/** Value of the height slider to be shown */
	private JLabel heightValueLabel;
	/** Value of the filling slider to be shown */
	private JLabel fillingValueLabel;
	/** Value of the outer extension slider to be shown */
	private JLabel outerExtensionValueLabel;

	/**
	 * Create the GUI for choosing the parameters for the random generator
	 * 
	 * @param mainWindow
	 *            The main window
	 */
	public GeneratorGUI(ApplicationWindow mainWindow) {
		generateButton = new ScalingButton("Generate Level");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int maxWidth = (int) Math.round(screenSize.getWidth() / 100);
		int maxHeight = (int) Math.round(screenSize.getHeight() / 100);

		widthSlider = new JSlider(3, maxWidth, 15);
		heightSlider = new JSlider(3, maxHeight, 10);
		fillingSlider = new JSlider(1, 25, 20); // 25% is the maximum value because we don't want to place nodes
												// directly next to each other... this would look ugly
		outerExtensionSlider = new JSlider(1, 100, 55);
		widthCaptionLabel = new JLabel("Width: ");
		heightCaptionLabel = new JLabel("Height: ");
		fillingCaptionLabel = new JLabel("Filling: ");
		outerExtensionCaptionLabel = new JLabel("Outer extension probability: ");
		widthValueLabel = new JLabel("15");
		heightValueLabel = new JLabel("10");
		fillingValueLabel = new JLabel("0.20");
		outerExtensionValueLabel = new JLabel("0.55");
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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.weighty = 0.1;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;
		add(topBorder, c);
		c = new GridBagConstraints();
		c.gridheight = 5;
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		add(leftBorder, c);
		c = new GridBagConstraints();
		c.gridheight = 5;
		c.gridx = 4;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		add(rightBorder, c);
		c = new GridBagConstraints();
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.1;
		add(bottomBorder, c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
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
		c.fill = GridBagConstraints.BOTH;
		add(widthValueLabel, c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
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
		c.fill = GridBagConstraints.BOTH;
		add(heightValueLabel, c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
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
		c.fill = GridBagConstraints.BOTH;
		add(fillingValueLabel, c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		add(outerExtensionCaptionLabel, c);
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
		c.fill = GridBagConstraints.BOTH;
		add(outerExtensionValueLabel, c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 3;
		c.weighty = 0.4;
		c.fill = GridBagConstraints.BOTH;
		add(generateButton, c);
		
		this.requestFocus();
	}
}
