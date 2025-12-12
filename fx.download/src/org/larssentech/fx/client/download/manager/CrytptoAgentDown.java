package org.larssentech.fx.client.download.manager;

import java.io.IOException;

import org.larssentech.fx.shared.crypto.CryptoAgent;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.basiclib.net.SocketBundle;

class CrytptoAgentDown extends CryptoAgent {

	protected Manager4Download dm;

	CrytptoAgentDown(SocketBundle sb, TransmissionSpec spec) {
		super(sb, spec);
	}

	void storeOne() throws IOException {

		this.dm = new Manager4Download(this.sb, this.spec);
		this.dm.storeOne();

		// Decrypt blah blah blah

		// ...

	}

	public void setOff() {

		super.setOff();
		this.dm.setOff();
	}
}