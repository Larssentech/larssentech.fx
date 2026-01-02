package org.larssentech.fx.ui.gui.download;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.util.FileMan;
import org.larssentech.fx.ui.gui.shared.WidgetMaker;

public class DownloaderJPanel extends JPanel implements FxConstants {

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
		this.add(WidgetMaker.makeTextField(DownloaderReg.FIELD_WIDTH, DownloaderReg.NAME_USER, FileMan.USER, false));
		this.add(WidgetMaker.makeLabel(DownloaderReg.LBL_USER));

		// Receive from
		this.add(WidgetMaker.makeTextField(DownloaderReg.FIELD_WIDTH, DownloaderReg.NAME_RECEIVE_FROM, FileMan.OTHER_USER, false));
		this.add(WidgetMaker.makeLabel(DownloaderReg.LBL_RECEIVE_FROM));

		// Host
		this.add(WidgetMaker.makeTextField(DownloaderReg.FIELD_WIDTH, DownloaderReg.NAME_HOST, FileMan.HOST, false));
		this.add(WidgetMaker.makeLabel(DownloaderReg.LBL_HOST));

		// Port
		this.add(WidgetMaker.makeTextField(DownloaderReg.FIELD_WIDTH, DownloaderReg.NAME_PORT, String.valueOf(FileMan.D_PORT), false));
		this.add(WidgetMaker.makeLabel(DownloaderReg.LBL_PORT));

		// File
		this.add(WidgetMaker.makeTextField(DownloaderReg.FIELD_WIDTH, DownloaderReg.NAME_FOLDER, FileMan.D_FOLDER + SEP + FileMan.OTHER_USER, false));
		this.add(WidgetMaker.makeLabel(DownloaderReg.LBL_FOLDER));

		// Output scroll pane with text area
		this.add(WidgetMaker.makeLabel(DownloaderReg.LBL_OUTPUT));

		this.outputArea = WidgetMaker.makeTextArea(DownloaderReg.NAME_OUTPUT);
		this.add(WidgetMaker.makeJScrollPane(this.outputArea));

		// Start button
		this.add(WidgetMaker.makeButton(this, DownloaderReg.NAME_BUTTON, DownloaderReg.LBL_START, DownloaderReg.CMD_START, new DownloaderUiEvent(this)));

		// Funky shit
		this.add(WidgetMaker.makeLabel("green_", new ImageIcon("green_.png")));
		this.add(WidgetMaker.makeLabel("green", new ImageIcon("green.gif")));

		// Final frame settings
		this.uiController.setFormat();

		// ...and show!
		this.setVisible(true);
	}

	JTextArea getOutputArea() { return this.outputArea; }

	public DownloaderUiController getUiController() { return this.uiController; }
}