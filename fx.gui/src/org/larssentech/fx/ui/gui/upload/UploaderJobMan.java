package org.larssentech.fx.ui.gui.upload;

import org.larssentech.fx.client.upload.FxUpload;
import org.larssentech.fx.shared.objects.TransmissionSpec;

class UploaderJobMan extends Thread {

	private FxUpload fx;
	private TransmissionSpec spec;

	UploaderJobMan(TransmissionSpec spec) {
		this.spec = spec;
		this.fx = new FxUpload(spec);
	}

	@Override
	public void run() {

		this.fx.startManager();
	}

	boolean isOn() {
		return this.fx.getManager().isOn();

	}

	String getClientProgress() {
		return this.spec.getProgress().getProgress();
	}

	void stopClient() {
		this.fx.getManager().setOff();
	}
}