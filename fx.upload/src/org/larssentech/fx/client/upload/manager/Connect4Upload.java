package org.larssentech.fx.client.upload.manager;

import java.io.File;
import java.io.IOException;

import org.larssentech.fx.shared.manager.Connect4Transmission;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.Util;
import org.larssentech.fx.shared.util.UtilMsg;
import org.larssentech.lib.basiclib.net.SocketBundle;

public class Connect4Upload extends Connect4Transmission {

	private CryptoAgentUp cau;

	public Connect4Upload(TransmissionSpec spec) {
		super(spec);

	}

	public void run() {

		this.spec.updateProgress(UtilMsg.uploadStartMsg(this.spec));

		this.spec.setOn(true);

		// Even if an exception is thrown, this loop should keep going while spec is
		// 'on'
		while (this.spec.isOn()) {

			// Poll directory for files
			File[] files = Util.getFiles(this.spec.getFolder().getAbsolutePath());

			// Send this batch of files[]
			for (int i = 0; i < files.length; i++) {

				File currentFile = files[i];

				// Skip hidden '.' files, directories and only take .blowfish
				if (Util.fileAllowed(currentFile)) {

					// Set the file once for everyone
					this.spec.setCurrentFile(currentFile);

					try {

						// For each file we need a new socket bundle as it closes after sending.
						SocketBundle sb = new SocketBundle(this.spec.getHost(), this.spec.getPort());

						this.cau = new CryptoAgentUp(sb, this.spec);
						this.cau.streamOne();

					} catch (IOException e) {

						this.spec.updateProgress(MSG_UPLOAD_STREAM_EXCEPTION);
						this.spec.updateProgress("Connect4Upload exception.");
					}
				}
			}

			Util.pause(MILLIS_SLEEP_UPLOAD, EMPTY);
		}

		this.spec.updateProgress(MSG_UPLOADER_STOP);
	}

	public void setOff() {

		super.setOff();
		this.cau.setOff();
	}
}