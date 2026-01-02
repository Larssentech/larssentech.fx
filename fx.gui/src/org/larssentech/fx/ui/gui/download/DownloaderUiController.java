package org.larssentech.fx.ui.gui.download;

import org.larssentech.CTK.driver.EmbeddedApi;
import org.larssentech.CTK.settings.CTKSettings;
import org.larssentech.fx.client.download.manager.DownloaderJobMan;
import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionProgress;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.FileMan;
import org.larssentech.fx.shared.util.Util;
import org.larssentech.fx.ui.gui.shared.SharedReg;
import org.larssentech.fx.ui.gui.shared.UiController;
import org.larssentech.fx.ui.gui.shared.WidgetMaker;
import org.larssentech.lib.CTK.objects.PUK;

class DownloaderUiController extends UiController implements FxConstants {

	private final DownloaderJPanel owner;
	private DownloaderJobMan jobMan;
	private final TransmissionProgress progress = new TransmissionProgress(D_LOG);

	protected DownloaderUiController(final DownloaderJPanel owner) {
		super(owner);
		this.owner = owner;
	}

	void start() {

		String path = CTKSettings.OTHER_USERS_PUB_KEY_LIB + FxConstants.SEP + FileMan.OTHER_USER + FxConstants.SEP + FileMan.OTHER_USER;

		PUK puk = new PUK(EmbeddedApi.exportPuk2Base64(path));

		if (puk.getStringValue().length() > 0) {

			puk.setEmail(FileMan.OTHER_USER);

			final String targetFolder = FileMan.D_FOLDER + FxConstants.SEP + FileMan.OTHER_USER;

			final TransmissionSpec spec = new TransmissionSpec(FileMan.HOST, FileMan.D_PORT, targetFolder, FileMan.USER, puk, this.progress, false);

			this.jobMan = new DownloaderJobMan(spec);
			this.jobMan.start();

			this.startReporting();
		}

	}

	private void startReporting() {

		WidgetMaker.getComponent(this.owner, "green_").setVisible(false);
		WidgetMaker.getComponent(this.owner, "green").setVisible(true);

		Thread t = new Thread() {

			@Override
			public void run() {

				DownloaderUiController.this.owner.getOutputArea().setText(null);

				while (true) {

					DownloaderUiController.super.doControls(DownloaderUiController.this.jobMan.isOn());

					String thisReport = DownloaderUiController.this.jobMan.getProgress();

					if (!thisReport.equals(DownloaderReg.NO_INFO)) {

						DownloaderUiController.this.owner.getOutputArea().append(thisReport + DownloaderReg.NEW_LINE);
						DownloaderUiController.this.owner.getOutputArea().setCaretPosition(DownloaderUiController.this.owner.getOutputArea().getText().length());
					}

					Util.pause(DownloaderReg.REPORT_SLEEP_MILLIS, EMPTY);

					if (!DownloaderUiController.this.jobMan.isOn()) {

						DownloaderUiController.this.doControls(DownloaderUiController.this.jobMan.isOn());
						DownloaderUiController.this.owner.getOutputArea().setCaretPosition(DownloaderUiController.this.owner.getOutputArea().getText().length());
						break;
					}
				}
			}
		};
		t.start();
	}

	public void stopClient() {
		DownloaderUiController.this.jobMan.stopConnect();
		DownloaderUiController.this.owner.getOutputArea().setText(SharedReg.MSG_TAREA_STOPPED);

		WidgetMaker.getComponent(this.owner, "green_").setVisible(true);
		WidgetMaker.getComponent(this.owner, "green").setVisible(false);

	}
}