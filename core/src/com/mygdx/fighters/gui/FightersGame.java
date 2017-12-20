package com.mygdx.fighters.gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.gui.input.MyMultiplexer;

public class FightersGame extends Game {
	
	public static MainScreen screen;
	public static MenuScreen menu;
	
	public static Skin skin;
	public static GameData data;
	
	public static Cursor select;
	public static Cursor noTarget, okTarget, target;
	private static boolean cursorState = false;
	
	public static MyMultiplexer multiplexer;
	
	public static FightersGame game;
	
	private static boolean online;
	private static boolean host;
	
	@Override
	public void create() {
		
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		game = this;
		
		select = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("sprites/select.gif")), 0, 0);
		noTarget = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("sprites/noTarget.gif")), 16, 16);
		okTarget = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("sprites/okTarget.gif")), 16, 16);
		setTargetCursor(cursorState);
		
		Gdx.graphics.setCursor(select);
		
		menu = new MenuScreen();
		screen = new MainScreen();
		
		this.setScreen(menu);
		menu.show();
				
		multiplexer = new MyMultiplexer();
		
	}
	
	public static void setOnline(boolean online, boolean isHost)
	{
		FightersGame.online = online;
		FightersGame.host = isHost;
	}
	
	public static boolean isOnline()
	{
		return online;
	}
	
	public static boolean isHost()
	{
		return host;
	}
	
	public static void setTargetCursor(boolean state)
	{
		if (state == true)
		{
			target = okTarget;
		}
		else
		{
			target = noTarget;
		}
		FightersGame.cursorState = state;
	}
	
}
