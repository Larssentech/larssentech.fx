package org.larssentech.fx.ui.gui.download;

import org.larssentech.fx.ui.gui.shared.SharedReg;

interface DownloaderReg extends SharedReg {

	String APP_NAME = "Download";

	String VAL_FOLDER = FILEXARE_INBOX;
	String VAL_HOST = "larssentech.org";
	String VAL_PORT = VAL_DOWNLOAD_PORT;

	String LBL_USER = "User";
	String NAME_RECEIVE_FROM = "FROM";
	String LBL_RECEIVE_FROM = "From";
}