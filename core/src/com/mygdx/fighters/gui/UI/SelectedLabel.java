package com.mygdx.fighters.gui.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.fighters.GameData;
import com.mygdx.fighters.gui.FightersGame;

public class SelectedLabel extends Label {

	private String[] labelElements;
	private CharSequence labelText;
	
	public SelectedLabel()
	{
		super("Selected", FightersGame.skin);
		this.labelElements = new String[4];
		refresh();
	}
	
	public void refresh()
	{
		try
		{
			com.mygdx.fighters.Character selected = GameData.selected.getCharacter();
		
			labelElements[0] = selected.getName();
			
			labelElements[1] = "HP: " + selected.getCurrentHP() + "/" + selected.getMaxHP();
			
			labelElements[2] = "AP: " + selected.getCurrentAP() + "/" + selected.getMaxAP();
			
			labelElements[3] = "Attack: " + selected.getToHit() + "\nDefence: " + selected.getToRes();
			
			labelText = labelElements[0] + "\n" + labelElements[1] + "\n" + labelElements[2] + "\n" + labelElements[3] ;
			this.setText(labelText);
		}
		catch (NullPointerException e)
		{
			this.setText("No unit selected!");
		}
	}
}
