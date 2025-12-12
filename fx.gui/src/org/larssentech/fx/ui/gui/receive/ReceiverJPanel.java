package org.larssentech.fx.ui.gui.receive;

import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.larssentech.fx.ui.gui.shared.WidgetMaker;

public class ReceiverJPanel extends JPanel {

	private ReceiverUiController uiController;
	protected JTextArea outputArea;

	public ReceiverJPanel() {

		this.build();
	}

	private void build() {

		this.uiController = new ReceiverUiController(this);

		// For this, the JPanel
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setName(ReceiverReg.APP_NAME);

		// Target (input)
		this.add(WidgetMaker.makeTextField(ReceiverReg.FIELD_WIDTH, ReceiverReg.NAME_HOST, ReceiverReg.FILEXARE_SERVER, true));
		this.add(WidgetMaker.makeLabel(ReceiverReg.LBL_FOLDER));

		// Listening to Port
		this.add(WidgetMaker.makeTextField(ReceiverReg.FIELD_WIDTH, ReceiverReg.NAME_PORT, ReceiverReg.VAL_PORT, true));
		this.add(WidgetMaker.makeLabel(ReceiverReg.LBL_PORT));

		// Output scroll pane with text area
		this.add(WidgetMaker.makeLabel(ReceiverReg.LBL_OUTPUT));

		// Output area
		this.outputArea = WidgetMaker.makeTextArea(ReceiverReg.NAME_OUTPUT);
		this.add(WidgetMaker.makeJScrollPane(this.outputArea));

		// Separator
		this.add(new Label(ReceiverReg.SEPARATOR));

		// Start button
		this.add(WidgetMaker.makeButton(this, ReceiverReg.NAME_BUTTON, ReceiverReg.LBL_START, ReceiverReg.CMD_START, new ReceiverUiEvent(this)));

		// Formatting stuff
		this.uiController.setFormat();

		// ...show time!
		this.setVisible(true);
	}

	JTextArea getOutputArea() {
		return this.outputArea;
	}

	public ReceiverUiController getUiController() {
		return this.uiController;
	}

}