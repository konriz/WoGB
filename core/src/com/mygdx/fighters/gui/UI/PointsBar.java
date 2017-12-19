package com.mygdx.fighters.gui.UI;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.entities.Team;

public class PointsBar extends HorizontalGroup {

	private List<PointsLabel> teams = new ArrayList<PointsLabel>();
	
	public PointsBar()
	{
		super();
		
		for (Team t : GameData.teams)
		{
			teams.add(new PointsLabel(t));
		}
		
		for (PointsLabel pointsLabel : teams) {
			addActor(pointsLabel);
		}
		refresh();
		pad(10);
	}
	
	public void refresh()
	{
		for (PointsLabel pointsLabel : teams) {
			pointsLabel.refresh();
		}
		
	}
}
