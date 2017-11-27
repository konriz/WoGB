package com.mygdx.fighters.gui.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;

public class GameTable extends Table {

	
	
	private StatusTable statusTable;
	private SelectedMenu selectedMenu;
	private Container<StatusLabel> gameContainer;
	
	// private Game screen
	
	public GameTable()
	{
		super();
		setFillParent(true);
		setDebug(true);
		
		statusTable = new StatusTable();
		gameContainer = new Container<StatusLabel>();
		selectedMenu = new SelectedMenu();
		
		
		add(statusTable).expandX().expandY().colspan(2);
		row();
		add(gameContainer).width(Value.percentWidth(.80f, this)).height(Value.percentHeight(.90f, this));
		add(selectedMenu).width(Value.percentWidth(.20f, this)).align(Align.top);
		
	}
	
	public void refresh()
	{
		this.statusTable.refresh();
		this.selectedMenu.refresh();
	}
	
	
}
