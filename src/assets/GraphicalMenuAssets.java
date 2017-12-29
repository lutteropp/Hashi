package assets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A class for managing the graphical assets of the menu screens (title screen,
 * generate new level screen). The graphics are drawn by myself.
 * 
 * @author Sarah Lutteropp
 */
public class GraphicalMenuAssets {
	public static BufferedImage generatorButtonImage;
	
	public static void loadAssets() throws IOException {
		generatorButtonImage = ImageIO.read(new File("assets/Generate.png"));
	}
}
