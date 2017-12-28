package model.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The game board, consisting of grid cells (which can be filled by either nodes
 * or connections) and nodes. This class also contains the game logic.
 * 
 * @author Sarah Lutteropp
 *
 */
public class GameBoard {
	/**
	 * The cells on the grid. They can be either empty or filled (by either a node
	 * or a connection).
	 */
	private ArrayList<ArrayList<GridCell>> cells;
	/**
	 * The nodes on the grid. These are the objects that need to be connected with
	 * each other.
	 */
	private ArrayList<GridNode> nodes;
	/**
	 * The links connecting pairs of nodes.
	 */
	private ArrayList<Link> links;

	/**
	 * Initializes the neighborhood relations of all nodes.
	 */
	private void initializeNeighbors() {
		// set the vertical neighbors.
		for (int i = 0; i < cells.size(); ++i) {
			GridNode lastNode = null;
			for (int j = 0; j < cells.get(0).size(); ++j) {
				GridNode actNode = cells.get(i).get(j).getMyNode();
				if (actNode != null) {
					if (lastNode != null) { // found a neighbor
						Link link = new Link(lastNode, actNode);
						links.add(link);
						lastNode.setLink(Direction.NORTH, link);
						actNode.setLink(Direction.SOUTH, link);
					}
					lastNode = actNode;
				}
			}
		}

		// set the horizontal neighbors.
		for (int j = 0; j < cells.get(0).size(); ++j) {
			GridNode lastNode = null;
			for (int i = 0; i < cells.size(); ++i) {
				GridNode actNode = cells.get(i).get(j).getMyNode();
				if (actNode != null) {
					if (lastNode != null) { // found a neighbor
						Link link = new Link(lastNode, actNode);
						links.add(link);
						lastNode.setLink(Direction.EAST, link);
						actNode.setLink(Direction.WEST, link);
					}
					lastNode = actNode;
				}
			}
		}
	}

	/**
	 * Create a game board.
	 * 
	 * @param width
	 *            Width of the grid.
	 * @param height
	 *            Height of the grid.
	 */
	public GameBoard(final int width, final int height, final ArrayList<GridNode> nodes) {
		if (width < 1) {
			throw new IllegalArgumentException("The width must be at least 1.");
		}
		if (height < 1) {
			throw new IllegalArgumentException("The height must be at least 1.");
		}
		HashSet<GridNode> controlSet = new HashSet<GridNode>();
		controlSet.addAll(nodes);
		if (controlSet.size() != nodes.size()) {
			throw new IllegalArgumentException("Some nodes occur multiple times!");
		}
		controlSet.clear();

		cells = new ArrayList<ArrayList<GridCell>>(width);
		for (int i = 0; i < width; ++i) {
			cells.add(new ArrayList<GridCell>(height));
			for (int j = 0; j < height; ++j) {
				cells.get(i).add(new GridCell());
			}
		}
		this.nodes = nodes;
		// fill the grid cells containing a node.
		for (GridNode node : nodes) {
			cells.get(node.getX()).get(node.getY()).setNode(node);
		}
		links = new ArrayList<Link>();
		initializeNeighbors();
	}

	/**
	 * @return All GridNodes.
	 */
	public ArrayList<GridNode> getAllNodes() {
		return nodes;
	}

	/**
	 * @return All Links.
	 */
	public ArrayList<Link> getAllLinks() {
		return links;
	}

	/**
	 * @return The unfinished grid nodes which still need connections.
	 */
	public ArrayList<GridNode> getUnfinishedNodes() {
		ArrayList<GridNode> res = new ArrayList<GridNode>();
		for (GridNode node : nodes) {
			if (node.getDegree() != node.getGoal()) {
				res.add(node);
			}
		}
		return res;
	}

	/**
	 * @return The finished grid nodes which have all their connections.
	 */
	public ArrayList<GridNode> getFinishedNodes() {
		ArrayList<GridNode> res = new ArrayList<GridNode>();
		for (GridNode node : nodes) {
			if (node.getDegree() == node.getGoal()) {
				res.add(node);
			}
		}
		return res;
	}

	/**
	 * @return The width of the grid in number of cells.
	 */
	public int getWidth() {
		return cells.size();
	}

	/**
	 * @return The height of the grid in number of cells.
	 */
	public int getHeight() {
		return cells.get(0).size();
	}

