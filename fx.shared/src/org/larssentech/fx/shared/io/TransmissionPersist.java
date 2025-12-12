package org.larssentech.fx.shared.io;

import java.io.File;
import java.io.IOException;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.lib.basiclib.console.Out;
import org.larssentech.lib.basiclib.io.file.StreamWriter;

public class TransmissionPersist implements FxConstants {

	public static void persistStream(String fileName, byte[] bytesRead, int readCount, File targetFolder) {

		if (!targetFolder.isDirectory()) {

			Out.pl("Creating user dir: " + targetFolder);
			targetFolder.mkdirs();

			Out.pl("Directory exists: " + targetFolder.isDirectory());
		}

		StreamWriter writer = new StreamWriter(new File(targetFolder + SEP + fileName));

		writer.writeBytes(bytesRead, readCount);
		writer.closeStream();
	}

	public static void persistFragment(String fileName, byte[] bytesRead, int readCount, String targetFolder, long num) {

		String fileFolder = targetFolder + SEP + fileName + "-n";

		if (!new File(fileFolder).isDirectory()) {

			Out.pl("Creating file dir: " + fileFolder);
			new File(fileFolder).mkdirs();

			Out.pl("Directory exists: " + new File(fileFolder).isDirectory());
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
