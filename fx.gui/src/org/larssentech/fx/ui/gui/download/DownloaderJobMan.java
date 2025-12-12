package org.larssentech.fx.ui.gui.download;

import org.larssentech.fx.client.download.FxDownload;
import org.larssentech.fx.shared.objects.TransmissionSpec;

class DownloaderJobMan extends Thread {

	private FxDownload fx;
	private TransmissionSpec spec;

	DownloaderJobMan(TransmissionSpec spec) {
		this.spec = spec;
		this.fx = new FxDownload(spec);
	}

	@Override
	public void run() {

		this.fx.startManager();
	}

	boolean isOn() {
		return this.fx.getManager().isOn();
	}

	String getProgress() {
		return this.spec.getProgress().getProgress();
	}

	void stopManager() {
		this.fx.getManager().setOff();
	}
}