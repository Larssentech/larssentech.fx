package org.larssentech.fx.shared.io;

import java.io.File;
import java.io.IOException;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.XmlGen;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.basiclib.net.SocketBundle;

public class TransmissionConfirm {

	public static String sendHeader(SocketBundle sb, File fileTarget, String user, String sendTo) throws IOException {

		String xml = XmlGen.generateHeader(fileTarget, user, sendTo);

		// Send header as text
		sb.printOut(xml);

		return sb.readLineIn();
	}

	public static boolean requestConfirm(SocketBundle sb, File fileTarget, long totalCount) throws IOException {

		String serverReceived = sb.readLineIn();

		boolean success;

		success = Long.parseLong(serverReceived) == totalCount;

		if (success) sb.printOut(FxConstants.OK);

		else sb.printOut(FxConstants.FAIL);

		return success;

	}

	public static String provideConfirmation(SocketBundle sb, long receivedBytes) throws IOException {

		sb.printOut(receivedBytes);
		String line = sb.readLineIn();

		return line;

	}

	public static String sendHeader(SocketBundle sb, TransmissionSpec spec) throws IOException {
		String xml = XmlGen.generateHeader(spec.getCurrentFile(), spec.getUser(), spec.getOtherUser());

		// Send header as text
		sb.printOut(xml);

		return sb.readLineIn();

	}

}
