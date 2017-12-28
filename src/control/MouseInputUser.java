package control;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.game.AbstractDrawable;
import view.game.DrawingBoardGUI;
import view.game.VisualGridNode;
import view.game.VisualLink;

/**
 * A class for managing the mouse input from the user.
 */
public class MouseInputUser extends MouseAdapter {
	/**
	 * The DrawingBoard to listen on.
	 */
	private DrawingBoardGUI myBoard;

	/**
	 * The last highlighted node or link.
	 */
	private AbstractDrawable lastHighlighted;
	/**
	 * The last selected node.
	 */
	private VisualGridNode lastSelectedNode;
	
	private boolean gameHasEnded;

	/**
	 * Creates a mouse listener for the drawing board.
	 * 
	 * @param board
	 *            The DrawingBoard to operate on.
	 */
	public MouseInputUser(final DrawingBoardGUI board) {
		this.myBoard = board;
		gameHasEnded = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (gameHasEnded) { // do nothing
			return;
		}
		
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
			gameHasEnded = true;
			System.out.println("Congratulations! You won the game.");
		}
		myBoard.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (gameHasEnded) {
			return; // do nothing
		}
		
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
}
