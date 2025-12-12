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

	public boolean writeStream() throws IOException {

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

			// Send pay load, which will quite often be below 64K ARRAY_SIZE, WHY?
			this.sb.write(bytesRead, 0, readCount);

			// TODO
			// If we sent an "OK", and then read the response, and is another "OK"
			// (meaning the chunk was both received and persisted).
			// That would mean this chunk has reached the receiver. Then we can
			// send the next chunk. Else, we send the same chunk again?

			this.updateProgress(totalCount, targetFileLength);
		}

		byteReader.closeStream();

		final boolean b = TransmissionConfirm.requestConfirm(this.sb, file, totalCount);

		this.printResult(b ? OK : FAIL);
		this.sb.close();

		return totalCount == targetFileLength;
	}
}