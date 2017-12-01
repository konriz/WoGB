package com.mygdx.fighters.moves;

import com.mygdx.fighters.Dice;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.units.Unit;

public class Leech extends Move {
	
	public Leech(String name, int apCost, int power)
	{
		setName(name);
		setApCost(apCost);
		setPower(power);
		setRange(1);
	}

	@Override
	public void useOn(Unit target) {
		GameData.selected.getCharacter().dropCurrentAP(getApCost());
		target.setHit(true);
		int damage = getDamage();
		target.getCharacter().dropCurrentHP(damage);
		target.getCharacter().checkAlive();
		GameData.selected.getCharacter().buffCurrentHP(damage);
		GameData.selected.setMoving(false);
	}
	
	public int getDamage()
	{
		return (GameData.selected.getCharacter().getToHit() + Dice.use(6)) * getPower();
	}
}
