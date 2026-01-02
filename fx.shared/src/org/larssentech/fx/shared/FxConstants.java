package org.larssentech.fx.shared;

import java.io.File;
import java.util.Date;

public interface FxConstants {

	String VERSION = "FX v0.8.2.1";
	String COPYRIGHT = " (c) 2022-2026 Larssentech.org" + "\n";

	String HOME = System.getProperty("user.home");
	String SEP = System.getProperty("file.separator");
	String EMPTY = "";
	String DOT = ".";
	String OK = "OK";
	String FAIL = "FAIL";
	String EXP_SIZE = "exp-size";
	String HELLO = "HELLO";
	String TMP_TOK = "_";

	// String FILEXARE_HOME = HOME + SEP + "FileXare";
	// String FILEXARE_OUTBOX = FILEXARE_HOME + SEP + "Uploads";
	// String FILEXARE_INBOX = FILEXARE_HOME + SEP + "Downloads";

	String FILEXARE_SERVER = HOME + SEP + "FileXare" + SEP + "server";

	String REPORT_HEADER_IN1 = "====================";
	String REPORT_HEADER_IN2 = "[%]" + "\t" + "[in]" + "\t" + "[total]";

	String LINER = "====================";
	String REPORT_HEADER_OUT2 = "[%]" + "\t" + "[out]" + "\t" + "[total]";

	String XML_RECEIVED_OK = "XML Received OK";

	int ARRAY_SIZE = 64000;
	int MILLIS_SLEEP_UPLOAD = 100;
	int MILLIS_SLEEP_DOWNLOAD = 2000;

	// Engine pause between file send/receive
	static final int DOWNLOAD_PAUSE = 2000;
	static final int UPLOAD_PAUSE = 2000;

	static final int STABILISE_PAUSE = 5000;

	String MSG_UPLOAD_STREAM_EXCEPTION = "Upload failed.";
	String MSG_UPLOAD_NOT_SUCCESSFUL = "FileTransfer failed, file unstable?";

	public static final File D_LOG = new File(new Date() + "_" + "download.log");
	public static final File U_LOG = new File(new Date() + "_" + "upload.log");
	public static final String DOWN_SYMBOL = " ^·-v-·^ ";
	public static final String UP_SYMBOL = " _.-^-._  ";

	public static final String ERR_CONNECTION_REFUSED = "Connection refused (Connection refused)";
}
