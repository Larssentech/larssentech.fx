package org.larssentech.fx.ui.gui.upload;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionProgress;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.ui.gui.shared.UiController;
import org.larssentech.fx.ui.gui.shared.WidgetMaker;

class UploaderUiController extends UiController implements FxConstants {

	private final UploaderJPanel owner;
	private UploaderJobMan jobMan;
	private final TransmissionProgress progress = new TransmissionProgress();

	protected UploaderUiController(final UploaderJPanel owner) {
		super(owner);
		this.owner = owner;
	}

	void start() {
		String host = WidgetMaker.getText(this.owner, UploaderReg.NAME_HOST);
		int port = WidgetMaker.getNumber(this.owner, UploaderReg.NAME_PORT);
		String folder = WidgetMaker.getText(this.owner, UploaderReg.NAME_FOLDER);
		String user = WidgetMaker.getText(this.owner, UploaderReg.NAME_USER);
		String otherUser = WidgetMaker.getText(this.owner, UploaderReg.NAME_SEND_TO);

		// new
		TransmissionSpec spec = new TransmissionSpec(host, port, folder + SEP + otherUser, user, otherUser, this.progress, false);
		this.jobMan = new UploaderJobMan(spec);

		// this.jobMan = new UploaderJobMan(host, port, folder, user, otherUser);
		this.jobMan.start();

		this.startReporting();

	}

	private void startReporting() {

		Thread t = new Thread() {

			@Override
			public void run() {

				UploaderUiController.this.owner.getOutputArea().setText(null);

				while (true) {

					UploaderUiController.super.doControls(UploaderUiController.this.jobMan.isOn());

					String thisReport = UploaderUiController.this.jobMan.getClientProgress();

					if (!thisReport.equals(UploaderReg.NO_INFO)) {

						UploaderUiController.this.owner.getOutputArea().append(thisReport + UploaderReg.NEW_LINE);
						UploaderUiController.this.owner.getOutputArea().setCaretPosition(UploaderUiController.this.owner.getOutputArea().getText().length());
					}

					try {
						Thread.sleep(UploaderReg.SLEEP_MILLIS);

					} catch (InterruptedException ignored) {

					}

					if (!UploaderUiController.this.jobMan.isOn()) {

						UploaderUiController.this.doControls(UploaderUiController.this.jobMan.isOn());
						UploaderUiController.this.owner.getOutputArea().append("User stopped upload. Closing connections.");
						UploaderUiController.this.owner.getOutputArea().setCaretPosition(UploaderUiController.this.owner.getOutputArea().getText().length());
						break;
					}
				}

			}
		};
		t.start();
	}

	public void stopClient() {
		UploaderUiController.this.jobMan.stopClient();

	}
}
