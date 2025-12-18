package org.larssentech.fx.ui.gui.receive;

import org.larssentech.fx.ui.gui.shared.UiController;
import org.larssentech.fx.ui.gui.shared.WidgetMaker;
import org.larssentech.lib.basiclib.console.Out;

class ReceiverUiController extends UiController {

	private ReceiverJPanel owner;
	private ReceiverJobMan jobMan;

	ReceiverUiController(ReceiverJPanel owner) {
		super(owner);
		this.owner = owner;
	}

	void startReceiver() {

		String portS = WidgetMaker.getTextField(this.owner, ReceiverReg.NAME_PORT).getText();
		int port = Integer.parseInt(portS);

		String inFolder = WidgetMaker.getTextField(this.owner, ReceiverReg.NAME_HOST).getText();

		
		// Start job manager
		this.jobMan = new ReceiverJobMan(null);
		this.jobMan.start();

		this.startReporting();
	}

	private void startReporting() {

		Thread t = new Thread() {

			@Override
			public void run() {

				ReceiverUiController.this.owner.getOutputArea().setText(null);
				Out.pl("Rexeiver is on: " + ReceiverUiController.this.jobMan.isOn());

				while (ReceiverUiController.this.jobMan.isOn()) {

					String thisReport = ReceiverUiController.this.jobMan.getServerProgress();

					if (!thisReport.equals(ReceiverReg.NO_INFO)) {

						ReceiverUiController.this.owner.getOutputArea().append(thisReport + ReceiverReg.NEW_LINE);
						ReceiverUiController.this.owner.getOutputArea().setCaretPosition(ReceiverUiController.this.owner.getOutputArea().getText().length());
					}

					try {
						Thread.sleep(ReceiverReg.SLEEP_MILLIS);

					} catch (InterruptedException ignored) {

					}

					if (!ReceiverUiController.this.jobMan.isOn()) {

						ReceiverUiController.this.doControls(ReceiverUiController.this.jobMan.isOn());
						ReceiverUiController.this.owner.getOutputArea().setCaretPosition(ReceiverUiController.this.owner.getOutputArea().getText().length());
						break;
					}
				}

				ReceiverUiController.this.owner.getOutputArea().append(ReceiverReg.MSG_STOPPED + ReceiverReg.NEW_LINE);
				ReceiverUiController.this.owner.getOutputArea().setCaretPosition(ReceiverUiController.this.owner.getOutputArea().getText().length());

			}
		};
		t.start();
	}

	public void stopReceiver() {
		ReceiverUiController.this.jobMan.stopServer();

	}

}
