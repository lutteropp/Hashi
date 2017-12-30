package control.title;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class OrangeButtonListener implements MouseListener {

	/** The button to listen to */
	private JButton myButton;
	private Color defaultColor;

	/**
	 * Create a listener that ensures orange mouseover effects
	 * 
	 * @param myButton
	 *            The button to listen to
	 */
	public OrangeButtonListener(JButton myButton) {
		this.myButton = myButton;
		this.defaultColor = myButton.getBackground();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (myButton.isEnabled()) {
			myButton.setBackground(new Color(255, 165, 0)); // darker orange
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (myButton.isEnabled()) {
			myButton.setBackground(Color.ORANGE);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		myButton.setBackground(defaultColor);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
