package com.mygdx.fighters.entities;

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
	
	public Teams()
	{
		
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
		for(Fraction team : Fraction.values())
		{
			teams.add(team.getTeam());
		}
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
	
	public boolean areUnique()
	{
		Array<String> teamsNames = new Array<String>();
		Array<String> playersNames = new Array<String>();
		for (Team team : this)
		{
			if (teamsNames.contains(team.getName(), false) || playersNames.contains(team.getPlayer().getName(), false))
			{
				return false;
			}
			teamsNames.add(team.getName());
			playersNames.add(team.getPlayer().getName());
		}
		return true;
	}

}
