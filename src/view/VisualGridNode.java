package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Direction;
import model.GridNode;
import model.Link;

public class VisualGridNode extends AbstractDrawable {
	private GridNode myGridNode;
	private HashMap<Direction, VisualLink> myLinks;

	public GridNode getMyGridNode() {
		return myGridNode;
	}
	
	public VisualGridNode(GridNode node) {
		if (node == null) {
			throw new IllegalArgumentException("The given node is null");
		}
		this.myGridNode = node;
		myLinks = new HashMap<Direction, VisualLink>();
	}
	
	public void setVisualLink(Direction dir, VisualLink link) {
		myLinks.put(dir, link);
	}

	/**
	 * Get the link to the specified neighbor.
	 * 
	 * @param target
	 *            The neighbor.
	 * @return The link.
	 */
	public Link getLinkToNeighbor(VisualGridNode target) {
		return myGridNode.getLinkToNeighbor(target.myGridNode);
	}

	/**
	 * @param node
	 *            The node in question
	 * @return True, if and only if the node in question is a neighbor of this node.
	 */
	public boolean isNeighborOf(VisualGridNode node) {
		return myGridNode.isNeighborOf(node.myGridNode);
	}

	/**
	 * @return True, if and only if the node's goal is to be fully connected.
	 */
	public boolean goalIsFullNode() {
		return myGridNode.goalIsFullNode();
	}

	/**
	 * Fill ALL possible connections of the node.
	 */
	public void fillNode() {
		myGridNode.fillNode();
	}

	@Override
	public boolean isSelectable() {
		return true;
	}

	@Override
	public boolean isLocatedInPosition(Point position, int cellSize) {
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

	private BufferedImage assembleNodeImage(int cellSize) throws IOException {
		ArrayList<BufferedImage> parts = new ArrayList<BufferedImage>();
		parts.add(Assets.getBorderImage(myGridNode.getDegree(), myGridNode.getGoal()));
		for (Direction dir : Direction.values()) {
			Link link = myGridNode.getLink(dir);
			if (link != null) {
				parts.add(Assets.getPinImage(dir, link.getThickness(), myLinks.get(dir).isHighlighted()));
			}
		}
		parts.add(Assets.getGoalImage(myGridNode.getGoal()));
		BufferedImage bi = new BufferedImage(cellSize, cellSize, BufferedImage.TYPE_INT_ARGB);
		Graphics biGraphics = bi.getGraphics();
		for (BufferedImage part : parts) {
			biGraphics.drawImage(part, 0, 0, cellSize, cellSize, null);
		}
		biGraphics.dispose();

		return bi;
	}

	@Override
	public void draw(Graphics2D g, int cellSize) throws IOException {
		int x = myGridNode.getX() * cellSize;
		int y = myGridNode.getY() * cellSize;

		if (this.isSelected) {
			g.setColor(Color.YELLOW);
			g.fillOval(myGridNode.getX() * cellSize, myGridNode.getY() * cellSize, cellSize, cellSize);
		} else if (this.isHighlighted) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillOval(myGridNode.getX() * cellSize, myGridNode.getY() * cellSize, cellSize, cellSize);
		}
		BufferedImage bi = assembleNodeImage(cellSize);
		g.drawImage(bi, x, y, cellSize, cellSize, null);

		/*
		 * if (this.isSelected) { g.setColor(Color.DARK_GRAY); } else if
		 * (this.isHighlighted) { g.setColor(Color.LIGHT_GRAY); } else {
		 * g.setColor(Color.WHITE); } g.fillOval(myGridNode.getX() * cellSize,
		 * myGridNode.getY() * cellSize, cellSize, cellSize);
		 * 
		 * g.setColor(Color.BLACK); g.drawOval(myGridNode.getX() * cellSize,
		 * myGridNode.getY() * cellSize, cellSize, cellSize);
		 * 
		 * g.setColor(Color.MAGENTA); int centerX = myGridNode.getX() * cellSize +
		 * cellSize / 2; int centerY = myGridNode.getY() * cellSize + cellSize / 2;
		 * g.setFont(new Font("TimesRoman", Font.BOLD, 20));
		 * g.drawString(Integer.toString(myGridNode.getGoal()), centerX, centerY);
		 */
	}
}
