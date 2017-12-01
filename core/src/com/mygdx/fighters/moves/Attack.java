package com.mygdx.fighters.moves;

import com.mygdx.fighters.Dice;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.units.Unit;

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
		return (GameData.selected.getCharacter().getToHit() + Dice.use(6)) * getPower();
	}
	
	public void useOn(Unit target)
	{
		GameData.selected.getCharacter().dropCurrentAP(getApCost());
		target.setHit(true);
		target.getCharacter().dropCurrentHP(getDamage());
		target.getCharacter().checkAlive();
		GameData.selected.setMoving(false);
	}

}
