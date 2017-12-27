package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Assets {
	public static BufferedImage coldBorder, warmBorder, hotBorder;
	public static HashMap<Integer, BufferedImage> goal;
	public static HashMap<Integer, BufferedImage> eastPins, westPins, northPins, southPins;

	public static void loadAssets() throws IOException {
		coldBorder = ImageIO.read(new File("assets/ColdBorder.png"));
		warmBorder = ImageIO.read(new File("assets/WarmBorder.png"));
		hotBorder = ImageIO.read(new File("assets/HotBorder.png"));
		eastPins = new HashMap<Integer, BufferedImage>();
		eastPins.put(0, ImageIO.read(new File("assets/RightConnections_0.png")));
		eastPins.put(1, ImageIO.read(new File("assets/RightConnections_1.png")));
		eastPins.put(2, ImageIO.read(new File("assets/RightConnections_2.png")));
		westPins = new HashMap<Integer, BufferedImage>();
		westPins.put(0, ImageIO.read(new File("assets/LeftConnections_0.png")));
		westPins.put(1, ImageIO.read(new File("assets/LeftConnections_1.png")));
		westPins.put(2, ImageIO.read(new File("assets/LeftConnections_2.png")));
		northPins = new HashMap<Integer, BufferedImage>();
		northPins.put(0, ImageIO.read(new File("assets/BottomConnections_0.png")));
		northPins.put(1, ImageIO.read(new File("assets/BottomConnections_1.png")));
		northPins.put(2, ImageIO.read(new File("assets/BottomConnections_2.png")));
		southPins = new HashMap<Integer, BufferedImage>();
		southPins.put(0, ImageIO.read(new File("assets/TopConnections_0.png")));
		southPins.put(1, ImageIO.read(new File("assets/TopConnections_1.png")));
		southPins.put(2, ImageIO.read(new File("assets/TopConnections_2.png")));
		
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
