package com.mygdx.fighters.gui.Messaging;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Soldier;

public class MeeleMessage extends Message{

	public MeeleMessage(Soldier target, int damage)
	{
		setText(GameData.selected.getCharacter().getName() + " hit " + target.getCharacter().getName() + " for " + damage + " HP.");
	}
}
