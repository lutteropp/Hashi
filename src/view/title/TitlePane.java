package view.title;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePane extends JPanel {
	public TitlePane() {
		this.setLayout(new GridBagLayout());
		this.add(new JLabel("Hashiwokakero"));
	}
}
