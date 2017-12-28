package com.mygdx.fighters.gui.Messaging.messages;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.gui.Messaging.Message;

public class TurnMessage extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 47L;

	public TurnMessage()
	{
		setText("Turn " + GameData.turn + ": " + GameData.teams.get(GameData.activeIndex).getName());
	}
}
