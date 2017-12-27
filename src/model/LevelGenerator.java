package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Generates a Hashiwokakero level. For the beginning, just create a static,
 * pre-fixed level.
 * 
 * @author Sarah Lutteropp
 *
 */
public class LevelGenerator {

	/**
	 * Generate a fixed level with width=15 and height=5. It is the level from
	 * https://www.it-talents.de/foerderung/code-competition/code-competition-12-2017
	 * 
	 * @return A fixed level with width=15 and height=5 and 20 nodes.
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
		nodes.add(new GridNode(2, 2, 3));
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

	public static final class DirectionSelection {
		Direction dir;
		int index;
		IncompleteNode node;
	}

	/**
	 * Randomly select the next direction for extending the generated level, taking
	 * the current extension possibilities into account.
	 * 
	 * @param possibleExtensions
	 *            The possible extensions
	 * @return The randomly selected direction as well as the selected index in the
	 *         corresponding list and the selected IncompleteNode.
	 */
	private static DirectionSelection nextDirection(HashMap<Direction, ArrayList<IncompleteNode>> possibleExtensions) {
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
			result.index = val;
			result.node = possibleExtensions.get(Direction.EAST).get(result.index);
		} else if (val < sizeEast + sizeWest) {
			result.dir = Direction.WEST;
			result.index = val - sizeEast;
			result.node = possibleExtensions.get(Direction.WEST).get(result.index);
		} else if (val < sizeEast + sizeWest + sizeNorth) {
			result.dir = Direction.NORTH;
			result.index = val - sizeEast - sizeWest;
			result.node = possibleExtensions.get(Direction.NORTH).get(result.index);
		} else {
			result.dir = Direction.SOUTH;
			result.index = val - sizeEast - sizeWest - sizeNorth;
			result.node = possibleExtensions.get(Direction.SOUTH).get(result.index);
		}
		return result;
	}

	public static ArrayList<GridNode> generateLevel(int width, int height) {
		ArrayList<GridNode> nodes = new ArrayList<GridNode>();
		// because the fixed example has 20 nodes in a grid containing 75 cells (i.e.,
		// it uses 26.67% of the grid) and it
		// looks nice, we aim for filling approximately 25% of the grid with nodes.
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

		Random rand = new Random();
		// place the first node randomly on the grid.
		int randX = rand.nextInt(width);
		int randY = rand.nextInt(height);

		ArrayList<IncompleteNode> incompleteNodes = new ArrayList<IncompleteNode>();
		IncompleteNode firstNode = new IncompleteNode(randX, randY);
		incompleteNodes.add(firstNode);
		cells.get(randX).set(randY, firstNode);
		nodesToPlace--;

		HashMap<Direction, ArrayList<IncompleteNode>> possibleExtensions = new HashMap<Direction, ArrayList<IncompleteNode>>();
		possibleExtensions.put(Direction.EAST, new ArrayList<IncompleteNode>());
		possibleExtensions.put(Direction.WEST, new ArrayList<IncompleteNode>());
		possibleExtensions.put(Direction.NORTH, new ArrayList<IncompleteNode>());
		possibleExtensions.put(Direction.SOUTH, new ArrayList<IncompleteNode>());
		for (Direction dir : Direction.values()) {
			if (firstNode.getPossible(dir, width, height) > 0) {
				possibleExtensions.get(dir).add(firstNode);
			}
		}

		// Place the remaining nodes.
		// For nicer aesthetics of the finished level, no nodes can be placed directly
		// north, south, east, or west of a node.
		while (nodesToPlace > 0) {
			DirectionSelection select;
			try {
				select = nextDirection(possibleExtensions);
			} catch (Exception e) { // In rare cases, there are no possible extensions left. These generated levels
									// tend to look ugly and have too many 8-nodes, thus we redo the whole
									// generation.
				return generateLevel(width, height);
			}
			IncompleteNode actNode = select.node;
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

			if (cells.get(newX).get(newY) == null) { // Create a new node.
				IncompleteNode newNode = new IncompleteNode(newX, newY);
				incompleteNodes.add(newNode);
				cells.get(newX).set(newY, newNode);
				nodesToPlace--;

				for (Direction dir : Direction.values()) {
					if (newNode.getPossible(dir, width, height) > 0) {
						possibleExtensions.get(dir).add(newNode);
					}
				}
			}
			IncompleteNode neighbor = cells.get(newX).get(newY);
			actNode.increaseGoal(select.dir);
			neighbor.increaseGoal(Direction.reverseDirection(select.dir));
			// update the possible East/West/North/South entries
			if (actNode.getPossible(select.dir, width, height) == 0) {
				possibleExtensions.get(select.dir).remove(actNode);
			}
			if (neighbor.getPossible(Direction.reverseDirection(select.dir), width, height) == 0) {
				possibleExtensions.get(Direction.reverseDirection(select.dir)).remove(neighbor);
			}
		}

		for (IncompleteNode inode : incompleteNodes) {
			nodes.add(new GridNode(inode.getX(), inode.getY(), inode.getTotalGoal()));
		}

		System.out.println("There are " + Integer.toString(nodes.size()) + " nodes.");
		return nodes;
	}
}
