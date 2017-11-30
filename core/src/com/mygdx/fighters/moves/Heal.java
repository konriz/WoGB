package com.mygdx.fighters.moves;

import com.mygdx.fighters.Dice;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Unit;

public class Heal extends Move {

	public Heal(String name, int apCost, int power)
	{
		setName(name);
		setApCost(apCost);
		setPower(power);
		setRange(5);
	}
	
	@Override
	public void useOn(Unit target) {
		GameData.selected.getCharacter().dropCurrentAP(getApCost());
		target.setBoosted(true);
		target.getCharacter().buffCurrentHP(getHeal());
		GameData.selected.setMoving(false);
	}
	
	public int getHeal()
	{
		return (GameData.selected.getCharacter().getToHit() + Dice.use(6)) * getPower();
	}
	
}
