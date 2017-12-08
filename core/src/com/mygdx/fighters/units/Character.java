package com.mygdx.fighters.units;

import com.mygdx.fighters.moves.Move;

/**
 * Character class for Soldier backend
 * @author konriz
 *
 */

public class Character {
	
	private boolean alive;
	private Race race;
	private Profession profession;
	private Move[] moves;
	
	private String name;
	private Stats stats;
	
	private String path;
	
	private int points = 0;

	// TODO make inventory useful!
	
	/**
	 * Creates character with given parameters
	 * @param n - name of character
	 * @param r - race of character
	 * @param p - profession of character
	 */
	public Character(Race r, Profession p)
	{

		alive = true;
		race = r;
		profession = p;
		name = race.getName() + " " + profession.getName();
		
		moves = profession.getMoves();
		// inventory = new Inventory(this, 15);
		
		stats = new Stats(race, profession);
		
		path = race.getPath() + "/" + profession.getPath();
		
		for(int stat : stats.getMaxStats())
		{
			points += stat;
		}
	}	
	
	/**
	 * Creates character with determined race but plain profession
	 * @param name - character name
	 * @param r - Enum defining race
	 */

	public String getName()
	{
		return name;
	}
	
	public Stats getStats()
	{
		return stats;
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public void setAlive(boolean state)
	{
		alive = state;
	}
	
	public Race getRace()
	{
		return race;
	}
	
	public Profession getProfession()
	{
		return profession;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public int getPoints()
	{
		return this.points;
	}
	
	public Move[] getMoves()
	{
		return moves;
	}
	
}
