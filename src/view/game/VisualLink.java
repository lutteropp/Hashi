package view.game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import model.base.Direction;
import model.base.Link;

/**
 * The visual representation of a Link.
 * @author Sarah Lutteropp
 */
public class VisualLink extends AbstractDrawable {
	/** The actual link */
	private Link myLink;
	/** The smaller x-coordinate of the connected nodes */
	private int minX;
	/** The larger x-coordinate of the connected nodes */
	private int maxX;
	/** The smaller y-coordinate of the connected nodes */
	private int minY;
	/** The larger y-coordinate of the connected nodes */
	private int maxY;
	/** Is the link vertical or horizontal? */
	private boolean isVertical;

	/**
	 * Create a visual representation of a Link.
	 * @param link The actual link.
	 */
	public VisualLink(Link link) {
		if (link == null) {
			throw new IllegalArgumentException("The given link is null");
		}
		this.myLink = link;
		minX = Math.min(myLink.getNode1().getX(), myLink.getNode2().getX());
		maxX = Math.max(myLink.getNode1().getX(), myLink.getNode2().getX());
		minY = Math.min(myLink.getNode1().getY(), myLink.getNode2().getY());
		maxY = Math.max(myLink.getNode1().getY(), myLink.getNode2().getY());
		if (minX == maxX) {
			isVertical = true;
		} else {
			isVertical = false;
		}
	}
	
	/**
	 * @return The actual link.
	 */
	public Link getMyLink() {
		return myLink;
	}

	@Override
	public boolean isSelectable() {
		if (myLink.getThickness() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isLocatedInPosition(final Point position, final int cellSize) {
		boolean located = false;
		if (myLink.getThickness() > 0) {
			int delta = cellSize / 6;
			if (position.getX() >= minX * cellSize + cellSize / 2 - delta
					&& position.getX() <= maxX * cellSize + cellSize / 2 + delta
					&& position.getY() >= minY * cellSize + cellSize / 2 - delta
					&& position.getY() <= maxY * cellSize + cellSize / 2 + delta) {
				located = true;
			}
		}
		return located;
	}

	@Override
	public void draw(Graphics2D g, final int cellSize) {
		if (myLink.getThickness() > 0) {
			BufferedImage wireImage;
			if (isVertical) {
				wireImage = Assets.getWireImage(Direction.SOUTH, myLink.getThickness(), this.highlighted);
			} else {
				wireImage = Assets.getWireImage(Direction.EAST, myLink.getThickness(), this.highlighted);
			}
			if (isVertical) {
				int x = minX;
				for (int y = minY + 1; y < maxY; ++y) {
					g.drawImage(wireImage, x * cellSize, y * cellSize, cellSize, cellSize, null);
				}
			} else {
				int y = minY;
				for (int x = minX + 1; x < maxX; ++x) {
					g.drawImage(wireImage, x * cellSize, y * cellSize, cellSize, cellSize, null);
				}
			}
		}
	}

}
