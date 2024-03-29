package assets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import model.base.Direction;

/**
 * A class for managing the graphical assets of the Hashiwokakero game. Most
 * graphics are drawn by myself. Only exceptions: - The lucky cat image is from
 * https://en.wikipedia.org/wiki/File:Manekineko1003.jpg - The big cat image is
 * from https://unsplash.com/photos/R26-yUBSgC0 Both cat images are distributed
 * under a Creative Commons License.
 * 
 * @author Sarah Lutteropp
 */
public class GraphicalGameAssets {
	/** The different node bodies. */
	private static BufferedImage coldBody, warmBody, hotBody;
	/** The different node goals. These are numbers from 1 to 8. */
	private static HashMap<Integer, BufferedImage> numbers;
	/**
	 * The different node pins, non-highlighted version. There are 1-pin images and
	 * 2-pin images.
	 */
	private static HashMap<Integer, BufferedImage> eastPins, westPins, northPins, southPins;
	/**
	 * The different node pins, highlighted version. There are 1-pin images and
	 * 2-pin images.
	 */
	private static HashMap<Integer, BufferedImage> eastPinsHighlighted, westPinsHighlighted, northPinsHighlighted,
			southPinsHighlighted;
	/**
	 * The different wires, non-highlighted version. There are 1-wire images and
	 * 2-wire images.
	 */
	private static HashMap<Integer, BufferedImage> verticalWire, horizontalWire;
	/**
	 * The different wires, highlighted version. There are 1-wire images and 2-wire
	 * images.
	 */
	private static HashMap<Integer, BufferedImage> verticalWireHighlighted, horizontalWireHighlighted;

	/**
	 * The game rules explained.
	 */
	private static BufferedImage gameRules;
	/**
	 * The game controls explained.
	 */
	private static BufferedImage gameControls;
	/**
	 * The generator settings explained.
	 */
	private static BufferedImage generatorSettings;
	/**
	 * A lucky cat.
	 */
	private static BufferedImage luckyCat;
	/**
	 * A big cat.
	 */
	private static BufferedImage bigCat;

