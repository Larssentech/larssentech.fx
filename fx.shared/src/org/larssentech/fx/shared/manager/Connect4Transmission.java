package org.larssentech.fx.shared.manager;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.log.Logg3r;

public class Connect4Transmission extends Thread implements FxConstants {

	protected final TransmissionSpec spec;

	public Connect4Transmission(final TransmissionSpec spec) {

		this.spec = spec;
	}

	public void run() {
	}

	public boolean isOn() {

		return this.spec.isOn();
	}

	public void setOff() {

		Logg3r.log("Connect4Transmission Stopped by user.");
		this.spec.setOn(false);
	}
}
