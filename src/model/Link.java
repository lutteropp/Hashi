package model;

/**
 * A link between two nodes.
 * 
 * @author Sarah Lutteropp
 *
 */
public class Link {
	private GridNode node1;
	private GridNode node2;
	/** The number of connections between the nodes. Can be 0, 1, or 2. */
	private int thickness;
	
	/**
	 * Creates a link between two nodes.
	 * @param node1 The first node.
	 * @param node2 The second node.
	 */
	public Link(GridNode node1, GridNode node2) {
		this.node1 = node1;
		this.node2 = node2;
		this.thickness = 0;
	}
	
	public GridNode getOtherNode(GridNode node) {
		if (node == node1) {
			return node2;
		} else if (node == node2) {
			return node1;
		} else {
			throw new IllegalArgumentException("Unknown node");
		}
	}

	/**
	 * @return The number of connections between the nodes. Can be 0, 1, or 2.
	 */
	public int getThickness() {
		return thickness;
	}

	/**
	 * @return The first node
	 */
	public GridNode getNode1() {
		return node1;
	}
	
	/**
	 * @return The second node
	 */
	public GridNode getNode2() {
		return node2;
	}

	/**
	 * Toggles the number of connections. 0->1, 1->2, or 2->0.
	 */
	public void toggle() {
		thickness = (thickness + 1) % 3;
	}
	
	/**
	 * Sets the number of connections.
	 * @param thickness The number of connections. Has to be either 0, 1, or 2.
	 */
	public void setThickness(int thickness) {
		if (thickness < 0 || thickness > 2) {
			throw new IllegalArgumentException("Thickness must be 0, 1, or 2");
		}
		this.thickness = thickness;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node1 == null) ? 0 : node1.hashCode());
		result = prime * result + ((node2 == null) ? 0 : node2.hashCode());
		result = prime * result + thickness;
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
		Link other = (Link) obj;
		if (node1 == null) {
			if (other.node1 != null)
				return false;
		} else if (!node1.equals(other.node1))
			return false;
		if (node2 == null) {
			if (other.node2 != null)
				return false;
		} else if (!node2.equals(other.node2))
			return false;
		if (thickness != other.thickness)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Link [node1=" + node1 + ", node2=" + node2 + ", thickness=" + thickness + "]";
	}
}
