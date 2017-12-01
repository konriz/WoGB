package com.mygdx.fighters.units;

public class RaceBuilder{
	private String name = "plain";
	private Profession[] professions = {};
	private int[] stats = {0, 0, 0, 0};
	
	public RaceBuilder() {};
	
	public Race buildRace()
	{
		return new Race(name, professions, stats);
	}
	
	public RaceBuilder name(String name)
	{
		this.name = name;
		return this;
	}
	
	public RaceBuilder professions(Profession[] professions)
	{
		this.professions = professions;
		return this;
	}
	
	public RaceBuilder stats(int[] stats)
	{
		this.stats = stats;
		return this;
	}

}
