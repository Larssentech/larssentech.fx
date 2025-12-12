package org.larssentech.fx.shared.util;

import java.io.File;

import org.larssentech.fx.shared.objects.StreamHeader;

public class UtilInfo {

	public static String downloadInfo(StreamHeader xp) {

		return

		"\n" + "[incoming-file] <" + xp.getName() + "> " + "\n" + "[incoming-size] <" + xp.getSize() + ">" + "\n" +

				"[incoming-blocks] <" + xp.getNum() + ">" + "\n" + "[user-receiving-you] <" + xp.getUser() + ">" + "\n" +

				"[user-sending] <" + xp.getOtherUser() + ">";
	}

	public static String uploadInfo(File file) {

		return "\n[sending-file] <" + file.getName() + ">\n" + "[sending-size] <" + file.length() + ">";
	}

	public static String receiverInfo(StreamHeader xp) {

		return "\n" + "[incoming-file] <" + xp.getName() + "> " + "\n" +

				"[incoming-size] <" + xp.getSize() + ">" + "\n" +

				"[incoming-blocks] <" + xp.getNum() + ">" + "\n" +

				"[incoming-user] <" + xp.getUser() + ">" + "\n" +

				"[incoming-send-to] <" + xp.getOtherUser() + ">";
	}

	public static String forwardInfo(File file) {

		return "[sending-file] <" + file.getName() + ">\n" + "[sending-size] <" + file.length() + ">";
	}

	public static String serverStartInfo() {

		return "Forwarder Server Socket Starting, Port: ";
	}

}
