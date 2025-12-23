package org.larssentech.fx.shared.util;

import java.io.File;

public class UtilInfo {

	public static String forwardInfo(File file) {

		return "[sending-file] <" + file.getName() + "> " + "[sending-size] <" + file.length() + ">";
	}

	public static String serverStartInfo() {

		return "Forwarder Server Socket Starting, Port: ";
	}

	public static String receiveInfo(String fileName) {

		return "[receiving-file] <" + fileName + "> " + "[receiving-size] <" + "?" + ">";
	}
}