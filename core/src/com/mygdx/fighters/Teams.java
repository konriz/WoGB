package com.mygdx.fighters;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Array;

/**
 * Aray for storing teams fighting on the screen;
 * @author konriz
 *
 */
public class Teams extends ArrayList<Team>{
	
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
		teams.add(new Team(0, "Humans Empire", Enums.Races.HUMAN));
		teams.add(new Team(1, "Orks Horde", Enums.Races.ORK));
		teams.add(new Team(2, "Dwarfs Division", Enums.Races.DWARF));
		teams.add(new Team(3, "Animals Kingdom", Enums.Races.ANIMAL));
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
		
		if (counter == this.size())
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

}
