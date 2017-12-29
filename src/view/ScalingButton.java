package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class ScalingButton extends JButton {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = -4009077360144092743L;

	private BufferedImage buttonImage;
	private double originalRatio;

	public ScalingButton(BufferedImage buttonImage) {
		super();
		this.buttonImage = buttonImage;
		this.originalRatio = (double) buttonImage.getWidth() / buttonImage.getHeight();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int maxWidth = this.getWidth() / 2;
		int maxHeight = this.getHeight() / 2;
		
		int height = maxHeight;
		int width = (int) Math.floor((double) maxHeight * originalRatio);
		if (width > maxWidth) {
			width = maxWidth;
			height = (int) Math.floor((double) maxWidth / originalRatio);
		}
		g.drawImage(buttonImage, (this.getWidth() - width) / 2, (this.getHeight() - height) / 2, width, height, null);
	}
}
