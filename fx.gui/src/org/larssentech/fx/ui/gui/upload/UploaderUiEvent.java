package org.larssentech.fx.ui.gui.upload;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.larssentech.fx.ui.gui.shared.WidgetMaker;

class UploaderUiEvent implements ActionListener {

	private UploaderJPanel owner;

	UploaderUiEvent(final UploaderJPanel owner) {

		this.owner = owner;
	}

	public void actionPerformed(ActionEvent event) {

		if (event.getActionCommand().equals(UploaderReg.CMD_START)) {

			WidgetMaker.updateButton(this.owner, UploaderReg.NAME_BUTTON, UploaderReg.CMD_STOP, UploaderReg.LBL_STOP);

			this.owner.getUiController().start();
		}

		if (event.getActionCommand().equals(UploaderReg.CMD_STOP)) {

			WidgetMaker.updateButton(this.owner, UploaderReg.NAME_BUTTON, UploaderReg.CMD_START, UploaderReg.LBL_START);

			this.owner.getUiController().stopClient();
		}
	}
}