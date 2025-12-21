package org.larssentech.fx.shared.io;

import java.io.File;
import java.io.IOException;

import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.basiclib.io.file.StreamReader;
import org.larssentech.lib.basiclib.net.SocketBundle;

public class FragmentWriter extends FragmentHandler {

	public FragmentWriter(final SocketBundle sb, final TransmissionSpec spec) { super(sb, spec); }

	public boolean streamOne(File encBase64File) {

		boolean success = false;

		try {
			if (!encBase64File.exists()) return false;

			// We need the size of the file (at this point it is stable)
			final long targetFileLength = encBase64File.length();

			// Now deal with the file
			final StreamReader byteReader = new StreamReader(encBase64File);

			// Check again it is a readable file
			if (null == byteReader.getIn()) return false;

			// This is the main array to hold the chunks to send one at a time
			final byte[] bytesRead = new byte[ARRAY_SIZE];

			// Counter for bytes read into the byte[], the last chunk may be smaller
			int readCount = 0;

			// Counter for the number of bytes sent so far (cumulative)
			long totalCount = 0;

			this.spec.updateProgress(LINER);
			this.spec.updateProgress(REPORT_HEADER_OUT2);

			while ((readCount = byteReader.readBytes(bytesRead)) != -1 && this.isOn()) {

				totalCount += readCount;

				try {
					// == Send =================
					this.sb.write(bytesRead, 0, readCount);
					// =========================

				} catch (IOException e) {

					e.printStackTrace();

					// Sleep and retry once
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ignored) {

					}
					try {
						this.sb.write(bytesRead, 0, readCount);

					} catch (IOException e1) {

						e1.printStackTrace();
					}
				}

				this.updateProgress(totalCount, targetFileLength);
			}

			byteReader.closeStream();

			success = TransmissionConfirm.requestConfirm(this.sb, encBase64File, totalCount);

			this.spec.updateProgress(LINER);
			this.spec.updateProgress("");

			this.sb.close();

		} catch (IOException e) {

			e.printStackTrace();

			this.spec.updateProgress("File: '" + encBase64File.getName() + "' FragWriter FAIL");
			this.spec.updateProgress(e.toString());
		}

		return success;
	}
}