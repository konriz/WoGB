package com.mygdx.fighters.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends AbstractConnector{

	private Socket host;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Client(String hostAddress)
	{
		
		try {
			host = new Socket("hostAddress", 4444);
			System.out.println("Host connected");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void connect()
	{
		try {
			out = new ObjectOutputStream(host.getOutputStream());
			System.out.println("Output stream OK!");
			in = new ObjectInputStream(host.getInputStream());
			System.out.println("Input stream OK!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect()
	{
		try {
			out.close();
			host.close();
			System.out.println("All closed, good bye!");
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
	
	public void sendData(Command command)
	{
		try {
			out.writeObject(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
