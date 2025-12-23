package org.larssentech.fx.server.receive.manager;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.io.FragmentReader;
import org.larssentech.fx.shared.manager.Manager4Server;
import org.larssentech.fx.shared.objects.StreamHeader;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.Util4Server;
import org.larssentech.fx.shared.util.UtilInfo;
import org.larssentech.lib.basiclib.net.SocketBundle;

public class Manager4Receive extends Manager4Server {

	public Manager4Receive(final SocketBundle sb, final TransmissionSpec spec) {

		super(sb, spec);
	}

	public void run() {

		StreamHeader xp = null;

		try {
			this.sb.printOut(HELLO);

			String line = this.sb.readLineIn();

			if (null == line) return;

			this.sb.printOut(FxConstants.XML_RECEIVED_OK);

			// Compose the Stream Header object to contain all needed details
			xp = new StreamHeader(line);

			// We may receive a bad 'line' and xp will be returned with size=0
			// if (xp.getSize() == 0) return;

			this.spec.setHeader(xp);

			// this.spec.getProgress().addInfo(line);
			// this.spec.getProgress().addInfo(UtilInfo.receiverInfo(xp));

			// Handle file particulars based on the Stream Header
			Util4Server.processFileStructure(this.spec);

			// this.spec.getProgress().addInfo(FxConstants.REPORT_HEADER_IN1);
			// this.spec.getProgress().addInfo(FxConstants.REPORT_HEADER_IN2);

			// Store the metadata file for incoming file
			Util4Server.processMetadata(this.spec);

			String encName = this.spec.getHeader().getName();

			this.spec.getProgress().addInfo(UtilInfo.receiveInfo(encName));

			// Read the stream of fragments
			new FragmentReader(this.sb, this.spec).retrieveOne(encName);
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		if (xp.getSize() > 0) Util4Server.processResult(this.spec);
	}
}