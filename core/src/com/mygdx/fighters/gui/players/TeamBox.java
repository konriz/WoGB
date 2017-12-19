package com.mygdx.fighters.gui.players;

import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fighters.entities.Team;

public class TeamBox extends VerticalGroup {

	private Array<TeamSelectionBar> teamSelectBars;
	
	public TeamBox(int playersNo)
	{
		createTable(playersNo);
		space(5);
	}
	
	public void createTable(Integer playersAmount)
	{
		clearChildren();
		teamSelectBars = new Array<>(playersAmount);
		
		int i = 0;
		while (i < playersAmount)
		{
			teamSelectBars.add(new TeamSelectionBar(i, playersAmount));
			addActor(teamSelectBars.get(i));
			i++;
		}
	}
	
	public Array<Team> getTeams()
	{
		Array<Team> teams = new Array<Team>();
		for (TeamSelectionBar selectBar : teamSelectBars)
		{
			Team team = selectBar.getTeam();
			team.setSide(selectBar.getSide());
			teams.add(team);
		}
		return teams;
	}
	
	public boolean areUnique()
	{
		Array<String> teamsNames = new Array<String>();
		for (Team team : getTeams())
		{
			if (teamsNames.contains(team.getName(), false))
			{
				return false;
			}
			teamsNames.add(team.getName());
		}
		return true;
	}
	
}
