package com.mygdx.fighters.gui.UI;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.gui.FightersGame;

public class ConsoleBox extends ScrollPane{

	public ConsoleBox() {
		super(GameData.console, FightersGame.skin);
	}
}
