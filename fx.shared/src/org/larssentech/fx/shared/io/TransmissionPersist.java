package org.larssentech.fx.shared.io;

import java.io.File;
import java.io.IOException;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.lib.basiclib.io.file.StreamWriter;
import org.larssentech.lib.log.Logg3r;

public class TransmissionPersist implements FxConstants {

	public static void persistStream(String fileName, byte[] bytesRead, int readCount, File targetFolder) {

		if (!targetFolder.isDirectory()) {

			Logg3r.log2(D_LOG, "Creating user dir: " + targetFolder);
			targetFolder.mkdirs();

			Logg3r.log("Directory exists: " + targetFolder.isDirectory());
		}

		Logg3r.log2(D_LOG, "Writing fragment to file...");
		StreamWriter writer = new StreamWriter(new File(targetFolder + SEP + "_" + fileName));
		writer.writeBytes(bytesRead, readCount);
		writer.closeStream();
		Logg3r.log2(D_LOG, "Fragment written to file... OK");
	}

	public static void persistFragment(String fileName, byte[] bytesRead, int readCount, String targetFolder, long num) {

		String fileFolder = targetFolder + SEP + fileName + "-n";

		if (!new File(fileFolder).isDirectory()) {

			Logg3r.log("Creating file dir: " + fileFolder);

			new File(fileFolder).mkdirs();

			Logg3r.log("Directory exists: " + new File(fileFolder).isDirectory());
		}

		StreamWriter writer = new StreamWriter(new File(fileFolder + SEP + fileName + DOT + num));

		writer.writeBytes(bytesRead, readCount);
		writer.closeStream();
	}

	public static void moveItems(boolean success, File file, boolean delete) {

		try {

			if (success) {

				if (delete) file.delete();
				else move2Sent(file);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void move2Sent(File file) throws IOException {

		File target = new File(FxConstants.FILEXARE_SENT + FxConstants.SEP + file.getName());

		if (target.exists()) target.delete();
		file.renameTo(target);
	}
}
