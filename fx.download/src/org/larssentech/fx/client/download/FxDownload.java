package org.larssentech.fx.client.download;

import org.larssentech.fx.client.download.manager.Connect4Download;
import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionProgress;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.Util;

public class FxDownload implements FxConstants {

	private Connect4Download manager;

	public static void main(String[] args) {

		TransmissionSpec spec = new TransmissionSpec(args[0], Integer.parseInt(args[1]), args[2] + SEP + args[4], args[3], args[4], new TransmissionProgress(), false);

		new FxDownload(spec).startManager();
	}

	public FxDownload(TransmissionSpec spec) {

		Util.createDownloadFolder(spec.getOtherUser());

		this.manager = new Connect4Download(spec);
	}

	public void startManager() {

		this.manager.start();
	}

	public Connect4Download getManager() {

		return this.manager;
	}
}