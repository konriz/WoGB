package com.mygdx.fighters.moves;

import com.mygdx.fighters.Dice;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Soldier;

public class Heal extends Move {

	public Heal(String name, int apCost, int power)
	{
		setName(name);
		setApCost(apCost);
		setPower(power);
		setRange(5);
		setDescription("Heals choosen unit " + getRange() + " apart from you, for " + getPower() + " times your base attack");
	}
	
	public void useOn(Soldier target) {
		GameData.selected.getCharacter().getStats().dropCurrentAP(getApCost());
		target.setBoosted(true);
		target.getCharacter().getStats().heal(getHeal());
		GameData.selected.setMoving(false);
	}
	
	public int getHeal()
	{
		return (GameData.selected.getCharacter().getStats().getDamage() + Dice.use(6)) * getPower();
	}
	
}
