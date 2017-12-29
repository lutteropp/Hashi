package model.generator;

import java.util.HashMap;

import model.base.Direction;

/**
 * An incomplete node. This class is used for generating new Hashiwokakero
 * levels, because it stores connection goals for each direction and these can
 * be modified.
 * 
 * @author Sarah Lutteropp
 *
 */
public class IncompleteNode {
	/** x cordinate on the grid */
	private int x;
	/** y cordinate on the grid */
	private int y;
	/** Wanted number of connections in each direction */
	private HashMap<Direction, Integer> goals;

	/**
	 * Create an incomplete node for level generation.
	 * 
	 * @param x
	 *            The x-coordinate on the grid.
	 * @param y
	 *            The y-coordinate on the grid.
	 */
	public IncompleteNode(final int x, final int y) {
		if (x < 0) {
			throw new IllegalArgumentException("x must be non-negative");
		}
		if (y < 0) {
			throw new IllegalArgumentException("y must be non-negative");
		}
		this.x = x;
		this.y = y;
		goals = new HashMap<Direction, Integer>();
		for (Direction dir : Direction.values()) {
			goals.put(dir, 0);
		}
	}

	/**
	 * Get the number of possible extensions into the given direction.
	 * 
	 * @param dir
	 *            The direction. Can be either EAST, WEST, NORTH, or SOUTH.
	 * @param width
	 *            The width of the grid.
	 * @param height
	 *            The height of the grid.
	 * @return The number of possible extensions into the given direction. Can be 0,
	 *         1, or 2.
	 */
	public int getPossible(final Direction dir, final int width, final int height) {
		if (dir == Direction.EAST) {
			return getPossibleEast(width);
		} else if (dir == Direction.WEST) {
			return getPossibleWest();
		} else if (dir == Direction.NORTH) {
			return getPossibleNorth(height);
		} else { // Direction.SOUTH
			return getPossibleSouth();
		}
	}

	/**
	 * @return The number of possible extensions into the WEST direction.
	 */
	private int getPossibleWest() {
		if (x <= 1) {
			return 0;
		} else {
			return 2 - goals.get(Direction.WEST);
		}
	}

	/**
	 * @return The number of possible extensions into the EAST direction.
	 */
	private int getPossibleEast(final int width) {
		if (x >= width - 2) {
			return 0;
		} else {
			return 2 - goals.get(Direction.EAST);
		}
	}

	/**
	 * @return The number of possible extensions into the SOUTH direction.
	 */
	private int getPossibleSouth() {
		if (y <= 1) {
			return 0;
		} else {
			return 2 - goals.get(Direction.SOUTH);
		}
	}

	/**
	 * @return The number of possible extensions into the NORTH direction.
	 */
	private int getPossibleNorth(final int height) {
		if (y >= height - 2) {
			return 0;
		} else {
			return 2 - goals.get(Direction.NORTH);
		}
	}

	/**
	 * @return The total number of wanted connections to other nodes.
	 */
	public int getTotalGoal() {
		int total = 0;
		for (Direction dir : Direction.values()) {
			total += goals.get(dir);
		}
		return total;
	}

	/**
	 * @return The x-coordinate on the grid.
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return The y-coordinate on the grid.
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param dir
	 *            The direction. Can be EAST, WEST, NORTH, or SOUTH.
	 * @return The number of wanted connections into the specified direction. Can be
	 *         0, 1, or 2.
	 */
	public int getGoal(final Direction dir) {
		return goals.get(dir);
	}

	/**
	 * Set the number of wanted connections into the specified direction.
	 * 
	 * @param goal
	 *            The number of wanted connections into the specified direction. Can
	 *            be 0, 1, or 2.
	 * @param dir
	 *            The direction. Can be EAST, WEST, NORTH, or SOUTH.
	 */
	public void setGoal(final int goal, final Direction dir) {
		if (goal < 0 || goal > 2) {
			throw new IllegalArgumentException("Invalid goal value.");
		}
		goals.put(dir, goal);
	}

	/**
	 * Increase the number of wanted connections into the specified direction by 1.
	 * 
	 * @param dir
	 *            The direction. Can be EAST, WEST, NORTH, or SOUTH.
	 */
	public void increaseGoal(final Direction dir) {
		if (goals.get(dir) == 2) {
			throw new RuntimeException("The goal can not be further increased.");
		}
		goals.put(dir, goals.get(dir) + 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goals == null) ? 0 : goals.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		IncompleteNode other = (IncompleteNode) obj;
		if (goals == null) {
			if (other.goals != null)
				return false;
		} else if (!goals.equals(other.goals))
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
		return "IncompleteNode [x=" + x + ", y=" + y + ", goals=" + goals + "]";
	}
}