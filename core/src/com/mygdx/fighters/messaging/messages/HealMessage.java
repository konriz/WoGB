package com.mygdx.fighters.messaging.messages;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.entities.Soldier;
import com.mygdx.fighters.messaging.Message;

public class HealMessage extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 43L;

	public HealMessage(Soldier target, int heal)
	{
		setText(GameData.selected.getCharacter().getName() + " healed " + target.getCharacter().getName() + " for " + heal + " HP.");
	}
}
