package org.larssentech.fx.shared.io;

import java.io.File;
import java.io.IOException;

import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.Util;
import org.larssentech.lib.basiclib.net.SocketBundle;
import org.larssentech.lib.log.Logg3r;

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

		String fileName = this.spec.getHeader().getName();
		File folder = this.spec.getFolder();

		while ((readCount = this.sb.read(bytesRead)) > 0) {

			// All blocks have to be of size=ARRAY_SIZE except the last
			// Seems like sometimes 1483 bytes are read, then the diff
			// to 64K, 62517, in 2 hops... why?

			if (num < this.spec.getHeader().getNum()) {
				// if (readCount != FxConstants.ARRAY_SIZE);
				// Out.pl(FxConstants.INCORRECT_BLOCK_SIZE);
			}

			receivedBytes += readCount;

			Logg3r.log2(D_LOG, "Persisting fragment...");
			TransmissionPersist.persistStream(fileName, bytesRead, readCount, folder);

			this.updateProgress(receivedBytes, this.spec.getHeader().getSize());

			num++;

			if (receivedBytes == this.spec.getHeader().getSize()) break;

			Util.pause(100, "");
		}

		new File(folder + SEP + TMP_TOK + fileName).renameTo(new File(folder + SEP + fileName));
		this.updateProgress("[End Receive]");
		this.updateProgress("");

		Logg3r.log2(D_LOG, "(4) We says: " + receivedBytes);
		String line = TransmissionConfirm.provideConfirmation(this.sb, receivedBytes);

		Logg3r.log2(D_LOG, "(4) Server says: " + line);
		this.printResult(line);

		this.sb.close();
	}

}
