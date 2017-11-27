package com.mygdx.fighters.gui.UI;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.fighters.gui.FightersGame;

public class ControlsPanel extends Table {

	private TextButton skipTurnButton;
	public static ControlsPanel panel;
	
	public ControlsPanel()
	{
		panel = this;
		skipTurnButton = new TextButton("Pass turn", FightersGame.skin);
		skipTurnButton.addListener(new ClickListener(){

			public void clicked(InputEvent event, float x, float y)
			{
				FightersGame.data.skipTurn();
			}
			
		});
		
		this.add(skipTurnButton).width(100).pad(5);
		
	}
	
	
}

