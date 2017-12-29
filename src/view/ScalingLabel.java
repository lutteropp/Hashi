package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class ScalingLabel extends JLabel {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = 3189235547273253630L;
	private String text;
	private boolean bold;

	public ScalingLabel(String text) {
		this.text = text;
		this.bold = false;
	}

	public void setBoldFont(boolean bold) {
		this.bold = bold;
	}

	private BufferedImage createButtonImage() {
		Font font = getFont();
		font = font.deriveFont(150f); // make the font very large to avoid rescaling effects
		if (this.bold) {
			font = font.deriveFont(Font.BOLD);
		}
		FontMetrics metrics = getFontMetrics(font);
		int width = metrics.stringWidth(text);
		int height = metrics.getHeight();

		BufferedImage buttonImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buttonImage.getGraphics();
		g.setFont(font);
		if (this.isEnabled()) {
			g.setColor(Color.BLACK);
		} else {
			g.setColor(Color.GRAY);
		}
		g.drawString(text, 0, metrics.getAscent());
		return buttonImage;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		BufferedImage buttonImage = createButtonImage();
		double originalRatio = (double) buttonImage.getWidth() / buttonImage.getHeight();

		int maxWidth = this.getWidth();
		int maxHeight = this.getHeight();

		int height = maxHeight;
		int width = (int) Math.floor((double) maxHeight * originalRatio);
		if (width > maxWidth) {
			width = maxWidth;
			height = (int) Math.floor((double) maxWidth / originalRatio);
		}
		g.drawImage(buttonImage, (this.getWidth() - width) / 2, (this.getHeight() - height) / 2, width, height, null);
	}

	@Override
	public void setText(String text) {
		this.text = text;
		this.repaint();
	}
}
