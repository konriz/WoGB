package com.mygdx.fighters.messaging.messages;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.entities.Soldier;
import com.mygdx.fighters.messaging.Message;

public class LeechMessage extends Message{

	/**
	 * 
	 */
	private static final long serialVersionUID = 44L;

	public LeechMessage(Soldier target, int damage)
	{
		setText(GameData.selected.getCharacter().getName() + " leached " + damage + " HP from " + target.getCharacter().getName());
	}
}
