package model.base;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A node in the grid that needs to be connected to other nodes.
 * 
 * @author Sarah Lutteropp
 *
 */
public class GridNode {
	/** x cordinate on the grid */
	private int x;
	/** y-coordinate on the grid */
	private int y;
	/**
	 * connections to other nodes
	 */
	private HashMap<Direction, Link> connections;
	/** wanted number of connections to other nodes */
	private int goal;

	/**
	 * Create a node on the grid.
	 * 
	 * @param x
	 *            The x-coordinate of the node.
	 * @param y
	 *            The y-coordinate of the node.
	 * @param goal
	 *            The number of required connections to other GridNodes.
	 */
	public GridNode(int x, int y, int goal) {
		if (goal < 1 || goal > 8) {
			throw new IllegalArgumentException("Goal must be between 1 and 8");
		}
		
		this.x = x;
		this.y = y;
		this.goal = goal;
		connections = new HashMap<Direction, Link>();
		for (Direction dir : Direction.values()) {
			connections.put(dir, null);
		}
	}

	/**
	 * Define the neighboring node in a given direction.
	 * 
	 * @param dir
	 *            The direction. Has to be NORTH, SOUTH, EAST, or WEST.
	 * @param target
	 *            The neighborring node.
	 */
	public void setLink(Direction dir, Link link) {
		// check if the target node can indeed be the neighbor
		GridNode target = link.getOtherNode(this);
		if (dir == Direction.NORTH) {
			if (x != target.x || y >= target.y) {
				throw new IllegalArgumentException("The target node does not lie strictly to the north");
			}
		} else if (dir == Direction.SOUTH) {
			if (x != target.x || y <= target.y) {
				throw new IllegalArgumentException("The target node does not lie strictly to the south");
			}
		} else if (dir == Direction.EAST) {
			if (y != target.y || x >= target.x) {
				throw new IllegalArgumentException("The target node does not lie strictly to the east");
			}
		} else { // Direction.WEST
			if (y != target.y || x <= target.x) {
				throw new IllegalArgumentException("The target node does not lie strictly to the west");
			}
		}
		this.connections.put(dir, link);
	}

	/**
	 * Get the x-coordinate of the node.
	 * 
	 * @return The x-coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the y-coordinate of the node.
	 * 
	 * @return The y-coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return The neighboring nodes of this node.
	 */
	public ArrayList<GridNode> getAllNeighbors() {
		ArrayList<GridNode> res = new ArrayList<GridNode>();
		for (Direction dir : Direction.values()) {
			if (connections.get(dir) != null) {
				res.add(connections.get(dir).getOtherNode(this));
			}
		}
		return res;
	}
	
	/**
	 * @return The nodes connected to this node with at least one wire.
	 */
	public ArrayList<GridNode> getAllNonZeroNeighbors() {
		ArrayList<GridNode> res = new ArrayList<GridNode>();
		for (Direction dir : Direction.values()) {
			if (connections.get(dir) != null && connections.get(dir).getThickness() > 0) {
				res.add(connections.get(dir).getOtherNode(this));
			}
		}
		return res;
	}
	
	/**
	 * @param node The node in question
	 * @return {@value true}, if and only if the node in question is a neighbor of this node.
	 */
	public boolean isNeighborOf(GridNode node) {
		for (Direction dir : Direction.values()) {
			if (connections.get(dir) != null) {
				if (connections.get(dir).getOtherNode(this) == node) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @return {@value true}, if and only if the node's goal is to be fully connected.
	 */
	public boolean goalIsFullNode() {
		int numNeighbors = this.getAllNeighbors().size();
		if (goal == numNeighbors * 2) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get the link into the specified direction.
	 * 
	 * @param dir
	 *            The direction. Can be NORTH, SOUTH, EAST, or WEST.
	 * @return The link in the specified direction. This can be null.
	 */
	public Link getLink(Direction dir) {
		return connections.get(dir);
	}

	/**
	 * Get the wanted number of connections to other nodes.
	 * 
	 * @return The wanted number of connections to other nodes.
	 */
	public int getGoal() {
		return goal;
	}

	/**
	 * Get the current total degree of the node.
	 * 
	 * @return The current total degree of the node.
	 */
	public int getDegree() {
		int degree = 0;
		for (Direction dir : Direction.values()) {
			if (connections.get(dir) != null) {
				degree += connections.get(dir).getThickness();
			}
		}
		return degree;
	}

	/**
	 * Get the link to the specified neighbor.
	 * @param target The neighbor.
	 * @return The link.
	 */
	public Link getLinkToNeighbor(GridNode target) {
		Link link = null;
		for (Direction dir : Direction.values()) {
			if (connections.get(dir) != null) {
				if (connections.get(dir).getOtherNode(this) == target) {
					link = connections.get(dir);
					break;
				}
			}
		}
		if (link == null) {
			throw new IllegalArgumentException("The given node is not a neighbor");
		}
		return link;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + goal;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GridNode other = (GridNode) obj;
		if (goal != other.goal)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GridNode [x=" + x + ", y=" + y + ", goal=" + goal + "]";
	}
}