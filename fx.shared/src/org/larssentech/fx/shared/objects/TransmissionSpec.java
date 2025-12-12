package org.larssentech.fx.shared.objects;

import java.io.File;
import java.util.Date;

public class TransmissionSpec {

	private File folder;
	private String host;
	private int port;
	private final TransmissionProgress progress;
	private String user;
	private String otherUser;

	private boolean delete;
	private boolean on;
	private File currentFile;
	private StreamHeader header;
	private File metadataFile;
	private File serverRoot;

	public TransmissionSpec(String host, int port, String folder, String user, String otherUser, TransmissionProgress progress, boolean delete) {

		this.folder = new File(folder);
		this.host = host;
		this.port = port;
		this.progress = progress;
		this.user = user;
		this.otherUser = otherUser;
		this.delete = delete;

		this.on = true;
	}

	public TransmissionSpec(int port, String folder, TransmissionProgress progress) {
		this.port = port;
		this.folder = new File(folder);
		this.progress = progress;
	}

	public void setHeader(StreamHeader header) {
		this.header = header;
		this.user = this.header.getUser();
		this.otherUser = this.header.getOtherUser();

	}

	public StreamHeader getHeader() {
		return this.header;
	}

	public boolean isDelete() {
		return this.delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public boolean isOn() {
		return this.on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public File getFolder() {
		return this.folder;
	}

	public String getHost() {
		return this.host;
	}

	public int getPort() {
		return this.port;
	}

	public TransmissionProgress getProgress() {
		return this.progress;
	}

	public String getUser() {
		return this.user;
	}

	public String getOtherUser() {
		return this.otherUser;
	}

	public File getCurrentFile() {
		return this.currentFile;
	}

	public void setCurrentFile(File currentFile) {
		this.currentFile = currentFile;
	}

	public void setFolder(File targetFolder) {
		this.folder = targetFolder;

	}

	public void setUser(String user) {
		this.user = user;

	}

	public void setOtherUser(String otherUser) {
		this.otherUser = otherUser;
	}

	public File getMetadataFile() {
		return this.metadataFile;
	}

	public void setMetadataFile(File metadataFile) {
		this.metadataFile = metadataFile;
	}

	public void updateProgress(String text) {
		this.getProgress().addInfo(new Date().toString() + text);
	}

	public File getServerRoot() {
		return this.serverRoot;
	}

	public void setServerRoot(File serverRoot) {
		this.serverRoot = serverRoot;
	}
}
