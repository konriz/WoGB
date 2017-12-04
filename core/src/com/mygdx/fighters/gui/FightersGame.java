package com.mygdx.fighters.gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.fighters.GameData;

public class FightersGame extends Game {
	
	public static MainScreen screen;
	public static MenuScreen menu;
	
	public static Skin skin;
	public static GameData data;
	
	public static Cursor select;
	public static Cursor target;
	
	public static MyMultiplexer multiplexer;
	
	public static FightersGame game;

	@Override
	public void create() {
		
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		game = this;
		
		select = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("sprites/select.gif")), 0, 0);
		target = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("sprites/target.gif")), 16, 16);
		Gdx.graphics.setCursor(select);
		
		menu = new MenuScreen();
		screen = new MainScreen();
		
		this.setScreen(menu);
		menu.show();
				
		multiplexer = new MyMultiplexer();
		
	}
	
}

// TODO inputProcessors - change switch to table selection
// TODO ranged units
