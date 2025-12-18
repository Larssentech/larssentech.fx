package org.larssentech.fx.ui.gui.upload;

import org.larssentech.CTK.driver.EmbeddedApi;
import org.larssentech.CTK.settings.CTKSettings;
import org.larssentech.fx.client.upload.UploaderJobMan;
import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionProgress;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.Util;
import org.larssentech.fx.ui.gui.shared.SharedReg;
import org.larssentech.fx.ui.gui.shared.UiController;
import org.larssentech.fx.ui.gui.shared.WidgetMaker;
import org.larssentech.lib.CTK.objects.PUK;

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

		String path = CTKSettings.OTHER_USERS_PUB_KEY_LIB + FxConstants.SEP + otherUser + FxConstants.SEP + otherUser;

		PUK puk = new PUK(EmbeddedApi.exportPuk2Base64(path));

		if (puk.getStringValue().length() > 0) {

			puk.setEmail(otherUser);

			TransmissionSpec spec = new TransmissionSpec(host, port, folder + FxConstants.SEP + otherUser, user, puk, this.progress, false);
			this.jobMan = new UploaderJobMan(spec);
			this.jobMan.start();

			this.startReporting();
		}
	}

	private void startReporting() {

		Thread t = new Thread() {

			@Override
			public void run() {

				UploaderUiController.this.owner.getOutputArea().setText(null);

				while (true) {

					UploaderUiController.super.doControls(UploaderUiController.this.jobMan.isOn());

					String thisReport = UploaderUiController.this.jobMan.getProgress();

					if (!thisReport.equals(UploaderReg.NO_INFO)) {

						UploaderUiController.this.owner.getOutputArea().append(thisReport + UploaderReg.NEW_LINE);
						UploaderUiController.this.owner.getOutputArea().setCaretPosition(UploaderUiController.this.owner.getOutputArea().getText().length());
					}

					Util.pause(UploaderReg.SLEEP_MILLIS, "");

					if (!UploaderUiController.this.jobMan.isOn()) {

						UploaderUiController.this.doControls(UploaderUiController.this.jobMan.isOn());
						UploaderUiController.this.owner.getOutputArea().setCaretPosition(UploaderUiController.this.owner.getOutputArea().getText().length());
						break;
					}
				}
			}
		};
		t.start();
	}

	public void stopClient() {
		UploaderUiController.this.jobMan.stopConnect();
		UploaderUiController.this.owner.getOutputArea().setText(SharedReg.MSG_TAREA_STOPPED);

	}
}