package org.larssentech.fx.client.upload.manager;

import java.io.IOException;

import org.larssentech.fx.shared.io.FragmentWriter;
import org.larssentech.fx.shared.io.TransmissionConfirm;
import org.larssentech.fx.shared.io.TransmissionPersist;
import org.larssentech.fx.shared.manager.Manager4Transmission;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.Util;
import org.larssentech.fx.shared.util.UtilInfo;
import org.larssentech.lib.basiclib.net.SocketBundle;

class Manager4Upload extends Manager4Transmission {

	Manager4Upload(SocketBundle sb, TransmissionSpec spec) {

		super(sb, spec);
	}

	void streamOne() throws IOException {

		// Initialise the progress object for a new file
		this.spec.getProgress().init();

		// Will wait until file size is stable
		if (Util.stabiliseLocal(this.spec.getCurrentFile())) {

			// Display info about the file to send
			this.spec.updateProgress(UtilInfo.uploadInfo(this.spec.getCurrentFile()));

			this.spec.updateProgress(REPORT_HEADER_OUT);

			// Connect to the server
			this.sb.connect();

			// Read 'HELLO'
			this.sb.readLineIn();

			// Send header in XML so the server knows what we are sending, what size it is
			// and how many pieces to expect
			TransmissionConfirm.sendHeader(this.sb, this.spec);

			// The main thing
			this.fragw = new FragmentWriter(this.sb, this.spec);
			boolean success = this.fragw.writeStream();

			if (success) TransmissionPersist.moveItems(success, this.spec.getCurrentFile(), false);

			else this.spec.updateProgress(MSG_UPLOAD_NOT_SUCCESSFUL);
		}
	}
}