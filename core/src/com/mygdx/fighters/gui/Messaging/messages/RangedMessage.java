package com.mygdx.fighters.gui.Messaging.messages;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.entities.Soldier;
import com.mygdx.fighters.gui.Messaging.Message;

public class RangedMessage extends Message{

	public RangedMessage(Soldier target, int damage)
	{
		setText(GameData.selected.getCharacter().getName() + " shot " + target.getCharacter().getName() + " for " + damage + " HP.");
	}
}