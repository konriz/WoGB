package com.mygdx.fighters.gui.Messaging.messages;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.entities.Soldier;
import com.mygdx.fighters.gui.Messaging.Message;

public class HealMessage extends Message {

	public HealMessage(Soldier target, int heal)
	{
		setText(GameData.selected.getCharacter().getName() + " healed " + target.getCharacter().getName() + " for " + heal + " HP.");
	}
}
