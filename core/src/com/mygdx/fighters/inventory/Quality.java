package com.mygdx.fighters.inventory;

public class Quality {
	
	private String name;
	private int skillMod;
	private int weightMod;
	private int valueMod;
	
	public Quality(String n, int s, int w, int v)
	{
		name = n;
		skillMod = s;
		weightMod = w;
		valueMod = v;
	}
	
	public Quality()
	{
		name = "";
	}

	public String getName() {
		return name;
	}

	public int getSkillMod() {
		return skillMod;
	}

	public int getWeightMod() {
		return weightMod;
	}

	public int getValueMod() {
		return valueMod;
	}

}
