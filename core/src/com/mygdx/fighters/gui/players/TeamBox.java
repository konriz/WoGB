package com.mygdx.fighters.gui.players;

import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fighters.entities.Player;
import com.mygdx.fighters.entities.Team;
import com.mygdx.fighters.entities.Teams;

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
			teamSelectBars.add(new TeamSelectionBar(playersAmount));
			addActor(teamSelectBars.get(i));
			i++;
		}
	}
	
	public Teams getTeams()
	{
		Teams teams = new Teams();
		for (TeamSelectionBar selectBar : teamSelectBars)
		{
			Team team = selectBar.getTeam();
			team.setPlayer(new Player(selectBar.getName(), selectBar.getSide()));
			teams.add(team);
		}
		return teams;
	}
	
}
