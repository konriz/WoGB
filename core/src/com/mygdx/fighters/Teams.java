package com.mygdx.fighters;

import com.badlogic.gdx.utils.Array;

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
		Array<Team> teams = new Array<Team>();
		teams.add(new Team(0, "Humans Empire", Race.Races.HUMAN));
		teams.add(new Team(1, "Orks Horde", Race.Races.ORK));
		teams.add(new Team(2, "Dwarfs Division", Race.Races.DWARF));
		teams.add(new Team(3, "Animals Kingdom", Race.Races.ANIMAL));
		teams.add(new Team(4, "Undead Plague", Race.Races.UNDEAD));
		teams.shuffle();
		int i = 0;
		do 
		{
			this.add(teams.get(i));
			i++;
		}
		while (i < amount);
		
		
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
