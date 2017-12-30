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
	 * Process a single-click event
	 * 
	 * @param e
	 *            The event
	 * @return {@value true}, if and only if the game state has changed
	 */
	private boolean processSingleClick(MouseEvent e) {
		boolean gameStateChanged = false;
		VisualGridNode node = gameBoardGUI.getNearestNode(e.getPoint());
		secondSelectedNode = null;
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
					gameStateChanged = true;
				}
			}
		} else { // a node has been clicked
			if (lastSelectedNode != null) { // this was the second node to be selected
				if (lastSelectedNode.getMyGridNode().isNeighborOf(node.getMyGridNode())) {
					// increase the connection
					boolean increased = gameBoardGUI.getMyBoard().increaseConnection(node.getMyGridNode(),
							lastSelectedNode.getMyGridNode());
					if (increased) {
						SoundAssets.connectSound.play();
						gameStateChanged = true;
					}
					// the second node has sucessfully been selected
					secondSelectedNode = node;
				}
				lastSelectedNode.setSelected(false);
				lastSelectedNode = null;
			} else { // this was the first node to be selected
				node.setSelected(true);
				lastSelectedNode = node;
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
			if (lastSelectedNode != null) {
				lastSelectedNode.setSelected(false);
				lastSelectedNode = null;
			}
		} else {
			if (lastSelectedNode == node) { // the node has already been selected
				// fill the whole node with connections
				boolean connected = gameBoardGUI.getMyBoard().fillNode(node.getMyGridNode());
				if (connected) {
					gameStateChanged = true;
					SoundAssets.connectSound.play();
				}
			}
		}

		if (lastSelectedNode != null) { // a node has been selected before
			if (lastSelectedNode == node) { // ... and it was this node
				// fill the whole node with connections
				boolean connected = gameBoardGUI.getMyBoard().fillNode(node.getMyGridNode());
				if (connected) {
					gameStateChanged = true;
					SoundAssets.connectSound.play();
				}
			}
			lastSelectedNode.setSelected(false);
			lastSelectedNode = null;
		} else { // no node has been selected before
			if (node != null && secondSelectedNode != node) {
				// fill the whole node with connections
				boolean connected = gameBoardGUI.getMyBoard().fillNode(node.getMyGridNode());
				if (connected) {
					gameStateChanged = true;
					SoundAssets.connectSound.play();
				}
				lastSelectedNode = null;
			} else if (node != null && secondSelectedNode == node) {
				node.setSelected(true);
				lastSelectedNode = node;
			}
		}
		return gameStateChanged;
	}

	/**
	 * Since the game state has changed, check whether the player has won the game.
	 */
	private void processChangedGameState() {
		if (gameBoardGUI.getMyBoard().hasWon()) {
			gameHasEnded = true;
			if (lastSelectedNode != null) {
				lastSelectedNode.setSelected(false);
				lastSelectedNode = null;
			}
			if (lastHighlighted != null) {
				lastHighlighted.setHighlighted(false);
				lastHighlighted = null;
			}
			secondSelectedNode = null;
			gameBoardGUI.repaint();
			mainWindow.showGameFinishedWindow();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (gameHasEnded) { // do nothing
			return;
		}
		boolean gameStateChanged = false;
		if (e.getClickCount() == 2) {
			gameStateChanged = processDoubleClick(e);
		} else { // single-click... or the first click of a double-click
			gameStateChanged = processSingleClick(e);
		}
		gameBoardGUI.repaint();

		if (gameStateChanged) {
			processChangedGameState();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (gameHasEnded) {
			return; // do nothing
		}
		boolean highlightingChanged = false;
		AbstractDrawable drawable = null;
		drawable = gameBoardGUI.getNearestDrawableItem(e.getPoint());
		if (lastHighlighted != null) {
			lastHighlighted.setHighlighted(false);
			highlightingChanged = true;
		}
		if (drawable != null) {
			drawable.setHighlighted(true);
			highlightingChanged = true;
		}
		lastHighlighted = drawable;
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
			if (node != null && node != lastPressedNode) {
				boolean increased = false;
				if (lastPressedNode.getMyGridNode().isNeighborOf(node.getMyGridNode())) {
					// increase the connection
					increased = gameBoardGUI.getMyBoard().increaseConnection(node.getMyGridNode(),
							lastPressedNode.getMyGridNode());
					if (increased) {
						SoundAssets.connectSound.play();
						gameBoardGUI.repaint();
					}
				}
				if (increased) {
					processChangedGameState();
				}
			}
		}
		lastPressedNode = null;
		secondSelectedNode = null;
	}
}
