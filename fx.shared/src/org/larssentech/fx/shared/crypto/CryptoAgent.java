package org.larssentech.fx.shared.crypto;

import java.util.Date;

import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.basiclib.console.Out;
import org.larssentech.lib.basiclib.net.SocketBundle;

public class CryptoAgent {

	protected TransmissionSpec spec;
	protected SocketBundle sb;

	public CryptoAgent(SocketBundle sb, TransmissionSpec spec) {
		this.sb = sb;
		this.spec = spec;
	}

	public void setOff() {

		Out.pl(new Date().toString() + " - " + "CryptoAgent Stopped by user.");

	}
}
