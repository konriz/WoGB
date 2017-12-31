package com.mygdx.fighters.moves;

import com.mygdx.fighters.Dice;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.entities.Soldier;
import com.mygdx.fighters.messaging.messages.LeechMessage;

public class Leech extends Move {
	
	public Leech(String name, int apCost, int power)
	{
		super(name, apCost, power);
		setRange(1);
		setDescription("Damages unit for " + getPower() + " times your attack, and heals You with the same amount");
	}

	@Override
	public void useOn(Soldier target) {
		GameData.selected.getCharacter().getStats().dropCurrentAP(getApCost());
		target.setHit(true);
		int damage = getDamage();
		target.getCharacter().getStats().hit(damage);
		target.checkAlive();
		GameData.selected.getCharacter().getStats().heal(damage);
		GameData.selected.setMoving(false);
		GameData.console.add(new LeechMessage(target, damage));
	}
	
	public int getDamage()
	{
		return (GameData.selected.getCharacter().getStats().getDamage() + Dice.use(6)) * getPower();
	}
}
