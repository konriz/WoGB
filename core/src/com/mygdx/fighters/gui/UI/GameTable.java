package com.mygdx.fighters.gui.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;
import com.mygdx.fighters.gui.FightersGame;

public class GameTable extends Table {

	
	
	private StatusTable statusTable;
	private SelectedMenu selectedMenu;
	private Container<StatusLabel> gameContainer;
	public static ConsoleBox consoleBox;
	
	// private Game screen
	
	public GameTable()
	{
		super();
		setFillParent(true);
		
		statusTable = new StatusTable();
		gameContainer = new Container<StatusLabel>();
		selectedMenu = new SelectedMenu();
		consoleBox = new ConsoleBox();
		add(new ScrollPane(statusTable, FightersGame.skin)).fillX().fillY().colspan(2);
		row();
		add(gameContainer).width(Value.percentWidth(.80f, this)).height(Value.percentHeight(.70f, this));
		add(new ScrollPane(selectedMenu, FightersGame.skin)).width(Value.percentWidth(.20f, this)).align(Align.top).fillY();
		row();
		add(consoleBox).fillX().height(Value.percentHeight(.20f, this)).colspan(2);
	}
	
	public void refresh()
	{
		this.statusTable.refresh();
		this.selectedMenu.refresh();
	}
	
	
}
