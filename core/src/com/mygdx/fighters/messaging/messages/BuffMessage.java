package com.mygdx.fighters.messaging.messages;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.entities.Soldier;
import com.mygdx.fighters.messaging.Message;
import com.mygdx.fighters.units.Buff;

public class BuffMessage extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 41L;
	private String type;
	String[] stats = {" strength ", " endurance ", " dexterity ", " fighting skills "};
	
	public BuffMessage(Soldier target, Buff buff)
	{
		if(buff.getStrength() < 0)
		{
			type = " cursed ";
		}
		else
		{
			type = " buffed ";
		}
		
		setText(GameData.selected.getCharacter().getName() + type + target.getCharacter().getName() + stats[buff.getStatIndex()] 
				+ " for " + buff.getStrength() + " points. Duration: " + buff.getDuration() + " turns.");
	}
}
