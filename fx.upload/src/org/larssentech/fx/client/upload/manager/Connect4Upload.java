package org.larssentech.fx.client.upload.manager;

import java.io.File;
import java.io.IOException;

import org.larssentech.CTK.driver.EmbeddedApi;
import org.larssentech.CTK.settings.CTKSettings;
import org.larssentech.CTK.settings.RSAPathBundle;
import org.larssentech.fx.client.upload.FxUpload;
import org.larssentech.fx.shared.manager.Connect4Transmission;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.Util;
import org.larssentech.fx.shared.util.UtilMsg;
import org.larssentech.lib.basiclib.console.Out;
import org.larssentech.lib.basiclib.net.SocketBundle;
import org.larssentech.lib.log.Logg3r;

public class Connect4Upload extends Connect4Transmission implements CTKSettings {

	private CryptoAgentUp cau;
	private EmbeddedApi ctk;

	public Connect4Upload(TransmissionSpec spec) {
		super(spec);

		this.initCtk();
	}

	private void initCtk() {

		RSAPathBundle.setOwnPKPath(OWN_PRI_K_ABS_PATH);
		RSAPathBundle.setOwnPUKPath(OWN_PUB_K_ABS_PATH);
		RSAPathBundle.setOwnKeyPairPath(OWN_KEYPAIR_ABS_PATH);
		RSAPathBundle.setCipherString("RSA");

		// For out contact library
		new File(CTKSettings.HOME_DIR + CTKSettings.SEP + "nxrsa_pub_key_lib").mkdir();
		new File(CTKSettings.HOME_DIR + CTKSettings.SEP + CTKSettings.CTK_HOME).mkdir();
		new File(CTKSettings.HOME_DIR + CTKSettings.SEP + CTKSettings.OWN_RSA_DIR).mkdir();

		this.ctk = new EmbeddedApi();
		this.ctk.loadPublicKeyForUser(this.spec.getPuk());

	}

	public void run() {

		this.spec.updateProgress(UtilMsg.uploadStartMsg(this.spec));

		this.spec.setOn(true);

		// Even if an exception is thrown, this loop should keep going while spec is
		// 'on'
		while (this.spec.isOn()) {

			// Poll directory for files
			File[] files = Util.getFiles(this.spec.getFolder().getAbsolutePath());

			// Send this batch of files[]
			for (int i = 0; i < files.length; i++) {

				File currentFile = files[i];

				// Skip hidden '.' files, directories and only take .blowfish
				if (Util.fileValid(currentFile)) {

					// Set the file once for everyone
					this.spec.setCurrentFile(currentFile);

					try {

						// For each file we need a new socket bundle as it closes after sending.
						SocketBundle sb = new SocketBundle(this.spec.getHost(), this.spec.getPort());

						this.cau = new CryptoAgentUp(this.ctk, sb, this.spec);

						boolean b = this.cau.streamOne();

						if (!b) {

							// this.spec.getCurrentFile().delete();

							Out.pl();
							Logg3r.log2(FxUpload.U_LOG, "xxxxxxxxxxxxxxxxxxxxxxxxxxx");
							Logg3r.log2(FxUpload.U_LOG, "Uploader Crypto retry! " + this.spec.getCurrentFile());
							Logg3r.log2(FxUpload.U_LOG, "xxxxxxxxxxxxxxxxxxxxxxxxxxx");
							Out.pl();

							this.cau = new CryptoAgentUp(this.ctk, sb, this.spec);
							b = this.cau.streamOne();
						}

					} catch (IOException e) {

						this.spec.updateProgress(MSG_UPLOAD_STREAM_EXCEPTION);
						this.spec.updateProgress("Connect4Upload exception.");
					}
				}
			}

			Util.pause(MILLIS_SLEEP_UPLOAD, EMPTY);
		}

		this.spec.updateProgress(MSG_UPLOADER_STOP);
	}

	public void setOff() {

		super.setOff();
		this.cau.setOff();

	}
}