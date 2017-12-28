package com.mygdx.fighters.networking;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public abstract class AbstractConnector implements Connector {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	private boolean connected = false;
	
	public void setSocket(Socket socket)
	{
		this.socket = socket;
	}
	
	public Socket getSocket() {
		return socket;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

}
