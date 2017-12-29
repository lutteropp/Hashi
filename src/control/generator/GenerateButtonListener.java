package control.generator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;

import view.MainWindow;

public class GenerateButtonListener implements ActionListener {

	private MainWindow mainWindow;
	private JSlider widthSlider;
	private JSlider heightSlider;
	private JSlider fillingSlider;
	private double fillingScaling;

	public GenerateButtonListener(MainWindow mainWindow, JSlider widthSlider, JSlider heightSlider,
			JSlider fillingSlider, double fillingScaling) {
		this.mainWindow = mainWindow;
		this.widthSlider = widthSlider;
		this.heightSlider = heightSlider;
		this.fillingSlider = fillingSlider;
		this.fillingScaling = fillingScaling;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		mainWindow.showRandomGameWindow(widthSlider.getValue(), heightSlider.getValue(), fillingSlider.getValue() * fillingScaling);
	}

}
