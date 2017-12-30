package control.game;

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

	/**
	 * The last pressed node.
	 */
	private VisualGridNode lastPressedNode;

	/**
	 * The current successfully selected second node. Used for improving the
	 * double-click behaviour.
	 */
	private VisualGridNode secondSelectedNode;

	/**
	 * Has the game ended?
	 */
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

	/**
	 * Try to remove a connection wire from a link
	 * 
	 * @param link
	 *            The link, can be null
	 * @return {@value true}, if and only if the link was not null and has been
	 *         successfully decreased
	 */
	private boolean tryDecreaseLink(VisualLink link) {
		boolean disconnected = false;
		if (link != null) {
			disconnected = gameBoardGUI.getMyBoard().decreaseConnection(link.getMyLink().getNode1(),
					link.getMyLink().getNode2());
			if (disconnected) {
				SoundAssets.disconnectSound.play();
				gameBoardGUI.repaint();
			}
		}
		return disconnected;
	}

	/**
	 * Try to fully connect the node with all its reachable neighbors
	 * 
	 * @param node
	 *            The node, can be null
	 * @return {@value true}, if and only if the node was not null and has been
	 *         connected to more neighbors than before
	 */
	private boolean tryFillingNode(VisualGridNode node) {
		boolean connected = false;
		if (node != null) {
			connected = gameBoardGUI.getMyBoard().fillNode(node.getMyGridNode());
			if (connected) {
				SoundAssets.connectSound.play();
				gameBoardGUI.repaint();
			}
		}
		return connected;
	}

	/**
	 * Try to connect two nodes
	 * 
	 * @param node1
	 *            The first node, can be null
	 * @param node2
	 *            The second node, can be null
	 * @return {@value true}, if and only if the nodes were not null and have been
	 *         successfully connected
	 */
	private boolean tryConnectingNodes(VisualGridNode node1, VisualGridNode node2) {
		boolean connected = false;
		if (node1 != null && node2 != null && node1 != node2) {
			if (node1.getMyGridNode().isNeighborOf(node2.getMyGridNode())) {
				// increase the connection
				connected = gameBoardGUI.getMyBoard().increaseConnection(node1.getMyGridNode(), node2.getMyGridNode());
				if (connected) {
					SoundAssets.connectSound.play();
					gameBoardGUI.repaint();
				}
			}
		}
		return connected;
	}

	/**
	 * Since the game state has changed, check whether the player has won the game.
	 */
	private void processChangedGameState() {
		gameBoardGUI.repaint();
		if (gameBoardGUI.getMyBoard().hasWon()) {
			gameHasEnded = true;
			resetLastSelectedNode();
			resetLastHighlighted();
			secondSelectedNode = null;
			mainWindow.showGameFinishedWindow();
		}
	}

	/**
	 * Reset the last selected node.
	 * 
	 * @return {@value true}, if and only if the last selected node was not
	 *         {@value null} before.
	 */
	private boolean resetLastSelectedNode() {
		boolean changed = false;
		if (lastSelectedNode != null) {
			lastSelectedNode.setSelected(false);
			lastSelectedNode = null;
			gameBoardGUI.repaint();
			changed = true;
		}
		return changed;
	}

	/**
	 * Reset the last highlighted drawable.
	 * 
	 * @return {@value true}, if and only if the last highlighted node was not
	 *         {@value null} before.
	 */
	private boolean resetLastHighlighted() {
		boolean changed = false;
		if (lastHighlighted != null) {
			lastHighlighted.setHighlighted(false);
			lastHighlighted = null;
			gameBoardGUI.repaint();
			changed = true;
		}
		return changed;
	}

	/**
	 * Set the last selected node.
	 * 
	 * @param node
	 *            The node to be selected.
	 * @return
	 */
	private void setLastSelectedNode(VisualGridNode node) {
		lastSelectedNode = node;
		if (node != null) {
			node.setSelected(true);
			gameBoardGUI.repaint();
		}
	}

	/**
	 * Process a single-click event
	 * 
	 * @param e
	 *            The event
	 * @return {@value true}, if and only if the game state has changed
	 */
	private boolean processSingleClick(MouseEvent e) {
		boolean gameStateChanged = false;
		VisualGridNode node = gameBoardGUI.getNearestNode(e.getPoint());
		if (node == null) {
			resetLastSelectedNode();
			// could still be a selected link
			gameStateChanged = tryDecreaseLink(gameBoardGUI.getNearestLink(e.getPoint()));
		} else { // a node has been clicked
			if (lastSelectedNode != null) { // this was the second node to be selected
				gameStateChanged = tryConnectingNodes(lastSelectedNode, node);
				if (gameStateChanged) {
					// the second node has sucessfully been selected
					secondSelectedNode = node;
				}
				resetLastSelectedNode();
			} else { // this was the first node to be selected
				setLastSelectedNode(node);
			}
		}
		return gameStateChanged;
	}

	/**
	 * Process a double-click event
	 * 
	 * @param e
	 *            The event
	 * @return {@value true}, if and only if the game state has changed
	 */
	private boolean processDoubleClick(MouseEvent e) {
		boolean gameStateChanged = false;
		VisualGridNode node = gameBoardGUI.getNearestNode(e.getPoint());
		if (node == null) {
			// could still be a selected link
			gameStateChanged = tryDecreaseLink(gameBoardGUI.getNearestLink(e.getPoint()));
		} else {
			if (lastSelectedNode == node) { // the node has already been selected as first node
				// fill the whole node with connections
				gameStateChanged = tryFillingNode(node);
			} else if (secondSelectedNode != node) { // the second node in the last new connection was not this node
				// fill the whole node with connections
				gameStateChanged = tryFillingNode(node);
			} else { // for improved double-click behavior
				setLastSelectedNode(node);
			}
		}
		if (gameStateChanged) {
			resetLastSelectedNode();
		}
		secondSelectedNode = null;
		return gameStateChanged;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (gameHasEnded) {
			return; // do nothing
		}
		boolean gameStateChanged = false;
		if (e.getClickCount() == 2) {
			gameStateChanged = processDoubleClick(e);
		} else { // single-click... or the first click of a double-click
			gameStateChanged = processSingleClick(e);
		}
		if (gameStateChanged) {
			processChangedGameState();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (gameHasEnded) {
			return; // do nothing
		}
		boolean highlightingChanged = resetLastHighlighted();
		AbstractDrawable drawable = gameBoardGUI.getNearestDrawableItem(e.getPoint());
		if (drawable != null) {
			drawable.setHighlighted(true);
			lastHighlighted = drawable;
			highlightingChanged = true;
		}
		if (highlightingChanged) {
			gameBoardGUI.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (gameHasEnded) {
			return; // do nothing
		}
		lastPressedNode = gameBoardGUI.getNearestNode(e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (gameHasEnded) {
			return; // do nothing
		}
		if (lastPressedNode != null) {
			VisualGridNode node = gameBoardGUI.getNearestNode(e.getPoint());
			boolean gameStateChanged = tryConnectingNodes(lastPressedNode, node);
			if (gameStateChanged) {
				resetLastSelectedNode();
				processChangedGameState();
			}
		}
		lastPressedNode = null;
	}
}
