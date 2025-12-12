package org.larssentech.fx.shared.manager;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.basiclib.net.SocketBundle;

public class Manager4Server extends Thread implements FxConstants {

	protected final SocketBundle sb;
	protected final TransmissionSpec spec;

	public Manager4Server(final SocketBundle sb, final TransmissionSpec spec) {

		this.sb = sb;
		this.spec = spec;
	}
}
