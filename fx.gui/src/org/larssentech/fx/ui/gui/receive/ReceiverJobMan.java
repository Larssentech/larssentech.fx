package org.larssentech.fx.ui.gui.receive;

import org.larssentech.fx.server.receive.FxReceive;
import org.larssentech.fx.shared.objects.TransmissionProgress;

class ReceiverJobMan extends Thread {

	private FxReceive fx;
	private final TransmissionProgress progress = new TransmissionProgress();

	public ReceiverJobMan(final int port, final String inFolder) {

		this.fx = new FxReceive(port, inFolder, this.progress);
	}

	@Override
	public void run() {
		this.fx.startReceiver();

	}

	protected void setFx(FxReceive FxServer) {
		this.fx = FxServer;

	}

	boolean isOn() {
		return this.fx.getReceiver().isOn();
	}

	String getServerProgress() {
		return this.progress.getProgress();
	}

	public void stopServer() {
		this.fx.getReceiver().setoff();
	}

	public int getP100() {
		return Math.round(this.progress.getP100());
	}

}
