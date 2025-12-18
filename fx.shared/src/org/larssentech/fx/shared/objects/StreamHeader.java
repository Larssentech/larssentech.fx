package org.larssentech.fx.shared.objects;

import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.util.Util;
import org.larssentech.lib.basiclib.io.parser.XMLParser;

public class StreamHeader implements FxXmlSpec {

	private String name;
	private long size;

	// Number of fragments
	private long num;

	private String user;
	private String sendTo;

	public StreamHeader(String line) {

		for (int i = 0; i < ELEMENTS_O.length; i++) {

			String parsed = XMLParser.parseValueForTag(line, ELEMENTS_O[i]);

			if (i == 0) this.name = parsed;

			if (i == 1) this.size = Util.parseWithDefLong(parsed, 0);

			if (i == 4) this.user = parsed;
			if (i == 5) this.sendTo = parsed;

		}

		this.num = this.size / FxConstants.ARRAY_SIZE;

	}

	public String getName() {
		return this.name;
	}

	public long getSize() {
		return this.size;
	}

	public String getSizeS() {
		return "" + this.size;
	}

	// Returns the number of fragments
	public long getNum() {
		return this.num;
	}

	public String getUser() {
		return this.user;
	}

	public String getOtherUser() {
		return this.sendTo;
	}

	public void setName(String s) {
		this.name = s;

	}
}