package com.mygdx.fighters.networking;

import com.mygdx.fighters.gui.FightersGame;

public class ConnectionThread extends Thread {

	private boolean active = true;
	
	@Override
	public void run() {

		System.out.println("Do something with this thread moron!");
	}
	
	public void terminate()
	{
		active = false;
		System.out.println("Thread termination on the way.");
		FightersGame.connection.sendData(new TerminateCommand());
	}

	public boolean isActive()
	{
		return active;
	}
}
