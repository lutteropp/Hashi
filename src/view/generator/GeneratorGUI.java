package view.generator;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

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
	private JLabel widthCaptionLabel;
	private JLabel heightCaptionLabel;
	private JLabel widthValueLabel;
	private JLabel heightValueLabel;
	
	/**
	 * Create the GUI for choosing the parameters for the random generator
	 * @param mainWindow The main window
	 */
	public GeneratorGUI(MainWindow mainWindow) {
		generateButton = new JButton("Generate Level");
		widthSlider = new JSlider(1, 100, 15);
		heightSlider = new JSlider(1, 100, 10);
		widthCaptionLabel = new JLabel("Width: ");
		heightCaptionLabel = new JLabel("Height: ");
		widthValueLabel = new JLabel("15");
		heightValueLabel = new JLabel("10");
		
		JPanel widthPanel = new JPanel(new FlowLayout());
		widthPanel.add(widthCaptionLabel);
		widthPanel.add(widthSlider);
		widthPanel.add(widthValueLabel);
		
		JPanel heightPanel = new JPanel(new FlowLayout());
		heightPanel.add(heightCaptionLabel);
		heightPanel.add(heightSlider);
		heightPanel.add(heightValueLabel);
		
		this.setLayout(new GridLayout(0, 1));
		this.add(widthPanel);
		this.add(heightPanel);
		this.add(generateButton);
		this.requestFocus();
	}
}
