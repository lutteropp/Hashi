package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import control.MouseInputUser;
import model.base.Direction;
import model.base.GameBoard;
import model.base.GridNode;
import model.base.Link;

public class DrawingBoard extends JPanel {
	/**
	 * The serialVersionUID that caused a warning when it was missing.
	 */
	private static final long serialVersionUID = 8249093357328115400L;
	private ArrayList<VisualGridNode> nodes;
	private ArrayList<VisualLink> links;
	private int rows;
	private int cols;
	private final int preferredCellSize = 10;
	private boolean showGrid = false;
	private int cellSize;

	private GameBoard myBoard;

	public DrawingBoard(GameBoard gameBoard) {
		myBoard = gameBoard;
		setOpaque(true);
		cols = gameBoard.getWidth();
		rows = gameBoard.getHeight();
		nodes = new ArrayList<VisualGridNode>();
		ArrayList<GridNode> realNodes = gameBoard.getAllNodes();

		HashMap<Link, VisualLink> linksMap = new HashMap<Link, VisualLink>();
		links = new ArrayList<VisualLink>();
		ArrayList<Link> realLinks = gameBoard.getAllLinks();
		for (Link realLink : realLinks) {
			VisualLink visualLink = new VisualLink(realLink);
			linksMap.put(realLink, visualLink);
			links.add(visualLink);
		}

		for (GridNode node : realNodes) {
			VisualGridNode visualNode = new VisualGridNode(node);
			for (Direction dir : Direction.values()) {
				Link realLink = visualNode.getMyGridNode().getLink(dir);
				VisualLink visualLink = linksMap.get(realLink);
				visualNode.setVisualLink(dir, visualLink);
			}
			nodes.add(visualNode);
		}

		MouseInputUser myListener = new MouseInputUser(this);
		addMouseListener(myListener);
		addMouseMotionListener(myListener);
		this.setPreferredSize(new Dimension(cols * preferredCellSize, rows * preferredCellSize));
		this.requestFocus();
	}

	public GameBoard getMyBoard() {
		return myBoard;
	}

	/**
	 * Gets the nearest Drawable item.
	 *
	 * @param p
	 *            the point
	 * @return the nearest Drawable item
	 */
	public AbstractDrawable getNearestDrawableItem(Point p) {
		for (AbstractDrawable d : nodes) {
			if (d.isLocatedInPosition(p, cellSize)) {
				return d;
			}
		}
		for (AbstractDrawable d : links) {
			if (d.isLocatedInPosition(p, cellSize)) {
				return d;
			}
		}
		return null;
	}

	/**
	 * Gets the nearest VisualGridNode.
	 *
	 * @param p
	 *            the point
	 * @return the nearest VisualGridNode
	 */
	public VisualGridNode getNearestNode(Point p) {
		for (VisualGridNode d : nodes) {
			if (d.isLocatedInPosition(p, cellSize)) {
				return d;
			}
		}
		return null;
	}

	/**
	 * Gets the nearest VisualLink.
	 *
	 * @param p
	 *            the point
	 * @return the nearest VisualLink
	 */
	public VisualLink getNearestLink(Point p) {
		for (VisualLink d : links) {
			if (d.isLocatedInPosition(p, cellSize)) {
				return d;
			}
		}
		return null;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		cellSize = Math.min(this.getWidth() / cols, this.getHeight() / rows);
		int xStart = 0;
		int yStart = 0;
		for (VisualLink link : links) {
			link.draw((Graphics2D) g, cellSize);
		}
		for (VisualGridNode node : nodes) {
			try {
				node.draw((Graphics2D) g, cellSize);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (showGrid) {
			// draw the grid
			for (int i = 0; i < cols; ++i) {
				for (int j = 0; j < rows; ++j) {
					int x = xStart + i * cellSize;
					int y = yStart + j * cellSize;
					g.drawRect(x, y, cellSize, cellSize);
				}
			}
		}
	}
}