package view.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import control.game.KeyInputUser;
import control.game.MouseInputUser;
import jaco.mp3.player.MP3Player;
import model.base.Direction;
import model.base.GameBoard;
import model.base.GridNode;
import model.base.Link;
import view.MainWindow;

/**
 * The visual representation of the board where the game is played.
 * @author Sarah Lutteropp
 */
public class GameBoardGUI extends JPanel {
	/** The serialVersionUID that caused a warning when it was missing. */
	private static final long serialVersionUID = 8249093357328115400L;
	/** The VisualGridNodes to draw */
	private ArrayList<VisualGridNode> nodes;
	/** The VisualLinks to draw. */
	private ArrayList<VisualLink> links;
	/** Number of rows in the game grid. This is the same as the height of the grid. */
	private int rows;
	/** Number of columns in the game grid. This is the same as the width of the grid. */
	private int cols;
	/** The preferred size of a cell on the grid. */
	private final int preferredCellSize = 100;
	/** * Draw the grid or not. */
	private final boolean showGrid = false;
	/** The actual size of a cell on the grid. */
	private int cellSize;

	/** The actual game logic. */
	private GameBoard myBoard;

	/** The background music player */
	private MP3Player player;

	/**
	 * Start looping the background music.
	 */
	public void loopMusic() {
		player.setRepeat(true);
		player.play();
	}
	
	/**
	 * Stop looping the background music.
	 */
	public void stopMusic() {
		player.stop();
	}
	
	/**
	 * Create the main Hashiwokakero game board GUI.
	 * @param gameBoard The game board.
	 */
	public GameBoardGUI(GameBoard gameBoard, MainWindow mainWindow) {
		player = new MP3Player(new File("assets/DST-DayBreak.mp3"));
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

		MouseInputUser myListener = new MouseInputUser(this, mainWindow);
		addMouseListener(myListener);
		addMouseMotionListener(myListener);
		addKeyListener(new KeyInputUser(mainWindow));
		this.setPreferredSize(new Dimension(cols * preferredCellSize, rows * preferredCellSize));
		this.requestFocus();
	}

	/**
	 * @return The game board from model.base
	 */
	public GameBoard getMyBoard() {
		return myBoard;
	}

	/**
	 * Gets the nearest Drawable item. This is either a VisualLink or a VisualGridNode.
	 *
	 * @param p
	 *            the point
	 * @return the nearest Drawable item
	 */
	public AbstractDrawable getNearestDrawableItem(final Point p) {
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
	public VisualGridNode getNearestNode(final Point p) {
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
	public VisualLink getNearestLink(final Point p) {
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
			node.draw((Graphics2D) g, cellSize);
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