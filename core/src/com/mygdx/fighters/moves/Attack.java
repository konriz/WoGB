package com.mygdx.fighters.moves;

import com.mygdx.fighters.Dice;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Soldier;

public class Attack extends Move {
	
	public Attack(String name, int apCost, int power)
	{
		setName(name);
		setApCost(apCost);
		setPower(power);
		setRange(1);
	}
	
	public int getDamage()
	{
		return (GameData.selected.getCharacter().getDamage() + Dice.use(6) + getPower()) * getPower();
	}
	
	public void useOn(Soldier target)
	{
		GameData.selected.getCharacter().dropCurrentAP(getApCost());
		target.setHit(true);
		target.getCharacter().dropCurrentHP(getDamage());
		target.getCharacter().checkAlive();
		GameData.selected.setMoving(false);
	}

}
