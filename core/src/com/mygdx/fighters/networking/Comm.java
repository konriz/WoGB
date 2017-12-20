package com.mygdx.fighters.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Comm {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public void connectStreams()
	{
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Command receive()
	{
		try {
			return (Command) in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return new EmptyCommand();
		} catch (IOException e) {
			e.printStackTrace();
			return new EmptyCommand();
		}
	}
	
	public void send(Command o)
	{
		try {
			out.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
