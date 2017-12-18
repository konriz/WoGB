package com.mygdx.fighters.moves;

import com.mygdx.fighters.Dice;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Soldier;
import com.mygdx.fighters.gui.Messaging.HealMessage;

public class Heal extends Move {

	public Heal(String name, int apCost, int power)
	{
		super(name, apCost, power);
		setRange(5);
		setDescription("Heals choosen unit " + getRange() + " apart from you, for " + getPower() + " times your base attack");
	}
	
	public void useOn(Soldier target) {
		GameData.selected.getCharacter().getStats().dropCurrentAP(getApCost());
		target.setBoosted(true);
		int heal = getHeal();
		target.getCharacter().getStats().heal(heal);
		GameData.selected.setMoving(false);
		GameData.console.add(new HealMessage(target, heal));
	}
	
	public int getHeal()
	{
		return (GameData.selected.getCharacter().getStats().getDamage() + Dice.use(6)) * getPower();
	}
	
}
