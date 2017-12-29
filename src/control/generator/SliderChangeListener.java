package control.generator;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.ScalingLabel;

/**
 * A listener that updates the value label for a given JSlider.
 * 
 * @author Sarah Lutteropp
 */
public class SliderChangeListener implements ChangeListener {
	/**
	 * The label for showing the current slider value
	 */
	private JLabel label;
	/**
	 * The slider to listen to
	 */
	private JSlider slider;
	/**
	 * How to interpret the slider value. The displayed value will be the value of
	 * the slider times the scaling value.
	 */
	private double scaling;

	/**
	 * Create a listener for a given slider.
	 * 
	 * @param label
	 *            The label to show the value of the slider
	 * @param slider
	 *            The slider
	 * @param scaling
	 *            How to interpret the slider value. The displayed value will be the
	 *            value of the slider times the scaling value.
	 */
	public SliderChangeListener(ScalingLabel label, JSlider slider, double scaling) {
		this.label = label;
		this.slider = slider;
		this.scaling = scaling;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (scaling != 1) {
			label.setText(String.format("%3.2f", slider.getValue() * scaling));
		} else {
			label.setText(Integer.toString(slider.getValue()));
		}
	}
}
