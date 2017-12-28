package model.base;

/**
 * A link between two nodes. If its thickness is greater than zero, it can be
 * interpreted as an edge in an undirected graph connecting these two nodes.
 * 
 * @author Sarah Lutteropp
 *
 */
public class Link {
	/**
	 * The first node.
	 */
	private GridNode node1;
	/**
	 * The second node.
	 */
	private GridNode node2;
	/** The number of connections between the nodes. Can be 0, 1, or 2. */
	private int thickness;

	/**
	 * Creates a link between two nodes.
	 * 
	 * @param node1
	 *            The first node.
	 * @param node2
	 *            The second node.
	 */
	public Link(final GridNode node1, final GridNode node2) {
		this.node1 = node1;
		this.node2 = node2;
		this.thickness = 0;
	}

	/**
	 * Get the neighbor of the given node by following this link.
	 * @param node The node on one end of the link.
	 * @return The node on the other end of the link.
	 */
	public GridNode getOtherNode(final GridNode node) {
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
	 * Try to decrease the number of connections by one.
	 * @return {@value true}, if and only if the number of connections has been decreased.
	 */
	public boolean decreaseThickness() {
		if (thickness == 0) {
			return false;
		} else {
			thickness--;
			return true;
		}
	}

	/**
	 * Try to increase the the number of connections by one.
	 * @return {@value true}, if and only if the number of connections has been increased.
	 */
	public boolean increaseThickness() {
		if (thickness == 2) {
			return false;
		} else {
			thickness++;
			return true;
		}
	}
	
	/**
	 * Sets the number of connections.
	 * 
	 * @param thickness
	 *            The number of connections. Has to be either 0, 1, or 2.
	 */
	public void setThickness(final int thickness) {
		if (thickness < 0 || thickness > 2) {
			throw new IllegalArgumentException("Thickness must be 0, 1, or 2");
		}
		this.thickness = thickness;
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
		result = prime * result + ((node1 == null) ? 0 : node1.hashCode());
		result = prime * result + ((node2 == null) ? 0 : node2.hashCode());
		result = prime * result + thickness;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Link [node1=" + node1 + ", node2=" + node2 + ", thickness=" + thickness + "]";
	}
}
