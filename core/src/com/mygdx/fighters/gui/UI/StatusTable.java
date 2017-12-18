package com.mygdx.fighters.gui.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class StatusTable extends Table {

	private StatusLabel status;
	private PointsBar points;
	
	
	public StatusTable()
	{
		status = new StatusLabel();
		add(status).align(Align.center);
		row();
		points = new PointsBar();
		add(points).align(Align.center);
	}
	
	public void refresh()
	{
		
		this.status.refresh();
		this.points.refresh();
	}
}
