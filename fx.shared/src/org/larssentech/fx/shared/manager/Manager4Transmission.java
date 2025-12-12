package org.larssentech.fx.shared.manager;

import java.io.IOException;
import java.util.Date;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.io.FragmentReader;
import org.larssentech.fx.shared.io.FragmentWriter;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.basiclib.console.Out;
import org.larssentech.lib.basiclib.net.SocketBundle;

public class Manager4Transmission implements FxConstants {

	protected final SocketBundle sb;
	protected final TransmissionSpec spec;

	protected FragmentReader fragr;
	protected FragmentWriter fragw;

	protected Manager4Transmission(final SocketBundle sb, final TransmissionSpec spec) {

		this.sb = sb;
		this.spec = spec;
	}

	public void setOff() {

		Out.pl(new Date().toString() + " - " + "TransmissiondManager Stopped by user.");

		try {
			// FragmentWriter will be null if there is no current upload/download
			if (null != this.fragr) this.fragr.setOff();
			if (null != this.fragw) this.fragw.setOff();

		} catch (IOException e) {
			Out.pl(new Date().toString() + " - " + "TransmissiondManager Stopped by user. Forced close socket. Exception caught.");
		}
	}
}