	/**
	 * Check whether node1 and node2 can currently be connected.
	 * 
	 * @param node1
	 *            The first node to be connected.
	 * @param node2
	 *            The second node to be connected.
	 * @return {@value true}, if and only if the nodes can be connected.
	 */
	public boolean unblockedConnection(final GridNode node1, final GridNode node2) {
		if (!node1.getAllNeighbors().contains(node2)) {
			throw new IllegalArgumentException("The nodes are not neighbors");
		}
		// If the nodes are already connected, then the path is not blocked by another
		// connection. Otherwise, check whether the grid cells on the path between the
		// nodes are currently free.
		Link link = node1.getLinkToNeighbor(node2);
		boolean unblocked = true;
		if (link.getThickness() == 0) { // only in this case we still have to check the grid cells
			if (node1.getX() == node2.getX()) {
				// check if all cells on the vertical path are empty
				int x = node1.getX();
				for (int y = Math.min(node1.getY(), node2.getY()) + 1; y < Math.max(node1.getY(), node2.getY()); ++y) {
					if (!cells.get(x).get(y).isEmpty()) { // check cell [node1.x, y]
						unblocked = false;
						break;
					}
				}
			} else { // node1.y == node2.y, because otherwise they would not be neighbors
				// check if all cells on the horizontal path are empty
				int y = node1.getY();
				for (int x = Math.min(node1.getX(), node2.getX()) + 1; x < Math.max(node1.getX(), node2.getX()); ++x) {
					if (!cells.get(x).get(y).isEmpty()) { // check cell [x, node1.y]
						unblocked = false;
						break;
					}
				}
			}
		}
		return unblocked;
	}

	/**
	 * Tries to toggle the number of connections between two nodes either from 0->1,
	 * 1->2, or 2->0.
	 * 
	 * @param node1
	 *            The first node.
	 * @param node2
	 *            The second node.
	 * @return {@value true}, if and only if the connection could be toggled.
	 */
	public boolean toggleConnection(final GridNode node1, final GridNode node2) {
		Link link1 = node1.getLinkToNeighbor(node2);
		Link link2 = node2.getLinkToNeighbor(node1);
		boolean previouslyConnected = (link1.getThickness() > 0);
		if (link1.getThickness() == 0) { // in this case, we need to first check whether the path between the nodes is
											// currently free.
			if (!unblockedConnection(node1, node2)) {
				return false;
			}
		}
		link1.toggle();
		link2.toggle();
		boolean nowConnected = (link1.getThickness() > 0);

		if (previouslyConnected != nowConnected) { // update the grid cells because the connection state has changed
			boolean filledState = nowConnected;
			if (node1.getX() == node2.getX()) {
				int x = node1.getX();
				for (int y = Math.min(node1.getY(), node2.getY()) + 1; y < Math.max(node1.getY(), node2.getY()); ++y) {
					cells.get(x).get(y).setFilled(filledState);
				}
			} else { // node1.y == node2.y
				int y = node1.getY();
				for (int x = Math.min(node1.getX(), node2.getX()) + 1; x < Math.max(node1.getX(), node2.getX()); ++x) {
					cells.get(x).get(y).setFilled(filledState);
				}
			}
		}

		return true;
	}

	/**
	 * Check whether the nodes form a connected graph by doing a breadth-first
	 * search.
	 * 
	 * @param nodes
	 *            The nodes.
	 * @return {@value true}, if and only if the nodes form a connected graph.
	 */
	public boolean checkAllNodesConnected() {
		HashSet<GridNode> visited = new HashSet<GridNode>(nodes.size());
		Queue<GridNode> queue = new LinkedList<GridNode>();
		queue.add(nodes.get(0));
		while (!queue.isEmpty()) {
			GridNode actNode = queue.remove();
			if (!visited.contains(actNode)) {
				visited.add(actNode);
				ArrayList<GridNode> neighbors = actNode.getAllNonZeroNeighbors();
				for (GridNode neighbor : neighbors) {
					if (!visited.contains(neighbor)) {
						queue.add(neighbor);
					}
				}
			}
		}

		for (GridNode node : nodes) {
			if (!visited.contains(node)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check whether the game has been won. This is the case, if and only if the degree of all
	 * nodes matches their goal and all nodes form a connected graph.
	 * 
	 * @return {@value true}, if the game has been won.
	 */
	public boolean hasWon() {
		if (this.getUnfinishedNodes().isEmpty() && this.checkAllNodesConnected()) {
			return true;
		} else {
			return false;
		}
	}
}
