package org.larssentech.fx.client.download.manager;

import java.io.File;
import java.io.IOException;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.io.FragmentReader;
import org.larssentech.fx.shared.manager.Manager4Transmission;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.UtilInfo;
import org.larssentech.lib.basiclib.net.SocketBundle;

class Manager4Download extends Manager4Transmission {

	Manager4Download(SocketBundle sb, TransmissionSpec spec) {
		super(sb, spec);
	}

	void storeOne() throws IOException {

		this.spec.updateProgress(UtilInfo.downloadInfo(this.spec.getHeader()));

		this.spec.updateProgress(FxConstants.REPORT_HEADER_IN);

		if (!this.spec.getFolder().exists()) this.spec.getFolder().mkdir();

		// If we have a file with the same name, delete it first
		File currentFile = new File(this.spec.getFolder() + SEP + this.spec.getHeader().getName());
		this.spec.setCurrentFile(currentFile);
		this.spec.getCurrentFile().delete();

		// The main thing
		this.fragr = new FragmentReader(this.sb, this.spec);
		this.fragr.readStream();
	}
}