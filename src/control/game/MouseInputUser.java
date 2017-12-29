package control.game;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import assets.SoundAssets;
import view.ApplicationWindow;
import view.game.AbstractDrawable;
import view.game.GameBoardGUI;
import view.game.VisualGridNode;
import view.game.VisualLink;

/**
 * A class for managing the mouse input from the user.
 */
public class MouseInputUser extends MouseAdapter {
	/**
	 * The DrawingBoard to listen on.
	 */
	private GameBoardGUI gameBoardGUI;

	/**
	 * The main window.
	 */
	private ApplicationWindow mainWindow;

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
	 * @param mainWindow
	 *            The main window of the program.
	 */
	public MouseInputUser(final GameBoardGUI board, ApplicationWindow mainWindow) {
		this.gameBoardGUI = board;
		this.mainWindow = mainWindow;
		gameHasEnded = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (gameHasEnded) { // do nothing
			return;
		}

		VisualGridNode node = gameBoardGUI.getNearestNode(e.getPoint());
		if (e.getClickCount() == 1) {
			if (node == null) {
				if (lastSelectedNode != null) {
					lastSelectedNode.setSelected(false);
					lastSelectedNode = null;
				}
				// could still be a selected link
				VisualLink link = gameBoardGUI.getNearestLink(e.getPoint());
				if (link != null) { // decrease the connection
					boolean disconnected = gameBoardGUI.getMyBoard().decreaseConnection(link.getMyLink().getNode1(),
							link.getMyLink().getNode2());
					if (disconnected) {
						SoundAssets.disconnectSound.play();
					}
				}
			} else { // a node has been clicked
				if (lastSelectedNode != null) { // this was the second node to be selected
					if (lastSelectedNode.getMyGridNode().isNeighborOf(node.getMyGridNode())) {
						// toggle the connection
						boolean increased = gameBoardGUI.getMyBoard().increaseConnection(node.getMyGridNode(),
								lastSelectedNode.getMyGridNode());
						if (increased) {
							SoundAssets.connectSound.play();
						}
					}
					lastSelectedNode.setSelected(false);
					lastSelectedNode = null;
				} else { // this was the first node to be selected
					node.setSelected(true);
					lastSelectedNode = node;
				}
			}
		} else { // double-click
			if (lastSelectedNode != null) {
				lastSelectedNode.setSelected(false);
				if (lastSelectedNode == node) {
					// fill the whole node with connections
					boolean connected = gameBoardGUI.getMyBoard().fillNode(node.getMyGridNode());
					if (connected) {
						SoundAssets.connectSound.play();
					}
				}
			}
			lastSelectedNode = null;
		}
		if (gameBoardGUI.getMyBoard().hasWon()) {
			gameBoardGUI.setBackground(Color.WHITE);
			gameHasEnded = true;
			if (lastSelectedNode != null) {
				lastSelectedNode.setSelected(false);
				lastSelectedNode = null;
			}
			if (lastHighlighted != null) {
				lastHighlighted.setHighlighted(false);
				lastHighlighted = null;
			}
			mainWindow.showGameFinishedWindow();
		}
		gameBoardGUI.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (gameHasEnded) {
			return; // do nothing
		}

		AbstractDrawable drawable = null;
		drawable = gameBoardGUI.getNearestDrawableItem(e.getPoint());
		if (lastHighlighted != null) {
			lastHighlighted.setHighlighted(false);
		}
		if (drawable != null) {
			drawable.setHighlighted(true);
		}
		lastHighlighted = drawable;
		gameBoardGUI.repaint();
	}
}
