package org.larssentech.fx.shared.io;

import java.io.File;
import java.io.IOException;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.Util;
import org.larssentech.lib.basiclib.net.SocketBundle;
import org.larssentech.lib.log.Logg3r;

public class FragmentReader extends FragmentHandler {

	public FragmentReader(SocketBundle sb, TransmissionSpec spec) { super(sb, spec); }

	public File retrieveOne(String encBase64FileName) {

		String tmpEncFileName = TMP_TOK + encBase64FileName;
		File tmpBase64EncFile = new File(this.spec.getFolder() + SEP + tmpEncFileName);

		try {
			// Main receiver loop
			long receivedBytes = 0;

			final byte[] bytesRead = new byte[ARRAY_SIZE];

			// Counters
			int readCount = 0;

			// Expected download file details

			File folder = this.spec.getFolder();
			long encFileSize = this.spec.getHeader().getSize();

			this.spec.updateProgress(FxConstants.LINER);
			this.spec.updateProgress(FxConstants.REPORT_HEADER_IN2);

			// Downloads the file with a small pause
			while ((readCount = this.sb.read(bytesRead)) > 0) {

				// TODO: ERRORS SOMETIMES HERE 'SOCKET IS CLOSED'

				receivedBytes += readCount;

				// == Receive ==============
				TransmissionPersist.persistStream(tmpEncFileName, bytesRead, readCount, folder);
				// =========================

				this.updateProgress(receivedBytes, encFileSize);

				if (receivedBytes == encFileSize) break;

				Util.pause(100, "");
			}

			// If we get here, success, else tmpEncFile will be left here

			this.spec.updateProgress(LINER);
			this.spec.updateProgress("");

			// Confirm to the server the number of bytes
			Logg3r.log2(D_LOG, "< (3) We says: " + receivedBytes);

			// Get confirmation from the server the number is OK
			String line = TransmissionConfirm.provideConfirmation(this.sb, receivedBytes);
			Logg3r.log2(D_LOG, "> (3) Server says: " + line);

			this.sb.close();

			Util.pause(1000, "");

			// Server side
			if (null == line) {
				File base64EncFile = new File(folder + SEP + encBase64FileName);
				tmpBase64EncFile.renameTo(base64EncFile);
			}

			// Client side
			else if (line.equals("OK")) {

				File base64EncFile = new File(folder + SEP + encBase64FileName);
				tmpBase64EncFile.renameTo(base64EncFile);

				return base64EncFile;
			}

		} catch (IOException e) {

			e.printStackTrace();

			tmpBase64EncFile.delete();
			this.spec.updateProgress("File: '" + encBase64FileName + "' FragReader FAIL");
			this.spec.updateProgress(e.toString());
		}

		return null;
	}
}