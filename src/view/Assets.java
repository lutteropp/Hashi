package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Assets {
	public static BufferedImage coldBorder, warmBorder, hotBorder;
	public static BufferedImage eastPins, westPins, northPins, southPins;
	public static HashMap<Integer, BufferedImage> goal;

	public static void loadAssets() throws IOException {
		coldBorder = ImageIO.read(new File("assets/ColdBorder.png"));
		warmBorder = ImageIO.read(new File("assets/WarmBorder.png"));
		hotBorder = ImageIO.read(new File("assets/HotBorder.png"));
		eastPins = ImageIO.read(new File("assets/RightConnections.png"));
		westPins = ImageIO.read(new File("assets/LeftConnections.png"));
		northPins = ImageIO.read(new File("assets/BottomConnections.png"));
		southPins = ImageIO.read(new File("assets/TopConnections.png"));
		goal = new HashMap<Integer, BufferedImage>();
		goal.put(1, ImageIO.read(new File("assets/1.png")));
		goal.put(2, ImageIO.read(new File("assets/2.png")));
		goal.put(3, ImageIO.read(new File("assets/3.png")));
		goal.put(4, ImageIO.read(new File("assets/4.png")));
		goal.put(5, ImageIO.read(new File("assets/5.png")));
		goal.put(6, ImageIO.read(new File("assets/6.png")));
		goal.put(7, ImageIO.read(new File("assets/7.png")));
		goal.put(8, ImageIO.read(new File("assets/8.png")));
	}
}
