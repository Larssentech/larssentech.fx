package org.larssentech.fx.ui.gui.forward;

import org.larssentech.fx.server.forward.FxForward;
import org.larssentech.fx.shared.objects.TransmissionProgress;
import org.larssentech.fx.shared.objects.TransmissionSpec;

class ForwarderJobMan extends Thread {

	private FxForward fx;
	private final TransmissionProgress progress = new TransmissionProgress();

	public ForwarderJobMan(TransmissionSpec spec) {

		this.fx = new FxForward(spec);
	}

	@Override
	public void run() {
		this.fx.startForwarder();

	}

	boolean isOn() {
		return this.fx.getForwarder().isOn();
	}

	String getServerProgress() {
		return this.progress.getProgress();
	}

	public void stopServer() {
		this.fx.getForwarder().setoff();
	}

	public int getP100() {
		return Math.round(this.progress.getP100());
	}

}
