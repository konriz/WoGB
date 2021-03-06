package com.mygdx.fighters.gui;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.gui.input.MyMultiplexer;
import com.mygdx.fighters.networking.AbstractConnector;
import com.mygdx.fighters.networking.Client;
import com.mygdx.fighters.networking.Host;

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
	
	private static boolean isOnline;
	private static boolean isHost;
	public static String nickname;
	
	public static AbstractConnector connection;
	
	@Override
	public void create() {
		
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		game = this;
		
		// cursor graphics block
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
		FightersGame.isOnline = online;
		FightersGame.isHost = isHost;
	}
	
	public static void host()
	{
		connection = new Host();
	}
	
	public static void guest(String hostAddress) throws IOException
	{
		connection = new Client(hostAddress);
	}
	
	public static boolean isOnline()
	{
		return isOnline;
	}
	
	public static boolean isHost()
	{
		return isHost;
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
