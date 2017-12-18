package com.mygdx.fighters.gui;

import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fighters.Team;
import com.mygdx.fighters.Teams;

public class TeamBox extends VerticalGroup {

	private Array<SelectBox<Team>> teamSelectBoxes;
	
	public TeamBox(int playersNo)
	{
		createTable(playersNo);
	}
	
	public void createTable(Integer playersNo)
	{
		clearChildren();
		Array<Team> teams = Teams.getAllTeams();
		teamSelectBoxes = new Array<>(playersNo);
		
		int i = 0;
		while (i < playersNo)
		{
			teamSelectBoxes.add(new SelectBox<Team>(FightersGame.skin));
			teamSelectBoxes.get(i).setItems(teams);
			addActor(teamSelectBoxes.get(i));
			i++;
		}
	}
	
	public Array<Team> getTeams()
	{
		Array<Team> teams = new Array<Team>();
		for (SelectBox<Team> selectBox : teamSelectBoxes)
		{
			teams.add(selectBox.getSelected());
		}
		return teams;
	}
	
}
