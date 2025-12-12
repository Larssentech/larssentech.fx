package org.larssentech.fx.ui.gui.receive;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.larssentech.fx.ui.gui.shared.WidgetMaker;

class ReceiverUiEvent implements ActionListener {

	private ReceiverJPanel owner;

	ReceiverUiEvent(final ReceiverJPanel owner) {

		this.owner = owner;
	}

	public void actionPerformed(ActionEvent event) {

		if (event.getActionCommand().equals(ReceiverReg.CMD_START)) {

			JButton b = WidgetMaker.getButton(this.owner, ReceiverReg.NAME_BUTTON);
			b.setActionCommand(ReceiverReg.CMD_STOP);
			b.setText(ReceiverReg.LBL_STOP);

			// Start controller
			this.owner.getUiController().startReceiver();
		}

		if (event.getActionCommand().equals(ReceiverReg.CMD_STOP)) {

			JButton b = WidgetMaker.getButton(this.owner, ReceiverReg.NAME_BUTTON);
			b.setActionCommand(ReceiverReg.CMD_START);
			b.setText(ReceiverReg.LBL_START);

			this.owner.getUiController().stopReceiver();
		}
	}
}