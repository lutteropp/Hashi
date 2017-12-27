package model;

public class IncompleteNode {
	private int x;
	private int y;
	private int goalWest;
	private int goalEast;
	private int goalNorth;
	private int goalSouth;
	private IncompleteNode neighborWest;
	private IncompleteNode neighborEast;
	private IncompleteNode neighborNorth;
	private IncompleteNode neighborSouth;

	public IncompleteNode(int x, int y) {
		if (x < 0) {
			throw new IllegalArgumentException("x must be non-negative");
		}
		if (y < 0) {
			throw new IllegalArgumentException("y must be non-negative");
		}
		this.x = x;
		this.y = y;
		this.goalWest = 0;
		this.goalEast = 0;
		this.goalNorth = 0;
		this.goalSouth = 0;
	}

	public int getPossible(Direction dir, int width, int height) {
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
	
	public int getPossibleWest() {
		if (x <= 1) {
			return 0;
		} else {
			return 2 - goalWest;
		}
	}

	public int getPossibleEast(int width) {
		if (x >= width - 2) {
			return 0;
		} else {
			return 2 - goalEast;
		}
	}

	public int getPossibleSouth() {
		if (y <= 1) {
			return 0;
		} else {
			return 2 - goalSouth;
		}
	}

	public int getPossibleNorth(int height) {
		if (y >= height - 2) {
			return 0;
		} else {
			return 2 - goalNorth;
		}
	}

	public int getTotalGoal() {
		return goalEast + goalWest + goalNorth + goalSouth;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getGoal(Direction dir) {
		if (dir == Direction.EAST) {
			return goalEast;
		} else if (dir == Direction.WEST) {
			return goalWest;
		} else if (dir == Direction.NORTH) {
			return goalNorth;
		} else { // Direction.SOUTH
			return goalSouth;
		}
	}

	public void setGoal(int goal, Direction dir) {
		if (dir == Direction.EAST) {
			goalEast = goal;
		} else if (dir == Direction.WEST) {
			goalWest = goal;
		} else if (dir == Direction.NORTH) {
			goalNorth = goal;
		} else { // Direction.SOUTH
			goalSouth = goal;
		}
	}
	
	public void increaseGoal(Direction dir) {
		if (dir == Direction.EAST) {
			goalEast++;
		} else if (dir == Direction.WEST) {
			goalWest++;
		} else if (dir == Direction.NORTH) {
			goalNorth++;
		} else { // Direction.SOUTH
			goalSouth++;
		}
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + goalEast;
		result = prime * result + goalNorth;
		result = prime * result + goalSouth;
		result = prime * result + goalWest;
		result = prime * result + ((neighborEast == null) ? 0 : neighborEast.hashCode());
		result = prime * result + ((neighborNorth == null) ? 0 : neighborNorth.hashCode());
		result = prime * result + ((neighborSouth == null) ? 0 : neighborSouth.hashCode());
		result = prime * result + ((neighborWest == null) ? 0 : neighborWest.hashCode());
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
		IncompleteNode other = (IncompleteNode) obj;
		if (goalEast != other.goalEast)
			return false;
		if (goalNorth != other.goalNorth)
			return false;
		if (goalSouth != other.goalSouth)
			return false;
		if (goalWest != other.goalWest)
			return false;
		if (neighborEast == null) {
			if (other.neighborEast != null)
				return false;
		} else if (!neighborEast.equals(other.neighborEast))
			return false;
		if (neighborNorth == null) {
			if (other.neighborNorth != null)
				return false;
		} else if (!neighborNorth.equals(other.neighborNorth))
			return false;
		if (neighborSouth == null) {
			if (other.neighborSouth != null)
				return false;
		} else if (!neighborSouth.equals(other.neighborSouth))
			return false;
		if (neighborWest == null) {
			if (other.neighborWest != null)
				return false;
		} else if (!neighborWest.equals(other.neighborWest))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}