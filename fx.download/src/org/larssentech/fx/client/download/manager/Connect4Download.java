package org.larssentech.fx.client.download.manager;

import java.io.IOException;

import org.larssentech.fx.shared.manager.Connect4Transmission;
import org.larssentech.fx.shared.objects.StreamHeader;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.Util;
import org.larssentech.fx.shared.util.UtilMsg;
import org.larssentech.lib.basiclib.net.SocketBundle;

public class Connect4Download extends Connect4Transmission {

	private CrytptoAgentDown cad;

	public Connect4Download(TransmissionSpec spec) {
		super(spec);
	}

	public void run() {

		this.spec.updateProgress(UtilMsg.downloadStartMsg(this.spec));

		this.spec.setOn(true);

		while (this.spec.isOn()) {

			try {

				SocketBundle sb = new SocketBundle(this.spec.getHost(), this.spec.getPort());

				sb.connect();

				// Server says HELLO
				sb.readLineIn();

				// We send user name and who we receive from
				sb.printOut(this.spec.getUser());
				sb.printOut(this.spec.getOtherUser());

				String line = "";

				line = sb.readLineIn();

				sb.printOut(XML_RECEIVED_OK);

				this.spec.setHeader(new StreamHeader(line));

				if (!this.spec.getHeader().getName().equals(EMPTY)) {

					this.cad = new CrytptoAgentDown(sb, this.spec);
					this.cad.storeOne();
				}

			} catch (IOException e) {

				this.spec.updateProgress("Failed to connect to server. Check system is online.");
			}

			Util.pause(5000, "");
		}
	}

	public void setOff() {

		super.setOff();
		this.cad.setOff();
	}
}