package org.larssentech.fx.client.upload.manager;

import java.io.File;
import java.io.IOException;

import org.larssentech.CTK.driver.EmbeddedApi;
import org.larssentech.fx.client.upload.FxUpload;
import org.larssentech.fx.shared.crypto.CryptoAgent;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.Util;
import org.larssentech.lib.basiclib.net.SocketBundle;
import org.larssentech.lib.log.Logg3r;

class CryptoAgentUp extends CryptoAgent {

	private Manager4Upload um;
	private EmbeddedApi ctk;

	CryptoAgentUp(EmbeddedApi ctk, SocketBundle sb, TransmissionSpec spec) {
		super(sb, spec);
		this.ctk = ctk;
	}

	boolean streamOne() throws IOException {

		if (this.stabiliseLocal(this.spec.getCurrentFile())) {

			if (Util.fileValid(this.spec.getCurrentFile())) {

				String[] result = this.ctk.encryptBlowfish(this.spec.getCurrentFile().toString());

				if (result.length > 0) {

					File enc = new File(result[0]);

					if (Util.fileValid(enc)) {

						this.spec.setCurrentUnencFile(this.spec.getCurrentFile());
						this.spec.setCurrentFile(enc);
						this.spec.getCurrentUnencFile().delete();
					}

					this.um = new Manager4Upload(this.sb, this.spec);
					this.um.streamOne();

					return true;
				}

			}
		}

		return false;
	}

	private boolean stabiliseLocal(File file) {

		int counter = 0;

		long prevSize = file.length();
		long prevDate = file.lastModified();
		long currSize = 0;
		long currDate = 0;

		do {

			Util.pause(5000, "");

			prevSize = currSize;
			prevDate = currDate;
			currSize = file.length();
			currDate = file.lastModified();

			Logg3r.log2(FxUpload.U_LOG, "PrevSize: " + prevSize + ", CurrSize: " + currSize);
			Logg3r.log2(FxUpload.U_LOG, "PrevDate: " + prevDate + ", CurrDate: " + currDate);

			counter++;

			if (counter > 2) return false;

		} while ((currSize > prevSize) || currSize == 0 || prevSize == 0);

		return true;

	}

	public void setOff() {

		super.setOff();
		this.um.setOff();
	}
}