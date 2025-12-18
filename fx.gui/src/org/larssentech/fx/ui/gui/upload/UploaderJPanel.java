package org.larssentech.fx.ui.gui.upload;

import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.larssentech.fx.ui.gui.shared.WidgetMaker;
import org.larssentech.lib.basiclib.settings.SettingsExtractor;

public class UploaderJPanel extends JPanel {

	private UploaderUiController uiController;
	private JTextArea outputArea;

	public UploaderJPanel(String iniFile) {

		this.build(iniFile);
	}

	private void build(String iniFile) {

		this.uiController = new UploaderUiController(this);

		// For this, the JPanel
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setName(UploaderReg.APP_NAME);

		// User
		this.add(WidgetMaker.makeTextField(UploaderReg.FIELD_WIDTH, UploaderReg.NAME_USER, SettingsExtractor.extractThis4(iniFile, "USER"), false));
		this.add(WidgetMaker.makeLabel(UploaderReg.LBL_USER));

		// Send to
		this.add(WidgetMaker.makeTextField(UploaderReg.FIELD_WIDTH, UploaderReg.NAME_SEND_TO, SettingsExtractor.extractThis4(iniFile, "OTHER_USER"), false));
		this.add(WidgetMaker.makeLabel(UploaderReg.LBL_SEND_TO));

		// Host
		this.add(WidgetMaker.makeTextField(UploaderReg.FIELD_WIDTH, UploaderReg.NAME_HOST, SettingsExtractor.extractThis4(iniFile, "HOST"), false));
		this.add(WidgetMaker.makeLabel(UploaderReg.LBL_HOST));

		// Port
		this.add(WidgetMaker.makeTextField(UploaderReg.FIELD_WIDTH, UploaderReg.NAME_PORT, UploaderReg.VAL_PORT, false));
		this.add(WidgetMaker.makeLabel(UploaderReg.LBL_PORT));

		// File
		this.add(WidgetMaker.makeTextField(UploaderReg.FIELD_WIDTH, UploaderReg.NAME_FOLDER, UploaderReg.VAL_FOLDER, false));
		this.add(WidgetMaker.makeLabel(UploaderReg.LBL_FOLDER));

		// Output scroll pane with text area
		this.add(WidgetMaker.makeLabel(UploaderReg.LBL_OUTPUT));

		this.outputArea = WidgetMaker.makeTextArea(UploaderReg.NAME_OUTPUT);
		this.add(WidgetMaker.makeJScrollPane(this.outputArea));

		// Separator
		this.add(new Label(UploaderReg.SEPARATOR));

		// Start button
		this.add(WidgetMaker.makeButton(this, UploaderReg.NAME_BUTTON, UploaderReg.LBL_START, UploaderReg.CMD_START, new UploaderUiEvent(this)));

		// Final frame settings
		this.uiController.setFormat();

		// ...and show!
		this.setVisible(true);
	}

	JTextArea getOutputArea() {
		return this.outputArea;
	}

	public UploaderUiController getUiController() {
		return this.uiController;
	}
}