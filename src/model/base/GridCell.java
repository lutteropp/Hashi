package model.base;

/**
 * A cell in the grid. The cell is either empty or filled (by a node or a
 * connection).
 * 
 * @author Sarah Lutteropp
 *
 */
public class GridCell {
	/** Is the cell filled (by either a GridNode or a Link)? */
	private boolean filled;

	/** The grid node, in case it sits on the grid cell */
	private GridNode myNode;

	/**
	 * Create a GridCell which is empty on construction.
	 */
	public GridCell() {
		filled = false;
		myNode = null;
	}

	/**
	 * Place a node on the grid cell. This also fills the grid cell.
	 * @param node The node to place
	 */
	public void setNode(final GridNode node) {
		myNode = node;
		filled = true;
	}
	
	/**
	 * @return The grid node located in this cell or null if there is none.
	 */
	public GridNode getMyNode() {
		return myNode;
	}

	/**
	 * Fill the cell.
	 */
	public void fill() {
		filled = true;
	}

	/**
	 * Clear the cell.
	 */
	public void clear() {
		filled = false;
	}

	/**
	 * Set whether the cell is filled or not.
	 * 
	 * @param empty
	 *            Is the cell filled?
	 */
	public void setFilled(final boolean filled) {
		this.filled = filled;
	}

	/**
	 * Check whether the cell is filled or not.
	 * 
	 * @return {@value true}, if and only if the cell is filled.
	 */
	public boolean isFilled() {
		return filled;
	}

	/**
	 * Check whether the cell is empty or not.
	 * 
	 * @return {@value true}, if and only if the cell is empty.
	 */
	public boolean isEmpty() {
		return !filled;
	}

	@Override
	public String toString() {
		return "GridCell [filled=" + filled + ", myNode=" + myNode + "]";
	}
}
