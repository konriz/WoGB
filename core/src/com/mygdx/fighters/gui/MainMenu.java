package com.mygdx.fighters.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

public class MainMenu extends Dialog {
	
	public MainMenu()
	{
		super("Main Menu", FightersGame.skin);
		this.text("Game paused - choose option");
		this.button("Resume", false);
		this.button("Exit", true);
			
	}
	
	@Override
	protected void result(Object result)
	{
		if (result.equals(true))
		{
			System.exit(0);
		}
		else if (result.equals(false))
		{
			this.hide();
		}
	}
	

}
