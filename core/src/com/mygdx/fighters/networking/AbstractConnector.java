package com.mygdx.fighters.networking;

import java.io.IOException;
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
	
	public void disconnect()
	{
		try {
			getIn().close();
//			getOut().close();
			getSocket().close();
			System.out.println("All closed, good bye!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean connectStreams()
	{
		try {
			setOut(new ObjectOutputStream(getSocket().getOutputStream()));
			System.out.println("Output stream OK!");
			setIn(new ObjectInputStream(getSocket().getInputStream()));
			System.out.println("Input stream OK!");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Command receiveData()
	{
		try {
			return (Command) getIn().readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return new EmptyCommand();
		} catch (IOException e) {
			e.printStackTrace();
			return new EmptyCommand();
		}
	}
	
	public void sendData(Command command)
	{
		try {
			getOut().writeObject(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}