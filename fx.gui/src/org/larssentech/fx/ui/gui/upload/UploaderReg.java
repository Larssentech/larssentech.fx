package org.larssentech.fx.ui.gui.upload;

import org.larssentech.fx.ui.gui.shared.SharedReg;

interface UploaderReg extends SharedReg {

	String APP_NAME = "Upload";

	String VAL_FOLDER = FILEXARE_OUTBOX;
	String VAL_HOST = "larssentech.org";
	String VAL_PORT = VAL_UPLOAD_PORT;

	String LBL_USER = "User";
	String LBL_SEND_TO = "To";

}