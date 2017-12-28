package com.mygdx.fighters.gui.Messaging.messages;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.entities.Soldier;
import com.mygdx.fighters.gui.Messaging.Message;

public class MeeleMessage extends Message{

	/**
	 * 
	 */
	private static final long serialVersionUID = 45L;

	public MeeleMessage(Soldier target, int damage)
	{
		setText(GameData.selected.getCharacter().getName() + " hit " + target.getCharacter().getName() + " for " + damage + " HP.");
	}
}
