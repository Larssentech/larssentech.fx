package org.larssentech.fx.shared.io;

import java.io.IOException;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.basiclib.net.SocketBundle;

public class FragmentHandler implements FxConstants {

	protected SocketBundle sb;
	protected boolean on = true;
	protected TransmissionSpec spec;

	public FragmentHandler(final SocketBundle sb, final TransmissionSpec spec) {
		this.sb = sb;
		this.spec = spec;
		this.spec.getProgress().init();
	}

	public boolean isOn() { return this.on; }

	public void setOff() throws IOException {

		this.on = false;
		this.sb.close();
	}

	protected void updateProgress(long receivedBytes, long size) {
		this.spec.getProgress().setProgress(receivedBytes, size);

	}
}