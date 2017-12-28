package com.mygdx.fighters.networking;

import java.io.IOException;
import java.net.ServerSocket;

public class Host extends AbstractConnector{

	private ServerSocket host;
	
	public Host()
	{
		try {
			host = new ServerSocket(4444);
			host.setSoTimeout(10000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean connect()
	{
		try {
			setSocket(host.accept());
			System.out.println("Guest connected!");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
//		try {
//			in = new ObjectInputStream(guest.getInputStream());
//			System.out.println("Input stream OK!");
//			out = new ObjectOutputStream(guest.getOutputStream());
//			System.out.println("Output stream OK!");
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//			return false;
//		}
		setConnected(true);
		return true;
	}
	
	public void disconnect()
	{
		try {
			getIn().close();
			getOut().close();
			getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
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
	
	public void sendData(Command c)
	{
		try {
			getOut().writeObject(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
