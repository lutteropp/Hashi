package view;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import model.Direction;
import model.Link;

public class VisualLink extends AbstractDrawable {
	private Link myLink;
	private int minX;
	private int maxX;
	private int minY;
	private int maxY;
	private boolean isVertical;

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
	
	public void clear() {
		myLink.setThickness(0);
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
	public boolean isLocatedInPosition(Point position, int cellSize) {
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
	public void draw(Graphics2D g, int cellSize) {
		if (myLink.getThickness() > 0) {
			BufferedImage wireImage;
			if (isVertical) {
				wireImage = Assets.getWireImage(Direction.SOUTH, myLink.getThickness(), this.isHighlighted);
			} else {
				wireImage = Assets.getWireImage(Direction.EAST, myLink.getThickness(), this.isHighlighted);
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
