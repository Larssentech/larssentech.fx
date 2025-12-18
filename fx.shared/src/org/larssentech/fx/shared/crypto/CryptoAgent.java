package org.larssentech.fx.shared.crypto;

import org.larssentech.CTK.settings.CTKSettings;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.basiclib.net.SocketBundle;
import org.larssentech.lib.log.Logg3r;

public class CryptoAgent implements CTKSettings {

	protected final TransmissionSpec spec;
	protected final SocketBundle sb;

	protected CryptoAgent(final SocketBundle sb, final TransmissionSpec spec) {

		this.sb = sb;
		this.spec = spec;

	}

	protected void setOff() {

		Logg3r.log("CryptoAgent Stopped by user.");
	}
}