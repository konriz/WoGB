package com.mygdx.fighters.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.gui.FightersGame;

public class Client extends Comm{

	private Socket host;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Client()
	{
		
		try {
			host = new Socket("localhost", 4444);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			in = new ObjectInputStream(host.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			out = new ObjectOutputStream(host.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GameData receiveData()
	{
		try {
			GameData gameData = (GameData) in.readObject();
			return gameData;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return FightersGame.data;
		} catch (IOException e) {
			e.printStackTrace();
			return FightersGame.data;
		}
	}
	
	public void sendData()
	{
		try {
			out.writeObject(FightersGame.data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
