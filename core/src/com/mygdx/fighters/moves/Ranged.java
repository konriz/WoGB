package com.mygdx.fighters.moves;

import com.mygdx.fighters.Dice;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Soldier;

public class Ranged extends Move {

	public Ranged(String name, int apCost, int power, int range)
	{
		super(name, apCost, power);
		setRange(range);
		setDescription("Ranged attack for " + getPower() + " times your base attack. Range : " + this.getRange() + " tiles.");
	}
	
	public void useOn(Soldier target) {
		GameData.selected.getCharacter().getStats().dropCurrentAP(getApCost());
		target.setHit(true);
		target.getCharacter().getStats().hit(getDamage());
		target.checkAlive();
		GameData.selected.setMoving(false);
	}
	
	public int getDamage()
	{
		return (GameData.selected.getCharacter().getStats().getDamage() + Dice.use(6)) * getPower();
	}

}
