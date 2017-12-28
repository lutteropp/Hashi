package control.title;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

public class TitleGUIResizeListener extends ComponentAdapter {
	private ArrayList<JButton> buttons;
	private JLabel titleImage;
	
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
