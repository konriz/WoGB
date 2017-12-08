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
		this.labelElements = new String[5];
		refresh();
	}
	
	public void refresh()
	{
		try
		{
			com.mygdx.fighters.units.Character selected = GameData.selected.getCharacter();
		
			labelElements[0] = selected.getName();
			
			labelElements[1] = "HP: " + selected.getStats().getCurrentHP() + "/" + selected.getStats().getMaxHP();
			
			labelElements[2] = "AP: " + selected.getStats().getCurrentAP() + "/" + selected.getStats().getMaxAP();
			
			labelElements[3] = "Base damage: " + (selected.getStats().getDamage() + 1) + "-" + (selected.getStats().getDamage() + 6);
			
			labelElements[4] = "Defence: " + selected.getStats().getResistance();
			
			labelText = labelElements[0] + "\n" + labelElements[1] + "\n" + labelElements[2] + "\n" + labelElements[3] + "\n" + labelElements[4] ;
			this.setText(labelText);
		}
		catch (NullPointerException e)
		{
			this.setText("No unit selected!");
		}
	}
}
