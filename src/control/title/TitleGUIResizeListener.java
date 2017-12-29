package control.title;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * A listener for resize events of the TitleGUI. It ensures that the text size on the title screen is not too small.
 * @author Sarah Lutteropp
 */
public class TitleGUIResizeListener extends ComponentAdapter {
	/**
	 * The buttons on the title screen
	 */
	private ArrayList<JButton> buttons;
	/**
	 * The title image
	 */
	private JLabel titleImage;
	
	/**
	 * Create a new listener for resize events of the TitleGUI.
	 * @param buttons The buttons on the title screen.
	 * @param titleImage The title image.
	 */
	public TitleGUIResizeListener(ArrayList<JButton> buttons, JLabel titleImage) {
		this.buttons = buttons;
		this.titleImage = titleImage;
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		for (JButton button : buttons) {
			button.setFont(
					button.getFont().deriveFont((float) (Math.min(button.getHeight() / 2, button.getWidth()) / 2)));
		}
		titleImage.setFont(buttons.get(0).getFont());
	}
}
