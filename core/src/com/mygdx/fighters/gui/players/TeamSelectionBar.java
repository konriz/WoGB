package com.mygdx.fighters.gui.players;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fighters.entities.Team;
import com.mygdx.fighters.entities.Teams;
import com.mygdx.fighters.gui.FightersGame;

public class TeamSelectionBar extends HorizontalGroup {
	
	private Label playerLabel;
	private SelectBox<Integer> teamSide;
	private SelectBox<Team> teamType;
	
	public TeamSelectionBar(int playerNo, int playersCount)
	{
		this.playerLabel = new Label("Player " + playerNo + ":", FightersGame.skin);
		
		teamSide = new SelectBox<Integer>(FightersGame.skin);
		Array<Integer> sides = new Array<Integer>();
		
		for(int i = 1; i <= playersCount; i++)
		{
			sides.add(i);
		}
		teamSide.setItems(sides);
		
		teamType = new SelectBox<Team>(FightersGame.skin);
		teamType.setItems(Teams.getAllTeams());
		
		addActor(playerLabel);
		addActor(teamSide);
		addActor(teamType);
		space(10);
		
	}
	
	public int getSide()
	{
		return teamSide.getSelected();
	}
	
	public Team getTeam()
	{
		return teamType.getSelected();
	}

}
