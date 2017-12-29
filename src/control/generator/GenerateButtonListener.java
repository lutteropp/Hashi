package control.generator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;

import view.MainWindow;

/**
 * Listener for the "generate" button in the level generator GUI.
 * 
 * @author Sarah Lutteropp
 */
public class GenerateButtonListener implements ActionListener {
	/**
	 * The main window of the program
	 */
	private MainWindow mainWindow;
	/**
	 * The slider for the width of the grid
	 */
	private JSlider widthSlider;
	/**
	 * The slider for the height of the grid
	 */
	private JSlider heightSlider;
	/**
	 * The slider for the percentage of the grid to be filled with nodes
	 */
	private JSlider fillingSlider;
	/**
	 * The slider for outer link extension probability during level generation
	 */
	private JSlider outerExtensionSlider;

	/**
	 * Create a listener for the "generate" button.
	 * 
	 * @param mainWindow
	 *            The main window of the program
	 * @param widthSlider
	 *            The slider for the width of the grid
	 * @param heightSlider
	 *            The slider for the height of the grid
	 * @param fillingSlider
	 *            The slider for the percentage of the grid to be filled with nodes
	 * @param outerExtensionSlider
	 *            The slider for outer link extension probability during level
	 *            generation
	 */
	public GenerateButtonListener(MainWindow mainWindow, JSlider widthSlider, JSlider heightSlider,
			JSlider fillingSlider, JSlider outerExtensionSlider) {
		this.mainWindow = mainWindow;
		this.widthSlider = widthSlider;
		this.heightSlider = heightSlider;
		this.fillingSlider = fillingSlider;
		this.outerExtensionSlider = outerExtensionSlider;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		mainWindow.showRandomGameWindow(widthSlider.getValue(), heightSlider.getValue(),
				fillingSlider.getValue() * 0.01, outerExtensionSlider.getValue() * 0.01);
	}

}
