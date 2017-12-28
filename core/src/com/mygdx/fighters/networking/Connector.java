package com.mygdx.fighters.networking;

public interface Connector {

	public boolean connect();
	public void disconnect();
	public void sendData(Command c);
	public Command receiveData();
	
	public boolean isConnected();
}
