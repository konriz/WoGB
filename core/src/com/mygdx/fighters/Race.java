package com.mygdx.fighters;

import com.badlogic.gdx.utils.Array;
import com.mygdx.fighters.Enums.Professions;

public class Race {
	

	
	private String name, path;
	private Array<Enums.Professions> professions = new Array<Enums.Professions>();
	
	private int str, end, dex, mel;
	
	public Race(Enums.Races race)
	{
		switch (race)
		{
		case DWARF:
			name = "Dwarf";
			path = "DWARF";
			professions.addAll(Professions.WARRIOR, Professions.ELITE);
			str = 10;
			end = 10;
			dex = 0;
			mel = 10;
			break;
		case HUMAN:
			name = "Human";
			path = "HUMAN";
			professions.addAll(Professions.WARRIOR, Professions.ELITE);
			str = 5;
			end = 5;
			dex = 5;
			mel = 5;
			break;
		case ANIMAL:
			name = "Animal";
			path = "ANIMAL";
			professions.addAll(Professions.WARRIOR);
			str = -5;
			end = -5;
			dex = 15;
			mel = 5;
			break;
		case ORK:
			name = "Ork";
			path = "ORK";
			professions.addAll(Professions.WARRIOR, Professions.ELITE);
			str = 15;
			end = 10;
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
	
	public Array<Enums.Professions> getProfessions()
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
