package view.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import assets.GraphicalAssets;
import model.base.Direction;
import model.base.GridNode;
import model.base.Link;

/**
 * The visual representation of a GridNode.
 * @author Sarah Lutteropp
 */
public class VisualGridNode extends AbstractDrawable {
	/** The actual GridNode */
	private GridNode myGridNode;
	/** The VisualLinks connected to this node */
	private HashMap<Direction, VisualLink> myLinks;

	/**
	 * @return The actual GridNode.
	 */
	public GridNode getMyGridNode() {
		return myGridNode;
	}
	
	/**
	 * Create a visual representation of a GridNode.
	 * @param node The actual GridNode.
	 */
	public VisualGridNode(GridNode node) {
		if (node == null) {
			throw new IllegalArgumentException("The given node is null");
		}
		this.myGridNode = node;
		myLinks = new HashMap<Direction, VisualLink>();
	}
	
	/**
	 * Set the VisualLink into the given direction.
	 * @param dir The direction. Can be EAST, WEST, NORTH, or SOUTH.
	 * @param link The VisualLink.
	 */
	public void setVisualLink(final Direction dir, final VisualLink link) {
		myLinks.put(dir, link);
	}

	@Override
	public boolean isSelectable() {
		return true;
	}

	@Override
	public boolean isLocatedInPosition(final Point position, final int cellSize) {
		int centerX = myGridNode.getX() * cellSize + cellSize / 2;
		int centerY = myGridNode.getY() * cellSize + cellSize / 2;
		double radius = (double) cellSize / 2;

		int deltaX = centerX - position.x;
		int deltaY = centerY - position.y;
		double dist = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

		if (dist <= radius) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Put together the current image of the node.
	 * @param cellSize The size of a grid cell.
	 * @return The image representing the node.
	 */
	private BufferedImage assembleNodeImage(final int cellSize) {
		ArrayList<BufferedImage> parts = new ArrayList<BufferedImage>();
		parts.add(GraphicalAssets.getBodyImage(myGridNode.getDegree(), myGridNode.getGoal()));
		for (Direction dir : Direction.values()) {
			Link link = myGridNode.getLink(dir);
			if (link != null) {
				parts.add(GraphicalAssets.getPinImage(dir, link.getThickness(), myLinks.get(dir).isHighlighted()));
			}
		}
		parts.add(GraphicalAssets.getGoalImage(myGridNode.getGoal()));
		BufferedImage bi = new BufferedImage(cellSize, cellSize, BufferedImage.TYPE_INT_ARGB);
		Graphics biGraphics = bi.getGraphics();
		for (BufferedImage part : parts) {
			biGraphics.drawImage(part, 0, 0, cellSize, cellSize, null);
		}
		biGraphics.dispose();
		return bi;
	}

	@Override
	public void draw(Graphics2D g, final int cellSize) {
		final int x = myGridNode.getX() * cellSize;
		final int y = myGridNode.getY() * cellSize;

		if (this.selected) {
			g.setColor(Color.YELLOW);
			g.fillOval(myGridNode.getX() * cellSize, myGridNode.getY() * cellSize, cellSize, cellSize);
		} else if (this.highlighted) {
			g.setColor(new Color(234, 234, 234));
			g.fillOval(myGridNode.getX() * cellSize, myGridNode.getY() * cellSize, cellSize, cellSize);
		}
		BufferedImage bi = assembleNodeImage(cellSize);
		g.drawImage(bi, x, y, cellSize, cellSize, null);
	}
}
