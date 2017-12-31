package com.mygdx.fighters.gui.players;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fighters.entities.Fraction;
import com.mygdx.fighters.entities.Team;
import com.mygdx.fighters.gui.FightersGame;

public class TeamSelectionBar extends HorizontalGroup {
	
	private String playerName;
	private Label playerLabel;
	private SelectBox<Integer> teamSide;
	private SelectBox<Fraction> teamType;
	
	public TeamSelectionBar(int playersCount)
	{
		if(FightersGame.isOnline())
		{
			playerName = FightersGame.nickname;
		}
		else
		{
			playerName = "Player";
		}
		
		playerLabel = new Label(playerName, FightersGame.skin);
		
		teamSide = new SelectBox<Integer>(FightersGame.skin);
		Array<Integer> sides = new Array<Integer>();
		
		for(int i = 1; i <= playersCount; i++)
		{
			sides.add(i);
		}
		teamSide.setItems(sides);
		
		teamType = new SelectBox<Fraction>(FightersGame.skin);
		teamType.setItems(Fraction.values());
		
		addActor(playerLabel);
		addActor(teamSide);
		addActor(teamType);
		space(10);
		
	}
	public String getName()
	{
		return playerName;
	}
	
	public int getSide()
	{
		return teamSide.getSelected();
	}
	
	public Team getTeam()
	{
		return teamType.getSelected().getTeam();
	}
	
	public Fraction getFraction()
	{
		return teamType.getSelected();
	}

}
