package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * A panel that scales its background image based on the panel's size.
 * @author Sarah Lutteropp
 */
public class ScalingImagePanel extends JPanel {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = -6204916434736490048L;

	/** The image to be shown in rescaled form */
	private BufferedImage myImage;
	
	/**
	 * Create a new panel with the given image as automatically scaled background image.
	 * @param myImage The image to be shown in rescaled form
	 */
	public ScalingImagePanel(BufferedImage myImage) {
		super();
		this.myImage = myImage;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ScalingPainter.paint(g, this, myImage, 1.0);
	}
	
}