	/**
	 * Get the image for the node's pins in the given direction.
	 * 
	 * @param dir
	 *            The direction. Can be EAST, WEST, NORTH, or SOUTH.
	 * @param thickness
	 *            The thickness of the link in this direction from the node.
	 * @param highlighted
	 *            Is the link attached to the node in this direction highlighted?
	 * @return The image for the node's pins in the given direction.
	 */
	public static BufferedImage getPinImage(final Direction dir, final int thickness, final boolean highlighted) {
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

	/**
	 * Get the image representing a connection wire.
	 * 
	 * @param dir
	 *            The direction of the wire. Can be EAST, WEST, SOUTH, or NORTH. But
	 *            we only care whether it is horizontal or vertical.
	 * @param thickness
	 *            The thickness of the link.
	 * @param highlighted
	 *            Is the link highlighted?
	 * @return An image representing a connection wire.
	 */
	public static BufferedImage getWireImage(final Direction dir, final int thickness, final boolean highlighted) {
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

	/**
	 * Get the body image of a node.
	 * 
	 * @param degree
	 *            The current degree of the node. Can be 0, 1, 2, 3, 4, 5, 6, 7, or
	 *            8.
	 * @param goal
	 *            The goal of the node. Can be 1, 2, 3, 4, 5, 6, 7, or 8.
	 * @return The image representing the body of the node.
	 */
	public static BufferedImage getBodyImage(final int degree, final int goal) {
		if (degree < 0 || degree > 8) {
			throw new IllegalArgumentException("Invalid degree");
		}
		if (goal < 1 || goal > 8) {
			throw new IllegalArgumentException("Invalid goal");
		}
		if (degree < goal) {
			return coldBody;
		} else if (degree > goal) {
			return hotBody;
		} else {
			return warmBody;
		}
	}

	/**
	 * Get the goal image.
	 * 
	 * @param goal
	 *            The goal of the node. Can be 1, 2, 3, 4, 5, 6, 7, or 8.
	 * @return The image representing the nodes goal.
	 */
	public static BufferedImage getGoalImage(final int goal) {
		return numbers.get(goal);
	}

	/**
	 * @return The image explaining the game rules.
	 */
	public static BufferedImage getGameRulesImage() {
		return gameRules;
	}

	/**
	 * @return The image explaining the game controls.
	 */
	public static BufferedImage getGameControlsImage() {
		return gameControls;
	}

	/**
	 * @return The image explaining the generator settings.
	 */
	public static BufferedImage getGeneratorSettingsImage() {
		return generatorSettings;
	}

	/**
	 * @return An image of a lucky cat.
	 */
	public static BufferedImage getLuckyCatImage() {
		return luckyCat;
	}

	/**
	 * @return A big cat image.
	 */
	public static BufferedImage getBigCatImage() {
		return bigCat;
	}

	/**
	 * Load an image from the assets
	 * @param path The path to the image
	 * @return The image
	 * @throws IOException
	 */
	private static BufferedImage load(String path) throws IOException {
		return ImageIO.read(ClassLoader.getSystemResourceAsStream("assets/" + path));
	}

	/**
	 * Load all the image files.
	 * 
	 * @throws IOException
	 */
	public static void loadAssets() throws IOException {
		coldBody = load("ColdBorder.png");
		warmBody = load("WarmBorder.png");
		hotBody = load("HotBorder.png");
		eastPins = new HashMap<Integer, BufferedImage>();
		eastPins.put(0, load("RightConnections_0.png"));
		eastPins.put(1, load("RightConnections_1.png"));
		eastPins.put(2, load("RightConnections_2.png"));
		westPins = new HashMap<Integer, BufferedImage>();
		westPins.put(0, load("LeftConnections_0.png"));
		westPins.put(1, load("LeftConnections_1.png"));
		westPins.put(2, load("LeftConnections_2.png"));
		northPins = new HashMap<Integer, BufferedImage>();
		northPins.put(0, load("BottomConnections_0.png"));
		northPins.put(1, load("BottomConnections_1.png"));
		northPins.put(2, load("BottomConnections_2.png"));
		southPins = new HashMap<Integer, BufferedImage>();
		southPins.put(0, load("TopConnections_0.png"));
		southPins.put(1, load("TopConnections_1.png"));
		southPins.put(2, load("TopConnections_2.png"));
		eastPinsHighlighted = new HashMap<Integer, BufferedImage>();
		eastPinsHighlighted.put(1, load("RightConnections_1_Highlighted.png"));
		eastPinsHighlighted.put(2, load("RightConnections_2_Highlighted.png"));
		westPinsHighlighted = new HashMap<Integer, BufferedImage>();
		westPinsHighlighted.put(1, load("LeftConnections_1_Highlighted.png"));
		westPinsHighlighted.put(2, load("LeftConnections_2_Highlighted.png"));
		northPinsHighlighted = new HashMap<Integer, BufferedImage>();
		northPinsHighlighted.put(1, load("BottomConnections_1_Highlighted.png"));
		northPinsHighlighted.put(2, load("BottomConnections_2_Highlighted.png"));
		southPinsHighlighted = new HashMap<Integer, BufferedImage>();
		southPinsHighlighted.put(1, load("TopConnections_1_Highlighted.png"));
		southPinsHighlighted.put(2, load("TopConnections_2_Highlighted.png"));
		verticalWire = new HashMap<Integer, BufferedImage>();
		verticalWire.put(1, load("VerticalConnection_1.png"));
		verticalWire.put(2, load("VerticalConnection_2.png"));
		horizontalWire = new HashMap<Integer, BufferedImage>();
		horizontalWire.put(1, load("HorizontalConnection_1.png"));
		horizontalWire.put(2, load("HorizontalConnection_2.png"));
		verticalWireHighlighted = new HashMap<Integer, BufferedImage>();
		verticalWireHighlighted.put(1, load("VerticalConnection_1_Highlighted.png"));
		verticalWireHighlighted.put(2, load("VerticalConnection_2_Highlighted.png"));
		horizontalWireHighlighted = new HashMap<Integer, BufferedImage>();
		horizontalWireHighlighted.put(1, load("HorizontalConnection_1_Highlighted.png"));
		horizontalWireHighlighted.put(2, load("HorizontalConnection_2_Highlighted.png"));

		numbers = new HashMap<Integer, BufferedImage>();
		numbers.put(1, load("1.png"));
		numbers.put(2, load("2.png"));
		numbers.put(3, load("3.png"));
		numbers.put(4, load("4.png"));
		numbers.put(5, load("5.png"));
		numbers.put(6, load("6.png"));
		numbers.put(7, load("7.png"));
		numbers.put(8, load("8.png"));

		gameRules = load("GameRules.png");
		gameControls = load("GameControls.png");
		generatorSettings = load("GeneratorSettings.png");
		luckyCat = load("cat.jpg");
		bigCat = load("big_cat.jpg");
	}
}
