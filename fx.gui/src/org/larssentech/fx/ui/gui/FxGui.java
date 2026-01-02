package org.larssentech.fx.ui.gui;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.larssentech.fx.shared.util.FileMan;
import org.larssentech.fx.ui.gui.download.DownloaderJPanel;
import org.larssentech.fx.ui.gui.shared.SharedReg;
import org.larssentech.fx.ui.gui.shared.WidgetMaker;
import org.larssentech.fx.ui.gui.upload.UploaderJPanel;

public class FxGui extends JFrame {

	public static void main(String[] args) {

		try {
			if (new File("AppIcon.png").exists()) Thread.sleep(3000);

		} catch (InterruptedException ignored) {

		}

		if (args.length > 0) new FxGui(args[0]);
		else new FxGui("fx-gui.ini");
	}

	public FxGui(String iniFile) {

		new FileMan(iniFile);
		FileMan.createClientFolders();

		JTabbedPane tp = WidgetMaker.makeTabbedPane();

		// Add fully functional panels here
		// tp.add(new StreamUpJPanel(iniFile));
		// tp.add(new StreamDownJPanel(iniFile));
		tp.add(new UploaderJPanel(iniFile));
		tp.add(new DownloaderJPanel(iniFile));

		this.add(tp);

		this.setTitle(SharedReg.VERSION);
		this.setSize(430, 800);
		this.setResizable(false);
		this.setLocation(50, 50);

		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(SharedReg.COLOUR_BACKGROUND);
	}
}