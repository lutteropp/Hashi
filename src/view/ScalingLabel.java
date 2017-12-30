package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

/**
 * A label that maximizes its font size based on the label's size.
 * @author Sarah Lutteropp
 *
 */
public class ScalingLabel extends JLabel {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = 3189235547273253630L;
	/** The caption of the label */
	private String text;
	/** Is the caption in bold text? */
	private boolean bold;

	/**
	 * Create a new scaling label.
	 * @param text The caption of the label
	 */
	public ScalingLabel(String text) {
		this.text = text;
		this.bold = false;
	}

	/**
	 * Set the boldness state of the font.
	 * @param bold Whether the font is bold or not.
	 */
	public void setBoldFont(boolean bold) {
		this.bold = bold;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage labelImage = ScalingPainter.createTextImage(this, text, bold, true);
		ScalingPainter.paint(g, this, labelImage, 1.0);
	}

	@Override
	public void setText(String text) {
		this.text = text;
		this.repaint();
	}
}
