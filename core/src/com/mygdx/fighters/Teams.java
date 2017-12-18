package com.mygdx.fighters;

import com.badlogic.gdx.utils.Array;
import com.mygdx.fighters.units.Races;

/**
 * Aray for storing teams fighting on the screen;
 * @author konriz
 *
 */
public class Teams extends Array<Team>{
	
	/**
	 * Automatically shuffles teams - something like initiative roll
	 * @param teams - teams that are in battle
	 */
	
	public Teams(Array<Team> teams)
	{
		for (Team t : teams)
		{
			this.add(t);
		}
	}
	
	public Teams(int amount)
	{
		Array<Team> teams = getAllTeams();
		teams.shuffle();
		int i = 0;
		do 
		{
			this.add(teams.get(i));
			i++;
		}
		while (i < amount);
		
		
	}
	
	public static Array<Team> getAllTeams()
	{
		Array<Team> teams = new Array<Team>();
		teams.add(new Team(0, "Humans Empire", Races.human));
		teams.add(new Team(1, "Orks Horde", Races.ork));
		teams.add(new Team(2, "Dwarfs Division", Races.dwarf));
		teams.add(new Team(3, "Animals Kingdom", Races.animal));
		teams.add(new Team(4, "Undead Plague", Races.undead));
		return teams;
	}

	public boolean areDeployed() {
		
		int counter = 0;
		for (Team t : this)
		{
			if (t.isDeployed())
			{
				counter ++;
			}
		}
		
		if (counter == this.size)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

}
