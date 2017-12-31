package com.mygdx.fighters.networking;

import com.mygdx.fighters.gui.FightersGame;
import com.mygdx.fighters.networking.gui.LobbyDialog;

public class Master extends ConnectionThread {

	@Override
	public void run()
	{
		FightersGame.connection.connect();
		FightersGame.connection.sendData(new InitDataCommand());
		LobbyDialog.refresh();
		
		while (isActive())
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