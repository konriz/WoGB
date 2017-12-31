package com.mygdx.fighters.entities;

public class Player {

	private String name;
	private int side;
	
	public Player(String name, int side)
	{
		this.name = name;
		this.side = side;
	}
	
	public Player()
	{
		this("NoName", 0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

}
