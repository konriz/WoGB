package com.mygdx.fighters.units;

public class Buff {

	private int duration;
	private int strength;
	private int statIndex;
	private boolean active = true;
	
	public Buff(int duration, int strength, int statIndex)
	{
		this.duration = duration;
		this.strength = strength;
		this.statIndex = statIndex;
	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getStatIndex() {
		return statIndex;
	}
	public void setStatIndex(int statIndex) {
		this.statIndex = statIndex;
	}
	public boolean isActive()
	{
		return active;
	}
	
	public void expire()
	{
		if (duration > 1)
		{
			duration --;
		}
		else
		{
			active = false;
		}
	}
	
	//TODO make this more universal - change weaker buffs to stronger, don't add many buffs of one type
	public boolean overlap(Buff test)
	{
		if(test.getStatIndex() == statIndex)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
