package com.mygdx.fighters.messaging.messages;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.entities.Soldier;
import com.mygdx.fighters.messaging.Message;

public class RangedMessage extends Message{

	/**
	 * 
	 */
	private static final long serialVersionUID = 46L;

	public RangedMessage(Soldier target, int damage)
	{
		setText(GameData.selected.getCharacter().getName() + " shot " + target.getCharacter().getName() + " for " + damage + " HP.");
	}
}
