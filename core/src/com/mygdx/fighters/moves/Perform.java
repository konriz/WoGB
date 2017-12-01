package com.mygdx.fighters.moves;

import com.mygdx.fighters.Soldier;

public interface Perform {

	public void setName(String name);
	public String getName();
	
	public void setApCost(int apCost);
	public int getApCost();
	
	public void setPower(int power);
	public int getPower();
	
	public void setRange(int range);
	public int getRange();
	
	public void useOn(Soldier target);
	public void tryOn(Soldier target);
	
}
