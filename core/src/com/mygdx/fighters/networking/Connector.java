package com.mygdx.fighters.networking;

public interface Connector {

	public void connect();
	public void disconnect();
	public void sendData(Command c);
	public Command receiveData();
}
