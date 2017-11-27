package com.mygdx.fighters.gui.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.gui.FightersGame;

public class StatusLabel extends Label {
	
	private CharSequence turnCount;
	
	public StatusLabel()
	{
		super("Status", FightersGame.skin);
		refresh();
	}
	
	public void refresh()
	{
		turnCount = "Turn : " + GameData.turn + " - " + GameData.getActive().getName();
		this.setText(turnCount);
	}
}
