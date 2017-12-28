package control.game;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import jaco.mp3.player.MP3Player;
import view.game.AbstractDrawable;
import view.game.GameBoardGUI;
import view.game.VisualGridNode;
import view.game.VisualLink;

// The button sound effect files are under the Creative Commons License and from https://www.soundjay.com

/**
 * A class for managing the mouse input from the user.
 */
public class MouseInputUser extends MouseAdapter {
	/**
	 * The DrawingBoard to listen on.
	 */
	private GameBoardGUI myBoard;

	/**
	 * The last highlighted node or link.
	 */
	private AbstractDrawable lastHighlighted;
	/**
	 * The last selected node.
	 */
	private VisualGridNode lastSelectedNode;

	private MP3Player connectSound;
	private MP3Player disconnectSound;

	private boolean gameHasEnded;

	/**
	 * Creates a mouse listener for the drawing board.
	 * 
	 * @param board
	 *            The DrawingBoard to operate on.
	 */
	public MouseInputUser(final GameBoardGUI board) {
		this.myBoard = board;
		gameHasEnded = false;
		connectSound = new MP3Player(new File("assets/button-20.mp3"));
		disconnectSound = new MP3Player(new File("assets/button-46.mp3"));
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
				if (link != null) { // decrease the connection
					boolean disconnected = myBoard.getMyBoard().decreaseConnection(link.getMyLink().getNode1(),
							link.getMyLink().getNode2());
					if (disconnected) {
						disconnectSound.play();
					}
				}
			} else {
				if (lastSelectedNode != null) {
					if (lastSelectedNode.getMyGridNode().isNeighborOf(node.getMyGridNode())) {
						// toggle the connection
						boolean increased = myBoard.getMyBoard().increaseConnection(node.getMyGridNode(),
								lastSelectedNode.getMyGridNode());
						if (increased) {
							connectSound.play();
						}
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
				boolean connected = myBoard.getMyBoard().fillNode(node.getMyGridNode());
				if (connected) {
					connectSound.play();
				}
			}
		}
		if (myBoard.getMyBoard().hasWon()) {
			myBoard.setBackground(Color.WHITE);
			gameHasEnded = true;
			if (lastSelectedNode != null) {
				lastSelectedNode.setSelected(false);
				lastSelectedNode = null;
			}
			if (lastHighlighted != null) {
				lastHighlighted.setHighlighted(false);
				lastHighlighted = null;
			}
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
