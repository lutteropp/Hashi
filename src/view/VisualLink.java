package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import model.Link;

public class VisualLink extends AbstractDrawable {
	private Link myLink;
	private int minX;
	private int maxX;
	private int minY;
	private int maxY;

	public VisualLink(Link link) {
		if (link == null) {
			throw new IllegalArgumentException("The given link is null");
		}
		this.myLink = link;
		minX = Math.min(myLink.getNode1().getX(), myLink.getNode2().getX());
		maxX = Math.max(myLink.getNode1().getX(), myLink.getNode2().getX());
		minY = Math.min(myLink.getNode1().getY(), myLink.getNode2().getY());
		maxY = Math.max(myLink.getNode1().getY(), myLink.getNode2().getY());
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
			if (this.isHighlighted) {
				g.setColor(Color.GRAY);
			} else {
				g.setColor(Color.BLACK);
			}
			if (myLink.getThickness() == 1) {
				g.setStroke(new BasicStroke(5));
			} else { // thickness = 2
				g.setStroke(new BasicStroke(10));
			}
			g.drawLine(minX * cellSize + cellSize / 2, minY * cellSize + cellSize / 2, maxX * cellSize + cellSize / 2,
					maxY * cellSize + cellSize / 2);
			g.setStroke(new BasicStroke(1));
		}
	}

}
