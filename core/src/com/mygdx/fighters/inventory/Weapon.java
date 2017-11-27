package com.mygdx.fighters.inventory;

public class Weapon extends Wearable {
	
	private int attackBase;
	private int attack;
	private int mobility;
	private int price;
	
	public Weapon(String n, Character o, int w, int v, SlotType s, Quality q, int a, int m)
	{
		super(n, o, w, v, ItemType.OFFENSE, s, q);
		setAttackBase(a);
		calcAttack();
		setMobility(m);
	}
	
	public Weapon(Character c)
	{
		this("hand", c, 0, 0, SlotType.BOTH, new Quality(), 0, 0);
	}
	
	public int getAttackBase()
	{
		return attackBase;
	}
	
	public void setAttackBase(int aB)
	{
		attackBase = aB;
	}
	
	public int getAttack()
	{
		return attack;
	}
	
	public void calcAttack()
	{
		this.attack = this.getAttackBase() + this.getQuality().getSkillMod();
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
