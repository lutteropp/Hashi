package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import control.title.OrangeButtonListener;

/**
 * A button that maximizes its font size based on the button's size.
 * 
 * @author Sarah Lutteropp
 *
 */
public class ScalingButton extends JButton {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = -4009077360144092743L;
	/** The caption of the button */
	private String text;

	/**
	 * Create a new scaling button.
	 * 
	 * @param text
	 *            The caption of the button
	 */
	public ScalingButton(String text) {
		super();
		this.text = text;
		this.addMouseListener(new OrangeButtonListener(this));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage buttonImage = ScalingPainter.createTextImage(this, text, false, this.isEnabled());
		ScalingPainter.paint(g, this, buttonImage, 0.5);
	}
}
