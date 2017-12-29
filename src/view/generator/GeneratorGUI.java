package view.generator;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import control.generator.GenerateButtonListener;
import control.generator.SliderChangeListener;
import view.MainWindow;

/**
 * The GUI for setting the level generator parameters.
 * 
 * @author Sarah Lutteropp
 */
public class GeneratorGUI extends JPanel {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = 4753107236165358560L;

	private JButton generateButton;
	private JSlider widthSlider;
	private JSlider heightSlider;
	private JSlider fillingSlider;
	private JLabel widthCaptionLabel;
	private JLabel heightCaptionLabel;
	private JLabel fillingCaptionLabel;
	private JLabel widthValueLabel;
	private JLabel heightValueLabel;
	private JLabel fillingValueLabel;

	/**
	 * Create the GUI for choosing the parameters for the random generator
	 * 
	 * @param mainWindow
	 *            The main window
	 */
	public GeneratorGUI(MainWindow mainWindow) {
		generateButton = new JButton("Generate Level");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int maxWidth = (int) Math.round(screenSize.getWidth() / 100);
		int maxHeight = (int) Math.round(screenSize.getHeight() / 100);

		widthSlider = new JSlider(1, maxWidth, 15);
		heightSlider = new JSlider(1, maxHeight, 10);
		fillingSlider = new JSlider(1, 25, 15); // 25% is the maximum value because we don't want to place nodes
												// directly next to each other... this would look ugly
		widthCaptionLabel = new JLabel("Width: ");
		heightCaptionLabel = new JLabel("Height: ");
		fillingCaptionLabel = new JLabel("Filling: ");
		widthValueLabel = new JLabel("15");
		heightValueLabel = new JLabel("10");
		fillingValueLabel = new JLabel("0.15");
		widthSlider.addChangeListener(new SliderChangeListener(widthValueLabel, widthSlider, 1.0));
		heightSlider.addChangeListener(new SliderChangeListener(heightValueLabel, heightSlider, 1.0));
		double fillingScaling = 0.01;
		fillingSlider.addChangeListener(new SliderChangeListener(fillingValueLabel, fillingSlider, fillingScaling));
		generateButton.addActionListener(
				new GenerateButtonListener(mainWindow, widthSlider, heightSlider, fillingSlider, fillingScaling));

		JPanel widthPanel = new JPanel(new FlowLayout());
		widthPanel.add(widthCaptionLabel);
		widthPanel.add(widthSlider);
		widthPanel.add(widthValueLabel);

		JPanel heightPanel = new JPanel(new FlowLayout());
		heightPanel.add(heightCaptionLabel);
		heightPanel.add(heightSlider);
		heightPanel.add(heightValueLabel);

		JPanel fillingPanel = new JPanel(new FlowLayout());
		fillingPanel.add(fillingCaptionLabel);
		fillingPanel.add(fillingSlider);
		fillingPanel.add(fillingValueLabel);

		this.setLayout(new GridLayout(0, 1));
		this.add(widthPanel);
		this.add(heightPanel);
		this.add(fillingPanel);
		this.add(generateButton);
		this.requestFocus();
	}
}
