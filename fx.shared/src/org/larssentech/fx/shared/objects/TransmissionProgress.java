package org.larssentech.fx.shared.objects;

import java.util.Vector;

import org.larssentech.lib.log.Logg3r;

public class TransmissionProgress {

	private Vector<String> progressStore;
	private int p100;
	private long lastByteCount;

	public TransmissionProgress() {

		this.init();
	}

	public void init() {
		this.progressStore = new Vector<String>();
		this.p100 = 0;
		this.lastByteCount = 0;

	}

	public void setProgress(long receivedBytes, long totalBytes) {

		String initialProgress = this.p100 + "%\t" + receivedBytes + "\t" + totalBytes;

		if (this.lastByteCount == 0) {
			addInfo(initialProgress);
			this.lastByteCount = receivedBytes;
		}

		float f = ((float) receivedBytes / totalBytes);

		this.p100 = (Math.round(f * 100));

		String progress = this.p100 + "%\t" + receivedBytes + "\t" + totalBytes;

		long limit = totalBytes / 20;

		if (receivedBytes - this.lastByteCount >= limit) {
			addInfo(progress);

			this.lastByteCount = receivedBytes;
		}

		if (receivedBytes == totalBytes) addInfo(progress);

	}

	public String getProgress() {
		if (this.progressStore.size() > 0) {

			String s = this.progressStore.elementAt(0).toString();
			this.progressStore.removeElementAt(0);

			return s;

		} else return "NO_INFO";
	}

	public void addInfo(String string) {
		this.progressStore.addElement(string);
		Logg3r.log(string);
	}

	public int getP100() {
		return this.p100;
	}

	public void printResult(String line) {

		addInfo("====================" + "\n" + "Done: " + line);

	}

}
