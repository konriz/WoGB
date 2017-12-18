package com.mygdx.fighters.gui.Messaging;

import com.mygdx.fighters.GameData;

public class TurnMessage extends Message {

	public TurnMessage()
	{
		setText("Turn " + GameData.turn + ": " + GameData.teams.get(GameData.activeIndex).getName());
	}
}
