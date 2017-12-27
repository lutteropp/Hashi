package view;

import java.awt.Graphics2D;
import java.awt.Point;

/** An abstract base class for all drawable objects.
 * It contains methods to draw an (highlighted) object
 * and check for collision with a point.
 */
public abstract class AbstractDrawable {

	/** Whether the object should be drawn as highlighted.
	 */
	protected boolean isHighlighted;
	
	/** Whether the object should be drawn as selected.
	 */
	protected boolean isSelected;

	/** Returns if the object should be drawn highlighted.
	 * @return {@value true} if the object should be highlighted.
	 */
	public boolean isHighlighted() {
		return this.isHighlighted;
	}
	
	/** Sets the highlighted flag.
	 * @param highlighted A boolean whether this object should be highlighted.
	 */
	public void setHighlighted(final boolean highlighted) {
		this.isHighlighted = highlighted;
	}

	/** Returns if the object should be drawn selected.
	 * @return {@value true} if the object should be selected.
	 */
	public boolean isSelected() {
		return this.isSelected;
	}
	
	/** Sets the selected flag.
	 * @param selected A boolean whether this object should be selected.
	 */
	public void setSelected(final boolean selected) {
		this.isSelected = selected;
	}

	/** Returns whether the object is selectable.
	 * @return A boolean that is {@value true} if the object can be selected by
	 * 		   a mouse click.
	 */
	public abstract boolean isSelectable();

	/** Returns whether the drawable is drawn at the given position.
	 * This is not the same as the own position, since the object might be drawn
	 * as a random object.
	 * @param position The position that should be tested.
	 * @param cellSize The size of the cells on the board.
	 * @return {@value true} if this object is drawn at the position.
	 */
	public abstract boolean isLocatedInPosition(final Point position, int cellSize);

	/** Draws the object.
	 * @param g The canvas to draw the object on.
	 * @param cellSize The size of the cells on the board.
	 */
	// Graphics can always be casted to Graphics2D
	public abstract void draw(final Graphics2D g, int cellSize);
}
