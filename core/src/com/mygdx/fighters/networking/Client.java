package com.mygdx.fighters.networking;

import java.io.IOException;
import java.net.Socket;

public class Client extends AbstractConnector{

	private String hostAddress;
	
	public Client(String hostAddress) 
	{
			this.hostAddress = hostAddress;
	}
	
	public boolean connect()
	{
		try
		{
			setSocket(new Socket(hostAddress, 4444));
			System.out.println("Host connected");
			setConnected(true);
			connectStreams();
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
}
