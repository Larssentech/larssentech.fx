package org.larssentech.fx.ui.gui.download;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionProgress;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.Util;
import org.larssentech.fx.ui.gui.shared.UiController;
import org.larssentech.fx.ui.gui.shared.WidgetMaker;

class DownloaderUiController extends UiController implements FxConstants {

	private final DownloaderJPanel owner;
	private DownloaderJobMan jobMan;
	private final TransmissionProgress progress = new TransmissionProgress();

	protected DownloaderUiController(final DownloaderJPanel owner) {
		super(owner);
		this.owner = owner;
	}

	void start() {

		// JTextField h = WidgetMaker.getTextField(this.owner, DownloaderReg.NAME_HOST);
		// JTextField portS = WidgetMaker.getTextField(this.owner,
		// DownloaderReg.NAME_PORT);

		String host = WidgetMaker.getText(this.owner, DownloaderReg.NAME_HOST);
		int port = WidgetMaker.getNumber(this.owner, DownloaderReg.NAME_PORT);
		String folder = WidgetMaker.getText(this.owner, DownloaderReg.NAME_FOLDER);
		String user = WidgetMaker.getText(this.owner, DownloaderReg.NAME_USER);
		String otherUser = WidgetMaker.getText(this.owner, DownloaderReg.NAME_RECEIVE_FROM);

		// new
		TransmissionSpec spec = new TransmissionSpec(host, port, folder + SEP + otherUser, user, otherUser, this.progress, false);
		this.jobMan = new DownloaderJobMan(spec);

		// this.jobMan = new DownloaderJobMan(host, port, folder, user, otherUser);
		this.jobMan.start();

		this.startReporting();

	}

	private void startReporting() {

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

					Util.pause(DownloaderReg.SLEEP_MILLIS, "");

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
		DownloaderUiController.this.jobMan.stopManager();

	}
}
