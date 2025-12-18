package org.larssentech.fx.ui.gui.forward;

import org.larssentech.fx.ui.gui.shared.UiController;
import org.larssentech.fx.ui.gui.shared.WidgetMaker;
import org.larssentech.lib.basiclib.console.Out;

class ForwarderUiController extends UiController {

	private ForwarderJPanel owner;
	private ForwarderJobMan jobMan;

	ForwarderUiController(ForwarderJPanel owner) {
		super(owner);
		this.owner = owner;
	}

	void startReceiver() {

		String portS = WidgetMaker.getTextField(this.owner, ForwarderReg.NAME_PORT).getText();
		int port = Integer.parseInt(portS);

		String inFolder = WidgetMaker.getTextField(this.owner, ForwarderReg.NAME_FOLDER).getText();

		// Start job manager
		this.jobMan = new ForwarderJobMan(null);
		this.jobMan.start();

		this.startReporting();
	}

	private void startReporting() {

		Thread t = new Thread() {

			@Override
			public void run() {

				ForwarderUiController.this.owner.getOutputArea().setText(null);
				Out.pl("Rexeiver is on: " + ForwarderUiController.this.jobMan.isOn());

				while (ForwarderUiController.this.jobMan.isOn()) {

					String thisReport = ForwarderUiController.this.jobMan.getServerProgress();

					if (!thisReport.equals(ForwarderReg.NO_INFO)) {

						ForwarderUiController.this.owner.getOutputArea().append(thisReport + ForwarderReg.NEW_LINE);
						ForwarderUiController.this.owner.getOutputArea().setCaretPosition(ForwarderUiController.this.owner.getOutputArea().getText().length());
					}

					try {
						Thread.sleep(ForwarderReg.SLEEP_MILLIS);

					} catch (InterruptedException ignored) {

					}

					if (!ForwarderUiController.this.jobMan.isOn()) {

						ForwarderUiController.this.doControls(ForwarderUiController.this.jobMan.isOn());
						ForwarderUiController.this.owner.getOutputArea().setCaretPosition(ForwarderUiController.this.owner.getOutputArea().getText().length());
						break;
					}
				}

				ForwarderUiController.this.owner.getOutputArea().append(ForwarderReg.MSG_STOPPED + ForwarderReg.NEW_LINE);
				ForwarderUiController.this.owner.getOutputArea().setCaretPosition(ForwarderUiController.this.owner.getOutputArea().getText().length());

			}
		};
		t.start();
	}

	public void stopReceiver() {
		ForwarderUiController.this.jobMan.stopServer();

	}

}
