package com.mygdx.fighters.gui.Messaging;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Soldier;

public class LeechMessage extends Message{

	public LeechMessage(Soldier target, int damage)
	{
		setText(GameData.selected.getCharacter().getName() + " leached " + damage + " HP from " + target.getCharacter().getName());
	}
}
