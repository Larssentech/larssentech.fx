package org.larssentech.fx.server.receive;

import org.larssentech.fx.server.receive.server.Receiver;
import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionProgress;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.UtilMsg;

public class FxReceive implements FxConstants {

	private final Receiver rx;

	public static void main(String[] args) {

		TransmissionSpec spec = new TransmissionSpec(Integer.parseInt(args[0]), args[1], new TransmissionProgress());

		new FxReceive(spec).startReceiver();
	}

	public FxReceive(TransmissionSpec spec) {

		spec.getProgress().addInfo(UtilMsg.receiverStartMsg(spec));

		this.rx = new Receiver(spec);
	}

	public void startReceiver() {

		this.rx.startReceiverServer();
	}

	public Receiver getReceiver() {

		return this.rx;
	}
}