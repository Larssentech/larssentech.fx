package org.larssentech.fx.ui.gui.forward;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.larssentech.fx.ui.gui.shared.WidgetMaker;

public class ForwarderUiEvent implements ActionListener {

	private ForwarderJPanel owner;

	public ForwarderUiEvent(final ForwarderJPanel owner) {

		this.owner = owner;
	}

	public void actionPerformed(ActionEvent event) {

		if (event.getActionCommand().equals(ForwarderReg.CMD_START)) {

			JButton b = WidgetMaker.getButton(this.owner, ForwarderReg.NAME_BUTTON);
			b.setActionCommand(ForwarderReg.CMD_STOP);
			b.setText(ForwarderReg.LBL_STOP);

			// Start controller
			this.owner.getUiController().startReceiver();
		}

		if (event.getActionCommand().equals(ForwarderReg.CMD_STOP)) {

			JButton b = WidgetMaker.getButton(this.owner, ForwarderReg.NAME_BUTTON);
			b.setActionCommand(ForwarderReg.CMD_START);
			b.setText(ForwarderReg.LBL_START);

			this.owner.getUiController().stopReceiver();
		}
	}
}