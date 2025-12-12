package org.larssentech.fx.server.receive.server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import org.larssentech.fx.server.receive.manager.Manager4Receive;
import org.larssentech.fx.shared.FxConstants;
import org.larssentech.fx.shared.objects.TransmissionSpec;
import org.larssentech.lib.basiclib.console.Out;
import org.larssentech.lib.basiclib.net.SocketBundle;

/**
 * Class to implement a server socket and pass the resulting accept() socket to
 * an independently threaded RequestManager to handle the request.
 */
public class Receiver implements FxConstants {

	private static ServerSocket ss;
	private boolean on = true;
	private final TransmissionSpec spec;

	public Receiver(TransmissionSpec spec) {
		this.spec = spec;
		this.spec.setServerRoot(this.spec.getFolder());
	}

	public boolean isOn() {
		return this.on;
	}

	public void setoff() {
		this.on = false;

		try {
			ss.close();
		}

		catch (IOException e) {

		}
	}

	public void setOn() {
		this.on = true;
	}

	public void startReceiverServer() {

		try {
			Out.pl("Receiver Server Socket Starting, Port: " + this.spec.getPort());
			Receiver.ss = new ServerSocket(this.spec.getPort());

			do {

				Socket s = Receiver.ss.accept();

				SocketBundle sb = new SocketBundle(s);

				this.spec.updateProgress(new Date().toString() + " - " + "Request to Receive...");
				new Manager4Receive(sb, this.spec).start();

				Out.pl("Receiver manager closed for current upload."); // End

				System.gc();
			}

			while (this.on);

			Out.pl("Server Off."); // End
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

				Receiver.ss.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}
}