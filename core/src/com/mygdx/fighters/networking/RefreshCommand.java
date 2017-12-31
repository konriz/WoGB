package com.mygdx.fighters.networking;

import com.mygdx.fighters.gui.FightersGame;
import com.mygdx.fighters.networking.gui.LobbyDialog;

public class RefreshCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int players;

	@Override
	public void perform() {
		if (FightersGame.isHost())
		{
			players = ((Host) FightersGame.connection).getConnections();
			FightersGame.connection.sendData(this);
		}
		else
		{
			LobbyDialog.countPlayers(players);
		}
		

	}

}
