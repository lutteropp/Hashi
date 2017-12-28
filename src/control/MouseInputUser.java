package control;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.AbstractDrawable;
import view.DrawingBoard;
import view.VisualGridNode;
import view.VisualLink;

/**
 * A class for managing the user mouse input.
 */
public class MouseInputUser extends MouseAdapter {
	/**
	 * The DrawingBoard to listen on.
	 */
	private DrawingBoard myBoard;

	/**
	 * The last mouse position when dragging.
	 */
	private Point lastMousePos;

	/**
	 * The last highlighted node or link.
	 */
	private AbstractDrawable lastHighlighted;
	/**
	 * The last selected node.
	 */
	private VisualGridNode lastSelectedNode;

	/**
	 * Creates a mouse listener for the given map view. After creation, the current
	 * tool has to be set manually.
	 * 
	 * @param map
	 *            The MapView to operate on.
	 */
	public MouseInputUser(final DrawingBoard board) {
		this.myBoard = board;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		VisualGridNode node = myBoard.getNearestNode(e.getPoint());
		if (e.getClickCount() == 1) {
			if (node == null) {
				if (lastSelectedNode != null) {
					lastSelectedNode.setSelected(false);
					lastSelectedNode = null;
				}
				// could still be a selected link
				VisualLink link = myBoard.getNearestLink(e.getPoint());
				if (link != null) { // clear the connection
					link.getMyLink().decreaseThickness();
				}
			} else {
				if (lastSelectedNode != null) {
					if (lastSelectedNode.getMyGridNode().isNeighborOf(node.getMyGridNode())) {
						// toggle the connection
						node.getMyGridNode().getLinkToNeighbor(lastSelectedNode.getMyGridNode()).toggle();
					}
					lastSelectedNode.setSelected(false);
					lastSelectedNode = null;
				} else {
					node.setSelected(true);
					lastSelectedNode = node;
				}
			}
		} else { // double-click
			if (lastSelectedNode != null) {
				lastSelectedNode.setSelected(false);
			}
			lastSelectedNode = null;
			
			if (node != null) {
				// fill the whole node with connections
				node.getMyGridNode().fillNode();
			}
		}
		if (myBoard.getMyBoard().hasWon()) {
			myBoard.setBackground(Color.WHITE);
			System.out.println("The game has been won.");
		}
		myBoard.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.myBoard.requestFocus();
		Point newPoint = e.getPoint();
		this.lastMousePos = newPoint;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		AbstractDrawable drawable = null;
		drawable = myBoard.getNearestDrawableItem(e.getPoint());
		if (lastHighlighted != null) {
			lastHighlighted.setHighlighted(false);
		}
		if (drawable != null) {
			drawable.setHighlighted(true);
		}
		lastHighlighted = drawable;
		myBoard.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.lastMousePos = e.getPoint();
		myBoard.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
