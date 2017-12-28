package view.game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;

/**
 * An abstract base class for all drawable objects. It contains methods for
 * drawing an (highlighted) object and checking for collision with a point.
 */
public abstract class AbstractDrawable {

	/**
	 * Whether the object is highlighted.
	 */
	protected boolean highlighted;

	/**
	 * Whether the object is selected.
	 */
	protected boolean selected;

	/**
	 * Returns if the object is highlighted.
	 * 
	 * @return {@value true}, if and only if the object is highlighted.
	 */
	public boolean isHighlighted() {
		return this.highlighted;
	}

	/**
	 * Sets the highlighted flag.
	 * 
	 * @param highlighted
	 *            A boolean whether this object is highlighted or not.
	 */
	public void setHighlighted(final boolean highlighted) {
		this.highlighted = highlighted;
	}

	/**
	 * Returns if the object is selected.
	 * 
	 * @return {@value true}, if and only if the object is selected.
	 */
	public boolean isSelected() {
		return this.selected;
	}

	/**
	 * Sets the selected flag.
	 * 
	 * @param selected
	 *            A boolean whether this object is selected or not.
	 */
	public void setSelected(final boolean selected) {
		this.selected = selected;
	}

	/**
	 * Returns whether the object is selectable.
	 * 
	 * @return A boolean that is {@value true}, if and only if the object can be
	 *         selected by a mouse click.
	 */
	public abstract boolean isSelectable();

	/**
	 * Returns whether the visual representation of the drawable contains the given
	 * position.
	 * 
	 * @param position
	 *            The position that should be tested.
	 * @param cellSize
	 *            The size of the cells on the board.
	 * @return {@value true}, if and only if the drawing of this object contains the
	 *         position.
	 */
	public abstract boolean isLocatedInPosition(final Point position, final int cellSize);

	/**
	 * Draws the object.
	 * 
	 * @param g
	 *            The canvas to draw the object on.
	 * @param cellSize
	 *            The size of the cells on the board.
	 * @throws IOException
	 */
	// Graphics can always be casted to Graphics2D
	public abstract void draw(Graphics2D g, final int cellSize) throws IOException;
}
