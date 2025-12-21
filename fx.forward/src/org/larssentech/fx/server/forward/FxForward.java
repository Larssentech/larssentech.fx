package org.larssentech.fx.server.forward;

import java.io.File;

import org.larssentech.fx.server.forward.server.Forwarder;
import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionProgress;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.UtilMsg;

public class FxForward implements FxConstants {

	private final Forwarder fx;

	public static void main(String[] args) {

		TransmissionSpec spec = new TransmissionSpec(Integer.parseInt(args[0]), args[1], new TransmissionProgress(new File("forward.log")));

		new FxForward(spec).startForwarder();
	}

	public FxForward(TransmissionSpec spec) {

		spec.getProgress().addInfo(UtilMsg.forwarderStartMsg(spec.getFolder(), spec.getPort()));
		spec.setDelete(true);

		this.fx = new Forwarder(spec);
	}

	public void startForwarder() {

		this.fx.startForwarderServer();
	}

	public Forwarder getForwarder() {

		return this.fx;
	}
}