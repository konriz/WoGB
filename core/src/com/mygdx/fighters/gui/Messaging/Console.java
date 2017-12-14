package com.mygdx.fighters.gui.Messaging;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.mygdx.fighters.gui.FightersGame;

public class Console extends VerticalGroup {

	public Console()
	{
		this.align(Align.bottom);
		add("Console test");
	}
	
	public void add(String message)
	{
		addActor(new Label(message, FightersGame.skin));
	}
}
