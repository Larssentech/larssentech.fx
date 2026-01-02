package org.larssentech.fx.ui.gui.upload;

import org.larssentech.CTK.driver.EmbeddedApi;
import org.larssentech.CTK.settings.CTKSettings;
import org.larssentech.fx.client.upload.manager.UploaderJobMan;
import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionProgress;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.FileMan;
import org.larssentech.fx.shared.util.Util;
import org.larssentech.fx.ui.gui.shared.SharedReg;
import org.larssentech.fx.ui.gui.shared.UiController;
import org.larssentech.fx.ui.gui.shared.WidgetMaker;
import org.larssentech.lib.CTK.objects.PUK;

class UploaderUiController extends UiController implements FxConstants {

	private final UploaderJPanel owner;
	private UploaderJobMan jobMan;
	private final TransmissionProgress progress = new TransmissionProgress(U_LOG);

	protected UploaderUiController(final UploaderJPanel owner) {
		super(owner);
		this.owner = owner;
	}

	void start() {

		String path = CTKSettings.OTHER_USERS_PUB_KEY_LIB + FxConstants.SEP + FileMan.OTHER_USER + FxConstants.SEP + FileMan.OTHER_USER;

		PUK puk = new PUK(EmbeddedApi.exportPuk2Base64(path));

		if (puk.getStringValue().length() > 0) {

			puk.setEmail(FileMan.OTHER_USER);

			TransmissionSpec spec = new TransmissionSpec(FileMan.HOST, FileMan.U_PORT, FileMan.U_FOLDER + FxConstants.SEP + FileMan.OTHER_USER, FileMan.USER, puk, this.progress, false);
			this.jobMan = new UploaderJobMan(spec);
			this.jobMan.start();

			this.startReporting();
		}
	}

	private void startReporting() {

		// this.owner.getScroller().setVisible(true);
		WidgetMaker.getComponent(this.owner, "green_").setVisible(false);
		WidgetMaker.getComponent(this.owner, "green").setVisible(true);

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

					Util.pause(UploaderReg.REPORT_SLEEP_MILLIS, EMPTY);

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

		// this.owner.getScroller().setVisible(false);
		WidgetMaker.getComponent(this.owner, "green_").setVisible(true);
		WidgetMaker.getComponent(this.owner, "green").setVisible(false);

	}
}