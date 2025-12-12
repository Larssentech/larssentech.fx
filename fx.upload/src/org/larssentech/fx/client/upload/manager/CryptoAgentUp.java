package org.larssentech.fx.client.upload.manager;

import java.io.IOException;

import org.larssentech.fx.shared.crypto.CryptoAgent;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.basiclib.net.SocketBundle;

class CryptoAgentUp extends CryptoAgent {

	private Manager4Upload um;

	CryptoAgentUp(SocketBundle sb, TransmissionSpec spec) {
		super(sb, spec);
	}

	void streamOne() throws IOException {

		// Encrypt blah blah blah

		// ...

		this.um = new Manager4Upload(this.sb, this.spec);
		this.um.streamOne();
	}

	public void setOff() {

		super.setOff();
		this.um.setOff();
	}
}