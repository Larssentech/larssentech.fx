package org.larssentech.fx.shared.io;

import java.io.File;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.lib.basiclib.io.file.StreamWriter;
import org.larssentech.lib.log.Logg3r;

public class TransmissionPersist implements FxConstants {

	public static void persistStream(String fileName, byte[] bytesRead, int readCount, File targetFolder) {

		if (!targetFolder.isDirectory()) {

			Logg3r.log2(D_LOG, "Creating user dir: " + targetFolder);
			targetFolder.mkdirs();
			Logg3r.log2(D_LOG, "Directory exists: " + targetFolder.isDirectory());
		}

		StreamWriter writer = new StreamWriter(new File(targetFolder + SEP + fileName));
		double c = writer.writeBytes(bytesRead, readCount);
		writer.closeStream();
	}
}