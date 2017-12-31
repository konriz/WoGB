package com.mygdx.fighters.networking;

import com.mygdx.fighters.gui.FightersGame;

public class Slave extends ConnectionThread {

	public void run()
	{
		while(isActive())
		{
			Command command = (Command) FightersGame.connection.receiveData();
			
			if (command instanceof TerminateCommand)
			{
				terminate();
			}
			command.perform();
		}
		
		System.out.println("Thread terminated!");
	}
	
}
