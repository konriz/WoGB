package com.mygdx.fighters;

import com.mygdx.fighters.units.Unit;
import com.mygdx.fighters.units.Character;
import com.mygdx.fighters.units.Profession;
import com.mygdx.fighters.units.Race;

public class SoldierBuilder {

	private Character character;
	private Unit unit;
	private Team team;
	
	public SoldierBuilder()
	{}
	
	public Soldier buildSoldier()
	{
		return new Soldier(character, unit, team);
	}
	
	public SoldierBuilder setCharacter(Race race, Profession profession)
	{
		this.character = new Character(race, profession);
		return this;
	}
	
	public SoldierBuilder setUnit(Unit u)
	{
		this.unit = u;
		return this;
	}
	
	public SoldierBuilder setTeam(Team team)
	{
		this.team = team;
		return this;
	}
	
}
