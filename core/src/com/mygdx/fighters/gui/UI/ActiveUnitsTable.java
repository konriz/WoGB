package com.mygdx.fighters.gui.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.gui.FightersGame;
import com.mygdx.fighters.units.Unit;

public class ActiveUnitsTable extends Table {

	private Table units;
	private Skin skin;
	
	public ActiveUnitsTable()
	{
		units = new Table();
		this.skin = FightersGame.skin;
		
		add(new Label("Available units: ", this.skin));
		row();

		add(units);
		units.setWidth(100);
	}
	
	public void refresh()
	{
		units.clear();
		units.add(new Label("Unit:", skin)).width(Value.percentWidth(.30f, this));
		units.add(new Label("HP%:", skin)).width(Value.percentWidth(.35f, this));
		units.add(new Label("AP%:", skin)).width(Value.percentWidth(.35f, this));
		units.row();
		for (Unit u : GameData.getActive().getAll())
		{
			if (u.equals(GameData.selected))
			{
				units.add(new Image(u.getTableSelected()));
			}
			else
			{
				units.add(new Image(u.getDown()));
			}
			
			units.add(new Label("" + u.getHpPercent(), this.skin)).width(Value.percentWidth(.35f, this));
			units.add(new Label("" + u.getApPercent(), this.skin)).width(Value.percentWidth(.35f, this));
			units.row();
		}
	}
}
