package org.larssentech.fx.server.forward.manager;

import java.io.File;
import java.io.IOException;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.io.FragmentWriter;
import org.larssentech.fx.shared.io.TransmissionConfirm;
import org.larssentech.fx.shared.io.TransmissionPersist;
import org.larssentech.fx.shared.manager.Manager4Server;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.Util;
import org.larssentech.fx.shared.util.UtilInfo;
import org.larssentech.lib.basiclib.net.SocketBundle;

public class Manager4Forward extends Manager4Server {

	public Manager4Forward(final SocketBundle sb, final TransmissionSpec spec) {

		super(sb, spec);
	}

	public void run() {

		try {
			this.sb.printOut(HELLO);

			// Yeah, crap login
			this.spec.setUser(this.sb.readLineIn());
			this.spec.setOtherUser(this.sb.readLineIn());

		} catch (IOException e) {

			e.printStackTrace();
		}

		File targetFolder = new File(this.spec.getServerRoot() + FxConstants.SEP + this.spec.getUser() + FxConstants.SEP + this.spec.getOtherUser());

		targetFolder.mkdirs();

		this.spec.setFolder(targetFolder);

		File[] filez = Util.getFiles(targetFolder.getAbsolutePath());

		boolean filesSent = false;

		for (int i = 0; i < filez.length; i++) {

			File currentFile = filez[i];

			this.spec.setCurrentFile(currentFile);

			if (Util.fileAllowed(currentFile)) {

				try {

					// Will wait until file size is stable or quit if too long
					if (Util.stabilise(currentFile)) {

						// Now we have a stable file, send the header with info we know is correct
						TransmissionConfirm.sendHeader(this.sb, currentFile, this.spec.getUser(), this.spec.getOtherUser());

						// Initialise the progress object for a new file
						this.spec.getProgress().init();

						// Display info about the file to send
						this.spec.getProgress().addInfo(UtilInfo.forwardInfo(currentFile));

						this.spec.getProgress().addInfo(FxConstants.REPORT_HEADER_OUT);

						// This is the critical part
						boolean success = new FragmentWriter(this.sb, this.spec).writeStream();

						if (success) {
							TransmissionPersist.moveItems(success, currentFile, true);
							filesSent = true;

						}
						else System.out.println("FileTransfer failed, file unstable?");
					}

				} catch (IOException e) {

					e.printStackTrace();
				}

				break;

			}
		}

		// If no files, don't leave the requester hanging
		try {
			if (!filesSent) TransmissionConfirm.sendHeader(this.sb, new File(EMPTY), this.spec.getUser(), this.spec.getOtherUser());
		} catch (IOException e) {

			e.printStackTrace();
		}

		try {

			this.sb.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}