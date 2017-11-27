package com.mygdx.fighters;

public class Move {

	public static enum Type
	{
		ATTACK,
		RANGED,
		HEAL,
		LEECH
	}
	
	private Type type;
	private String name;
	private int apCost;
	
	public Move(String name, Type type, int apCost)
	{
		this.type = type;
		this.name = name + " " + type;
		this.apCost = apCost;
	}
	
	public Move()
	{
		this.name = "normal";
		this.apCost = 1;
	}

	public String getName() {
		return name;
	}

	public int getApCost() {
		return apCost;
	}
	
	public int getPower()
	{
		return this.apCost;
	}
	
	public Type getType()
	{
		return this.type;
	}
	
}
