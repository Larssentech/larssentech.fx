package org.larssentech.fx.ui.gui.download;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.larssentech.fx.ui.gui.shared.WidgetMaker;

class DownloaderUiEvent implements ActionListener {

	private DownloaderJPanel owner;

	DownloaderUiEvent(final DownloaderJPanel owner) {

		this.owner = owner;
	}

	public void actionPerformed(ActionEvent event) {

		if (event.getActionCommand().equals(DownloaderReg.CMD_START)) {

			WidgetMaker.updateButton(this.owner, DownloaderReg.NAME_BUTTON, DownloaderReg.CMD_STOP, DownloaderReg.LBL_STOP);

			this.owner.getUiController().start();
		}

		if (event.getActionCommand().equals(DownloaderReg.CMD_STOP)) {

			WidgetMaker.updateButton(this.owner, DownloaderReg.NAME_BUTTON, DownloaderReg.CMD_START, DownloaderReg.LBL_START);

			this.owner.getUiController().stopClient();
		}

	}
}