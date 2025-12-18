package org.larssentech.fx.ui.gui.download;

import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.larssentech.fx.ui.gui.shared.WidgetMaker;
import org.larssentech.lib.basiclib.settings.SettingsExtractor;

@SuppressWarnings("serial")
public class DownloaderJPanel extends JPanel {

	private DownloaderUiController uiController;
	private JTextArea outputArea;

	public DownloaderJPanel(String iniFile) {

		this.build(iniFile);
	}

	private void build(String iniFile) {

		this.uiController = new DownloaderUiController(this);

		// For this, the JPanel
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setName(DownloaderReg.APP_NAME);

		// User
		this.add(WidgetMaker.makeTextField(DownloaderReg.FIELD_WIDTH, DownloaderReg.NAME_USER, SettingsExtractor.extractThis4(iniFile, "USER"), false));
		this.add(WidgetMaker.makeLabel(DownloaderReg.LBL_USER));

		// Receive from
		this.add(WidgetMaker.makeTextField(DownloaderReg.FIELD_WIDTH, DownloaderReg.NAME_RECEIVE_FROM, SettingsExtractor.extractThis4(iniFile, "OTHER_USER"), false));
		this.add(WidgetMaker.makeLabel(DownloaderReg.LBL_RECEIVE_FROM));

		// Host
		this.add(WidgetMaker.makeTextField(DownloaderReg.FIELD_WIDTH, DownloaderReg.NAME_HOST, SettingsExtractor.extractThis4(iniFile, "HOST"), false));
		this.add(WidgetMaker.makeLabel(DownloaderReg.LBL_HOST));

		// Port
		this.add(WidgetMaker.makeTextField(DownloaderReg.FIELD_WIDTH, DownloaderReg.NAME_PORT, DownloaderReg.VAL_PORT, false));
		this.add(WidgetMaker.makeLabel(DownloaderReg.LBL_PORT));

		// File
		this.add(WidgetMaker.makeTextField(DownloaderReg.FIELD_WIDTH, DownloaderReg.NAME_FOLDER, DownloaderReg.VAL_FOLDER, false));
		this.add(WidgetMaker.makeLabel(DownloaderReg.LBL_FOLDER));

		// Output scroll pane with text area
		this.add(WidgetMaker.makeLabel(DownloaderReg.LBL_OUTPUT));

		this.outputArea = WidgetMaker.makeTextArea(DownloaderReg.NAME_OUTPUT);
		this.add(WidgetMaker.makeJScrollPane(this.outputArea));

		// Separator
		this.add(new Label(DownloaderReg.SEPARATOR));

		// Start button
		this.add(WidgetMaker.makeButton(this, DownloaderReg.NAME_BUTTON, DownloaderReg.LBL_START, DownloaderReg.CMD_START, new DownloaderUiEvent(this)));

		// Final frame settings
		this.uiController.setFormat();

		// ...and show!
		this.setVisible(true);
	}

	JTextArea getOutputArea() {
		return this.outputArea;
	}

	public DownloaderUiController getUiController() {
		return this.uiController;
	}
}