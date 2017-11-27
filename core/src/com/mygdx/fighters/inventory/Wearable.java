package com.mygdx.fighters.inventory;

public abstract class Wearable extends Item {

	
	enum SlotType {HEAD, BODY, LEGS, ALL, MAIN, BOTH, OFF}
	private SlotType slot;
	private Quality quality;
	private boolean equiped;
	
	public Wearable(String n, Character o, int w, int v, ItemType iT, SlotType s, Quality q)
	{
		super(n ,o, w, v, iT);
		equiped = false;
		setSlot(s);
		setQuality(q);
		setName(nameFromQuality());
	}
	
	public Wearable(Character o)
	{
		this("cloth", o, 0, 0, ItemType.NONE, SlotType.ALL, new Quality());
	}
	
	public boolean isEquiped()
	{
		return equiped;
	}
	
	public void setEquiped(boolean state)
	{
		equiped = state;
	}
	
	public void setSlot(SlotType sl)
	{
		slot = sl;
	}
	
	public SlotType getSlot()
	{
		return slot;
	}
	
	public Quality getQuality()
	{
		return quality;
	}
	
	public void setQuality(Quality q)
	{
		quality = q;
	}
	
	public String nameFromQuality()
	{
		String n = this.getName();
		String q = this.getQuality().getName();
		return q + " " + n;
	}
	
}
