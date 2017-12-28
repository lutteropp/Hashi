package view.generator;

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
		
	}
}
