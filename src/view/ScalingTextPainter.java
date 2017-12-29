package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

/**
 * A class that paints a text on a component, using a font size as large as
 * possible.
 * 
 * @author Sarah Lutteropp
 *
 */
public class ScalingTextPainter {

	/**
	 * Create an image containing the text in a very large font size.
	 * 
	 * @param component
	 *            The component on which the text is written
	 * @param text
	 *            The text
	 * @param bold
	 *            Is the font bold or not?
	 * @param enabled
	 *            Is the component enabled or not?
	 * @return An image containing the text in a very large font size.
	 */
	private static BufferedImage createButtonImage(JComponent component, String text, boolean bold, boolean enabled) {
		Font font = component.getFont();
		font = font.deriveFont(350f); // make the font very large to avoid rescaling effects
		if (bold) {
			font = font.deriveFont(Font.BOLD);
		}
		FontMetrics metrics = component.getFontMetrics(font);
		int width = metrics.stringWidth(text);
		int height = metrics.getHeight();

		BufferedImage buttonImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buttonImage.getGraphics();
		g.setFont(font);
		if (enabled) {
			g.setColor(Color.BLACK);
		} else {
			g.setColor(Color.GRAY);
		}
		g.drawString(text, 0, metrics.getAscent());
		return buttonImage;
	}

	/**
	 * Create an image containing the text resized to its maximum size and paint it
	 * on the given component's graphics.
	 * 
	 * @param g
	 *            The graphics object of the component
	 * @param component
	 *            The component
	 * @param text
	 *            The text
	 * @param bold
	 *            Is the font bold?
	 * @param enabled
	 *            Is the component enabled?
	 * @param heightUsage
	 *            Percentage of the component's height to be used for the text. Has
	 *            to be greater than 0 and <= 1.
	 */
	public static void paint(Graphics g, JComponent component, String text, boolean bold, boolean enabled,
			double heightUsage) {
		if (heightUsage <= 0 || heightUsage > 1) {
			throw new IllegalArgumentException("Height usage must lie in the interval (0,1]");
		}

		BufferedImage buttonImage = createButtonImage(component, text, bold, enabled);
		double originalRatio = (double) buttonImage.getWidth() / buttonImage.getHeight();

		int maxWidth = component.getWidth();
		int maxHeight = (int) Math.round(component.getHeight() * heightUsage);

		int height = maxHeight;
		int width = (int) Math.floor((double) maxHeight * originalRatio);
		if (width > maxWidth) {
			width = maxWidth;
			height = (int) Math.floor((double) maxWidth / originalRatio);
		}
		g.drawImage(buttonImage, (component.getWidth() - width) / 2, (component.getHeight() - height) / 2, width,
				height, null);
	}
}
