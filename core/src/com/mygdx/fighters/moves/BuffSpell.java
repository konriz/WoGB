package com.mygdx.fighters.moves;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.entities.Soldier;
import com.mygdx.fighters.gui.Messaging.messages.BuffMessage;
import com.mygdx.fighters.units.Buff;
import com.mygdx.fighters.units.Stats;

public class BuffSpell extends Move {

	private Buff buff;
	
	public BuffSpell(String name, int apCost, int power, int range, Buff buff)
	{
		super(name, apCost, power);
		this.setRange(range);
		this.buff = buff;
		String type;
		if(buff.getStrength() < 0)
		{
			type = "Curses ";
		}
		else
		{
			type = "Buffs ";
		}
		setDescription(type + Stats.names[buff.getStatIndex()] + " by " + buff.getStrength() + " points for " + buff.getDuration() + " turns.");
	}
	
	public void useOn(Soldier target) {
		
		GameData.selected.getCharacter().getStats().dropCurrentAP(getApCost());
		target.getCharacter().getStats().buff(buff);
		target.getCharacter().getStats().update();
		target.setBoosted(true);
		GameData.selected.setMoving(false);
		GameData.console.add(new BuffMessage(target, buff));
		
	}
	
	

}
