package com.mygdx.fighters.networking.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.mygdx.fighters.entities.Team;
import com.mygdx.fighters.entities.Teams;
import com.mygdx.fighters.gui.FightersGame;

public class NetPlayersList extends Table{
	
	private Teams teams;
	
	public NetPlayersList()
	{
		teams = new Teams();
		add(new Label("Nickname", FightersGame.skin)).pad(5);
		add(new Label("Side", FightersGame.skin)).pad(5);
		add(new Label("Team", FightersGame.skin)).pad(5);
		align(Align.top);

	}
	
	public void add(Team newPlayer)
	{
		Teams temp = new Teams(teams);
		temp.add(newPlayer);
		if (temp.areUnique())
		{
			row();
			add(new Label(newPlayer.getPlayer().getName(), FightersGame.skin)).pad(5);
			add(new Label(Integer.toString(newPlayer.getPlayer().getSide()), FightersGame.skin)).pad(5);
			add(new Label(newPlayer.getName(), FightersGame.skin)).pad(5);
			teams.add(newPlayer);
		}
		else
		{
			// TODO split this, maybe exception?
			System.out.println("Team/player already choosen!");
		}
		
	}
	
	public Teams getTeams()
	{
		return teams;
	}

}
