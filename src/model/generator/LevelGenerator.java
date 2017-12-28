package model.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import model.base.Direction;
import model.base.GridNode;

/**
 * Generates a Hashiwokakero level.
 * 
 * @author Sarah Lutteropp
 *
 */
public class LevelGenerator {

	/**
	 * Generate a fixed level with width=15 and height=5. It is the level from
	 * https://www.it-talents.de/foerderung/code-competition/code-competition-12-2017
	 * 
	 * @return A fixed level with width=15, height=5 and 20 nodes.
	 */
	public static ArrayList<GridNode> getFixedLevelWidth15Height5() {
		ArrayList<GridNode> nodes = new ArrayList<GridNode>();
		nodes.add(new GridNode(0, 0, 2));
		nodes.add(new GridNode(2, 0, 3));
		nodes.add(new GridNode(5, 0, 3));
		nodes.add(new GridNode(12, 0, 6));
		nodes.add(new GridNode(14, 0, 3));
		nodes.add(new GridNode(1, 1, 2));
		nodes.add(new GridNode(9, 1, 4));
		nodes.add(new GridNode(11, 1, 3));
		nodes.add(new GridNode(6, 2, 2));
		nodes.add(new GridNode(2, 2, 3));
		nodes.add(new GridNode(12, 2, 2));
		nodes.add(new GridNode(1, 3, 3));
		nodes.add(new GridNode(5, 3, 3));
		nodes.add(new GridNode(7, 3, 3));
		nodes.add(new GridNode(9, 3, 3));
		nodes.add(new GridNode(0, 4, 1));
		nodes.add(new GridNode(2, 4, 1));
		nodes.add(new GridNode(11, 4, 4));
		nodes.add(new GridNode(14, 4, 3));
		return nodes;
	}

	/**
	 * A struct for encapsulating the return value of the nextDirection method.
	 * 
	 * @author Sarah Lutteropp
	 */
	private static final class DirectionSelection {
		/**
		 * The chosen direction.
		 */
		Direction dir;
		/**
		 * The node to be extended.
		 */
		IncompleteNode node;
	}

	/**
	 * Randomly pick the next direction for extending the generated level, taking
	 * the current extension possibilities into account. Each free pin counts as a
	 * possible extension. Each extension is equally likely.
	 * 
	 * @param possibleExtensions
	 *            The possible extensions
	 * @return The randomly selected direction as well as the selected
	 *         IncompleteNode.
	 */
	private static DirectionSelection nextDirection(
			final HashMap<Direction, ArrayList<IncompleteNode>> possibleExtensions) {
		int sizeEast = possibleExtensions.get(Direction.EAST).size();
		int sizeWest = possibleExtensions.get(Direction.WEST).size();
		int sizeNorth = possibleExtensions.get(Direction.NORTH).size();
		int sizeSouth = possibleExtensions.get(Direction.SOUTH).size();
		int sizeTotal = sizeEast + sizeWest + sizeNorth + sizeSouth;
		if (sizeTotal == 0) {
			throw new RuntimeException("No directional extensions are possible anymore");
		}
		Random rand = new Random();
		int val = rand.nextInt(sizeTotal);
		DirectionSelection result = new DirectionSelection();
		if (val < sizeEast) {
			result.dir = Direction.EAST;
			result.node = possibleExtensions.get(Direction.EAST).get(val);
		} else if (val < sizeEast + sizeWest) {
			result.dir = Direction.WEST;
			result.node = possibleExtensions.get(Direction.WEST).get(val - sizeEast);
		} else if (val < sizeEast + sizeWest + sizeNorth) {
			result.dir = Direction.NORTH;
			result.node = possibleExtensions.get(Direction.NORTH).get(val - sizeEast - sizeWest);
		} else {
			result.dir = Direction.SOUTH;
			result.node = possibleExtensions.get(Direction.SOUTH).get(val - sizeEast - sizeWest - sizeNorth);
		}
		return result;
	}

