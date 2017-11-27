package com.mygdx.fighters.gui.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class SelectedMenu extends Table {

	private SelectedLabel selectedLabel;
	private ActiveUnitsTable activeUnits;
	private ControlsPanel controlsTable;
	
	public SelectedMenu()
	{
		super();
		
		selectedLabel = new SelectedLabel();
		activeUnits = new ActiveUnitsTable();
		controlsTable = new ControlsPanel();
		
		add(selectedLabel).align(Align.top);
		row();
		add(activeUnits).spaceTop(50);
		row();
		add(controlsTable).align(Align.bottom).spaceTop(50);
	}
	
	public void refresh()
	{
		selectedLabel.refresh();
		activeUnits.refresh();
//		controlsTable.refresh();
	}
}
