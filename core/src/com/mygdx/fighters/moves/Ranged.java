package com.mygdx.fighters.moves;

import com.mygdx.fighters.Dice;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.entities.Soldier;
import com.mygdx.fighters.messaging.messages.RangedMessage;

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
		int damage = getDamage();
		target.getCharacter().getStats().hit(damage);
		target.checkAlive();
		GameData.selected.setMoving(false);
		GameData.console.add(new RangedMessage(target, damage));
	}
	
	public int getDamage()
	{
		return (GameData.selected.getCharacter().getStats().getDamage() + Dice.use(6)) * getPower();
	}

}
