package model;

import java.util.Random;

/**
 * A direction on a 2-dimensional grid.
 * 
 * @author Sarah Lutteropp
 *
 */
public enum Direction {
	NORTH, SOUTH, EAST, WEST;

	/**
	 * @param dir
	 *            The direction. Has to be one of NORTH, SOUTH, EAST, or WEST.
	 * @return The reverse direction.
	 */
	public static Direction reverseDirection(Direction dir) {
		if (dir == Direction.NORTH) {
			return Direction.SOUTH;
		} else if (dir == Direction.SOUTH) {
			return Direction.NORTH;
		} else if (dir == Direction.EAST) {
			return Direction.WEST;
		} else { // Direction.WEST
			return Direction.EAST;
		}
	}
}