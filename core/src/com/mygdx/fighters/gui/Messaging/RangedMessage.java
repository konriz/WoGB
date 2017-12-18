package com.mygdx.fighters.gui.Messaging;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Soldier;

public class RangedMessage extends Message{

	public RangedMessage(Soldier target, int damage)
	{
		setText(GameData.selected.getCharacter().getName() + " shot " + target.getCharacter().getName() + " for " + damage + " HP.");
	}
}
