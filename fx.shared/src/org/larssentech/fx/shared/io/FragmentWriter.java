package org.larssentech.fx.shared.io;

import java.io.File;
import java.io.IOException;

import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.basiclib.io.file.StreamReader;
import org.larssentech.lib.basiclib.net.SocketBundle;

public class FragmentWriter extends FragmentHandler {

	public FragmentWriter(final SocketBundle sb, final TransmissionSpec spec) {

		super(sb, spec);
	}

	public boolean writeStream() {

		File file = this.spec.getCurrentFile();

		if (!file.exists()) return false;

		// We need the size of the file (at this point it is stable)
		final long targetFileLength = file.length();

		// Now deal with the file
		final StreamReader byteReader = new StreamReader(file);

		// Check again it is a readable file
		if (null == byteReader.getIn()) return false;

		// This is the main array to hold the chunks to send one at a time
		final byte[] bytesRead = new byte[ARRAY_SIZE];

		// Counter for bytes read into the byte[], the last chunk may be smaller
		int readCount = 0;

		// Counter for the number of bytes sent so far (cumulative)
		long totalCount = 0;

		while ((readCount = byteReader.readBytes(bytesRead)) != -1 && this.isOn()) {

			totalCount += readCount;

			try {
				// Send pay load, which will quite often be below 64K ARRAY_SIZE, WHY?
				this.sb.write(bytesRead, 0, readCount);

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

		boolean success = false;
		try {
			success = TransmissionConfirm.requestConfirm(this.sb, file, totalCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.printResult(success ? OK : FAIL);

		try {
			this.sb.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

		if (success) file.delete();

		return success;
	}
}