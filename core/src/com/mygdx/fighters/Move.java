package com.mygdx.fighters;

public class Move {

	private String name;
	private int apCost;
	
	public Move(String name, int apCost)
	{
		this.name = name;
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
	
	
}
