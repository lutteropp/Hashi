package control.generator;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderChangeListener implements ChangeListener {
	private JLabel label;
	private JSlider slider;
	private double scaling;
	
	public SliderChangeListener(JLabel label, JSlider slider, double scaling) {
		this.label = label;
		this.slider = slider;
		this.scaling = scaling;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (scaling != 1) {
			label.setText(Double.toString(slider.getValue() * scaling));
		} else {
			label.setText(Integer.toString(slider.getValue()));
		}
	}
}
