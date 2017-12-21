package com.mygdx.fighters.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Host implements Connector{

	private ServerSocket host;
	private Socket guest;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Host()
	{
		try {
			host = new ServerSocket(4444);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void connect()
	{
		try {
			guest = host.accept();
			System.out.println("Guest connected!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			in = new ObjectInputStream(guest.getInputStream());
			System.out.println("Input stream OK!");
			out = new ObjectOutputStream(guest.getOutputStream());
			System.out.println("Output stream OK!");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void disconnect()
	{
		try {
			in.close();
			out.close();
			guest.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Command receiveData()
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
	
	public void sendData(Command c)
	{
		try {
			out.writeObject(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
