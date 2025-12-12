package org.larssentech.fx.shared.io;

import java.io.IOException;

import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.basiclib.net.SocketBundle;

public class FragmentReader extends FragmentHandler {

	public FragmentReader(SocketBundle sb, TransmissionSpec spec) {

		super(sb, spec);
	}

	public void readStream() throws IOException {

		// Main receiver loop
		long receivedBytes = 0;

		final byte[] bytesRead = new byte[ARRAY_SIZE];

		// Counters
		int readCount = 0;
		long num = 0;

		while ((readCount = this.sb.read(bytesRead)) > 0) {

			// All blocks have to be of size=ARRAY_SIZE except the last
			// Seems like sometimes 1483 bytes are read, then the diff
			// to 64K, 62517, in 2 hops... why?

			if (num < this.spec.getHeader().getNum()) {
				// if (readCount != FxConstants.ARRAY_SIZE);
				// Out.pl(FxConstants.INCORRECT_BLOCK_SIZE);
			}

			receivedBytes += readCount;

			TransmissionPersist.persistStream(this.spec.getHeader().getName(), bytesRead, readCount, this.spec.getFolder());

			this.updateProgress(receivedBytes, this.spec.getHeader().getSize());

			num++;

			if (receivedBytes == this.spec.getHeader().getSize()) break;
		}

		this.updateProgress("[End Receive]");
		this.updateProgress("");

		String line = TransmissionConfirm.provideConfirmation(this.sb, receivedBytes);
		this.printResult(line);

		this.sb.close();
	}

}
