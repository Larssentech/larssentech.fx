package org.larssentech.fx.ui.gui.shared;

import java.awt.Color;
import java.awt.Font;

import org.larssentech.fx.shared.FxConstants;

public interface SharedReg extends FxConstants {

	String VERSION = "FX GUI - v0.3.9" + " / " + FxConstants.VERSION;

	String OS_NAME = System.getProperty("os.name");
	String MAC_OS = "Mac OS X";
	String HOME = System.getProperty("user.home");
	String SEP = System.getProperty("file.separator");
	String SEPARATOR = "                                                                                      ";
	String EMPTY_STRING = "";
	String NEW_LINE = "\n";

	Color COLOUR_FOREGROUND = Color.white;
	Color COLOUR_BACKGROUND = new Color(48, 47, 47);
	Color COLOUR_TEXT = Color.GREEN;
	Color COLOUR_LABEL = Color.WHITE;

	String CMD_START = "CMD_START_SERVER";
	String CMD_STOP = "CMD_STOP_SERVER";

	String MSG_STOPPED = "Stopped";
	String MSG_TAREA_STOPPED = "Inactive";

	int REPORT_SLEEP_MILLIS = 10;
	int FIELD_WIDTH = 20;

	Font FONT_SMALL = new Font("SANS_SERIF", 0, 10);

	String NO_INFO = "NO_INFO";

	String APPLE_FILE_CHOOSER = "apple.awt.fileDialogForDirectories";

	String LBL_STOP = "Stop";
	String LBL_START = "Start";
	String LBL_OUTPUT = "Progress";
	String LBL_PORT = "Port";
	String LBL_FOLDER = "Folder";
	String LBL_HOST = "Host";

	String NAME_SERVER_SCROLL_PANE = "NAME_SERVER_SCROLL_PANE";
	String NAME_OUTPUT = "OUTPUT";
	String NAME_HOST = "HOST";
	String NAME_PORT = "PORT";
	String NAME_BUTTON = "BUTTON";
	String NAME_FOLDER = "FOLDER";
	String NAME_USER = "USER";
	String NAME_SEND_TO = "SEND_TO";
}