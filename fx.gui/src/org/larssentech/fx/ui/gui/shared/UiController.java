package org.larssentech.fx.ui.gui.shared;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class UiController {

	private final JPanel owner;

	public UiController(JPanel owner) {
		this.owner = owner;
	}

	public void doControls(boolean on) {

		// Component[] c = this.owner.getComponents();

		// for (int i = 0; i < c.length; i++) if (!c[i].getClass().equals(Label.class)
		// && !c[i].getClass().equals(JButton.class)) c[i].setEnabled(!on);
	}

	public void setFormat() {

		this.owner.setBackground(SharedReg.COLOUR_BACKGROUND);
		this.owner.setForeground(SharedReg.COLOUR_TEXT);

		Component[] c = this.owner.getComponents();

		for (int i = 0; i < c.length; i++) {

			c[i].setBackground(SharedReg.COLOUR_BACKGROUND);
			c[i].setForeground(SharedReg.COLOUR_TEXT);

			if (c[i].getClass().equals(JButton.class)) {
				((JComponent) c[i]).setOpaque(true);
				c[i].setBackground(Color.DARK_GRAY);
				c[i].setForeground(Color.white);
				c[i].setPreferredSize(new Dimension(75, 20));
			}

			if (c[i].getClass().equals(Label.class)) {
				// c[i].setFont(ClientReg.FONT_LARGE);
				c[i].setForeground(SharedReg.COLOUR_LABEL);
			}
		}
	}

	public Component getComponent(String name) {

		Component[] c = this.owner.getComponents();

		for (int i = 0; i < c.length; i++) {

			if (c[i].getName().equals(name)) {

				return c[i];
			}
		}

		return null;
	}
}
