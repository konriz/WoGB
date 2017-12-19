package com.mygdx.fighters;

import com.mygdx.fighters.entities.Flag;
import com.mygdx.fighters.entities.Placeable;

public class Grid extends Placeable{

	private Placeable occupation;
	private Flag master;
	
	public Grid(int xPos, int yPos)
	{
		super(xPos, yPos);
		occupation = null;
		master = null;
		
	}

	public Placeable getOccupation() {
		return occupation;
	}

	public void setOccupation(Placeable object) {
		this.occupation = object;
	}
	
	public void clearOccupation() 
	{
		this.occupation = null;
	}
	
	public void setMaster(Flag f)
	{
		this.master = f;
	}
	
	public Flag getMaster()
	{
		return this.master;
	}
	
}
