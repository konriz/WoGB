package com.mygdx.fighters;

import com.badlogic.gdx.utils.Array;

public class Race {
	
	public static enum Races
	{
		HUMAN,
		ANIMAL,
		DWARF,
		ORK,
		UNDEAD,
		PLAIN
	}

	private String name, path;
	private Array<Profession.Professions> professions = new Array<Profession.Professions>();
	
	private int str, end, dex, mel;
	
	public Race(Races race)
	{
		switch (race)
		{
		case DWARF:
			name = "Dwarf";
			path = "DWARF";
			professions.addAll(Profession.Professions.WARRIOR, Profession.Professions.ELITE);
			str = 10;
			end = 10;
			dex = 0;
			mel = 10;
			break;
		case HUMAN:
			name = "Human";
			path = "HUMAN";
			professions.addAll(Profession.Professions.WARRIOR, Profession.Professions.ELITE);
			str = 5;
			end = 5;
			dex = 5;
			mel = 5;
			break;
		case ANIMAL:
			name = "Animal";
			path = "ANIMAL";
			professions.addAll(Profession.Professions.WARRIOR);
			str = -5;
			end = -5;
			dex = 15;
			mel = 5;
			break;
		case ORK:
			name = "Ork";
			path = "ORK";
			professions.addAll(Profession.Professions.WARRIOR, Profession.Professions.ELITE);
			str = 15;
			end = 10;
			dex = 0;
			mel = 5;
			break;
			
		case UNDEAD:
			name = "Undead";
			path = "UNDEAD";
			professions.addAll(Profession.Professions.WARRIOR);
			str = 5;
			end = 20;
			dex = 0;
			mel = 5;
			break;
		
		case PLAIN:
			name = "Plain";
			path = "";
			str = 0;
			end = 0;
			dex = 0;
			mel = 0;
			break;
		}		
		
	}

	public String getName() {
		return name;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public Array<Profession.Professions> getProfessions()
	{
		return this.professions;
	}

	public int getStr() {
		return str;
	}

	public int getEnd() {
		return end;
	}

	public int getDex() {
		return dex;
	}

	public int getMel() {
		return mel;
	}

}
