package com.mygdx.fighters.moves;

import com.mygdx.fighters.Dice;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Soldier;

public class Attack extends Move {
	
	public Attack(String name, int apCost, int power)
	{
		super(name, apCost, power);
		setRange(1);
		setDescription("Melee attack for " + getPower() + " times your base attack.");
	}
	
	public int getDamage()
	{
		return (GameData.selected.getCharacter().getStats().getDamage() + Dice.use(6)) * getPower();
	}
	
	public void useOn(Soldier target)
	{
		GameData.selected.getCharacter().getStats().dropCurrentAP(getApCost());
		target.setHit(true);
		target.getCharacter().getStats().hit(getDamage());
		target.checkAlive();
		GameData.selected.setMoving(false);
	}

}
