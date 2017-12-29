package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class ScalingButton extends JButton {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = -4009077360144092743L;

	private String text;
	private int height;
	private int width;
	
	public int getScaledFontSize () {
		return getFontSizeGivenHeight(height);
	}
	
	private int getFontSizeGivenHeight(int height) {
		Font font = getFont();
		for (int i = 1; i < 200; ++i) {
			Font f = font.deriveFont((float) i);
			FontMetrics metrics = getFontMetrics(f);
			if (metrics.getHeight() >= height) {
				System.out.println("FONT SIZE IS: " + Integer.toString(i));
				return i;
			}
		}
		throw new RuntimeException("Could not find font size");
	}
	
	public BufferedImage createButtonImage(String text) {
		Font font = getFont();
		font = font.deriveFont(150f); // make the font very large to avoid rescaling effects
		
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

	public ScalingButton(String text) {
		super();
		this.text = text;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		BufferedImage buttonImage = createButtonImage(text);
		double originalRatio = (double) buttonImage.getWidth() / buttonImage.getHeight();
		
		int maxWidth = this.getWidth() / 2;
		int maxHeight = this.getHeight() / 3;
		
		height = maxHeight;
		width = (int) Math.floor((double) maxHeight * originalRatio);
		if (width > maxWidth) {
			width = maxWidth;
			height = (int) Math.floor((double) maxWidth / originalRatio);
		}
		g.drawImage(buttonImage, (this.getWidth() - width) / 2, (this.getHeight() - height) / 2, width, height, null);
	}
}
