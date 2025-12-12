package org.larssentech.fx.ui.gui.forward;

import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.larssentech.fx.ui.gui.shared.WidgetMaker;

public class ForwarderJPanel extends JPanel {

	private ForwarderUiController uiController;
	protected JTextArea outputArea;

	public ForwarderJPanel() {

		this.build();
	}

	private void build() {

		this.uiController = new ForwarderUiController(this);

		// For this, the JPanel
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setName(ForwarderReg.APP_NAME);

		// Target (input)
		this.add(WidgetMaker.makeTextField(ForwarderReg.FIELD_WIDTH, ForwarderReg.NAME_FOLDER, ForwarderReg.VAL_FOLDER, true));
		this.add(WidgetMaker.makeLabel(ForwarderReg.LBL_FOLDER));

		// Listening to Port
		this.add(WidgetMaker.makeTextField(ForwarderReg.FIELD_WIDTH, ForwarderReg.NAME_PORT, ForwarderReg.VAL_PORT, true));
		this.add(WidgetMaker.makeLabel(ForwarderReg.LBL_PORT));

		// Output scroll pane with text area
		this.add(WidgetMaker.makeLabel(ForwarderReg.LBL_OUTPUT));

		// Output area
		this.outputArea = WidgetMaker.makeTextArea(ForwarderReg.NAME_OUTPUT);
		this.add(WidgetMaker.makeJScrollPane(this.outputArea));

		// Separator
		this.add(new Label(ForwarderReg.SEPARATOR));

		// Start button
		this.add(WidgetMaker.makeButton(this, ForwarderReg.NAME_BUTTON, ForwarderReg.LBL_START, ForwarderReg.CMD_START, new ForwarderUiEvent(this)));

		// Formatting stuff
		this.uiController.setFormat();

		// ...show time!
		this.setVisible(true);
	}

	JTextArea getOutputArea() {
		return this.outputArea;
	}

	public ForwarderUiController getUiController() {
		return this.uiController;
	}

}