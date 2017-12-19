package com.mygdx.fighters.gui.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.mygdx.fighters.gui.MainScreen;
import com.mygdx.fighters.gui.MenuScreen;

public class MyMultiplexer extends InputMultiplexer {

	public MyMultiplexer()
	{
		super();
		this.addProcessor(MenuScreen.stage);
		Gdx.input.setInputProcessor(this);
	}
	
	public void setCombat()
	{
		this.addProcessor(1, new FightProcessor());
		Gdx.input.setInputProcessor(this);
	}
	
	public void setGame()
	{
		this.addProcessor(0, MainScreen.stage);
		this.addProcessor(1, new SelectProcessor());
		Gdx.input.setInputProcessor(this);
	}
}
