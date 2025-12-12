package org.larssentech.fx.client.upload;

import org.larssentech.fx.client.upload.manager.Connect4Upload;
import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionProgress;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.Util;

public class FxUpload implements FxConstants {

	private Connect4Upload manager;

	public static void main(String[] args) {

		TransmissionSpec spec = new TransmissionSpec(args[0], Integer.parseInt(args[1]), args[2] + SEP + args[4], args[3], args[4], new TransmissionProgress(), false);

		new FxUpload(spec).startManager();
	}

	public FxUpload(TransmissionSpec spec) {

		Util.createUploadFolder(spec.getOtherUser());

		this.manager = new Connect4Upload(spec);

	}

	public void startManager() {

		this.manager.start();
	}

	public Connect4Upload getManager() {

		return this.manager;
	}
}
