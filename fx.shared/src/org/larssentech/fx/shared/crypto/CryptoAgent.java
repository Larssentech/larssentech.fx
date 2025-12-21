package org.larssentech.fx.shared.crypto;

import java.io.File;

import org.larssentech.CTK.driver.EmbeddedApi;
import org.larssentech.CTK.settings.CTKSettings;
import org.larssentech.CTK.settings.RSAPathBundle;
import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.manager.NetAgent;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.basiclib.net.SocketBundle;
import org.larssentech.lib.log.Logg3r;

public class CryptoAgent implements CTKSettings, FxConstants {

	protected final TransmissionSpec spec;
	protected final SocketBundle sb;
	protected NetAgent man;
	protected EmbeddedApi ctk;
	private File log;

	protected CryptoAgent(File log, final SocketBundle sb, final TransmissionSpec spec) {

		this.log = log;
		this.sb = sb;
		this.spec = spec;
		this.initCtk();

	}

	protected void setOff() {
		this.man.setOff();
		Logg3r.log2(this.log, "CryptoAgent Stopped by user.");
	}

	protected void initCtk() {

		RSAPathBundle.setOwnPKPath(OWN_PRI_K_ABS_PATH);
		RSAPathBundle.setOwnPUKPath(OWN_PUB_K_ABS_PATH);
		RSAPathBundle.setOwnKeyPairPath(OWN_KEYPAIR_ABS_PATH);
		RSAPathBundle.setCipherString("RSA");

		// For out contact library
		new File(CTKSettings.HOME_DIR + CTKSettings.SEP + "nxrsa_pub_key_lib").mkdir();
		new File(CTKSettings.HOME_DIR + CTKSettings.SEP + CTKSettings.CTK_HOME).mkdir();
		new File(CTKSettings.HOME_DIR + CTKSettings.SEP + CTKSettings.OWN_RSA_DIR).mkdir();

		this.ctk = new EmbeddedApi(this.log);
		this.ctk.loadPublicKeyForUser(this.spec.getPuk());

	}
}