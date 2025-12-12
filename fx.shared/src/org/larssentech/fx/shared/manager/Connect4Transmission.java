package org.larssentech.fx.shared.manager;

import java.util.Date;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.basiclib.console.Out;

public class Connect4Transmission extends Thread implements FxConstants {

	protected TransmissionSpec spec;

	public Connect4Transmission(TransmissionSpec spec) {

		this.spec = spec;
	}

	public void run() {
	}

	public boolean isOn() {

		return this.spec.isOn();
	}

	public void setOff() {

		Out.pl(new Date().toString() + " - " + "Connect4Transmission Stopped by user.");
		this.spec.setOn(false);
	}
}
