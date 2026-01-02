package org.larssentech.fx.ui.gui.upload;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.util.FileMan;
import org.larssentech.fx.ui.gui.shared.WidgetMaker;

public class UploaderJPanel extends JPanel implements FxConstants {

	private UploaderUiController uiController;
	private JTextArea outputArea;
	private JLabel anime;
	private JLabel anime0;
	private JScrollPane scroller;

	public JScrollPane getScroller() { return this.scroller; }

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
		this.add(WidgetMaker.makeTextField(UploaderReg.FIELD_WIDTH, UploaderReg.NAME_USER, FileMan.USER, false));
		this.add(WidgetMaker.makeLabel(UploaderReg.LBL_USER));

		// Send to
		this.add(WidgetMaker.makeTextField(UploaderReg.FIELD_WIDTH, UploaderReg.NAME_SEND_TO, FileMan.OTHER_USER, false));
		this.add(WidgetMaker.makeLabel(UploaderReg.LBL_SEND_TO));

		// Host
		this.add(WidgetMaker.makeTextField(UploaderReg.FIELD_WIDTH, UploaderReg.NAME_HOST, FileMan.HOST, false));
		this.add(WidgetMaker.makeLabel(UploaderReg.LBL_HOST));

		// Port
		this.add(WidgetMaker.makeTextField(UploaderReg.FIELD_WIDTH, UploaderReg.NAME_PORT, String.valueOf(FileMan.U_PORT), false));
		this.add(WidgetMaker.makeLabel(UploaderReg.LBL_PORT));

		// File
		this.add(WidgetMaker.makeTextField(UploaderReg.FIELD_WIDTH, UploaderReg.NAME_FOLDER, FileMan.U_FOLDER + SEP + FileMan.OTHER_USER, false));
		this.add(WidgetMaker.makeLabel(UploaderReg.LBL_FOLDER));

		// Output scroll pane with text area
		this.add(WidgetMaker.makeLabel(UploaderReg.LBL_OUTPUT));

		this.outputArea = WidgetMaker.makeTextArea(UploaderReg.NAME_OUTPUT);
		this.scroller = WidgetMaker.makeJScrollPane(this.outputArea);
		// this.scroller.setVisible(false);
		this.add(this.scroller);

		// Separator
		// this.add(new Label(UploaderReg.SEPARATOR));

		// Start button
		this.add(WidgetMaker.makeButton(this, UploaderReg.NAME_BUTTON, UploaderReg.LBL_START, UploaderReg.CMD_START, new UploaderUiEvent(this)));

		// Funky shit
		this.add(WidgetMaker.makeLabel("green_", new ImageIcon("green_.png")));
		this.add(WidgetMaker.makeLabel("green", new ImageIcon("green.gif")));

		// Final frame settings
		this.uiController.setFormat();

		// ...and show!
		this.setVisible(true);
	}

	public JLabel getAnime0() { return this.anime0; }

	JTextArea getOutputArea() { return this.outputArea; }

	public UploaderUiController getUiController() { return this.uiController; }

	public JComponent getAnime() { return this.anime; }
}