	/**
	 * Generates a Hashiwokakero level which fills 25% of the grid with nodes. The
	 * level is generated by first placing a random node on the grid, then randomly
	 * extending the current graph until all nodes are created.
	 * 
	 * @param width
	 *            The width of the generated grid.
	 * @param height
	 *            The height of the generated grid.
	 * @return A list of nodes which constitute the level.
	 */
	public static ArrayList<GridNode> generateLevel(final int width, final int height) {
		// Because the fixed example has 20 nodes in a grid containing 75 cells (i.e.,
		// it uses 26.67% of the grid) and it looks nice, we aim for filling
		// approximately 25% of the grid with nodes.
		double gridUsage = 0.25;
		int nodesToPlace = (int) Math.round(width * height * gridUsage);

		ArrayList<ArrayList<IncompleteNode>> cells = new ArrayList<ArrayList<IncompleteNode>>();
		for (int i = 0; i < width; ++i) {
			ArrayList<IncompleteNode> list = new ArrayList<IncompleteNode>();
			for (int j = 0; j < height; ++j) {
				list.add(null);
			}
			cells.add(list);
		}
		// List for easier creation of the final GridNode array.
		ArrayList<IncompleteNode> incompleteNodes = new ArrayList<IncompleteNode>();

		// Place the first node randomly on the grid.
		Random rand = new Random();
		int randX = rand.nextInt(width);
		int randY = rand.nextInt(height);
		IncompleteNode firstNode = new IncompleteNode(randX, randY);
		incompleteNodes.add(firstNode);
		cells.get(randX).set(randY, firstNode);
		nodesToPlace--;

		// Keep track of the possible extensions in each direction.
		// A node with two free pins into a direction will be added twice. A node with
		// only one free pin into a direction will be added once. This ensures that each
		// free pin has the same chance of being selected for extension.
		HashMap<Direction, ArrayList<IncompleteNode>> possibleExtensions = new HashMap<Direction, ArrayList<IncompleteNode>>();
		possibleExtensions.put(Direction.EAST, new ArrayList<IncompleteNode>());
		possibleExtensions.put(Direction.WEST, new ArrayList<IncompleteNode>());
		possibleExtensions.put(Direction.NORTH, new ArrayList<IncompleteNode>());
		possibleExtensions.put(Direction.SOUTH, new ArrayList<IncompleteNode>());
		for (Direction dir : Direction.values()) {
			for (int i = 0; i < firstNode.getPossible(dir, width, height); ++i) {
				possibleExtensions.get(dir).add(firstNode);
			}
		}

		// Place the remaining nodes.
		// For nicer aesthetics of the finished level, no nodes can be placed directly
		// north, south, east, or west of a node.
		while (nodesToPlace > 0) {
			int totalPossible = 0;
			for (Direction dir: Direction.values()) {
				totalPossible += possibleExtensions.get(dir).size();
			}
			if (totalPossible == 0) {
				// In rare cases, there are no possible extensions left. These generated levels
				// tend to look ugly and have too many 8-nodes, thus we redo the whole
				// generation.
				return generateLevel(width, height);
			}
			
			DirectionSelection select = nextDirection(possibleExtensions);
			IncompleteNode actNode = select.node;
			// Compute the coordinates of the neighbor for extension.
			int newX = actNode.getX();
			int newY = actNode.getY();
			if (select.dir == Direction.EAST) {
				newX += 2;
			} else if (select.dir == Direction.WEST) {
				newX -= 2;
			} else if (select.dir == Direction.NORTH) {
				newY += 2;
			} else { // Direction.SOUTH
				newY -= 2;
			}
			IncompleteNode neighbor = cells.get(newX).get(newY);

			if (neighbor == null) { // Create a new node.
				neighbor = new IncompleteNode(newX, newY);
				incompleteNodes.add(neighbor);
				cells.get(newX).set(newY, neighbor);
				nodesToPlace--;
				for (Direction dir : Direction.values()) {
					for (int i = 0; i < neighbor.getPossible(dir, width, height); ++i) {
						possibleExtensions.get(dir).add(neighbor);
					}
				}
			}
			actNode.increaseGoal(select.dir);
			neighbor.increaseGoal(Direction.reverseDirection(select.dir));
			// update the possible East/West/North/South entries
			possibleExtensions.get(select.dir).remove(actNode);
			possibleExtensions.get(Direction.reverseDirection(select.dir)).remove(neighbor);
		}

		ArrayList<GridNode> nodes = new ArrayList<GridNode>();
		for (IncompleteNode inode : incompleteNodes) {
			nodes.add(new GridNode(inode.getX(), inode.getY(), inode.getTotalGoal()));
		}
		return nodes;
	}
}