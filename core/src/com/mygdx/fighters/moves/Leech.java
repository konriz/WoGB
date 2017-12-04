package com.mygdx.fighters.moves;

import com.mygdx.fighters.Dice;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Soldier;

public class Leech extends Move {
	
	public Leech(String name, int apCost, int power)
	{
		setName(name);
		setApCost(apCost);
		setPower(power);
		setRange(1);
		setDescription("Damages unit for " + getPower() + " times your attack, and heals You with the same amount");
	}

	@Override
	public void useOn(Soldier target) {
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
		return (GameData.selected.getCharacter().getDamage() + Dice.use(6)) * getPower();
	}
}
