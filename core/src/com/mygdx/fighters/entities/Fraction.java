package com.mygdx.fighters.entities;

import com.mygdx.fighters.units.Races;

public enum Fraction {

	HUMANS,
	ORKS,
	DWARFS,
	ANIMALS,
	UNDEAD;
	
	private Team team;
	
	static {
		HUMANS.team = new Team("Humans Empire", Races.human);
		ORKS.team = new Team("Orks Horde", Races.ork);
		DWARFS.team = new Team("Dwarfs Division", Races.dwarf);
		ANIMALS.team = new Team("Animals Kingdom", Races.animal);
		UNDEAD.team = new Team("Undead Plague", Races.undead);
	}
	
	public Team getTeam()
	{
		return team;
	}
	
}
