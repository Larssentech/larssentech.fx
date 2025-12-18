package org.larssentech.fx.client.upload;

import java.io.File;
import java.util.Date;

import org.larssentech.fx.client.upload.manager.Connect4Upload;
import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.Util;

public class FxUpload implements FxConstants {

	public static final File U_LOG = new File(new Date() + "_" + "upload.log");

	private Connect4Upload manager;

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
