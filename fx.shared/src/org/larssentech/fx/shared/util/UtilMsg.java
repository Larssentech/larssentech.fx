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

				"FileXare Receiver Service: " + "\n" + " - Server socket on PORT: " + FileMan.U_PORT + "\n" +

				" - Receiving folder: " + spec.getFolder() + "\n" + "========================================" + "\n";
	}

	public static synchronized String uploadStartMsg(TransmissionSpec spec) {

		return "\nFileXare " + VERSION + FxConstants.COPYRIGHT +

				"FileXare Uploader Service: " + "\n" +

				" - User: " + FileMan.USER + "\n" +

				" - Sending to: " + FileMan.OTHER_USER + "\n" +

				" - Sending to host: " + FileMan.HOST + "\n" +

				" - Sending to port: " + FileMan.U_PORT + "\n" +

				" - Sending from folder: " + spec.getFolder() + "\n" +

				"========================================" + "\n";
	}

	public static synchronized String downloadStartMsg(TransmissionSpec spec) {

		return "\nFileXare " + VERSION + FxConstants.COPYRIGHT +

				"FileXare Downloader Service: " + "\n" +

				" - User: " + FileMan.USER + "\n" +

				" - Receiving from: " + FileMan.OTHER_USER + "\n" +

				" - Downloading from host: " + FileMan.HOST + "\n" +

				" - Downloading from port: " + FileMan.D_PORT + "\n" +

				" - Downloading to folder: " + spec.getFolder() + "\n" +

				"========================================" + "\n";
	}
}