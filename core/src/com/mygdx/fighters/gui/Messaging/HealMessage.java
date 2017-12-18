package com.mygdx.fighters.gui.Messaging;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Soldier;

public class HealMessage extends Message {

	public HealMessage(Soldier target, int heal)
	{
		setText(GameData.selected.getCharacter().getName() + " healed " + target.getCharacter().getName() + " for " + heal + " HP.");
	}
}
