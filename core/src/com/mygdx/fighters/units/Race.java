package com.mygdx.fighters.units;

public class Race {
	
	private String name, path;
	private Profession[] professions;
	
	private int[] stats = new int[4];
	
	public Race(String name, Profession[] professions, int[] stats)
	{
		this.name = name;
		this.path = name.toUpperCase();
		this.professions = professions;
		this.stats = stats;
	}
	
	public Race()
	{
		this.name = "Plain";
		this.path = name.toUpperCase();
	}
	
	public String getName() {
		return name;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public Profession[] getProfessions()
	{
		return this.professions;
	}

	public int[] getStats()
	{
		return this.stats;
	}

}
