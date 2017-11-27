package com.mygdx.fighters.gui.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.Team;
import com.mygdx.fighters.gui.FightersGame;

public class PointsLabel extends Label {

	
	public PointsLabel()
	{
		super("Points", FightersGame.skin);
		refresh();
	}
	
	public void refresh()
	{
		String text = new String();
		for (Team t : GameData.teams)
		{
			text += "| " + t.getName() + " - " + t.getPoints() + " |";
		}
		this.setText(text);
	}
}
