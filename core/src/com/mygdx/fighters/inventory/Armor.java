package com.mygdx.fighters.inventory;

public class Armor extends Wearable {
	
	private int defenseBase;
	private int defense;
	private int mobility;
	private int price;
	
	
	
	public Armor(String n, Character o, int w, int v, SlotType s, Quality q, int d, int m)
	{
		super(n, o, w, v, ItemType.DEFENSE, s, q);
		setDefenseBase(d);
		calcDefense();
		setMobility(m);
	}
	
	public Armor(Character c)
	{
		this("skin", c, 0, 0, SlotType.ALL, new Quality(), 0, 0);
	}
	
	public int getDefenseBase()
	{
		return defenseBase;
	}
	
	public void setDefenseBase(int aB)
	{
		defenseBase = aB;
	}
	
	public int getDefense()
	{
		return defense;
	}
	
	public void calcDefense()
	{
		this.defense = this.getDefenseBase() + this.getQuality().getSkillMod();
	}
	
	public int getMobility() {
		return mobility;
	}

	public void setMobility(int mobility) {
		this.mobility = mobility;
	}
	
	public int getPrice()
	{
		return this.price;
	}
	
	public void setPrice(int price)
	{
		this.price = price;
	}
}
