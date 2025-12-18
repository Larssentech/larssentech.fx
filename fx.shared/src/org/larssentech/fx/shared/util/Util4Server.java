package org.larssentech.fx.shared.util;

import java.io.File;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.basiclib.settings.SettingsUpdater;

public class Util4Server implements FxConstants {

	public static void processFileStructure(TransmissionSpec spec) {

		File targetFolder = new File(spec.getServerRoot() + SEP + spec.getOtherUser() + SEP + spec.getMe());
		targetFolder.mkdirs();
		spec.setFolder(targetFolder);

		spec.setMetadataFile(new File(targetFolder + SEP + DOT + spec.getHeader().getName()));
		File expectedFile = new File(targetFolder + SEP + spec.getHeader().getName());
		spec.setCurrentFile(expectedFile);

		// If we have a file with the same name, delete it first
		expectedFile.delete();

	}

	public static void processResult(TransmissionSpec spec) {

		if (spec.getHeader().getSize() != spec.getCurrentFile().length()) {

			spec.getProgress().addInfo("Stream reading encountered a problem.");
			spec.getProgress().addInfo("This may result in a partial upload.");

			spec.getProgress().addInfo("Expected size: " + spec.getHeader().getSizeS());
			spec.getProgress().addInfo("Actual size: " + new File(spec.getFolder() + SEP + spec.getHeader().getName()).length());

			spec.getProgress().addInfo("Deleting partial file: " + spec.getHeader().getName() + ": " + spec.getCurrentFile().delete());

		}

		return;

	}

	public static void processMetadata(TransmissionSpec spec) {
		SettingsUpdater.updateLine(spec.getMetadataFile().getAbsolutePath(), EXP_SIZE, spec.getHeader().getSizeS());

	}

}
