package org.larssentech.fx.ui.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.larssentech.fx.shared.util.Util;
import org.larssentech.fx.ui.gui.download.DownloaderJPanel;
import org.larssentech.fx.ui.gui.shared.SharedReg;
import org.larssentech.fx.ui.gui.shared.WidgetMaker;
import org.larssentech.fx.ui.gui.upload.UploaderJPanel;

public class FxGui extends JFrame {

	public static void main(String[] args) {

		new FxGui();
	}

	public FxGui() {

		Util.createClientFolders();

		JTabbedPane tp = WidgetMaker.makeTabbedPane();

		// Add fully functional panels here
		// tp.add(new ForwarderJPanel());
		tp.add(new DownloaderJPanel());
		// tp.add(new ReceiverJPanel());
		tp.add(new UploaderJPanel());

		this.add(tp);

		this.setTitle(SharedReg.VERSION);
		this.setSize(355, 700);
		this.setLocation(50, 50);

		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(SharedReg.COLOUR_BACKGROUND);

	}
}