package org.larssentech.fx.shared;

import java.io.File;
import java.util.Date;

public interface FxConstants {

	String VERSION = "FX v0.7.1";
	String COPYRIGHT = " (c) 2025 Larssentech.org" + "\n";

	String HOME = System.getProperty("user.home");
	String SEP = System.getProperty("file.separator");
	String EMPTY = "";
	String DOT = ".";
	String OK = "OK";
	String FAIL = "FAIL";
	String EXP_SIZE = "exp-size";
	String HELLO = "HELLO";
	String TMP_TOK = "_";

	String FILEXARE_HOME = HOME + SEP + "FileXare";
	String FILEXARE_OUTBOX = FILEXARE_HOME + SEP + "Uploads";
	String FILEXARE_INBOX = FILEXARE_HOME + SEP + "Downloads";
	String FILEXARE_SENT = FILEXARE_HOME + SEP + "Sent";
	String FILEXARE_SERVER = HOME + SEP + "FileXare" + SEP + "server";

	String REPORT_HEADER_IN = "\n" + "PROGRESS REPORT" + "\n" + "====================" + "\n" + "[%]" + "\t" + "[bytes_in]" + "\t" + "[total]";
	String REPORT_HEADER_OUT = "\n" + "PROGRESS REPORT" + "\n" + "====================" + "\n" + "[%]" + "\t" + "[bytes_out]" + "\t" + "[total]";

	String XML_RECEIVED_OK = "XML Received OK";

	String VAL_UPLOAD_PORT = "54545";
	String VAL_DOWNLOAD_PORT = "54546";

	int ARRAY_SIZE = 64000;
	int MILLIS_SLEEP_UPLOAD = 100;
	int MILLIS_SLEEP_DOWNLOAD = 2000;

	String MSG_UPLOAD_STREAM_EXCEPTION = "Upload Exception.\nOnly part of the file may have been sent!";
	String MSG_UPLOAD_NOT_SUCCESSFUL = "FileTransfer failed, file unstable?";

	public static final File D_LOG = new File(new Date() + "_" + "download.log");
	public static final File U_LOG = new File(new Date() + "_" + "upload.log");

}
