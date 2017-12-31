package com.mygdx.fighters.networking;

import java.io.IOException;
import java.net.ServerSocket;

public class Host extends AbstractConnector{

	private ServerSocket host;
	private int connections;
	
	public Host()
	{
		connections = 1;
		try {
			host = new ServerSocket(4444);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean connect()
	{
		try {
			setSocket(host.accept());
			System.out.println("Guest connected!");
			connectStreams();
			setConnected(true);
			connections ++;
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int getConnections()
	{
		return connections;
	}
	
}
