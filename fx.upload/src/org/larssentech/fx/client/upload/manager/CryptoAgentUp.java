package org.larssentech.fx.client.upload.manager;

import java.io.File;

import org.larssentech.fx.shared.crypto.CryptoAgent;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.Util;
import org.larssentech.lib.basiclib.net.SocketBundle;

class CryptoAgentUp extends CryptoAgent {

	CryptoAgentUp(SocketBundle sb, TransmissionSpec spec) { super(U_LOG, sb, spec); }

	boolean streamOne(File base64File) {

		boolean success = false;

		if (Util.fileValid(base64File)) {

			// Encrypt base64File
			String base64FileName = base64File.getAbsoluteFile().toString();
			String[] result = this.ctk.encryptBlowfish(base64FileName);

			// If we received a good CTK response
			if (result.length > 0) {

				// Get the .blowfish file
				File encBase64File = new File(result[0]);

				// If the .blowfish file is good
				if (Util.fileValid(encBase64File)) {

					// == Send =================
					success = new NetAgent4Upload(this.sb, this.spec).streamOne(encBase64File);
					// =========================
				}

				// Delete .blowfish file
				encBase64File.delete();
			}

			else
				// If CTK failed, put base64File back into clearFile and report
				this.spec.updateProgress("File: '" + base64File.getName() + "' CTK FAIL");

		}

		return success;
	}
}