package org.larssentech.fx.shared.util;

import java.io.File;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionSpec;

public class UtilMsg implements FxConstants {

	public static synchronized String forwarderStartMsg(File file, int port) {

		return "FileXare " + VERSION + FxConstants.COPYRIGHT +

				"FileXare Forwarder Service: " + "\n" + " - Server socket on PORT: " + port + "\n" +

				" - Forwarding folder: " + file.getAbsolutePath() + "\n" + "========================================" + "\n";
	}

	public static synchronized String receiverStartMsg(TransmissionSpec spec) {

		return "FileXare " + VERSION + FxConstants.COPYRIGHT +

				"FileXare Receiver Service: " + "\n" + " - Server socket on PORT: " + spec.getPort() + "\n" +

				" - Receiving folder: " + spec.getFolder() + "\n" + "========================================" + "\n";
	}

	public static synchronized String uploadStartMsg(TransmissionSpec spec) {

		return "\nFileXare " + VERSION + FxConstants.COPYRIGHT +

				"FileXare Uploader Service: " + "\n" +

				" - User: " + spec.getMe() + "\n" +

				" - Sending to: " + spec.getOtherUser() + "\n" +

				" - Sending to host: " + spec.getHost() + "\n" +

				" - Sending to port: " + spec.getHost() + "\n" +

				" - Sending from folder: " + spec.getFolder() + "\n" +

				"========================================" + "\n";
	}

	public static synchronized String downloadStartMsg(TransmissionSpec spec) {

		return "\nFileXare " + VERSION + FxConstants.COPYRIGHT +

				"FileXare Downloader Service: " + "\n" +

				" - User: " + spec.getMe() + "\n" +

				" - Receiving from: " + spec.getOtherUser() + "\n" +

				" - Downloading from host: " + spec.getHost() + "\n" +

				" - Downloading from port: " + spec.getPort() + "\n" +

				" - Downloading to folder: " + spec.getFolder() + "\n" +

				"========================================" + "\n";
	}
}