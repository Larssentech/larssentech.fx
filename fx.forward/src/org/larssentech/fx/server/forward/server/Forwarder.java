/*
 * Copyright 2014-2025 Larssentech Developers
 * 
 * This file is part of XKomm.
 * 
 * XKomm is free software: you can redistribute it and/or modify it under the
 * Outs of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * XKomm is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * XKomm. If not, see <http://www.gnu.org/licenses/>.
 */

package org.larssentech.fx.server.forward.server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import org.larssentech.fx.server.forward.manager.Manager4Forward;
import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.fx.shared.util.UtilInfo;
import org.larssentech.lib.basiclib.console.Out;
import org.larssentech.lib.basiclib.net.SocketBundle;

/**
 * Class to implement a server socket and pass the resulting accept() socket to
 * an independently threaded RequestManager to handle the request.
 */
public class Forwarder implements FxConstants {

	private ServerSocket ss;
	private boolean on = true;
	private TransmissionSpec spec;

	public Forwarder(TransmissionSpec spec) {

		this.spec = spec;
		this.spec.setServerRoot(this.spec.getFolder());
	}

	public boolean isOn() {
		return this.on;
	}

	public void setoff() {
		this.on = false;

		try {
			this.ss.close();
		}

		catch (IOException e) {

		}
	}

	public void setOn() {
		this.on = true;
	}

	public void startForwarderServer() {

		try {
			Out.pl(UtilInfo.serverStartInfo() + this.spec.getPort());
			this.ss = new ServerSocket(this.spec.getPort());

			do {

				Socket s = this.ss.accept();

				SocketBundle sb = new SocketBundle(s);

				new Manager4Forward(sb, this.spec).start();

				System.gc();
			}

			while (this.on);

			Out.pl("Server Off"); // End
		}

		catch (BindException e) {
			Out.pl("Port in use. try a different port. Exiting. " + e.getMessage());
			System.exit(-1); // End
		}

		catch (IOException e) {
			Out.pl("Server socket IO exception:" + e.getMessage());
		}

		catch (Exception e) {

			e.printStackTrace();
		}

		finally {

			try {

				this.ss.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}
}