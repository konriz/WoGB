package com.mygdx.fighters.moves;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Soldier;
import com.mygdx.fighters.units.Buff;

public class BuffSpell extends Move {

	private Buff buff;
	
	public BuffSpell(String name, int apCost, int power, int range, Buff buff)
	{
		super(name, apCost, power);
		this.setRange(range);
		this.buff = buff;
	}
	
	public void useOn(Soldier target) {
		
		GameData.selected.getCharacter().getStats().dropCurrentAP(getApCost());
		target.getCharacter().getStats().buff(buff);
		target.getCharacter().getStats().update();
		target.setBoosted(true);
		GameData.selected.setMoving(false);
		
	}
	
	

}
