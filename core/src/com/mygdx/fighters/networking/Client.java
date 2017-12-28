package com.mygdx.fighters.networking;

import java.io.IOException;
import java.net.Socket;

public class Client extends AbstractConnector{

	
	public Client(String hostAddress) throws IOException
	{
		
			setSocket(new Socket(hostAddress, 4444));
			System.out.println("Host connected");
			setConnected(true);

		
	}
	
	public boolean connect()
	{
//		try {
//			out = new ObjectOutputStream(host.getOutputStream());
//			System.out.println("Output stream OK!");
//			in = new ObjectInputStream(host.getInputStream());
//			System.out.println("Input stream OK!");
//			return true;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}
	
	public void disconnect()
	{
		try {
			getOut().close();
			getSocket().close();
			System.out.println("All closed, good bye!");
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
	
	public void sendData(Command command)
	{
		try {
			getOut().writeObject(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
