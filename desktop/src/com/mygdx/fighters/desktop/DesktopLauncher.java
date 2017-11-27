package com.mygdx.fighters.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.fighters.gui.FightersGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 800;
		new LwjglApplication(new FightersGame(), config);
	}
}
