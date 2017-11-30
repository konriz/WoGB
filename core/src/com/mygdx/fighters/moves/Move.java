package com.mygdx.fighters.moves;

import java.util.Arrays;

import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Unit;

public abstract class Move implements Perform {

	
	private String name;
	private int apCost;
	private int power;
	private int range;
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setApCost(int apCost)
	{
		this.apCost = apCost;
	}
	
	public int getApCost() {
		return apCost;
	}
	
	public void setPower(int power)
	{
		this.power = power;
	}
	
	public void setRange(int range)
	{
		this.range = range;
	}
	
	public boolean reach(int[] target)
	{
		for (int[] range : GameData.selected.range(this.getRange()))
		{
			if (Arrays.equals(range, target))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean reach(Unit target)
	{
		return reach(target.getPos());
	}
	
	public int getRange()
	{
		return this.range;
	}
	
	public int getPower()
	{
		return this.power;
	}
	
	public void tryOn(Unit target)
	{
		if (reach(target))
		{
			useOn(target);
		}
	}
	
}