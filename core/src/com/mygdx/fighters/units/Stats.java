package com.mygdx.fighters.units;

import java.util.ArrayList;
import java.util.List;

public class Stats {

	
	private int[] maxStats = new int[4];
	private int[] currentStats = new int[4];
	
	private int maxHP, currentHP, maxAP, currentAP, damage, resistance;
	
	private List<Buff> buffs = new ArrayList<Buff>();
	
	public int[] getMaxStats()
	{
		return maxStats;
	}
	
	public Stats(Race race, Profession profession)
	{
		for(int i=0; i<4; i++)
		{
			maxStats[i] = race.getStats()[i] + profession.getStats()[i] + 10;
		}
		
		maxHP = 30 + maxStats[1];
		currentHP = maxHP;
		update();
	}
	
	public void update()
	{
		for(int i=0; i<4; i++)
		{
			currentStats[i] = maxStats[i];
		}
		
		for(Buff buff : buffs)
		{
			currentStats[buff.getStatIndex()] += buff.getStrength();
		}
		
		maxAP = currentStats[2] / 5;
		currentAP = maxAP;
		damage = currentStats[0] / 5 + currentStats[3] / 5;
		resistance = currentStats[1] / 5;
	}

	public int[] getCurrentStats() {
		return currentStats;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public int getCurrentHP() {
		return currentHP;
	}
	
	public int getHpPercent()
	{
		return currentHP * 100 / maxHP;
	}
	
	public void dropHP(int hp)
	{
		currentHP -= hp;
	}
	
	public void hit(int hp)
	{
		dropHP(hp - resistance);
	}
	
	public void heal(int heal)
	{
		if (getCurrentHP() + heal > getMaxHP())
		{
			resetHP();
		}
		else
		{
			currentHP += heal;
		}
	}
	
	public void resetHP()
	{
		currentHP = maxHP;
	}

	public int getMaxAP() {
		return maxAP;
	}

	public int getCurrentAP() {
		return currentAP;
	}
	
	public int getApPercent()
	{
	return currentAP * 100 / maxAP;
	}
	
	public void dropCurrentAP(int ap)
	{
		currentAP -= ap;
	}
	
	public void resetAP()
	{
		currentAP = maxAP;
	}

	public int getDamage() {
		return damage;
	}

	public int getResistance() {
		return resistance;
	}
	
	public void buff(Buff buff)
	{
		for (Buff pendingBuff : buffs)
		{
			if (pendingBuff.overlap(buff))
			{
				pendingBuff.setDuration(buff.getDuration());
				return;
			}
		}
		buffs.add(buff);
	}
	
	public void debuff()
	{
		buffs.stream().forEach(p->p.expire());
		buffs.stream().filter(p->!p.isActive()).forEach(p->buffs.remove(p));
	}
	
	public void rest()
	{
		debuff();
		resetAP();
	}
	
}
