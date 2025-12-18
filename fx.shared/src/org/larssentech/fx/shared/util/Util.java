package org.larssentech.fx.shared.util;

import java.io.File;
import java.util.Base64;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.lib.basiclib.settings.SettingsExtractor;

public class Util implements FxConstants {

	public static void createClientFolders() {

		new File(FILEXARE_HOME).mkdir();
		new File(FILEXARE_OUTBOX).mkdir();
		new File(FILEXARE_INBOX).mkdir();
		new File(FILEXARE_SENT).mkdir();
	}

	public static void createServerFolders() {

		createClientFolders();
		new File(FILEXARE_SERVER).mkdir();
	}

	public static void pause(int i, String string) {
		try {

			Thread.sleep(i);
			if (string.length() > 0) {
				System.out.println("");
				System.out.println(string);
				System.out.println("");
			}

		} catch (InterruptedException ignored) {
		}
	}

	public static File[] getFiles(String dir) {

		String[] fileNames = new File(dir).list();
		File[] files = new File[fileNames.length];

		for (int i = 0; i < fileNames.length; i++) {

			files[i] = new File(dir + SEP + fileNames[i]);

		}
		return files;
	}

	public static boolean stabilise(File file) {

		String metadataFileName = file.getParent() + SEP + "." + file.getName();

		long expectedSize = Long.parseLong(SettingsExtractor.extractThis4(metadataFileName, "exp-size"));

		int counter = 0;

		long currSize = 0;

		do {

			currSize = file.length();

			System.out.println("Expected: " + expectedSize + ", Current: " + currSize);

			counter++;

			// We only want to return true if file sizes exactly match
			if (expectedSize == currSize) return true;

			if (counter > 2) return false;

			Util.pause(1000, "");

		} while (expectedSize > currSize);

		return false;

	}

	public static void createDownloadFolder(String receiveFrom) {
		new File(FILEXARE_INBOX + SEP + receiveFrom).mkdir();

	}

	public static void createUploadFolder(String sendTo) {
		new File(FILEXARE_OUTBOX + SEP + sendTo).mkdir();

	}

	public static long parseWithDefLong(String s, int def) {

		try {
			return Long.parseLong(s);
		} catch (NumberFormatException e) {
			// It's OK to ignore "e" here because returning a default value is the
			// documented behaviour on invalid input.
			return def;

		}

		catch (Exception e) {
			// It's OK to ignore "e" here because returning a default value is the
			// documented behaviour on invalid input.
			return def;

		}
	}

	public static boolean fileValid(File file) {

		return file.exists() &&

				!file.getName().startsWith(DOT) &&

				!file.isDirectory() &&

				file.length() > 0;
	}

	public static File base64Enc(File clearFile) {

		String path = clearFile.getParent();
		String name = clearFile.getName();

		String b64name = Base64.getEncoder().encodeToString(name.getBytes());

		String base64FileName = path + FxConstants.SEP + b64name;
		File base64File = new File(base64FileName);

		return base64File;
	}

	public static File base64Dec(File encFile) {
		String path = encFile.getParent();
		String name = encFile.getName();

		byte[] clearName = Base64.getDecoder().decode(name);

		String base64FileName = path + FxConstants.SEP + new String(clearName);
		File base64File = new File(base64FileName);

		return base64File;
	}
}