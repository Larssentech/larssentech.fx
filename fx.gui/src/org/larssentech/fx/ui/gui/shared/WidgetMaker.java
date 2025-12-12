package org.larssentech.fx.ui.gui.shared;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class WidgetMaker {

	/**
	 * We pass here the super class because all the methods we need are both in the
	 * super class and the subclass, thus removing the need for two methods, for two
	 * different subclasses, that do the same thing.
	 * 
	 * @param owner
	 * @param label
	 * @param actionCommand
	 * @return
	 */
	public static JButton makeButton(JPanel owner, String name, String label, String actionCommand, ActionListener action) {

		JButton b = new JButton(label);

		b.setName(name);
		b.setActionCommand(actionCommand);
		b.addActionListener(action);
		b.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

		return b;
	}

	public static Label makeLabel(String text) {

		return new Label(text);
	}

	public static JTextField makeTextField(int i, String name, String text, boolean editable) {

		JTextField t = new JTextField(i);

		t.setName(name);
		t.setText(text);
		t.setEditable(editable);
		// t.setPreferredSize(new Dimension(1, 40));

		t.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		t.setBackground(null);

		return t;
	}

	public static void setFileChooserModeDir(boolean b) {

		if (SharedReg.OS_NAME.equals(SharedReg.MAC_OS)) System.setProperty(SharedReg.APPLE_FILE_CHOOSER, String.valueOf(b));
	}

	public static JTextArea makeTextArea(String name) {

		JTextArea outputArea = new JTextArea(SharedReg.MSG_TAREA_STOPPED, 20, 32);

		outputArea.setName(name);
		outputArea.setBackground(SharedReg.COLOUR_BACKGROUND);
		outputArea.setForeground(SharedReg.COLOUR_TEXT);
		outputArea.setFont(SharedReg.FONT_SMALL);
		outputArea.setDoubleBuffered(true);
		outputArea.setEditable(false);
		outputArea.setMargin(new Insets(5, 5, 5, 5));

		return outputArea;
	}

	public static JScrollPane makeJScrollPane(JTextArea outputArea) {

		JScrollPane scrollPane = new JScrollPane(outputArea);

		scrollPane.setName(SharedReg.NAME_SERVER_SCROLL_PANE);

		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

		scrollPane.getVerticalScrollBar().setBackground(SharedReg.COLOUR_BACKGROUND);
		scrollPane.getVerticalScrollBar().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		scrollPane.setPreferredSize(new Dimension(300, 400));

		return scrollPane;
	}

	public static String getFolder(JPanel owner) {

		// Boilerplate stuff
		final JFileChooser j = new JFileChooser();

		j.setCurrentDirectory(new File(SharedReg.HOME));
		j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int returnVal = j.showOpenDialog(owner);

		if (returnVal == JFileChooser.APPROVE_OPTION) return j.getSelectedFile().getAbsolutePath();

		return "";
	}

	public static JTabbedPane makeTabbedPane() {

		// Fancy shit to make it look cool
		UIManager.put("TabbedPane.selected", new Color(0, 135, 0));

		Insets insets = UIManager.getInsets("TabbedPane.contentBorderInsets");
		insets.top = -1;
		insets.left = -1;
		insets.right = -1;
		insets.bottom = -1;
		UIManager.put("TabbedPane.contentBorderInsets", insets);

		// The actual tabbed pane
		JTabbedPane tp = new JTabbedPane();

		tp.setBackground(Color.darkGray);
		tp.setForeground(Color.white);
		tp.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		return tp;
	}

	public static Component getComponent(JPanel owner, String componentName) {
		Component[] c = owner.getComponents();

		for (Component element : c) {
			if (null != element.getName()) if (element.getName().equals(componentName)) return element;
		}

		return null;
	}

	public static JButton getButton(JPanel owner, String name) {
		Component[] c = owner.getComponents();

		for (Component element : c) {
			if (element.getClass().equals(JButton.class) && element.getName().equals(name)) return (JButton) element;
		}

		return null;
	}

	public static JTextField getTextField(JPanel owner, String string) {

		Component[] c = owner.getComponents();

		for (Component element : c) {
			if (element.getClass().equals(JTextField.class) && element.getName().equals(string)) return (JTextField) element;
		}

		return null;
	}

	public static String getText(JPanel owner, String widgetName) {

		JTextField h = WidgetMaker.getTextField(owner, widgetName);
		return h.getText();
	}

	public static int getNumber(JPanel owner, String widgetName) {
		JTextField p = WidgetMaker.getTextField(owner, widgetName);
		return Integer.parseInt(p.getText());
	}

	public static void updateButton(JPanel owner, String buttonName, String newCommand, String newLabel) {

		JButton b = WidgetMaker.getButton(owner, buttonName);
		b.setActionCommand(newCommand);
		b.setText(newLabel);

	}
}
