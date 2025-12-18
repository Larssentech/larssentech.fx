package org.larssentech.fx.shared.objects;

import java.io.File;

import org.larssentech.lib.CTK.objects.PUK;

public class TransmissionSpec {

	private File folder;
	private String host;
	private int port;
	private final TransmissionProgress progress;
	private String me;
	private String otherUser;

	private boolean delete;
	private boolean on;
	private File currentFile;
	private StreamHeader header;
	private File metadataFile;
	private File serverRoot;
	private File currentUnencFile;
	private PUK puk;

	public TransmissionSpec(String host, int port, String folder, String user, PUK puk, TransmissionProgress progress, boolean delete) {

		this.folder = new File(folder);
		this.host = host;
		this.port = port;
		this.progress = progress;
		this.me = user;
		this.otherUser = puk.getEmail();
		this.setPuk(puk);
		this.delete = delete;

		this.on = true;
	}

	public TransmissionSpec(int port, String folder, TransmissionProgress progress) {
		this.port = port;
		this.folder = new File(folder);
		this.progress = progress;
	}

	public void setClientHeader(StreamHeader header) {
		this.header = header;

	}

	public void setHeader(StreamHeader header) {
		this.header = header;
		this.me = this.header.getUser();
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

	public String getMe() {
		return this.me;
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

	public File getCurrentUnencFile() {
		return this.currentUnencFile;
	}

	public void setCurrentUnencFile(File currentFile) {
		this.currentUnencFile = currentFile;
	}

	public void setFolder(File targetFolder) {
		this.folder = targetFolder;

	}

	public void setMe(String user) {
		this.me = user;

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
		this.getProgress().addInfo(text);
	}

	public File getServerRoot() {
		return this.serverRoot;
	}

	public void setServerRoot(File serverRoot) {
		this.serverRoot = serverRoot;
	}

	public PUK getPuk() {
		return this.puk;
	}

	public void setPuk(PUK puk) {
		this.puk = puk;
	}
}
