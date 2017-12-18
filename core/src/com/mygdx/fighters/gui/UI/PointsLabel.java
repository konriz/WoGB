package com.mygdx.fighters.gui.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.fighters.Team;
import com.mygdx.fighters.gui.FightersGame;

public class PointsLabel extends Container<Label> {

	private Team team;
	
	public PointsLabel(Team team)
	{
		super(new Label("Points", FightersGame.skin));
		this.team = team;
		padLeft(15);
		refresh();
	}
	
	public void refresh()
	{
		getActor().setText(team.getName() + " - " + team.getPoints());
	}
}
