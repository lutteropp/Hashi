package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import model.base.Direction;

public class Assets {
	public static BufferedImage coldBorder, warmBorder, hotBorder;
	public static HashMap<Integer, BufferedImage> numbers;
	public static HashMap<Integer, BufferedImage> eastPins, westPins, northPins, southPins;
	public static HashMap<Integer, BufferedImage> eastPinsHighlighted, westPinsHighlighted, northPinsHighlighted, southPinsHighlighted;
	public static HashMap<Integer, BufferedImage> verticalWire, horizontalWire;
	public static HashMap<Integer, BufferedImage> verticalWireHighlighted, horizontalWireHighlighted;

	public static BufferedImage getPinImage(Direction dir, int thickness, boolean highlighted) {
		if (dir == Direction.EAST) {
			if (highlighted && thickness > 0) {
				return eastPinsHighlighted.get(thickness);
			} else {
				return eastPins.get(thickness);
			}
		} else if (dir == Direction.WEST) {
			if (highlighted && thickness > 0) {
				return westPinsHighlighted.get(thickness);
			} else {
				return westPins.get(thickness);
			}
		} else if (dir == Direction.NORTH) {
			if (highlighted && thickness > 0) {
				return northPinsHighlighted.get(thickness);
			} else {
				return northPins.get(thickness);
			}
		} else { // Direction.SOUTH
			if (highlighted && thickness > 0) {
				return southPinsHighlighted.get(thickness);
			} else {
				return southPins.get(thickness);
			}
		}
	}
	
	public static BufferedImage getWireImage(Direction dir, int thickness, boolean highlighted) {
		if (dir == Direction.EAST || dir == Direction.WEST) {
			if (highlighted) {
				return horizontalWireHighlighted.get(thickness);
			} else {
				return horizontalWire.get(thickness);
			}
		} else { // Direction.NORTH || Direction.SOUTH
			if (highlighted) {
				return verticalWireHighlighted.get(thickness);
			} else {
				return verticalWire.get(thickness);
			}
		}
	}
	
	public static BufferedImage getBorderImage(int degree, int goal) {
		if (degree < goal) {
			return coldBorder;
		} else if (degree > goal) {
			return hotBorder;
		} else {
			return warmBorder;
		}
	}
	
	public static BufferedImage getGoalImage(int goal) {
		return numbers.get(goal);
	}
	
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
		eastPinsHighlighted = new HashMap<Integer, BufferedImage>();
		eastPinsHighlighted.put(1, ImageIO.read(new File("assets/RightConnections_1_Highlighted.png")));
		eastPinsHighlighted.put(2, ImageIO.read(new File("assets/RightConnections_2_Highlighted.png")));
		westPinsHighlighted = new HashMap<Integer, BufferedImage>();
		westPinsHighlighted.put(1, ImageIO.read(new File("assets/LeftConnections_1_Highlighted.png")));
		westPinsHighlighted.put(2, ImageIO.read(new File("assets/LeftConnections_2_Highlighted.png")));
		northPinsHighlighted = new HashMap<Integer, BufferedImage>();
		northPinsHighlighted.put(1, ImageIO.read(new File("assets/BottomConnections_1_Highlighted.png")));
		northPinsHighlighted.put(2, ImageIO.read(new File("assets/BottomConnections_2_Highlighted.png")));
		southPinsHighlighted = new HashMap<Integer, BufferedImage>();
		southPinsHighlighted.put(1, ImageIO.read(new File("assets/TopConnections_1_Highlighted.png")));
		southPinsHighlighted.put(2, ImageIO.read(new File("assets/TopConnections_2_Highlighted.png")));
		verticalWire = new HashMap<Integer, BufferedImage>();
		verticalWire.put(1, ImageIO.read(new File("assets/VerticalConnection_1.png")));
		verticalWire.put(2, ImageIO.read(new File("assets/VerticalConnection_2.png")));
		horizontalWire = new HashMap<Integer, BufferedImage>();
		horizontalWire.put(1, ImageIO.read(new File("assets/HorizontalConnection_1.png")));
		horizontalWire.put(2, ImageIO.read(new File("assets/HorizontalConnection_2.png")));
		verticalWireHighlighted = new HashMap<Integer, BufferedImage>();
		verticalWireHighlighted.put(1, ImageIO.read(new File("assets/VerticalConnection_1_Highlighted.png")));
		verticalWireHighlighted.put(2, ImageIO.read(new File("assets/VerticalConnection_2_Highlighted.png")));
		horizontalWireHighlighted = new HashMap<Integer, BufferedImage>();
		horizontalWireHighlighted.put(1, ImageIO.read(new File("assets/HorizontalConnection_1_Highlighted.png")));
		horizontalWireHighlighted.put(2, ImageIO.read(new File("assets/HorizontalConnection_2_Highlighted.png")));
		
		numbers = new HashMap<Integer, BufferedImage>();
		numbers.put(1, ImageIO.read(new File("assets/1.png")));
		numbers.put(2, ImageIO.read(new File("assets/2.png")));
		numbers.put(3, ImageIO.read(new File("assets/3.png")));
		numbers.put(4, ImageIO.read(new File("assets/4.png")));
		numbers.put(5, ImageIO.read(new File("assets/5.png")));
		numbers.put(6, ImageIO.read(new File("assets/6.png")));
		numbers.put(7, ImageIO.read(new File("assets/7.png")));
		numbers.put(8, ImageIO.read(new File("assets/8.png")));
	}
}
